package controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.methods.HttpPost;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/salidaDelSistema")
public class SalidaDelSistema {
	
	@RequestMapping(method = RequestMethod.GET)
	public String salirDelSistema() {
		//HttpPost httpPost = new HttpPost("/j_spring_security_logout");
		
		return "redirect:/j_spring_security_logout";
	}

}
