package service;

import java.util.List;

import dao.TipoObjetoDAO;
import model.TipoObjeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoObjetoService {
	
	@Autowired
	private TipoObjetoDAO tipoObjetoDAO;

	@Transactional
    public void addTipoObjeto(TipoObjeto tipoObjeto) {    	
		tipoObjetoDAO.save(tipoObjeto);
    }
    
    @Transactional
    public void updateTipoObjeto(TipoObjeto tipoObjeto) {
    	tipoObjetoDAO.update(tipoObjeto);
    }
    
    @Transactional
    public void removeTipoObjeto(Integer id) {
    	TipoObjeto tipoObjeto = tipoObjetoDAO.findById(id);
    	tipoObjetoDAO.delete(tipoObjeto);
    }
    
    @Transactional
    public TipoObjeto getTipoObjetoById(Integer id) {
    	TipoObjeto tipoObjeto = tipoObjetoDAO.findById(id);
    	
        return tipoObjeto;
    }
    
    @Transactional
    public List<TipoObjeto> getTiposObjetoByAtributo(String nombreAtributo, Object valorAtributo) {
        return tipoObjetoDAO.findByProperty(nombreAtributo, valorAtributo);
	}

    @Transactional
    public List<TipoObjeto> getTiposObjeto() {
        return tipoObjetoDAO.findAll();
	}
}
