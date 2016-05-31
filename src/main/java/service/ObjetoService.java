package service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import dao.ObjetoDAO;
import model.Objeto;
import model.TipoObjeto;
import model.Usuario;
import utilities.Box;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ObjetoService {
	
	@Autowired
	private ObjetoDAO objetoDAO;
	@Autowired
    private ServletContext servletContext;

	@Transactional
    public void addObjeto(Objeto objeto) {
		objetoDAO.save(objeto);
	}
	
	@Transactional
    public void addObjeto(Objeto objeto, MultipartFile imagen) {
		System.out.println( "Nombre del objeto:" + objeto.getNombreObjeto() );
		System.out.println( "Descripcion del objeto:" + objeto.getDescripcion() );
		System.out.println( "File:" + imagen.getName() );
		System.out.println( "ContentType:" + imagen.getContentType() );
		
		System.out.println("Context Path: " + servletContext.getContextPath());
		String contextPath = servletContext.getRealPath(File.separator);
		System.out.println("Real Path: " + contextPath);
		
		String directorioBaseImagen = contextPath+File.separator+"resources"+File.separator+"images";
		System.out.println("Directorio base imagen: " + directorioBaseImagen);
		String directorioImagen = File.separator+"objetos";
		System.out.println("Directorio imagen: " + directorioImagen);
		System.out.println("Real Path: " + contextPath);
		File directorio = new File(directorioBaseImagen, directorioImagen);
        directorio.mkdirs();
        
        String extension = imagen.getOriginalFilename().split("[.]", 2)[1];
        String nombreImagen = "objeto"+"."+extension;
        
        objeto.setNombreImagen(nombreImagen);
        objetoDAO.save(objeto);
		try {
			imagen.transferTo(new File(directorio, objeto.getObjetoId()+"-"+nombreImagen));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	/*@Transactional
	public void savedocumentfile( Document document, MultipartFile file ) {
	System.out.println( "Name:" + document.getName() );
	System.out.println( "Desc:" + document.getDescription() );
	System.out.println( "File:" + file.getName() );
	System.out.println( "ContentType:" + file.getContentType() );
	try {
	Blob blob = Hibernate.createBlob( file.getInputStream() );
	document.setFilename( file.getOriginalFilename() );
	document.setContent( blob );
	document.setContentType( file.getContentType() );
	}
	catch ( IOException e ) {
	e.printStackTrace();
	}
	documentDAO.save( document );
	}*/
    
    @Transactional
    public void updateObjeto(Objeto objeto) {
    	objetoDAO.update(objeto);
    }
    
    @Transactional
    public void updateObjeto(Objeto objeto, MultipartFile imagen) {
		System.out.println( "Nombre del objeto:" + objeto.getNombreObjeto() );
		System.out.println( "Descripcion del objeto:" + objeto.getDescripcion() );
		System.out.println( "File:" + imagen.getName() );
		System.out.println( "ContentType:" + imagen.getContentType() );
		
		System.out.println("Context Path: " + servletContext.getContextPath());
		String contextPath = servletContext.getRealPath(File.separator);
		System.out.println("Real Path: " + contextPath);
		
		String directorioBaseImagen = contextPath+File.separator+"resources"+File.separator+"images";
		System.out.println("Directorio base imagen: " + directorioBaseImagen);
		String directorioImagen = File.separator+"objetos";
		System.out.println("Directorio imagen: " + directorioImagen);
		System.out.println("Real Path: " + contextPath);
		File directorio = new File(directorioBaseImagen, directorioImagen);
        directorio.mkdirs();
        
        String extension = imagen.getOriginalFilename().split("[.]", 2)[1];
        String nombreImagen = "objeto"+"."+extension;
        
        objeto.setNombreImagen(nombreImagen);
        objetoDAO.update(objeto);
		try {
			imagen.transferTo(new File(directorio, objeto.getObjetoId()+"-"+nombreImagen));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @Transactional
    public void removeObjeto(Objeto objeto) {
    	try {
    	    objetoDAO.delete(objeto);
    	} catch(NumberFormatException e) {
			e.printStackTrace();
		}
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
    	List<Objeto> objetos = objetoDAO.findByPrestador(prestador);
    	for (Objeto objeto : objetos) {
    		// Para inicializar las solicitudes de prestamo de cada objeto
			objeto.getSolicitudPrestamoObjetos().size();
		}
    	
    	return objetos;
    }
    
    @Transactional
    public List<Objeto> getObjetos() {
        return objetoDAO.findAll();
	}
    
    @Transactional
    public List<Objeto> getObjetosByTexto(String texto) {
    	
    	texto = texto.toLowerCase();
    	System.out.println("Despues de toLowerCase "+texto);
    	texto = replaceAccentedVowel(texto);
    	System.out.println("Despues de replaceAccentedVowel "+texto);
    	texto = replaceDiscardedWord(texto);
    	System.out.println("Despues de replaceDiscardedWord "+texto);
        List<String> palabrasTexto = splitByRegexp(texto);
        System.out.println("Despues de splitByRegexp "+palabrasTexto.toString());
        palabrasTexto = removeInvalidElement(palabrasTexto);
        
        List<Objeto> objetos = null;
        
        String condicion = "";
        String condicionPalabraString;
        
        //////////////////////////////////////////////////
        condicionPalabraString = "";
        for (String palabra : palabrasTexto) {
            if (condicionPalabraString != "") {
                condicionPalabraString += " AND ";
            }
            condicionPalabraString += "UPPER(objeto.nombreObjeto) LIKE UPPER('%" + replaceSpecialCharLIKE(palabra) + "%') ESCAPE '!'";
        }

        if (condicionPalabraString != "") {
            if (condicion != "") {
            	condicion += " OR ";
            }
            condicion += "(" + condicionPalabraString + ")";
        }
        //////////////////////////////////////////////////

		//////////////////////////////////////////////////
		condicionPalabraString = "";
		for (String palabra : palabrasTexto) {
			if (condicionPalabraString != "") {
			    condicionPalabraString += " AND ";
			}
			condicionPalabraString += "UPPER(objeto.descripcion) LIKE UPPER('%" + replaceSpecialCharLIKE(palabra) + "%') ESCAPE '!'";
		}
		
		if (condicionPalabraString != "") {
			if (condicion != "") {
			    condicion += " OR ";
			}
		    condicion += "(" + condicionPalabraString + ")";
		}
		//////////////////////////////////////////////////
        
        if (condicion != "") {
        	condicion = " WHERE " + condicion + "";
        	
        	System.out.println(condicion);
        	
        	objetos = objetoDAO.findByQuery("SELECT objeto FROM Objeto AS objeto" + condicion + "");
        }
    	
        return objetos;
	}
    
    public String replaceSpecialCharLIKE(String cadena) {
    	Map<String, String> specialsCharLIKE = new HashMap<String, String>();
        ////specialsCharLIKE.put("\\\\\\", "[\\\\]");
    	//specialsCharLIKE.put("\\\\\"", "[\"]");
    	//specialsCharLIKE.put("\\\\\'", "[']");
    	//specialsCharLIKE.put("\\\\_", "[_]");
    	//specialsCharLIKE.put("\\\\%", "[%]");
    	specialsCharLIKE.put("!!", "[!]");
    	specialsCharLIKE.put("!\"", "[\"]");
    	specialsCharLIKE.put("''", "[']");
    	specialsCharLIKE.put("\\\\\\\\", "[\\\\]");
    	specialsCharLIKE.put("!_", "[_]");
    	specialsCharLIKE.put("!%", "[%]");
    	
        
        for (Entry<String, String> specialCharLIKE : specialsCharLIKE.entrySet()){
    		String clave = specialCharLIKE.getKey();
    		String valor = specialCharLIKE.getValue();
    		
    		cadena = cadena.replaceAll(valor, clave);
    	}
    	
    	return cadena;
    }
    
    public String replaceAccentedVowel(String cadena) {
    	
    	Map<String, String> accentedVowels = new HashMap<String, String>();
    	accentedVowels.put("a", "(á|à|â|ä|Á|À|Â|Ä)");
    	accentedVowels.put("e", "(é|è|ê|ë|É|È|Ê|Ë)");
    	accentedVowels.put("i", "(í|ì|î|ï|Í|Ì|Î|Ï)");
    	accentedVowels.put("o", "(ó|ò|ô|ö|Ó|Ò|Ô|Ö)");
    	accentedVowels.put("u", "(ú|ù|û|ü|Ú|Ù|Û|Ü)");
    	
    	for (Entry<String, String> accentedVowel : accentedVowels.entrySet()){
    		String clave = accentedVowel.getKey();
    		String valor = accentedVowel.getValue();
    		
    		cadena = cadena.replaceAll(valor, clave);
    	}
    	
    	return cadena;
    }
    
    public String replaceDiscardedWord(String cadena) {
    	
    	String discardedWord = "de|el|la|los|las|un|una|con|en|lo|a|desde|hasta|entre|hacia|para|por|segun|sin|sobre|tras|y|o|e|of|the|to|and|for|on|in";
        String regexDiscardedWord = "(^(" + discardedWord + ")([\\s.,;_\\-]+|$))|(([\\s.,;_\\-]+(" + discardedWord + "))+([\\s.,;_\\-]+|$))";
        
        System.out.println(regexDiscardedWord);
    	
    	cadena = cadena.replaceAll(regexDiscardedWord, " ");
    	
    	return cadena;
    }
    
    public List<String> splitByRegexp(String cadena) {
    	
    	/*List<String> matches = new ArrayList<String>();
    	
    	Pattern p = Pattern.compile("[\\s.,;_\\-]+");
    	Matcher m = p.matcher(cadena);
    	while (m.find()) {
            matches.add(m.group());
    	}
    	
    	return matches;*/
    	
    	return Arrays.asList(cadena.split("[\\s.,;_\\-]+"));
    }
    
    public List<String> removeInvalidElement(List<String> cadenas) {
    	
    	List<String> lista = new ArrayList<String>();
        for (String cadena : cadenas) {
            if (cadena != "" && cadena != null && !cadena.isEmpty()) {
            	lista.add(cadena);
            }
        }
        
        return lista;
    	/*System.out.println(cadenas.size());
    	if (cadenas.size() > 0) {
    	    cadenas.removeAll(Arrays.asList("", null));
    	}
    	
    	return cadenas;*/
    }
}
