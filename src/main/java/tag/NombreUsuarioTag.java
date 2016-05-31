package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import service.UsuarioService;

public class NombreUsuarioTag extends TagSupport {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
    public int doStartTag() throws JspException {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String name = authentication.getName();
	    System.out.println(name);
	    
	    Usuario prestador = usuarioService.getUsuarioByCorreoElectronicoAndActivo(name, true);
	    
	    String nombre = prestador.getNombre();
	    String apellidos = prestador.getApellidos().isEmpty() ? "" : " " + prestador.getApellidos();
         
        try {
            //Get the writer object for output.
            JspWriter out = pageContext.getOut();

            out.println("<a href=\"\\usuario\\consulta\\"+prestador.getUsuarioId()+"\">"+nombre+apellidos+"</a>");
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

}
