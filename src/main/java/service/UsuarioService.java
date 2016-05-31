package service;

import dao.UsuarioDAO;
//import form.UsuarioForm;
import model.Usuario;
import model.TipoUsuario;
import dao.TipoUsuarioDAO;
import service.TipoUsuarioService;
import validator.UsuarioValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UsuarioService implements UserDetailsService {
	
	private UsuarioDAO usuarioDAO;
	private TipoUsuarioService tipoUsuarioService;
	
	public UsuarioService() {}
	
	@Autowired
	public UsuarioService(UsuarioDAO usuarioDAO, TipoUsuarioService tipoUsuarioService) {
		this.usuarioDAO = usuarioDAO;
		this.tipoUsuarioService = tipoUsuarioService;
	}
    
    @Transactional
    public void removeUsuario(Usuario usuario) {
    	try {
			usuarioDAO.delete(usuario);
		} catch(NumberFormatException e) {
			e.printStackTrace();
		}
    }
    
    @Transactional
    public Usuario getUsuarioById(String id) {
    	Usuario usuario = null;
    	try {
			Integer usuarioId = Integer.parseInt(id);
			usuario = usuarioDAO.findById(usuarioId);
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
		}
    	
        return usuario;
    }

    
    
    @Transactional
    public void addUsuario(Usuario usuario) {    	
    	usuarioDAO.save(usuario);
    }
    
    @Transactional
    public void updateUsuario(Usuario usuario) {
    	usuarioDAO.update(usuario);
    }
    
    @Transactional
    public void removeUsuario(Integer id) {
    	Usuario usuario = usuarioDAO.findById(id);
    	usuarioDAO.delete(usuario);
    }
    
    @Transactional
    public Usuario getUsuarioById(Integer id) {
    	Usuario usuario = usuarioDAO.findById(id);
    	
        return usuario;
    }
    
    @Transactional
    public Usuario getUsuarioByCorreoElectronico(String correoElectronico) {
    	return usuarioDAO.findByCorreoElectronico(correoElectronico);
    }
    
    @Transactional
    public Usuario getUsuarioByCorreoElectronicoAndActivo(String correoElectronico, Boolean activo) {
    	return usuarioDAO.findByCorreoElectronicoAndActivo(correoElectronico, activo);
    }
    
    @Transactional
    public Usuario getUsuarioByCorreoElectronicoAndTipoUsuario(String correoElectronico, TipoUsuario tipoUsuario) {
    	return usuarioDAO.findByCorreoElectronicoAndTipoUsuario(correoElectronico, tipoUsuario);
    }
    
    @Transactional
    public List<Usuario> getUsuariosByAtributo(String nombreAtributo, Object valorAtributo) {
        return usuarioDAO.findByProperty(nombreAtributo, valorAtributo);
	}
    
    @Transactional
    public List<Usuario> getUsuarios() {
        return (List<Usuario>)usuarioDAO.findAll();
    }
    
    //@Transactional(readOnly=true)
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String correoElectronico) throws UsernameNotFoundException {
    	UserDetails user = null;
    	
    	System.out.println(correoElectronico);
	    Usuario usuario = usuarioDAO.findByCorreoElectronicoAndActivo(correoElectronico, true);
	    
	    //if (usuario.getActivo() == true) {
		    String correElectronico = null;
		    String contrasenia = null;
		    
		    Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
		     
		    SimpleGrantedAuthority prestadorAuthority = new SimpleGrantedAuthority("ROLE_PRESTADOR");
		    SimpleGrantedAuthority consumidorAuthority = new SimpleGrantedAuthority("ROLE_CONSUMIDOR");
		    SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
		    
		    if (usuario != null) {
		    	correElectronico = usuario.getCorreoElectronico();
			    contrasenia = usuario.getContrasenia();
			    TipoUsuario tipoUsuario = usuario.getTipoUsuario();
			    if (tipoUsuario != null) {
				    String role = tipoUsuario.getNombreTipoUsuario();
				    if (role.toUpperCase().equals("Admin".toUpperCase())) {
				    	authorities.add(prestadorAuthority);
			            authorities.add(consumidorAuthority);
			            authorities.add(adminAuthority);
			        }
				    else if (role.toUpperCase().equals("Prestador".toUpperCase())) {
				        authorities.add(prestadorAuthority);
				    }
				    else if (role.toUpperCase().equals("Consumidor".toUpperCase())) {
				        authorities.add(consumidorAuthority);
				    }
			    }
			    System.out.println(authorities.toString());
			    user = new User(correElectronico, contrasenia, true, true, true, true, authorities);
		    }
	    //}
	    
	    return user;
    }
}
