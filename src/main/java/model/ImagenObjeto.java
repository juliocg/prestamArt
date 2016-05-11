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
 * ImagenObjeto entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "imagen_objeto", catalog = "prestamart")
public class ImagenObjeto implements java.io.Serializable {

	// Fields

	private Integer imagenId;
	private Objeto objeto;
	private String nombreImagen;

	// Constructors

	/** default constructor */
	public ImagenObjeto() {
	}

	/** full constructor */
	public ImagenObjeto(Objeto objeto, String nombreImagen) {
		this.objeto = objeto;
		this.nombreImagen = nombreImagen;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "imagen_id", unique = true, nullable = false)
	public Integer getImagenId() {
		return this.imagenId;
	}

	public void setImagenId(Integer imagenId) {
		this.imagenId = imagenId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "objeto_id", nullable = false)
	public Objeto getObjeto() {
		return this.objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}

	@Column(name = "nombre_imagen", nullable = false)
	public String getNombreImagen() {
		return this.nombreImagen;
	}

	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}

}