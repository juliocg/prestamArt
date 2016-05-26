package validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import form.UsuarioForm;
import model.TipoUsuario;
import model.Usuario;
import service.UsuarioService;
import service.TipoUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UsuarioValidator implements Validator {
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private TipoUsuarioService tipoUsuarioService;
	
	public boolean isValidEmailAddress(String emailAddress){ 
        String expression = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = emailAddress; 
        Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE); 
        Matcher matcher = pattern.matcher(inputStr); 
        return matcher.matches(); 
    }

	@Override
	public boolean supports( Class<?> clazz ) {
		return Usuario.class.isAssignableFrom( clazz );
	}

	/*@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "correoElectronico", "correoElectronico.required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contrasenia", "contrasenia.required");
		
		ValidationUtils.rejectIfEmpty(errors, "nombre", "nombre.required");
		
		UsuarioForm usuarioForm = (UsuarioForm) target;
		TipoUsuario tipoUsuario = tipoUsuarioService.getTipoUsuarioById(usuarioForm.getTipoUsuarioId());
		//TipoUsuario tipoUsuario = null;
		//Integer tipoUsuarioId = -1;
		//try {
			//tipoUsuarioId = Integer.parseInt(usuarioForm.getTipoUsuarioId());
			//tipoUsuario = tipoUsuarioService.getTipoUsuarioById(tipoUsuarioId);
		//}
		//catch(NumberFormatException e) {
			//e.printStackTrace();
		//}
		//if (tipoUsuario == null) {
			//errors.rejectValue("tipoUsuarioId", "tipoUsuario.invalid");
		//}
		
		String telefono = usuarioForm.getTelefono();
		Pattern pattern = Pattern.compile("[^ 0-9+-]+");
		Matcher matcher = pattern.matcher(telefono);
		if (matcher.find()) {
			errors.rejectValue("telefono", "telefono.invalid");
		}
		else {
			telefono = telefono.replaceAll("[^0-9]+", "");
			if (telefono.length() < 8) {
				errors.rejectValue("telefono", "telefono.invalid");
			}
		}
	}*/
	
	@Override
	public void validate(Object target, Errors errors) {
		
		Usuario usuario = (Usuario) target;
		
		String correoElectronico = usuario.getCorreoElectronico();
		if (usuario.getUsuarioId() == null && correoElectronico != null && !correoElectronico.equals("")) {
			Usuario usuarioPersistente = usuarioService.getUsuarioByCorreoElectronicoAndActivo(correoElectronico, true);
			if (usuarioPersistente != null) {
			    errors.rejectValue("correoElectronico", "correoElectronico.alreadyExists");
			}
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "correoElectronico", "correoElectronico.required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contrasenia", "contrasenia.required");
		
		ValidationUtils.rejectIfEmpty(errors, "nombre", "nombre.required");
		
		TipoUsuario tipoUsuario = usuario.getTipoUsuario();
		if (tipoUsuario.getTipoUsuarioId() == null) {
			errors.rejectValue("tipoUsuario", "tipoUsuario.invalid");
		}
		
		String telefono = usuario.getTelefono();
		if (telefono == null) {
			errors.rejectValue("telefono", "tipoUsuario.invalid");
		}
		else {
			Pattern pattern = Pattern.compile("[^ 0-9+-]+");
			Matcher matcher = pattern.matcher(telefono);
			if (matcher.find()) {
				errors.rejectValue("telefono", "telefono.invalid");
			}
			else {
				telefono = telefono.replaceAll("[^0-9]+", "");
				if (telefono.length() < 8) {
					errors.rejectValue("telefono", "telefono.invalid");
				}
			}
		}
		
        ValidationUtils.rejectIfEmpty(errors, "telefono", "telefono.required");
	}
}
