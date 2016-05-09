package form;

import java.util.HashSet;
import java.util.Set;

public class TipoUsuarioForm {
	private String tipoUsuarioId;
	private String nombreTipoUsuario;
	private String elegible;
	private Set<String> usuarios = new HashSet<String>(0);
	
	public String getTipoUsuarioId() {
		return tipoUsuarioId;
	}
	public void setTipoUsuarioId(String tipoUsuarioId) {
		this.tipoUsuarioId = tipoUsuarioId;
	}
	public String getNombreTipoUsuario() {
		return nombreTipoUsuario;
	}
	public void setNombreTipoUsuario(String nombreTipoUsuario) {
		this.nombreTipoUsuario = nombreTipoUsuario;
	}
	public String getElegible() {
		return elegible;
	}
	public void setElegible(String elegible) {
		this.elegible = elegible;
	}
	public Set<String> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Set<String> usuarios) {
		this.usuarios = usuarios;
	}
}
