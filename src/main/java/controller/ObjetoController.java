package controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.ImagenObjeto;
import model.SolicitudPrestamoObjeto;
import model.Usuario;
import model.TipoUsuario;
import model.Objeto;
import model.TipoObjeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import service.ObjetoService;
import service.TipoObjetoService;
import service.UsuarioService;
import validator.ObjetoValidator;

@Controller
@RequestMapping("/objeto")
public class ObjetoController {
	
	@Autowired
	private ObjetoService objetoService;
	@Autowired
	private TipoObjetoService tipoObjetoService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ObjetoValidator objetoValidator;
	
	@RequestMapping(value = "/adminPrestador", method = RequestMethod.GET)
    public String mostrarAdminPrestador(
    		ModelMap map, 
    		@RequestParam(value = "deleteSuccessful", required = false) String deleteSuccessful) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String name = authentication.getName();
	    System.out.println(name);
	    
	    /*User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String username = user.getUsername();
	    System.out.println(username);*/
		
	    Usuario prestador = usuarioService.getUsuarioByCorreoElectronicoAndActivo(name, true);
	    
		List<Objeto> objetos = objetoService.getObjetosByPrestador(prestador);

        map.put("objetos", objetos);
        
        if (deleteSuccessful != null) {
        	map.put("mensaje", "El objeto se ha dado de baja correctamente");
        }
			
	    return "objeto/adminPrestador";
	}
	
	@RequestMapping(value = "/alta", method = RequestMethod.GET)
    public ModelAndView mostrarAlta() {
		
		Objeto objeto = new Objeto();
		
		/*if (objeto.getImagenObjetos().size() < 1) {
			Set<ImagenObjeto> imagenObjetos = new HashSet<ImagenObjeto>();
			imagenObjetos.add(new ImagenObjeto());
			objeto.setImagenObjetos(imagenObjetos);
		}*/
		
		ModelMap map = new ModelMap();
		map.put("objeto", objeto);
		
		List<TipoObjeto> tiposObjeto = tipoObjetoService.getTiposObjeto();
		map.put("tiposObjeto", tiposObjeto);
		
		return new ModelAndView("objeto/alta", map);
	}
	
	@RequestMapping(value = "/realizarAlta", method = RequestMethod.POST)
    public ModelAndView realizarAlta(
    		@ModelAttribute("objeto") Objeto objetoForm, 
    		@RequestParam("imagen") MultipartFile imagen,  
            BindingResult result) {
		
		Objeto objeto = objetoForm;
		
		objeto.setNombreImagen(imagen.getOriginalFilename());
		
		objetoValidator.validate(objeto, result);
		if (!result.hasErrors()) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    String name = authentication.getName();
		    
		    Usuario prestador = usuarioService.getUsuarioByCorreoElectronicoAndActivo(name, true);
			
		    objeto.setPrestador(prestador);
		    objeto.setActivo(true);
			
		    try {
		        objetoService.addObjeto(objeto,imagen);
		    } catch(Exception e) {
			    e.printStackTrace();
			}
			
			return new ModelAndView("redirect:/objeto/adminPrestador?saveSuccessful");
			//return new ModelAndView("redirect:/objeto/consulta/"+objeto.getObjetoId());
		}
        else {
        	/*if (objeto.getImagenObjetos().size() < 1) {
    			Set<ImagenObjeto> imagenObjetos = new HashSet<ImagenObjeto>();
    			imagenObjetos.add(new ImagenObjeto());
    			objeto.setImagenObjetos(imagenObjetos);
    		}*/
        	
        	ModelMap map = new ModelMap();
			map.put("objeto", objeto);
        	
			List<TipoObjeto> tiposObjeto = tipoObjetoService.getTiposObjeto();
			map.put("tiposObjeto", tiposObjeto);
        	
        	return new ModelAndView("objeto/alta", map);
		}
	}
	
	@RequestMapping(value = "/consulta/{objetoId}", method = RequestMethod.GET)
    public ModelAndView mostrarConsulta(
    		@PathVariable String objetoId/*, 
            ModelMap map*/) {
    		
		Integer id = stringToId(objetoId);
		Objeto objeto = objetoService.getObjetoById(id);
		if (objeto == null) {
			return new ModelAndView("redirect:/");
		}
		else {
			ModelMap map = new ModelMap();
			map.put("objeto", objeto);
			
			/*List<TipoObjeto> tiposObjeto = tipoObjetoService.getTiposObjeto();
			map.put("tiposObjeto", tiposObjeto);*/
			
	        return new ModelAndView("objeto/consulta", map);
		}
    }
	
	@RequestMapping(value = "/realizarBaja", method = RequestMethod.POST)
	public ModelAndView realizarBaja(@RequestParam(value = "objetoId", required = true) Integer objetoId) {
		
		Objeto objeto = objetoService.getObjetoById(objetoId);
		if (objeto != null) {
			try {
			    objetoService.removeObjeto(objeto);
			    
			    return new ModelAndView("redirect:/objeto/adminPrestador?deleteSuccessful");
			} catch (Exception e) {
				e.printStackTrace();
				
				return new ModelAndView("redirect:/objeto/adminPrestador?deleteteError");
			}
		}
		else {
			return new ModelAndView("redirect:/objeto/adminPrestador?deleteteError");
		}
	}
	
	@RequestMapping(value = "/cambios/{objetoId}", method = RequestMethod.GET)
    public ModelAndView mostrarCambios(
    		@PathVariable String objetoId/*, 
            ModelMap map*/) {
		
		Integer id = stringToId(objetoId);
		Objeto objeto = objetoService.getObjetoById(id);
		if (objeto == null) {
			return new ModelAndView("redirect:/");
		}
		else {
			ModelMap map = new ModelMap();
			map.put("objeto", objeto);
			
			List<TipoObjeto> tiposObjeto = tipoObjetoService.getTiposObjeto();
			map.put("tiposObjeto", tiposObjeto);
			
	        return new ModelAndView("objeto/cambios", map);
		}
	}
	
	@RequestMapping(value = "/realizarCambios", method = RequestMethod.POST)
	public ModelAndView realizarCambios(
			@ModelAttribute("objeto") Objeto objetoForm, 
    		@RequestParam("imagen") MultipartFile imagen, 
            BindingResult result) {
		
		Objeto objeto = objetoService.getObjetoById(objetoForm.getObjetoId());
		if (objeto != null) {
			objeto = objetoForm;
			
			objeto.setNombreImagen(imagen.getOriginalFilename());
			
			objetoValidator.validate(objeto, result);
			if (!result.hasErrors()) {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			    String name = authentication.getName();
			    
			    Usuario prestador = usuarioService.getUsuarioByCorreoElectronicoAndActivo(name, true);
				
			    objeto.setPrestador(prestador);
			    objeto.setActivo(true);
				
				try {
			        objetoService.updateObjeto(objeto,imagen);
			    } catch(Exception e) {
				    e.printStackTrace();
				}
				
				return new ModelAndView("redirect:/objeto/adminPrestador?updateSuccessful");
				//return new ModelAndView("redirect:/objeto/consulta/"+objeto.getObjetoId());
			}
            else {
            	ModelMap map = new ModelMap();
    			map.put("objeto", objeto);
            	
    			List<TipoObjeto> tiposObjeto = tipoObjetoService.getTiposObjeto();
    			map.put("tiposObjeto", tiposObjeto);
            	
            	return new ModelAndView("objeto/cambios", map);
			}
		}
		else {
			return new ModelAndView("redirect:/");
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
