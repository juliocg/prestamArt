package service;

import java.util.HashSet;
import java.util.Set;

import dao.UsuarioDAO;
import model.TipoUsuario;
import model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public class AutenticacionService implements UserDetailsService {
	 
    @Autowired
    UsuarioDAO usuarioDAO;
	 
    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String correoElectronico) throws UsernameNotFoundException {
	    Usuario usuario = usuarioDAO.findByCorreoElectronicoAndActivo(correoElectronico, true);
	    
	    Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
	     
	    SimpleGrantedAuthority prestadorAuthority = new SimpleGrantedAuthority("ROLE_PRESTADOR");
	    SimpleGrantedAuthority consumidorAuthority = new SimpleGrantedAuthority("ROLE_CONSUMIDOR");
	    SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
	    
	    TipoUsuario tipoUsuario = usuario.getTipoUsuario();
	    if (tipoUsuario != null) {
		    String role = tipoUsuario.getNombreTipoUsuario(); 
		    if (role.toUpperCase().equals("Prestador".toUpperCase())) {
		        authorities.add(prestadorAuthority);
		    }
		    else if (role.toUpperCase().equals("Consumidor".toUpperCase())) {
		        authorities.add(consumidorAuthority);
		    }
		    else if (role.toUpperCase().equals("Admin".toUpperCase())) {
		    	authorities.add(prestadorAuthority);
	            authorities.add(consumidorAuthority);
	            authorities.add(adminAuthority);
	        }
	    }
	    UserDetails user = new User(usuario.getCorreoElectronico(), usuario.getContrasenia(), true, true, true, true, authorities);
	    
	    return user;
    }
}

