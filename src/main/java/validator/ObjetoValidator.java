package validator;

import java.util.Set;

import model.ImagenObjeto;
import model.TipoObjeto;
import model.Objeto;
import service.ObjetoService;
import service.TipoObjetoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ObjetoValidator implements Validator {
	
	@Autowired
	private ObjetoService objetoService;
	@Autowired
	private TipoObjetoService tipoObjetoService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Objeto.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
		Objeto objeto = (Objeto) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombreObjeto", "objeto.nombreObjeto.required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descripcion", "objeto.descripcion.required");
		
		ValidationUtils.rejectIfEmpty(errors, "beneficioEsperado", "objeto.beneficioEsperado.required");
		
		ValidationUtils.rejectIfEmpty(errors, "periodoTiempoPrestamo", "objeto.periodoTiempoPrestamo.required");
		
		TipoObjeto tipoObjeto = objeto.getTipoObjeto();
		if (tipoObjeto.getTipoObjetoId() == null) {
			errors.rejectValue("tipoObjeto", "objeto.tipoObjeto.invalid");
		}
		
		/*Set<ImagenObjeto> imagenesObjeto = objeto.getImagenObjetos();
		if (imagenesObjeto.size() < 1) {
			errors.rejectValue("imagenObjetos", "objeto.imagenesObjeto.required");
		}*/
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombreImagen", "objeto.nombreImagen.required");
		
	}

}
