package utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	protected Log logger = LogFactory.getLog(this.getClass());
	 
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
    		                            HttpServletResponse response, 
    		                            Authentication authResult) throws IOException, ServletException {
		
        Collection<? extends GrantedAuthority> grantedAuthorities = authResult.getAuthorities();
        List<String> authorities = new ArrayList<String>();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
        	authorities.add(grantedAuthority.getAuthority());
		}
        
        String redirectAddress = "/";
        if (authorities.contains("ROLE_ADMIN")) {
        	redirectAddress = "/admin";
        }
        else if (authorities.contains("ROLE_PRESTADOR")) {
        	redirectAddress = "/objeto/adminPrestador";
        }
        else if (authorities.contains("ROLE_CONSUMIDOR")) {
        	redirectAddress = "/prestamo/adminConsumidor";
        }
        
        //response.sendRedirect(response.encodeURL(redirectAddress));
        redirectStrategy.sendRedirect(request, response, redirectAddress);
    }
	
	protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
