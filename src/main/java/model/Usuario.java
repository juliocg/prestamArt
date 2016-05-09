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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Usuario entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usuario", catalog = "prestamart", uniqueConstraints = @UniqueConstraint(columnNames = "correo_electronico"))
public class Usuario implements java.io.Serializable/*, UserDetails*/ {

	// Fields

	private Integer usuarioId;
	private TipoUsuario tipoUsuario;
	private String correoElectronico;
	private String contrasenia;
	private String nombre;
	private String apellidos;
	private String telefono;
	private String otroDatoContacto;
	private Boolean activo;
	private Set<TipoUsuario> tiposUsuario = new HashSet<TipoUsuario>(0);
	/*private Set<RespuestaSolicitudPrestamo> respuestaSolicitudPrestamos = new HashSet<RespuestaSolicitudPrestamo>(0);
	private Set<CalificacionConsumidor> calificacionConsumidorsForPrestadorId = new HashSet<CalificacionConsumidor>(0);
	private Set<CalificacionConsumidor> calificacionConsumidorsForConsumidorId = new HashSet<CalificacionConsumidor>(0);
	private Set<SolicitudPrestamoObjeto> solicitudPrestamoObjetos = new HashSet<SolicitudPrestamoObjeto>(0);
	private Set<PrestamoObjeto> prestamoObjetos = new HashSet<PrestamoObjeto>(0);
	private Set<CalificacionObjeto> calificacionObjetos = new HashSet<CalificacionObjeto>(0);
	private Set<Objeto> objetos = new HashSet<Objeto>(0);*/
	
	
	
	/*private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;*/
	
	
	
	// Constructors

	/** default constructor */
	public Usuario() {
	}

	/** minimal constructor */
	public Usuario(TipoUsuario tipoUsuario, String correoElectronico,
			String contrasenia, String nombre, Boolean activo) {
		this.tipoUsuario = tipoUsuario;
		this.correoElectronico = correoElectronico;
		this.contrasenia = contrasenia;
		this.nombre = nombre;
		this.activo = activo;
	}

	/** full constructor */
	public Usuario(TipoUsuario tipoUsuario, String correoElectronico,
			String contrasenia, String nombre, String apellidos,
			String telefono, String otroDatoContacto, Boolean activo/*,
			Set<TipoUsuario> tiposUsuario,
			Set<RespuestaSolicitudPrestamo> respuestaSolicitudPrestamos,
			Set<CalificacionConsumidor> calificacionConsumidorsForPrestadorId,
			Set<CalificacionConsumidor> calificacionConsumidorsForConsumidorId,
			Set<SolicitudPrestamoObjeto> solicitudPrestamoObjetos,
			Set<PrestamoObjeto> prestamoObjetos,
			Set<CalificacionObjeto> calificacionObjetos, Set<Objeto> objetos*/) {
		this.tipoUsuario = tipoUsuario;
		this.correoElectronico = correoElectronico;
		this.contrasenia = contrasenia;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.otroDatoContacto = otroDatoContacto;
		this.activo = activo;
		/*this.tiposUsuario = tipoUsuario;
		this.respuestaSolicitudPrestamos = respuestaSolicitudPrestamos;
		this.calificacionConsumidorsForPrestadorId = calificacionConsumidorsForPrestadorId;
		this.calificacionConsumidorsForConsumidorId = calificacionConsumidorsForConsumidorId;
		this.solicitudPrestamoObjetos = solicitudPrestamoObjetos;
		this.prestamoObjetos = prestamoObjetos;
		this.calificacionObjetos = calificacionObjetos;
		this.objetos = objetos;*/
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "usuario_id", unique = true, nullable = false)
	public Integer getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_usuario_id", nullable = false)
	public TipoUsuario getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	@Column(name = "correo_electronico", unique = true, nullable = false)
	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	@Column(name = "contrasenia", nullable = false)
	public String getContrasenia() {
		return this.contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	@Column(name = "nombre", nullable = false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "apellidos")
	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	@Column(name = "telefono", length = 23)
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "otro_dato_contacto", length = 2047)
	public String getOtroDatoContacto() {
		return this.otroDatoContacto;
	}

	public void setOtroDatoContacto(String otroDatoContacto) {
		this.otroDatoContacto = otroDatoContacto;
	}

	@Column(name = "activo", nullable = false)
	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "usuario_tipo_usuario", catalog = "prestamart", joinColumns = { @JoinColumn(name = "usuario_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "tipo_usuario_id", nullable = false, updatable = false) })
	public Set<TipoUsuario> getTiposUsuario() {
		return this.tiposUsuario;
	}

	public void setTiposUsuario(Set<TipoUsuario> tiposUsuario) {
		this.tiposUsuario = tiposUsuario;
	}

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<RespuestaSolicitudPrestamo> getRespuestaSolicitudPrestamos() {
		return this.respuestaSolicitudPrestamos;
	}

	public void setRespuestaSolicitudPrestamos(
			Set<RespuestaSolicitudPrestamo> respuestaSolicitudPrestamos) {
		this.respuestaSolicitudPrestamos = respuestaSolicitudPrestamos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuarioByPrestadorId")
	public Set<CalificacionConsumidor> getCalificacionConsumidorsForPrestadorId() {
		return this.calificacionConsumidorsForPrestadorId;
	}

	public void setCalificacionConsumidorsForPrestadorId(
			Set<CalificacionConsumidor> calificacionConsumidorsForPrestadorId) {
		this.calificacionConsumidorsForPrestadorId = calificacionConsumidorsForPrestadorId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuarioByConsumidorId")
	public Set<CalificacionConsumidor> getCalificacionConsumidorsForConsumidorId() {
		return this.calificacionConsumidorsForConsumidorId;
	}

	public void setCalificacionConsumidorsForConsumidorId(
			Set<CalificacionConsumidor> calificacionConsumidorsForConsumidorId) {
		this.calificacionConsumidorsForConsumidorId = calificacionConsumidorsForConsumidorId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<SolicitudPrestamoObjeto> getSolicitudPrestamoObjetos() {
		return this.solicitudPrestamoObjetos;
	}

	public void setSolicitudPrestamoObjetos(
			Set<SolicitudPrestamoObjeto> solicitudPrestamoObjetos) {
		this.solicitudPrestamoObjetos = solicitudPrestamoObjetos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<PrestamoObjeto> getPrestamoObjetos() {
		return this.prestamoObjetos;
	}

	public void setPrestamoObjetos(Set<PrestamoObjeto> prestamoObjetos) {
		this.prestamoObjetos = prestamoObjetos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<CalificacionObjeto> getCalificacionObjetos() {
		return this.calificacionObjetos;
	}

	public void setCalificacionObjetos(
			Set<CalificacionObjeto> calificacionObjetos) {
		this.calificacionObjetos = calificacionObjetos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<Objeto> getObjetos() {
		return this.objetos;
	}

	public void setObjetos(Set<Objeto> objetos) {
		this.objetos = objetos;
	}*/
	
	
		
	/*public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }
    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }
    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }
    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
    public boolean isEnabled() {
        return this.enabled;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }*/
	
	public String toString() {
		return correoElectronico;
	}

}