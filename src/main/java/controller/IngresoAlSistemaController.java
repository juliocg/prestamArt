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
	public ModelAndView mostrarInicio(ModelMap map, HttpServletRequest request, @RequestParam(value = "error", required = false) String error) {
		
		return ingresoAlSistema(map, request, error);
	}

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public ModelAndView ingresoAlSistema(ModelMap map, HttpServletRequest request, @RequestParam(value = "error", required = false) String error) {
        IngresoAlSistemaForm ingresoAlSistemaForm = new IngresoAlSistemaForm();
        
        
        if (error != null) {
			map.put("error", "Invalid username and password!");
		}
        map.put("ingresoAlSistemaForm", ingresoAlSistemaForm);
        
        return new ModelAndView("ingresoAlSistema/index", map);
	}
	
	@RequestMapping(value = "/ingresarAlSistema.html", method = RequestMethod.POST)
    public ModelAndView ingresarAlSistema(@ModelAttribute("IngresoAlSistemaForm") IngresoAlSistemaForm ingresoAlSistemaForm, 
    		                              ModelMap map) {
        
		//ModelAndView model= null;
		
		Usuario usuario = usuarioService.getUsuarioByCorreoElectronico(ingresoAlSistemaForm.getCorreoElectronico());
		if (usuario != null) {
			String contrasenia = usuario.getContrasenia();
			if (contrasenia == ingresoAlSistemaForm.getCorreoElectronico()) {
				
				TipoUsuario tipoUsuario = usuario.getTipoUsuario();
				if (tipoUsuario != null) {
					map.put("usuario", usuario);
					
					if (tipoUsuario.getNombreTipoUsuario() == "Prestador") {
					    return new ModelAndView("prestamo/administracionPrestadamos", map);
					}
					else if (tipoUsuario.getNombreTipoUsuario() == "Consumidor") {
						return new ModelAndView("objeto/administracionPrestadamos", map);
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
				map.put("message", "Invalid credentials!!");
				
				return new ModelAndView("ingresoAlSistema/index");
			}
		}
		else {
			map.put("message", "Invalid credentials!!");
			
			return new ModelAndView("ingresoAlSistema/index");
		}
		
		/*try {
			boolean isValidUser = ingresoAlSistemaService.isValidUser(ingresoAlSistemaForm.getCorreoElectronico(), ingresoAlSistemaForm.getContrasenia());
			if (isValidUser) {
				System.out.println("User Login Successful");
				request.setAttribute("loggedInUser", loginBean.getUsername());
				map = new ModelAndView("welcome");
			}
			else {
				map = new ModelAndView("login");
				request.setAttribute("message", "Invalid credentials!!");
			}

		}
		catch (Exception e) {
		    e.printStackTrace();
		}
		
        return new ModelAndView("ingresoAlSistema/index", map);*/
	}
}
