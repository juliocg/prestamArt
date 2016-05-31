package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import model.Usuario;
import service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.tags.form.InputTag;
import org.springframework.web.servlet.tags.form.TagWriter;

public class BienvenidaTag extends TagSupport {
	
	/*@Autowired
	private UsuarioService usuarioService;*/
	
	@Override
    public int doStartTag() throws JspException {
		System.out.println("Desbe BienvenidaTag");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String name = authentication.getName();
	    System.out.println("correoElectronico = "+name);
	    
	 // Get the Spring application context
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
	    
	    UsuarioService usuarioService = context.getBean(UsuarioService.class);
	    
	    Usuario usuario = usuarioService.getUsuarioByCorreoElectronicoAndActivo(name, true);
	    
	    String nombre = usuario.getNombre();
	    String apellidos = usuario.getApellidos().isEmpty() ? "" : " " + usuario.getApellidos();
         
        try {
            //Get the writer object for output.
            JspWriter out = pageContext.getOut();

            out.println("<div style=\"padding:10px\">Bienvenido(a) <a href=\"/prestamArt/usuario/consulta/"+usuario.getUsuarioId()+"\">"+nombre+apellidos+"</a></div>");
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

}
