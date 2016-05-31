package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

/**
 * TipoUsuario entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tipo_usuario", catalog = "prestamart")
public class TipoUsuario implements java.io.Serializable/*, GrantedAuthority*/ {

	// Fields

	private Integer tipoUsuarioId;
	private String nombreTipoUsuario;
	private Boolean elegible;
	private Set<Usuario> usuarios = new HashSet<Usuario>(0);
	/*private Set<Usuario> users = new HashSet<Usuario>(0);*/

	// Constructors

	/** default constructor */
	public TipoUsuario() {
	}

	/** minimal constructor */
	public TipoUsuario(String nombreTipoUsuario, Boolean elegible) {
		this.nombreTipoUsuario = nombreTipoUsuario;
		this.elegible = elegible;
	}

	/** full constructor */
	public TipoUsuario(String nombreTipoUsuario, Boolean elegible,
			Set<Usuario> usuarios) {
		this.nombreTipoUsuario = nombreTipoUsuario;
		this.elegible = elegible;
		this.usuarios = usuarios;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "tipo_usuario_id", unique = true, nullable = false)
	public Integer getTipoUsuarioId() {
		return this.tipoUsuarioId;
	}

	public void setTipoUsuarioId(Integer tipoUsuarioId) {
		this.tipoUsuarioId = tipoUsuarioId;
	}

	@Column(name = "nombre_tipo_usuario", nullable = false)
	public String getNombreTipoUsuario() {
		return this.nombreTipoUsuario;
	}

	public void setNombreTipoUsuario(String nombreTipoUsuario) {
		this.nombreTipoUsuario = nombreTipoUsuario;
	}

	@Column(name = "elegible", nullable = false)
	public Boolean getElegible() {
		return this.elegible;
	}

	public void setElegible(Boolean elegible) {
		this.elegible = elegible;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tipoUsuario")
	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	/*@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tiposUsuario")
	public Set<Usuario> getUsers() {
		return this.users;
	}

	public void setUsers(Set<Usuario> users) {
		this.users = users;
	}*/
	
	/*public String getAuthority() {
        return this.nombreTipoUsuario;
    }*/
	
	public String toString() {
		return nombreTipoUsuario;
	}

}