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
 * RespuestaSolicitudPrestamo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "respuesta_solicitud_prestamo", catalog = "prestamart")
public class RespuestaSolicitudPrestamo implements java.io.Serializable {

	// Fields

	private Integer respuestaSolicitudPrestamoId;
	private Usuario usuario;
	private SolicitudPrestamoObjeto solicitudPrestamoObjeto;
	private String mensajeRespuesta;
	private Integer estadoRespuesta;

	// Constructors

	/** default constructor */
	public RespuestaSolicitudPrestamo() {
	}

	/** minimal constructor */
	public RespuestaSolicitudPrestamo(Usuario usuario,
			SolicitudPrestamoObjeto solicitudPrestamoObjeto,
			Integer estadoRespuesta) {
		this.usuario = usuario;
		this.solicitudPrestamoObjeto = solicitudPrestamoObjeto;
		this.estadoRespuesta = estadoRespuesta;
	}

	/** full constructor */
	public RespuestaSolicitudPrestamo(Usuario usuario,
			SolicitudPrestamoObjeto solicitudPrestamoObjeto,
			String mensajeRespuesta, Integer estadoRespuesta) {
		this.usuario = usuario;
		this.solicitudPrestamoObjeto = solicitudPrestamoObjeto;
		this.mensajeRespuesta = mensajeRespuesta;
		this.estadoRespuesta = estadoRespuesta;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "respuesta_solicitud_prestamo_id", unique = true, nullable = false)
	public Integer getRespuestaSolicitudPrestamoId() {
		return this.respuestaSolicitudPrestamoId;
	}

	public void setRespuestaSolicitudPrestamoId(
			Integer respuestaSolicitudPrestamoId) {
		this.respuestaSolicitudPrestamoId = respuestaSolicitudPrestamoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prestador_id", nullable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "solicitud_prestamo_objeto_id", nullable = false)
	public SolicitudPrestamoObjeto getSolicitudPrestamoObjeto() {
		return this.solicitudPrestamoObjeto;
	}

	public void setSolicitudPrestamoObjeto(
			SolicitudPrestamoObjeto solicitudPrestamoObjeto) {
		this.solicitudPrestamoObjeto = solicitudPrestamoObjeto;
	}

	@Column(name = "mensaje_respuesta", length = 2047)
	public String getMensajeRespuesta() {
		return this.mensajeRespuesta;
	}

	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}

	@Column(name = "estado_respuesta", nullable = false)
	public Integer getEstadoRespuesta() {
		return this.estadoRespuesta;
	}

	public void setEstadoRespuesta(Integer estadoRespuesta) {
		this.estadoRespuesta = estadoRespuesta;
	}

}