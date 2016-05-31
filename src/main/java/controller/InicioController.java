package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/")
public class InicioController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String mostrarInicio() {
		
		return mostrarIndex();
	}
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
    public String mostrarIndex() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
        List<String> authorities = new ArrayList<String>();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
        	authorities.add(grantedAuthority.getAuthority());
		}
        
        String redirectAddress = "index";
        if (authorities.contains("ROLE_ADMIN")) {
        	redirectAddress = "redirect:/admin";
        }
        else if (authorities.contains("ROLE_PRESTADOR")) {
        	redirectAddress = "redirect:/objeto/adminPrestador";
        }
        else if (authorities.contains("ROLE_CONSUMIDOR")) {
        	redirectAddress = "redirect:/solicitudPrestamoObjeto/adminConsumidor";
        }
		
		return redirectAddress;
	}
}
