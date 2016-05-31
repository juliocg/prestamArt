package validator;

import model.SolicitudPrestamoObjeto;
import model.Objeto;
import model.TipoUsuario;
import model.Usuario;
import service.SolicitudPrestamoObjetoService;
import service.ObjetoService;
import service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class SolicitudPrestamoObjetoValidator implements Validator {

	@Autowired
	private SolicitudPrestamoObjetoService solicitudPrestamoObjetoService;
	@Autowired
	private ObjetoService objetoService;
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Objeto.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
		SolicitudPrestamoObjeto solicitudPrestamoObjeto = (SolicitudPrestamoObjeto) target;
		
		Objeto objeto = solicitudPrestamoObjeto.getObjeto();
		if (objeto.getObjetoId() == null) {
			errors.rejectValue("objeto", "solicitudPrestamoObjeto.objeto.invalid");
		}
		
		Usuario consumidor = solicitudPrestamoObjeto.getConsumidor();
		if (consumidor.getUsuarioId() == null) {
			errors.rejectValue("consumidor", "solicitudPrestamoObjeto.consumidor.invalid");
		}
	}
}
