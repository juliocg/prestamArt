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
 * Objeto entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "objeto", catalog = "prestamart")
public class Objeto implements java.io.Serializable {

	// Fields

	private Integer objetoId;
	private Usuario prestador;
	private TipoObjeto tipoObjeto;
	private String nombreObjeto;
	private String descripcion;
	private String beneficioEsperado;
	private String periodoTiempoPrestamo;
	private Boolean activo;
	private Set<ImagenObjeto> imagenObjetos = new HashSet<ImagenObjeto>(0);
	private Set<SolicitudPrestamoObjeto> solicitudPrestamoObjetos = new HashSet<SolicitudPrestamoObjeto>(0);
	private Set<PrestamoObjeto> prestamoObjetos = new HashSet<PrestamoObjeto>(0);
	/*private Set<CalificacionObjeto> calificacionObjetos = new HashSet<CalificacionObjeto>(0);*/

	// Constructors

	/** default constructor */
	public Objeto() {
	}

	/** minimal constructor */
	public Objeto(Usuario prestador, TipoObjeto tipoObjeto, String nombreObjeto,
			String descripcion, String beneficioEsperado,
			String periodoTiempoPrestamo, Boolean activo) {
		this.prestador = prestador;
		this.tipoObjeto = tipoObjeto;
		this.nombreObjeto = nombreObjeto;
		this.descripcion = descripcion;
		this.beneficioEsperado = beneficioEsperado;
		this.periodoTiempoPrestamo = periodoTiempoPrestamo;
		this.activo = activo;
	}

	/** full constructor */
	public Objeto(Usuario prestador, TipoObjeto tipoObjeto, String nombreObjeto,
			String descripcion, String beneficioEsperado,
			String periodoTiempoPrestamo, Boolean activo,
			Set<ImagenObjeto> imagenObjetos,
			/*Set<CalificacionObjeto> calificacionObjetos,*/
			Set<PrestamoObjeto> prestamoObjetos,
			Set<SolicitudPrestamoObjeto> solicitudPrestamoObjetos) {
		this.prestador = prestador;
		this.tipoObjeto = tipoObjeto;
		this.nombreObjeto = nombreObjeto;
		this.descripcion = descripcion;
		this.beneficioEsperado = beneficioEsperado;
		this.periodoTiempoPrestamo = periodoTiempoPrestamo;
		this.activo = activo;
		this.imagenObjetos = imagenObjetos;
		/*this.calificacionObjetos = calificacionObjetos;*/
		this.prestamoObjetos = prestamoObjetos;
		this.solicitudPrestamoObjetos = solicitudPrestamoObjetos;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "objeto_id", unique = true, nullable = false)
	public Integer getObjetoId() {
		return this.objetoId;
	}

	public void setObjetoId(Integer objetoId) {
		this.objetoId = objetoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prestador_id"/*, referencedColumnName="usuario_id"*/, nullable = false)
	public Usuario getPrestador() {
		return this.prestador;
	}

	public void setPrestador(Usuario prestador) {
		this.prestador = prestador;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_objeto_id", nullable = false)
	public TipoObjeto getTipoObjeto() {
		return this.tipoObjeto;
	}

	public void setTipoObjeto(TipoObjeto tipoObjeto) {
		this.tipoObjeto = tipoObjeto;
	}

	@Column(name = "nombre_objeto", nullable = false)
	public String getNombreObjeto() {
		return this.nombreObjeto;
	}

	public void setNombreObjeto(String nombreObjeto) {
		this.nombreObjeto = nombreObjeto;
	}

	@Column(name = "descripcion", nullable = false, length = 2047)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "beneficio_esperado", nullable = false, length = 511)
	public String getBeneficioEsperado() {
		return this.beneficioEsperado;
	}

	public void setBeneficioEsperado(String beneficioEsperado) {
		this.beneficioEsperado = beneficioEsperado;
	}

	@Column(name = "periodo_tiempo_prestamo", nullable = false, length = 511)
	public String getPeriodoTiempoPrestamo() {
		return this.periodoTiempoPrestamo;
	}

	public void setPeriodoTiempoPrestamo(String periodoTiempoPrestamo) {
		this.periodoTiempoPrestamo = periodoTiempoPrestamo;
	}

	@Column(name = "activo", nullable = false)
	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "objeto")
	public Set<ImagenObjeto> getImagenObjetos() {
		return this.imagenObjetos;
	}

	public void setImagenObjetos(Set<ImagenObjeto> imagenObjetos) {
		this.imagenObjetos = imagenObjetos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "objeto")
	public Set<SolicitudPrestamoObjeto> getSolicitudPrestamoObjetos() {
		return this.solicitudPrestamoObjetos;
	}

	public void setSolicitudPrestamoObjetos(
			Set<SolicitudPrestamoObjeto> solicitudPrestamoObjetos) {
		this.solicitudPrestamoObjetos = solicitudPrestamoObjetos;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "objeto")
	public Set<PrestamoObjeto> getPrestamoObjetos() {
		return this.prestamoObjetos;
	}

	public void setPrestamoObjetos(Set<PrestamoObjeto> prestamoObjetos) {
		this.prestamoObjetos = prestamoObjetos;
	}
	
	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "objeto")
	public Set<CalificacionObjeto> getCalificacionObjetos() {
		return this.calificacionObjetos;
	}

	public void setCalificacionObjetos(
			Set<CalificacionObjeto> calificacionObjetos) {
		this.calificacionObjetos = calificacionObjetos;
	}*/
	
	public String toString() {
		return nombreObjeto;
	}

}