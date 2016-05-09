package utilities;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import service.UsuarioService;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String correoElectronico = authentication.getName();
        String contrasenia = (String) authentication.getCredentials();

        UserDetails user = usuarioService.loadUserByUsername(correoElectronico);
        if (user == null) {
            throw new BadCredentialsException("Username not found.");
        }
        if (!contrasenia.equals(user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        return new UsernamePasswordAuthenticationToken(correoElectronico, contrasenia, authorities);
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}
