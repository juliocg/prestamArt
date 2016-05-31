package controller;

import java.util.List;

import model.Objeto;
import model.SolicitudPrestamoObjeto;
import model.Usuario;
import service.SolicitudPrestamoObjetoService;
import service.UsuarioService;
import service.ObjetoService;
import validator.SolicitudPrestamoObjetoValidator;
import utilities.CorreoElectronicoSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/solicitudPrestamoObjeto")
public class SolicitudPrestamoObjetoController {
	
	@Autowired
	private SolicitudPrestamoObjetoService solicitudPrestamoObjetoService;
	@Autowired
	private ObjetoService objetoService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private SolicitudPrestamoObjetoValidator solicitudPrestamoObjetoValidator;
	@Autowired
	private CorreoElectronicoSender correoElectronicoSender;

	@RequestMapping(value = "/adminConsumidor", method = RequestMethod.GET)
    public String mostrarAdminConsumidor(ModelMap map) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String name = authentication.getName();
	    System.out.println(name);
		
	    Usuario consumidor = usuarioService.getUsuarioByCorreoElectronicoAndActivo(name, true);
	    
		List<SolicitudPrestamoObjeto> solicitudesPrestamoObjeto = solicitudPrestamoObjetoService.getSolicitudesPrestamoObjetoByConsumidor(consumidor);

        map.put("solicitudesPrestamoObjeto", solicitudesPrestamoObjeto);
			
