package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CalificacionObjeto entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "calificacion_objeto", catalog = "prestamart")
public class CalificacionObjeto implements java.io.Serializable {

	// Fields

	private Integer calificacionObjetoId;
	private Usuario usuario;
	private Objeto objeto;
	private Integer calificacionObjeto;
	private String reseniaObjeto;

	// Constructors

	/** default constructor */
	public CalificacionObjeto() {
	}

	/** minimal constructor */
	public CalificacionObjeto(Usuario usuario, Objeto objeto,
			Integer calificacionObjeto) {
		this.usuario = usuario;
		this.objeto = objeto;
		this.calificacionObjeto = calificacionObjeto;
	}

	/** full constructor */
	public CalificacionObjeto(Usuario usuario, Objeto objeto,
			Integer calificacionObjeto, String reseniaObjeto) {
		this.usuario = usuario;
		this.objeto = objeto;
		this.calificacionObjeto = calificacionObjeto;
		this.reseniaObjeto = reseniaObjeto;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "calificacion_objeto_id", unique = true, nullable = false)
	public Integer getCalificacionObjetoId() {
		return this.calificacionObjetoId;
	}

	public void setCalificacionObjetoId(Integer calificacionObjetoId) {
		this.calificacionObjetoId = calificacionObjetoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "consumidor_id", nullable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "objeto_id", nullable = false)
	public Objeto getObjeto() {
		return this.objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}

	@Column(name = "calificacion_objeto", nullable = false)
	public Integer getCalificacionObjeto() {
		return this.calificacionObjeto;
	}

	public void setCalificacionObjeto(Integer calificacionObjeto) {
		this.calificacionObjeto = calificacionObjeto;
	}

	@Column(name = "resenia_objeto", length = 2047)
	public String getReseniaObjeto() {
		return this.reseniaObjeto;
	}

	public void setReseniaObjeto(String reseniaObjeto) {
		this.reseniaObjeto = reseniaObjeto;
	}

}