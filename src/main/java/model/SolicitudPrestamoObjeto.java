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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * SolicitudPrestamoObjeto entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "solicitud_prestamo_objeto", catalog = "prestamart")
public class SolicitudPrestamoObjeto implements java.io.Serializable {

	// Fields

	private Integer solicitudPrestamoObjetoId;
	private Usuario usuario;
	private Objeto objeto;
	private String mensajeSolicitudPrestamo;
	private Set<RespuestaSolicitudPrestamo> respuestaSolicitudPrestamos = new HashSet<RespuestaSolicitudPrestamo>(
			0);

	// Constructors

	/** default constructor */
	public SolicitudPrestamoObjeto() {
	}

	/** minimal constructor */
	public SolicitudPrestamoObjeto(Usuario usuario, Objeto objeto,
			String mensajeSolicitudPrestamo) {
		this.usuario = usuario;
		this.objeto = objeto;
		this.mensajeSolicitudPrestamo = mensajeSolicitudPrestamo;
	}

	/** full constructor */
	public SolicitudPrestamoObjeto(Usuario usuario, Objeto objeto,
			String mensajeSolicitudPrestamo,
			Set<RespuestaSolicitudPrestamo> respuestaSolicitudPrestamos) {
		this.usuario = usuario;
		this.objeto = objeto;
		this.mensajeSolicitudPrestamo = mensajeSolicitudPrestamo;
		this.respuestaSolicitudPrestamos = respuestaSolicitudPrestamos;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "solicitud_prestamo_objeto_id", unique = true, nullable = false)
	public Integer getSolicitudPrestamoObjetoId() {
		return this.solicitudPrestamoObjetoId;
	}

	public void setSolicitudPrestamoObjetoId(Integer solicitudPrestamoObjetoId) {
		this.solicitudPrestamoObjetoId = solicitudPrestamoObjetoId;
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

	@Column(name = "mensaje_solicitud_prestamo", nullable = false, length = 2047)
	public String getMensajeSolicitudPrestamo() {
		return this.mensajeSolicitudPrestamo;
	}

	public void setMensajeSolicitudPrestamo(String mensajeSolicitudPrestamo) {
		this.mensajeSolicitudPrestamo = mensajeSolicitudPrestamo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "solicitudPrestamoObjeto")
	public Set<RespuestaSolicitudPrestamo> getRespuestaSolicitudPrestamos() {
		return this.respuestaSolicitudPrestamos;
	}

	public void setRespuestaSolicitudPrestamos(
			Set<RespuestaSolicitudPrestamo> respuestaSolicitudPrestamos) {
		this.respuestaSolicitudPrestamos = respuestaSolicitudPrestamos;
	}

}