package service;

import java.util.List;

import dao.ObjetoDAO;
import model.Objeto;
import model.TipoObjeto;
import model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ObjetoService {
	
	@Autowired
	private ObjetoDAO objetoDAO;

	@Transactional
    public void addObjeto(Objeto objeto) {    	
    	objetoDAO.save(objeto);
    }
    
    @Transactional
    public void updateObjeto(Objeto objeto) {
    	objetoDAO.update(objeto);
    }
    
    @Transactional
    public void removeObjeto(Integer id) {
    	Objeto objeto = objetoDAO.findById(id);
    	objetoDAO.delete(objeto);
    }
    
    @Transactional
    public Objeto getObjetoById(Integer id) {
    	Objeto objeto = objetoDAO.findById(id);
    	
        return objeto;
    }
    
    @Transactional
    public List<Objeto> getObjetosByAtributo(String nombreAtributo, Object valorAtributo) {
        return objetoDAO.findByProperty(nombreAtributo, valorAtributo);
	}
    
    @Transactional
    public List<Objeto> getObjetosByTipoObjeto(TipoObjeto tipoObjeto) {
    	return objetoDAO.findByTipoObjeto(tipoObjeto);
    }
    
    @Transactional
    public List<Objeto> getObjetosByPrestador(Usuario prestador) {
    	return objetoDAO.findByPrestador(prestador);
    }
    
    @Transactional
    public List<Objeto> getObjetos() {
        return objetoDAO.findAll();
	}
}
