package controller;

import model.Objeto;
import model.SolicitudPrestamoObjeto;
import service.ObjetoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/consultaObjeto")
public class ConsultaObjetoController {
	
	@Autowired
	private ObjetoService objetoService;

	@RequestMapping(value = "/{objetoId}", method = RequestMethod.GET)
    public ModelAndView muestraConsultaObjeto(
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
			
			SolicitudPrestamoObjeto solicitudPrestamoObjeto = new SolicitudPrestamoObjeto();
			map.put("solicitudPrestamoObjeto", solicitudPrestamoObjeto);
			
			map.put("solicitudPrestamoObjetoAltaDialog", false);
			
	        return new ModelAndView("consultaObjeto/index", map);
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