	    return "solicitudPrestamoObjeto/adminConsumidor";
	}
	
	@RequestMapping(value = "/consulta/{solicitudPrestamoObjetoId}", method = RequestMethod.GET)
    public ModelAndView mostrarConsulta(
    		@PathVariable String solicitudPrestamoObjetoId/*, 
            ModelMap map*/) {
    		
		Integer id = stringToId(solicitudPrestamoObjetoId);
		SolicitudPrestamoObjeto solicitudPrestamoObjeto = solicitudPrestamoObjetoService.getSolicitudPrestamoObjetoById(id);
		if (solicitudPrestamoObjeto == null) {
			return new ModelAndView("redirect:/");
		}
		else {
			ModelMap map = new ModelMap();
			map.put("solicitudPrestamoObjeto", solicitudPrestamoObjeto);
			
	        return new ModelAndView("solicitudPrestamoObjeto/consulta", map);
		}
    }
	
	@RequestMapping(value = "/realizarAlta", method = RequestMethod.POST)
    public ModelAndView realizarAlta(
    		@ModelAttribute("solicitudPrestamoObjeto") SolicitudPrestamoObjeto solicitudPrestamoObjetoForm, 
            BindingResult result,
            @RequestParam(value = "objetoId", required = false) String objetoId) {
		
		SolicitudPrestamoObjeto solicitudPrestamoObjeto = solicitudPrestamoObjetoForm;
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String name = authentication.getName();
	    
	    Usuario consumidor = usuarioService.getUsuarioByCorreoElectronicoAndActivo(name, true);
		solicitudPrestamoObjeto.setConsumidor(consumidor);
		
		solicitudPrestamoObjetoValidator.validate(solicitudPrestamoObjeto, result);
		if (!result.hasErrors()) {
			solicitudPrestamoObjetoService.addSolicitudPrestamoObjeto(solicitudPrestamoObjeto);
			
			Objeto objeto = objetoService.getObjetoById(solicitudPrestamoObjeto.getObjeto().getObjetoId());
			
			CorreoElectronicoSender ce = (CorreoElectronicoSender) correoElectronicoSender;
			ce.sendMail("administracion@prestamart.com",
					objeto.getPrestador().getCorreoElectronico(),
	    		    "Solicitud de prestamo de objeto", 
	    		    "Un consumidor ha solicitado el prestamo del objeto: " + objeto.getNombreObjeto() + "\n\n" +
	    		    "Mensaje de la solicitud de prestamo del objeto: " + solicitudPrestamoObjeto.getMensajeSolicitudPrestamo() + "\n\n" +
	    		    "Saludos");
			
			return new ModelAndView("redirect:/solicitudPrestamoObjeto/adminConsumidor");
			//return new ModelAndView("redirect:/consultaObjeto/"+solicitudPrestamoObjeto.getObjeto().getObjetoId());
			//return new ModelAndView("redirect:/solicitudPrestamoObjeto/consulta/"+solicitudPrestamoObjeto.getSolicitudPrestamoObjetoId());
		}
        else {
        	ModelMap map = new ModelMap();
			map.put("solicitudPrestamoObjeto", solicitudPrestamoObjeto);
			
			map.put("solicitudPrestamoObjetoAltaDialog", true);
        	
        	return new ModelAndView("consultaObjeto/"+solicitudPrestamoObjeto.getObjeto().getObjetoId(), map);
		}
	}
	
	
	
	@RequestMapping(value = "/aceptar", method = RequestMethod.POST)
	public ModelAndView aceptar(
			@ModelAttribute("solicitudPrestamoObjeto") SolicitudPrestamoObjeto solicitudPrestamoObjetoForm, 
            BindingResult result) {
		
		SolicitudPrestamoObjeto solicitudPrestamoObjeto = solicitudPrestamoObjetoService.getSolicitudPrestamoObjetoById(solicitudPrestamoObjetoForm.getSolicitudPrestamoObjetoId());
		if (solicitudPrestamoObjeto != null) {
			solicitudPrestamoObjeto.setMensajeRespuesta(solicitudPrestamoObjetoForm.getMensajeRespuesta());
			solicitudPrestamoObjeto.setSolicitudAceptada(true);
			
			solicitudPrestamoObjetoValidator.validate(solicitudPrestamoObjeto, result);
			if (!result.hasErrors()) {
				solicitudPrestamoObjetoService.updateSolicitudPrestamoObjeto(solicitudPrestamoObjeto);
				
				CorreoElectronicoSender ce = (CorreoElectronicoSender) correoElectronicoSender;
				ce.sendMail("administracion@prestamart.com",
						solicitudPrestamoObjeto.getConsumidor().getCorreoElectronico(),
		    		    "Solicitud de prestamo de objeto aceptada", 
		    		    "El prestador ha aceptado la solicitud del prestamo del objeto: " + solicitudPrestamoObjeto.getObjeto().getNombreObjeto() + "\n\n" +
		    		    "Respuesta de la solicitud de prestamo del objeto: " + solicitudPrestamoObjeto.getMensajeRespuesta() + "\n\n" +
		    		    "Saludos");
				
				//return new ModelAndView("redirect:/objeto/adminPrestador");
				return new ModelAndView("redirect:/solicitudPrestamoObjeto/consulta/"+solicitudPrestamoObjeto.getSolicitudPrestamoObjetoId());
			}
            else {
            	ModelMap map = new ModelMap();
    			map.put("solicitudPrestamoObjeto", solicitudPrestamoObjeto);
            	
    			map.put("solicitudPrestamoObjetoAceptadaDialog", true);
            	
            	return new ModelAndView("solicitudPrestamoObjeto/consulta/"+solicitudPrestamoObjeto.getSolicitudPrestamoObjetoId(), map);
			}
		}
		else {
			return new ModelAndView("redirect:/");
		}
	}
	
	@RequestMapping(value = "/rechazar", method = RequestMethod.POST)
	public ModelAndView rechazar(
			@ModelAttribute("solicitudPrestamoObjeto") SolicitudPrestamoObjeto solicitudPrestamoObjetoForm, 
            BindingResult result) {
		
		SolicitudPrestamoObjeto solicitudPrestamoObjeto = solicitudPrestamoObjetoService.getSolicitudPrestamoObjetoById(solicitudPrestamoObjetoForm.getSolicitudPrestamoObjetoId());
		if (solicitudPrestamoObjeto != null) {
			solicitudPrestamoObjeto.setMensajeRespuesta(solicitudPrestamoObjetoForm.getMensajeRespuesta());
			solicitudPrestamoObjeto.setSolicitudAceptada(false);
			
			solicitudPrestamoObjetoValidator.validate(solicitudPrestamoObjeto, result);
			if (!result.hasErrors()) {
				solicitudPrestamoObjetoService.updateSolicitudPrestamoObjeto(solicitudPrestamoObjeto);
				
				CorreoElectronicoSender ce = (CorreoElectronicoSender) correoElectronicoSender;
				ce.sendMail("administracion@prestamart.com",
						solicitudPrestamoObjeto.getConsumidor().getCorreoElectronico(),
		    		    "Solicitud de prestamo de objeto rechazada", 
		    		    "El prestador ha rechazado la solicitud del prestamo del objeto: " + solicitudPrestamoObjeto.getObjeto().getNombreObjeto() + "\n\n" +
		    		    "Respuesta de la solicitud de prestamo del objeto: " + solicitudPrestamoObjeto.getMensajeRespuesta() + "\n\n" +
		    		    "Saludos");
				
				//return new ModelAndView("redirect:/objeto/adminPrestador");
				return new ModelAndView("redirect:/solicitudPrestamoObjeto/consulta/"+solicitudPrestamoObjeto.getSolicitudPrestamoObjetoId());
			}
            else {
            	ModelMap map = new ModelMap();
    			map.put("solicitudPrestamoObjeto", solicitudPrestamoObjeto);
            	
    			map.put("solicitudPrestamoObjetoRechazadaDialog", true);
            	
            	return new ModelAndView("solicitudPrestamoObjeto/consulta/"+solicitudPrestamoObjeto.getSolicitudPrestamoObjetoId(), map);
			}
		}
		else {
			return new ModelAndView("redirect:/");
		}
	}
	
	
	
	@RequestMapping(value = "/confirmarEnvioObjeto", method = RequestMethod.POST)
	public ModelAndView confirmarEnvioObjeto(@RequestParam(value = "solicitudPrestamoObjetoId", required = true) Integer solicitudPrestamoObjetoId) {
		
		SolicitudPrestamoObjeto solicitudPrestamoObjeto = solicitudPrestamoObjetoService.getSolicitudPrestamoObjetoById(solicitudPrestamoObjetoId);
		if (solicitudPrestamoObjeto != null) {
			solicitudPrestamoObjeto.setEnvioPrestamoObjetoPrestador(true);
			
			solicitudPrestamoObjetoService.updateSolicitudPrestamoObjeto(solicitudPrestamoObjeto);
			
			Objeto objeto = objetoService.getObjetoById(solicitudPrestamoObjeto.getObjeto().getObjetoId());
			objeto.setPrestado(true);
			
			objetoService.updateObjeto(objeto);
			
			return new ModelAndView("redirect:/solicitudPrestamoObjeto/consulta/"+solicitudPrestamoObjeto.getSolicitudPrestamoObjetoId());
			//return new ModelAndView("redirect:/objeto/adminPrestador?updateSuccessful");
		}
		else {
			return new ModelAndView("redirect:/objeto/adminPrestador?updateError");
		}
	}
	
	@RequestMapping(value = "/confirmarRecepcionEnvioObjeto", method = RequestMethod.POST)
	public ModelAndView confirmarRecepcionEnvioObjeto(@RequestParam(value = "solicitudPrestamoObjetoId", required = true) Integer solicitudPrestamoObjetoId) {
		
		SolicitudPrestamoObjeto solicitudPrestamoObjeto = solicitudPrestamoObjetoService.getSolicitudPrestamoObjetoById(solicitudPrestamoObjetoId);
		if (solicitudPrestamoObjeto != null) {
			solicitudPrestamoObjeto.setEnvioPrestamoObjetoRecibidoConsumidor(true);
			
			solicitudPrestamoObjetoService.updateSolicitudPrestamoObjeto(solicitudPrestamoObjeto);
			
			return new ModelAndView("redirect:/solicitudPrestamoObjeto/consulta/"+solicitudPrestamoObjeto.getSolicitudPrestamoObjetoId());
			//return new ModelAndView("redirect:/solicitudPrestamoObjeto/adminConsumidor?updateSuccessful");
		}
		else {
			return new ModelAndView("redirect:/solicitudPrestamoObjeto/adminConsumidor?updateError");
		}
	}
	
	@RequestMapping(value = "/confirmarDevolucionObjeto", method = RequestMethod.POST)
	public ModelAndView confirmarDevolucionObjeto(@RequestParam(value = "solicitudPrestamoObjetoId", required = true) Integer solicitudPrestamoObjetoId) {
		
		SolicitudPrestamoObjeto solicitudPrestamoObjeto = solicitudPrestamoObjetoService.getSolicitudPrestamoObjetoById(solicitudPrestamoObjetoId);
		if (solicitudPrestamoObjeto != null) {
			solicitudPrestamoObjeto.setDevolucionObjetoConsumidor(true);
			
			solicitudPrestamoObjetoService.updateSolicitudPrestamoObjeto(solicitudPrestamoObjeto);
			
			return new ModelAndView("redirect:/solicitudPrestamoObjeto/consulta/"+solicitudPrestamoObjeto.getSolicitudPrestamoObjetoId());
			//return new ModelAndView("redirect:/solicitudPrestamoObjeto/adminConsumidor?updateSuccessful");
		}
		else {
			return new ModelAndView("redirect:/solicitudPrestamoObjeto/adminConsumidor?updateError");
		}
	}
	
	@RequestMapping(value = "/confirmarRecepcionDevolucionObjeto", method = RequestMethod.POST)
	public ModelAndView confirmarRecepcionDevolucionObjeto(@RequestParam(value = "solicitudPrestamoObjetoId", required = true) Integer solicitudPrestamoObjetoId) {
		
		SolicitudPrestamoObjeto solicitudPrestamoObjeto = solicitudPrestamoObjetoService.getSolicitudPrestamoObjetoById(solicitudPrestamoObjetoId);
		if (solicitudPrestamoObjeto != null) {
			solicitudPrestamoObjeto.setDevolucionObjetoRecibidaPrestador(true);
			
			solicitudPrestamoObjetoService.updateSolicitudPrestamoObjeto(solicitudPrestamoObjeto);
			
			Objeto objeto = objetoService.getObjetoById(solicitudPrestamoObjeto.getObjeto().getObjetoId());
			objeto.setPrestado(false);
			
			objetoService.updateObjeto(objeto);
			
			return new ModelAndView("redirect:/solicitudPrestamoObjeto/consulta/"+solicitudPrestamoObjeto.getSolicitudPrestamoObjetoId());
			//return new ModelAndView("redirect:/objeto/adminPrestador?updateSuccessful");
		}
		else {
			return new ModelAndView("redirect:/objeto/adminPrestador?updateError");
		}
	}
	
	
	public Integer stringToId(String cadena) {
		Integer id = -1;
		try {
			id = Integer.parseInt(cadena);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return id;
	}

}
