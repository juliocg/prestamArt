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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private SessionRegistry sessionRegistry;
	
	private UsuarioService usuarioService;
	private TipoUsuarioService tipoUsuarioService;
	private UsuarioValidator usuarioValidator;
	
	@Autowired
	public UsuarioController(UsuarioService usuarioService, TipoUsuarioService tipoUsuarioService, UsuarioValidator usuarioValidator) {
		this.usuarioService = usuarioService;
		this.tipoUsuarioService = tipoUsuarioService;
		this.usuarioValidator = usuarioValidator;
	}
	
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
	
	@RequestMapping(value = "/realizarBaja", method = RequestMethod.POST)
	public ModelAndView realizarBaja(@RequestParam(value = "usuarioId", required = true) Integer usuarioId) {
		
		Usuario usuario = usuarioService.getUsuarioById(usuarioId);
		if (usuario != null) {
			try {
				usuarioService.removeUsuario(usuario);
				
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				
				/*UserDetails userDetails = usuarioService.loadUserByUsername(authentication.getName());
				for (SessionInformation sessionInformation : sessionRegistry.getAllSessions(userDetails, true)) {
					sessionInformation.expireNow();
					sessionRegistry.removeSessionInformation(sessionInformation.getSessionId());
                }*/
			    
			    return new ModelAndView("redirect:/j_spring_security_logout?spring-security-redirect=/ingresoAlSistema?usuarioDeleteteSuccessful");
			    
			    /*Usuario usuarioActual = usuarioService.getUsuarioByCorreoElectronicoAndActivo(authentication.getName(), true);
			    SecurityContextLogoutHandler ctxLogOut = new SecurityContextLogoutHandler(); // concern you

			    if (usuarioActual == null){
			        ctxLogOut.logout(request, response, auth); // concern you
			    }
			    
			    return new ModelAndView("redirect:/index?usuarioDeleteteSucessful");*/
			} catch (Exception e) {
				e.printStackTrace();
				
				return new ModelAndView("redirect:/usuario/consulta/"+usuario.getUsuarioId());
			}
		}
		else {
			return new ModelAndView("redirect:/index?usuarioDeleteteError");
		}
	}
	
	public Usuario getUsuarioActual() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Usuario usuarioActual = null;
		try {
	        usuarioActual = usuarioService.getUsuarioByCorreoElectronicoAndActivo(authentication.getName(), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    return usuarioActual;
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
