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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TipoObjeto entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tipo_objeto", catalog = "prestamart")
public class TipoObjeto implements java.io.Serializable {

	// Fields

	private Integer tipoObjetoId;
	private String nombreTipoObjeto;
	private Set<Objeto> objetos = new HashSet<Objeto>(0);

	// Constructors

	/** default constructor */
	public TipoObjeto() {
	}

	/** minimal constructor */
	public TipoObjeto(String nombreTipoObjeto) {
		this.nombreTipoObjeto = nombreTipoObjeto;
	}

	/** full constructor */
	public TipoObjeto(String nombreTipoObjeto, Set<Objeto> objetos) {
		this.nombreTipoObjeto = nombreTipoObjeto;
		this.objetos = objetos;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "tipo_objeto_id", unique = true, nullable = false)
	public Integer getTipoObjetoId() {
		return this.tipoObjetoId;
	}

	public void setTipoObjetoId(Integer tipoObjetoId) {
		this.tipoObjetoId = tipoObjetoId;
	}

	@Column(name = "nombre_tipo_objeto", nullable = false)
	public String getNombreTipoObjeto() {
		return this.nombreTipoObjeto;
	}

	public void setNombreTipoObjeto(String nombreTipoObjeto) {
		this.nombreTipoObjeto = nombreTipoObjeto;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tipoObjeto")
	public Set<Objeto> getObjetos() {
		return this.objetos;
	}

	public void setObjetos(Set<Objeto> objetos) {
		this.objetos = objetos;
	}
	
	public String toString() {
		return nombreTipoObjeto;
	}

}