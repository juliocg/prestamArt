package controller;

import model.Usuario;
import model.TipoUsuario;
import service.UsuarioService;
import service.TipoUsuarioService;
import validator.UsuarioValidator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	private UsuarioService usuarioService;
	private TipoUsuarioService tipoUsuarioService;
	private UsuarioValidator usuarioValidator;
	
	@Autowired
	public UsuarioController(UsuarioService usuarioService, TipoUsuarioService tipoUsuarioService, UsuarioValidator usuarioValidator) {
		this.usuarioService = usuarioService;
		this.tipoUsuarioService = tipoUsuarioService;
		this.usuarioValidator = usuarioValidator;
	}
	
	/*@RequestMapping(value = "/cambios.html", method = RequestMethod.GET)
    public ModelAndView cambios(HttpServletRequest request, HttpServletResponse response) {
		
		String usuarioIdString = request.getParameter("id");
		System.out.println(usuarioIdString);
		Usuario usuario = usuarioService.getUsuarioById(usuarioIdString);
		
		if (usuario == null) {
			return new ModelAndView("index");
		}
		else {
			UsuarioForm usuarioForm = new UsuarioForm();
			usuarioForm.setUsuarioId(usuario.getUsuarioId().toString());
			usuarioForm.setTipoUsuarioId(usuario.getTipoUsuario().getTipoUsuarioId().toString());
			usuarioForm.setCorreoElectronico(usuario.getCorreoElectronico());
			usuarioForm.setContrasenia(usuario.getContrasenia());
			usuarioForm.setNombre(usuario.getNombre());
			usuarioForm.setApellidos(usuario.getApellidos());
			usuarioForm.setTelefono(usuario.getTelefono());
			usuarioForm.setOtroDatoContacto(usuario.getOtroDatoContacto());
			//usuarioForm.setActivo(usuario.getActivo().toString());
	        
	        return new ModelAndView("usuario/cambios", "usuarioForm", usuarioForm);
		}
	}
	
	@RequestMapping(value = "/modificar.html", method = RequestMethod.POST)
	public String modificar(@ModelAttribute("usuarioForm") UsuarioForm usuarioForm, BindingResult result) {
		
		String usuarioIdString = usuarioForm.getUsuarioId();
		Usuario usuario = usuarioService.getUsuarioById(usuarioIdString);
		//Usuario usuario = null;
		//Integer usuarioId = -1;
		//try {
			//usuarioId = Integer.parseInt(usuarioForm.getUsuarioId());
			//usuario = usuarioService.getUsuarioById(usuarioId);
		//}
		//catch(NumberFormatException e) {
			//e.printStackTrace();
		//}
		
		if (usuario != null) {
			usuarioValidator.validate(usuarioForm, result);
			if (!result.hasErrors()) {
				usuarioService.addUsuario(usuarioForm);
				
				return "redirect:userSuccess.htm";
			}
            else {
				return "/usuario/cambios";
			}
		}
		else {
			return "/usuario/index";
		}
	}*/
	
	@RequestMapping(value = "/consulta/{usuarioId}", method = RequestMethod.GET)
    public ModelAndView mostrarConsulta(
    		@PathVariable String usuarioId/*, 
            ModelMap map*/) {
    		
		Integer id = stringToId(usuarioId);
		Usuario usuario = usuarioService.getUsuarioById(id);
		if (usuario == null) {
			return new ModelAndView("redirect:/");
		}
		else {
			ModelMap map = new ModelMap();
			map.put("usuario", usuario);
			
			/*List<TipoUsuario> tiposUsuario = tipoUsuarioService.getTiposUsuarioByAtributo("elegible", true);
			map.put("tiposUsuario", tiposUsuario);*/
			
	        return new ModelAndView("usuario/consulta", map);
		}
    }
	
	@RequestMapping(value = "/cambios/{usuarioId}", method = RequestMethod.GET)
    public ModelAndView mostrarCambios(
    		@PathVariable String usuarioId/*, 
            ModelMap map*/) {
		
		Integer id = stringToId(usuarioId);
		Usuario usuario = usuarioService.getUsuarioById(id);
		if (usuario == null) {
			return new ModelAndView("redirect:/");
			//return "redirect:/";
		}
		else {
			ModelMap map = new ModelMap();
			map.put("usuario", usuario);
			
			List<TipoUsuario> tiposUsuario = tipoUsuarioService.getTiposUsuarioByAtributo("elegible", true);
			map.put("tiposUsuario", tiposUsuario);
			
	        return new ModelAndView("usuario/cambios", map);
			//return "usuario/cambios"; // usar commandName en el form de la vista
		}
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.POST)
	public ModelAndView modificar(
			@ModelAttribute("usuario") Usuario usuarioForm, 
            BindingResult result) {
		
		Usuario usuario = usuarioService.getUsuarioById(usuarioForm.getUsuarioId());
		if (usuario != null) {
			usuario = usuarioForm;
			
			usuarioValidator.validate(usuario, result);
			if (!result.hasErrors()) {
				usuario.setActivo(true);
				
				usuarioService.updateUsuario(usuario);
				
				return new ModelAndView("redirect:/usuario/consulta/"+usuario.getUsuarioId());
			}
            else {
            	ModelMap map = new ModelMap();
    			map.put("usuario", usuario);
            	
            	List<TipoUsuario> tiposUsuario = tipoUsuarioService.getTiposUsuarioByAtributo("elegible", true);
            	map.put("tiposUsuario", tiposUsuario);
            	
            	return new ModelAndView("usuario/cambios", map);
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
