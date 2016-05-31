package service;

import dao.CalificacionConsumidorDAO;
import model.CalificacionConsumidor;
import model.Usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CalificacionConsumidorService {
	
	@Autowired
	private CalificacionConsumidorDAO calificacionConsumidorDAO;
	
	@Transactional
    public void addCalificacionConsumidor(CalificacionConsumidor calificacionConsumidor) {    	
		calificacionConsumidorDAO.save(calificacionConsumidor);
    }
    
    @Transactional
    public void updateCalificacionConsumidor(CalificacionConsumidor calificacionConsumidor) {
    	calificacionConsumidorDAO.update(calificacionConsumidor);
    }
    
    @Transactional
    public void removeCalificacionConsumidor(Integer id) {
    	CalificacionConsumidor calificacionConsumidor = calificacionConsumidorDAO.findById(id);
    	calificacionConsumidorDAO.delete(calificacionConsumidor);
    }
    
    @Transactional
    public CalificacionConsumidor getCalificacionConsumidorById(Integer id) {
    	CalificacionConsumidor calificacionConsumidor = calificacionConsumidorDAO.findById(id);
    	
        return calificacionConsumidor;
    }
    
    @Transactional
    public List<CalificacionConsumidor> getCalificacionConsumidorByAtributo(String nombreAtributo, Object valorAtributo) {
        return calificacionConsumidorDAO.findByProperty(nombreAtributo, valorAtributo);
	}
    
    @Transactional
    public List<CalificacionConsumidor> getCalificacionConsumidor() {
        return calificacionConsumidorDAO.findAll();
	}

}
