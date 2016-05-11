package controller;

import java.util.List;

import model.Usuario;
import model.Objeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String mostrarAdminPrestador(ModelMap map) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String name = authentication.getName();
	    System.out.println(name);
	    
	    /*User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String username = user.getUsername();
	    System.out.println(username);*/
		
	    Usuario prestador = usuarioService.getUsuarioByCorreoElectronico(name);
		List<Objeto> objetos = objetoService.getObjetosByPrestador(prestador);

        map.put("objetos", objetos);
			
	    return "objeto/adminPrestador";
	}
}
