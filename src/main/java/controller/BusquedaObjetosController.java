package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TipoUsuario;
import model.Objeto;
import service.ObjetoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/busquedaObjetos")
public class BusquedaObjetosController {
	
	@Autowired
	private ObjetoService objetoService;
	
	/*@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		return mostrarBuscaObjetos();
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView mostrarBusquedaObjetos() {
		ModelMap map = new ModelMap();
		
		return new ModelAndView("busquedaObjetos/index", map);
	}*/
	
	@PreAuthorize("hasAnyRole('ROLE_CONSUMIDOR')")
	@RequestMapping(value = "/buscar", method = RequestMethod.POST)
    public ModelAndView realizarBusquedaObjetos(HttpServletRequest request, HttpServletResponse response) {
		
		String texto = request.getParameter("texto");
		System.out.println(texto);

        List<Objeto> objetos = objetoService.getObjetosByTexto(texto);
		
		ModelMap map = new ModelMap();
		map.put("objetos", objetos);
		
		return new ModelAndView("busquedaObjetos/objetosEncontrados", map);
	}

}
