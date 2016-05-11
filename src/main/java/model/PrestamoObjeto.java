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
 * PrestamoObjeto entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "prestamo_objeto", catalog = "prestamart")
public class PrestamoObjeto implements java.io.Serializable {

	// Fields

	private Integer prestamoObjetoId;
	private Usuario usuario;
	private Objeto objeto;
	private Boolean objetoPrestado;
	private Boolean objetoRecibido;
	private Integer prestadorId;

	// Constructors

	/** default constructor */
	public PrestamoObjeto() {
	}

	/** minimal constructor */
	public PrestamoObjeto(Usuario usuario, Objeto objeto, Integer prestadorId) {
		this.usuario = usuario;
		this.objeto = objeto;
		this.prestadorId = prestadorId;
	}

	/** full constructor */
	public PrestamoObjeto(Usuario usuario, Objeto objeto,
			Boolean objetoPrestado, Boolean objetoRecibido, Integer prestadorId) {
		this.usuario = usuario;
		this.objeto = objeto;
		this.objetoPrestado = objetoPrestado;
		this.objetoRecibido = objetoRecibido;
		this.prestadorId = prestadorId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "prestamo_objeto_id", unique = true, nullable = false)
	public Integer getPrestamoObjetoId() {
		return this.prestamoObjetoId;
	}

	public void setPrestamoObjetoId(Integer prestamoObjetoId) {
		this.prestamoObjetoId = prestamoObjetoId;
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

	@Column(name = "objeto_prestado")
	public Boolean getObjetoPrestado() {
		return this.objetoPrestado;
	}

	public void setObjetoPrestado(Boolean objetoPrestado) {
		this.objetoPrestado = objetoPrestado;
	}

	@Column(name = "objeto_recibido")
	public Boolean getObjetoRecibido() {
		return this.objetoRecibido;
	}

	public void setObjetoRecibido(Boolean objetoRecibido) {
		this.objetoRecibido = objetoRecibido;
	}

	@Column(name = "prestador_id", nullable = false)
	public Integer getPrestadorId() {
		return this.prestadorId;
	}

	public void setPrestadorId(Integer prestadorId) {
		this.prestadorId = prestadorId;
	}

}