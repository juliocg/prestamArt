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
	private Usuario consumidor;
	private Objeto objeto;
	private String mensajeSolicitudPrestamo;
	private String mensajeRespuesta;
	private Boolean solicitudAceptada;
	private Boolean envioPrestamoObjetoPrestador;
	private Boolean envioPrestamoObjetoRecibidoConsumidor;
	private Boolean devolucionObjetoConsumidor;
	private Boolean devolucionObjetoRecibidaPrestador;
	private Set<RespuestaSolicitudPrestamo> respuestaSolicitudPrestamos = new HashSet<RespuestaSolicitudPrestamo>(0);

	// Constructors

	/** default constructor */
	public SolicitudPrestamoObjeto() {
	}

	/** minimal constructor */
	public SolicitudPrestamoObjeto(Usuario consumidor, Objeto objeto,
			String mensajeSolicitudPrestamo) {
		this.consumidor = consumidor;
		this.objeto = objeto;
		this.mensajeSolicitudPrestamo = mensajeSolicitudPrestamo;
	}

	/** full constructor */
	public SolicitudPrestamoObjeto(Usuario consumidor, Objeto objeto,
			String mensajeSolicitudPrestamo, String mensajeRespuesta,
			Boolean solicitudAceptada,
			Boolean envioPrestamoObjetoPrestador,
			Boolean envioPrestamoObjetoRecibidoConsumidor,
			Boolean devolucionObjetoConsumidor,
			Boolean devolucionObjetoRecibidaPrestador,
			Set<RespuestaSolicitudPrestamo> respuestaSolicitudPrestamos) {
		this.consumidor = consumidor;
		this.objeto = objeto;
		this.mensajeSolicitudPrestamo = mensajeSolicitudPrestamo;
		this.mensajeRespuesta = mensajeRespuesta;
		this.solicitudAceptada = solicitudAceptada;
		this.envioPrestamoObjetoPrestador = envioPrestamoObjetoPrestador;
		this.envioPrestamoObjetoRecibidoConsumidor = envioPrestamoObjetoRecibidoConsumidor;
		this.devolucionObjetoConsumidor = devolucionObjetoConsumidor;
		this.devolucionObjetoRecibidaPrestador = devolucionObjetoRecibidaPrestador;
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
	public Usuario getConsumidor() {
		return this.consumidor;
	}

	public void setConsumidor(Usuario consumidor) {
		this.consumidor = consumidor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "objeto_id", nullable = false)
	public Objeto getObjeto() {
		return this.objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}

	@Column(name = "mensaje_solicitud_prestamo", nullable = false, length = 65535)
	public String getMensajeSolicitudPrestamo() {
		return this.mensajeSolicitudPrestamo;
	}

	public void setMensajeSolicitudPrestamo(String mensajeSolicitudPrestamo) {
		this.mensajeSolicitudPrestamo = mensajeSolicitudPrestamo;
	}
	
	@Column(name = "mensaje_respuesta", length = 65535)
	public String getMensajeRespuesta() {
		return this.mensajeRespuesta;
	}

	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}
	
	@Column(name = "solicitud_aceptada")
	public Boolean getSolicitudAceptada() {
		return this.solicitudAceptada;
	}

	public void setSolicitudAceptada(Boolean solicitudAceptada) {
		this.solicitudAceptada = solicitudAceptada;
	}

	@Column(name = "envio_prestamo_objeto_prestador")
	public Boolean getEnvioPrestamoObjetoPrestador() {
		return this.envioPrestamoObjetoPrestador;
	}

	public void setEnvioPrestamoObjetoPrestador(
			Boolean envioPrestamoObjetoPrestador) {
		this.envioPrestamoObjetoPrestador = envioPrestamoObjetoPrestador;
	}

	@Column(name = "envio_prestamo_objeto_recibido_consumidor")
	public Boolean getEnvioPrestamoObjetoRecibidoConsumidor() {
		return this.envioPrestamoObjetoRecibidoConsumidor;
	}

	public void setEnvioPrestamoObjetoRecibidoConsumidor(
			Boolean envioPrestamoObjetoRecibidoConsumidor) {
		this.envioPrestamoObjetoRecibidoConsumidor = envioPrestamoObjetoRecibidoConsumidor;
	}

	@Column(name = "devolucion_objeto_consumidor")
	public Boolean getDevolucionObjetoConsumidor() {
		return this.devolucionObjetoConsumidor;
	}

	public void setDevolucionObjetoConsumidor(Boolean devolucionObjetoConsumidor) {
		this.devolucionObjetoConsumidor = devolucionObjetoConsumidor;
	}

	@Column(name = "devolucion_objeto_recibida_prestador")
	public Boolean getDevolucionObjetoRecibidaPrestador() {
		return this.devolucionObjetoRecibidaPrestador;
	}

	public void setDevolucionObjetoRecibidaPrestador(
			Boolean devolucionObjetoRecibidaPrestador) {
		this.devolucionObjetoRecibidaPrestador = devolucionObjetoRecibidaPrestador;
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