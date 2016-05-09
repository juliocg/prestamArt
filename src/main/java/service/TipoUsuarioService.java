package service;

import java.util.List;

import dao.TipoUsuarioDAO;
import dao.UsuarioDAO;
import form.TipoUsuarioForm;
import model.TipoUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class TipoUsuarioService {
	
	private TipoUsuarioDAO tipoUsuarioDAO;
	
	public TipoUsuarioService() {}
	
	@Autowired
	public TipoUsuarioService(TipoUsuarioDAO tipoUsuarioDAO) {
		this.tipoUsuarioDAO = tipoUsuarioDAO;
	}
    
    @Transactional
    public void addTipoUsuario(TipoUsuarioForm tipoUsuarioForm) {
    	TipoUsuario tipoUsuario = new TipoUsuario();
    	tipoUsuario.setNombreTipoUsuario(tipoUsuarioForm.getNombreTipoUsuario());
    	tipoUsuario.setElegible(Boolean.parseBoolean(tipoUsuarioForm.getElegible()));
    	
    	tipoUsuarioDAO.save(tipoUsuario);
    }
    
    @Transactional
    public void updateTipoUsuario(TipoUsuarioForm tipoUsuarioForm) {
    	TipoUsuario tipoUsuario = new TipoUsuario();
    	tipoUsuario.setTipoUsuarioId(Integer.parseInt(tipoUsuarioForm.getTipoUsuarioId()));
    	tipoUsuario.setNombreTipoUsuario(tipoUsuarioForm.getNombreTipoUsuario());
    	tipoUsuario.setElegible(Boolean.parseBoolean(tipoUsuarioForm.getElegible()));
    	
    	tipoUsuarioDAO.update(tipoUsuario);
    }
    
    @Transactional
    public void removeTipoUsuario(String id) {
    	TipoUsuario tipoUsuario = null;
    	try {
			Integer tipoUsuarioId = Integer.parseInt(id);
			tipoUsuario = tipoUsuarioDAO.findById(tipoUsuarioId);
			tipoUsuarioDAO.delete(tipoUsuario);
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
		}
    }
    
    @Transactional
    public TipoUsuario getTipoUsuarioById(String id) {
    	TipoUsuario tipoUsuario = null;
    	try {
			Integer tipoUsuarioId = Integer.parseInt(id);
			tipoUsuario = tipoUsuarioDAO.findById(tipoUsuarioId);
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
		}
    	
        return tipoUsuario;
    }
    
    
    
    @Transactional
    public void addTipoUsuario(TipoUsuario tipoUsuario) {
    	tipoUsuarioDAO.save(tipoUsuario);
    }
    
    @Transactional
    public void updateTipoUsuario(TipoUsuario tipoUsuario) {
    	tipoUsuarioDAO.update(tipoUsuario);
    }
    
    @Transactional
    public void removeTipoUsuario(Integer id) {
    	TipoUsuario tipoUsuario = tipoUsuarioDAO.findById(id);
    	tipoUsuarioDAO.delete(tipoUsuario);
    }
    
    @Transactional
    public TipoUsuario getTipoUsuarioById(Integer id) {
    	TipoUsuario tipoUsuario = tipoUsuarioDAO.findById(id);
    	
        return tipoUsuario;
    }
    
    @Transactional
    public List<TipoUsuario> getTiposUsuarioByAtributo(String nombreAtributo, Object valorAtributo) {
        return (List<TipoUsuario>)tipoUsuarioDAO.findByProperty(nombreAtributo, valorAtributo);
	}
    
    @Transactional
    public List<TipoUsuario> getTiposUsuario() {
        return (List<TipoUsuario>)tipoUsuarioDAO.findAll();
    }
}
