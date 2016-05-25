package controller;

import form.IngresoAlSistemaForm;
import model.Usuario;
import model.TipoUsuario;
import service.UsuarioService;

import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("user")
@RequestMapping("/ingresoAlSistema")
public class IngresoAlSistemaController {

	@Autowired
	private UsuarioService usuarioService;
	
	@ModelAttribute
    public void addGlobalAttr(ModelMap map) {
        map.put("usuario", null);
    }
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView muestraInicio(
			@RequestParam(value = "error", required = false) String error, 
    		@RequestParam(value = "logout", required = false) String logout,
    		@RequestParam(value = "registrationSuccessful", required = false) String registrationSuccessful) {
		return muestraIngresoAlSistema(error, logout, registrationSuccessful);
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView muestraIngresoAlSistema( 
    		@RequestParam(value = "error", required = false) String error, 
    		@RequestParam(value = "logout", required = false) String logout,
    		@RequestParam(value = "registrationSuccessful", required = false) String registrationSuccessful) {
        
		ModelMap map = new ModelMap();
		
		IngresoAlSistemaForm ingresoAlSistemaForm = new IngresoAlSistemaForm();
		map.put("ingresoAlSistemaForm", ingresoAlSistemaForm);
        
        if (error != null) {
			map.put("error", "El correo electr&oacute;nico o contrase&ntilde;a son incorrectos");
		}
        else if (logout != null) {
        	map.put("mensaje", "La sesi&oacute;n se ha cerrado correctamente");
        }
        else if (registrationSuccessful != null) {
        	map.put("mensaje", "El registro de usuario se ha realizado correctamente");
        }
        
        return new ModelAndView("ingresoAlSistema/index", map);
	}
	
	@RequestMapping(value = "/ingresarAlSistema", method = RequestMethod.POST)
    public ModelAndView ingresarAlSistema(@ModelAttribute("IngresoAlSistemaForm") IngresoAlSistemaForm ingresoAlSistemaForm, 
    		                              ModelMap map) {
        
		//ModelMap map = new ModelMap();
		Usuario usuario = usuarioService.getUsuarioByCorreoElectronico(ingresoAlSistemaForm.getCorreoElectronico());
		if (usuario != null) {
			String contrasenia = usuario.getContrasenia();
			if (contrasenia == ingresoAlSistemaForm.getCorreoElectronico()) {
				
				TipoUsuario tipoUsuario = usuario.getTipoUsuario();
				if (tipoUsuario != null) {
					map.put("usuario", usuario);
					
					if (tipoUsuario.getNombreTipoUsuario() == "Prestador") {
					    return new ModelAndView("objeto/adminPrestador", map);
					}
					else if (tipoUsuario.getNombreTipoUsuario() == "Consumidor") {
						return new ModelAndView("prestamo/adminConsumidor", map);
					}
					else {
						return new ModelAndView("ingresoAlSistema/index");
					}
				}
				else {
					return new ModelAndView("ingresoAlSistema/index");
				}
				
			}
			else {
				map.put("mensaje", "El correo electr&oacute;nico o contrase&ntilde;a son incorrectos");
				
				return new ModelAndView("ingresoAlSistema/index");
			}
		}
		else {
			map.put("mensaje", "El correo electr&oacute;nico o contrase&ntilde;a son incorrectos");
			
			return new ModelAndView("ingresoAlSistema/index");
		}
	}
}
