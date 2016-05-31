package service;

import dao.SolicitudPrestamoObjetoDAO;
import model.SolicitudPrestamoObjeto;
import model.Objeto;
import model.Usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SolicitudPrestamoObjetoService {
	
	@Autowired
	private SolicitudPrestamoObjetoDAO solicitudPrestamoObjetoDAO;

	@Transactional
    public void addSolicitudPrestamoObjeto(SolicitudPrestamoObjeto solicitudPrestamoObjeto) {    	
		solicitudPrestamoObjetoDAO.save(solicitudPrestamoObjeto);
    }
    
    @Transactional
    public void updateSolicitudPrestamoObjeto(SolicitudPrestamoObjeto solicitudPrestamoObjeto) {
    	solicitudPrestamoObjetoDAO.update(solicitudPrestamoObjeto);
    }
    
    @Transactional
    public void removeSolicitudPrestamoObjeto(Integer id) {
    	SolicitudPrestamoObjeto solicitudPrestamoObjeto = solicitudPrestamoObjetoDAO.findById(id);
    	solicitudPrestamoObjetoDAO.delete(solicitudPrestamoObjeto);
    }
    
    @Transactional
    public SolicitudPrestamoObjeto getSolicitudPrestamoObjetoById(Integer id) {
    	SolicitudPrestamoObjeto solicitudPrestamoObjeto = solicitudPrestamoObjetoDAO.findById(id);
    	
        return solicitudPrestamoObjeto;
    }
    
    @Transactional
    public List<SolicitudPrestamoObjeto> getSolicitudesPrestamoObjetoByAtributo(String nombreAtributo, Object valorAtributo) {
        return solicitudPrestamoObjetoDAO.findByProperty(nombreAtributo, valorAtributo);
	}
    
    @Transactional
    public List<SolicitudPrestamoObjeto> getSolicitudesPrestamoObjetoByConsumidor(Usuario consumidor) {
    	List<SolicitudPrestamoObjeto> solicitudesPrestamoObjeto = solicitudPrestamoObjetoDAO.findByConsumidor(consumidor);
    	for (SolicitudPrestamoObjeto solicitudPrestamoObjeto : solicitudesPrestamoObjeto) {
    		solicitudPrestamoObjeto.getObjeto().getObjetoId();
		}
    	
    	return solicitudesPrestamoObjeto;
    }

    @Transactional
    public List<SolicitudPrestamoObjeto> getSolicitudesPrestamoObjeto() {
        return solicitudPrestamoObjetoDAO.findAll();
	}

}
