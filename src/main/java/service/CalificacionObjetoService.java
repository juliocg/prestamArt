package service;

import dao.CalificacionObjetoDAO;
import model.CalificacionObjeto;
import model.Objeto;
import model.Usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CalificacionObjetoService {
	
	@Autowired
	private CalificacionObjetoDAO calificacionObjetoDAO;

	@Transactional
    public void addCalificacionObjeto(CalificacionObjeto calificacionObjeto) {    	
		calificacionObjetoDAO.save(calificacionObjeto);
    }
    
    @Transactional
    public void updateCalificacionObjeto(CalificacionObjeto calificacionObjeto) {
    	calificacionObjetoDAO.update(calificacionObjeto);
    }
    
    @Transactional
    public void removeCalificacionObjeto(Integer id) {
    	CalificacionObjeto calificacionObjeto = calificacionObjetoDAO.findById(id);
    	calificacionObjetoDAO.delete(calificacionObjeto);
    }
    
    @Transactional
    public CalificacionObjeto getCalificacionObjetoById(Integer id) {
    	CalificacionObjeto calificacionObjeto = calificacionObjetoDAO.findById(id);
    	
        return calificacionObjeto;
    }
    
    @Transactional
    public List<CalificacionObjeto> getCalificacionObjetoByAtributo(String nombreAtributo, Object valorAtributo) {
        return calificacionObjetoDAO.findByProperty(nombreAtributo, valorAtributo);
	}
    
    @Transactional
    public List<CalificacionObjeto> getCalificacionesObjeto() {
        return calificacionObjetoDAO.findAll();
	}

}
