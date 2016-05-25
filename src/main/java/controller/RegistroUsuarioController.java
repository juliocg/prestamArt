package controller;

import java.util.List;

import model.TipoUsuario;
import model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import service.TipoUsuarioService;
import service.UsuarioService;
import validator.UsuarioValidator;

@Controller
@RequestMapping("/registroUsuario")
public class RegistroUsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private TipoUsuarioService tipoUsuarioService;
	@Autowired
	private UsuarioValidator usuarioValidator;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		//return new ModelAndView("redirect:/registroUsuario/index");
		return mostrarRegistroUsuario();
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView mostrarRegistroUsuario() {
		
		Usuario usuario = new Usuario();
		
		ModelMap map = new ModelMap();
		map.put("usuario", usuario);
		
		List<TipoUsuario> tiposUsuario = tipoUsuarioService.getTiposUsuarioByAtributo("elegible", true);
		map.put("tiposUsuario", tiposUsuario);
		
		return new ModelAndView("registroUsuario/index", map);
	}
	
	@RequestMapping(value = "/registrar", method = RequestMethod.POST)
    public ModelAndView realizarRegistroUsuario(
    		@ModelAttribute("usuario") Usuario usuarioForm, 
            BindingResult result) {
		
		Usuario usuario = usuarioForm;
		usuarioValidator.validate(usuario, result);
		if (!result.hasErrors()) {
			usuario.setActivo(true);
			
			//String correoElectronico = usuario.getCorreoElectronico();
			//TipoUsuario tipoUsuario = usuario.getTipoUsuario();
			//Usuario usuarioPersistente = usuarioService.getUsuarioByCorreoElectronicoAndTipoUsuario(correoElectronico, tipoUsuario);
			//Integer usuarioId = usuarioPersistente.getUsuarioId();
			//if (usuarioPersistente != null) {
				//usuarioPersistente = usuario;
				//usuarioPersistente.setUsuarioId(usuarioId);
				//usuarioService.updateUsuario(usuarioPersistente);
			//}
			//else {
				usuarioService.addUsuario(usuario);
			//}
			
			return new ModelAndView("redirect:/ingresoAlSistema?registrationSuccessful");
		}
        else {
        	ModelMap map = new ModelMap();
			map.put("usuario", usuario);
        	
        	List<TipoUsuario> tiposUsuario = tipoUsuarioService.getTiposUsuarioByAtributo("elegible", true);
        	map.put("tiposUsuario", tiposUsuario);
        	
        	return new ModelAndView("registroUsuario/index", map);
		}
	}
	
	public Boolean existeUsuario(Usuario usuario) {
		Boolean existe = false;
		
		String correoElectronico = usuario.getCorreoElectronico();
		TipoUsuario tipoUsuario = usuario.getTipoUsuario();
		Usuario usuarioPersistente = usuarioService.getUsuarioByCorreoElectronicoAndTipoUsuario(correoElectronico, tipoUsuario);
		if (usuarioPersistente != null) {
			existe = true;
		}
		
		return existe;
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
