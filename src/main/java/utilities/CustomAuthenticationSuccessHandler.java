package utilities;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException {
        //logger.info("Hit the AuthSuccessHandler");
        String redirectAddress = null;
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();

        if (authorities.contains("ROLE_ADMIN")) {
            response.sendRedirect(response.encodeURL("homeadmin.jsf"));
        }
    }
}
