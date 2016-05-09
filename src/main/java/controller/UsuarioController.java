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
	
	
	
	@RequestMapping(value = "/cambios/{usuarioId}", method = RequestMethod.GET)
    public String cambios(@PathVariable String usuarioId, 
    		              ModelMap map
    		              /*@ModelAttribute("command") Usuario usuario, 
    		              BindingResult result*/) {
		
		Integer id = -1;
		try {
			id = Integer.parseInt(usuarioId);
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		Usuario usuario = usuarioService.getUsuarioById(id);
		if (usuario == null) {
			return "redirect:/";
		}
		else {
			map.put("usuario", usuario);
			
			List<TipoUsuario> tiposUsuario = tipoUsuarioService.getTiposUsuarioByAtributo("elegible", true);
			map.put("tiposUsuario", tiposUsuario);
			
	        return "usuario/cambios";
		}
	}
	
	@RequestMapping(value = "/modificar", method = RequestMethod.POST)
	public ModelAndView modificar(@ModelAttribute("usuario") Usuario usuario, 
			                      BindingResult result) {
		
		Usuario usuarioAModificar = usuarioService.getUsuarioById(usuario.getUsuarioId());
		if (usuarioAModificar != null) {
			usuarioAModificar = usuario;
			
			ModelMap map = new ModelMap();
			map.put("usuario", usuarioAModificar);
			
			System.out.println(usuarioAModificar.getTipoUsuario());
			
			usuarioValidator.validate(usuarioAModificar, result);
			if (!result.hasErrors()) {
				usuarioAModificar.setActivo(true);
				usuarioService.updateUsuario(usuarioAModificar);
				
				return new ModelAndView("usuario/mostrar", map);
			}
            else {
            	List<TipoUsuario> tiposUsuario = tipoUsuarioService.getTiposUsuarioByAtributo("elegible", true);
            	map.put("tiposUsuario", tiposUsuario);
            	
            	return new ModelAndView("usuario/cambios", map);
			}
		}
		else {
			return new ModelAndView("index");
		}
	}
}
