package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/")
public class InicioController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String mostrarInicio() {
		
		return "index";
	}
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
    public String mostrarIndex() {
		
		return "index";
	}
}
