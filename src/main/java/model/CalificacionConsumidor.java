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
 * CalificacionConsumidor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "calificacion_consumidor", catalog = "prestamart")
public class CalificacionConsumidor implements java.io.Serializable {

	// Fields

	private Integer calificacionConsumidorId;
	private Usuario usuarioByPrestadorId;
	private Usuario usuarioByConsumidorId;
	private Integer calificacionConsumidor;
	private String reseniaConsumidor;

	// Constructors

	/** default constructor */
	public CalificacionConsumidor() {
	}

	/** minimal constructor */
	public CalificacionConsumidor(Usuario usuarioByPrestadorId,
			Usuario usuarioByConsumidorId, Integer calificacionConsumidor) {
		this.usuarioByPrestadorId = usuarioByPrestadorId;
		this.usuarioByConsumidorId = usuarioByConsumidorId;
		this.calificacionConsumidor = calificacionConsumidor;
	}

	/** full constructor */
	public CalificacionConsumidor(Usuario usuarioByPrestadorId,
			Usuario usuarioByConsumidorId, Integer calificacionConsumidor,
			String reseniaConsumidor) {
		this.usuarioByPrestadorId = usuarioByPrestadorId;
		this.usuarioByConsumidorId = usuarioByConsumidorId;
		this.calificacionConsumidor = calificacionConsumidor;
		this.reseniaConsumidor = reseniaConsumidor;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "calificacion_consumidor_id", unique = true, nullable = false)
	public Integer getCalificacionConsumidorId() {
		return this.calificacionConsumidorId;
	}

	public void setCalificacionConsumidorId(Integer calificacionConsumidorId) {
		this.calificacionConsumidorId = calificacionConsumidorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prestador_id", nullable = false)
	public Usuario getUsuarioByPrestadorId() {
		return this.usuarioByPrestadorId;
	}

	public void setUsuarioByPrestadorId(Usuario usuarioByPrestadorId) {
		this.usuarioByPrestadorId = usuarioByPrestadorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "consumidor_id", nullable = false)
	public Usuario getUsuarioByConsumidorId() {
		return this.usuarioByConsumidorId;
	}

	public void setUsuarioByConsumidorId(Usuario usuarioByConsumidorId) {
		this.usuarioByConsumidorId = usuarioByConsumidorId;
	}

	@Column(name = "calificacion_consumidor", nullable = false)
	public Integer getCalificacionConsumidor() {
		return this.calificacionConsumidor;
	}

	public void setCalificacionConsumidor(Integer calificacionConsumidor) {
		this.calificacionConsumidor = calificacionConsumidor;
	}

	@Column(name = "resenia_consumidor", length = 2047)
	public String getReseniaConsumidor() {
		return this.reseniaConsumidor;
	}

	public void setReseniaConsumidor(String reseniaConsumidor) {
		this.reseniaConsumidor = reseniaConsumidor;
	}

}