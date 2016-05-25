package dao;

import model.Usuario;
import model.TipoUsuario;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Usuario entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see model.Usuario
 * @author MyEclipse Persistence Tools
 */
public class UsuarioDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(UsuarioDAO.class);
	// property constants
	public static final String CORREO_ELECTRONICO = "correoElectronico";
	public static final String CONTRASENIA = "contrasenia";
	public static final String NOMBRE = "nombre";
	public static final String APELLIDOS = "apellidos";
	public static final String TELEFONO = "telefono";
	public static final String OTRO_DATO_CONTACTO = "otroDatoContacto";
	public static final String ACTIVO = "activo";

	public void save(Usuario transientInstance) {
		log.debug("saving Usuario instance");
        try {
        	Session session = getSession();
        	Transaction txt = session.beginTransaction();
            try {
            	session.save(transientInstance);
                txt.commit();
                log.debug("save successful");
            } catch (HibernateException he){
            	txt.rollback();
            	log.debug("save failed", he);
            	throw he;
            } finally {
            	//session.close();
            }        	
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
	}
	
	public void update(Usuario transientInstance) {
		log.debug("saving Usuario instance");
        try {
        	Session session = getSession();
        	Transaction txt = session.beginTransaction();
            try {
            	session.update(transientInstance);
                txt.commit();
                log.debug("save successful");
            } catch (HibernateException he){
            	txt.rollback();
            	log.debug("save failed", he);
            	throw he;
            } finally {
            	//session.close();
            }        	
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
	}
	
	public void delete(Usuario persistentInstance) {
        log.debug("deleting Usuario instance");
        try {
        	Session session = getSession();
        	Transaction txt = session.beginTransaction();
            try {
            	session.delete(persistentInstance);
                txt.commit();                
                log.debug("delete successful");
            } catch (HibernateException he){
            	txt.rollback();
            	log.debug("delete failed", he);
            	throw he;
            } finally {
            	//session.close();
            }
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

	public Usuario findById(java.lang.Integer id) {
		log.debug("getting Usuario instance with id: " + id);
		Session session = getSession();
		try {
			Usuario instance = (Usuario) session.get("model.Usuario", id);
			session.evict(instance);
			//session.refresh(instance);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		} finally {
        	//session.close();
        }
	}

	public List<Usuario> findByExample(Usuario instance) {
		log.debug("finding Usuario instance by example");
		Session session = getSession();
		try {
			List<Usuario> results = (List<Usuario>) session
					.createCriteria("model.Usuario").add(create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		} finally {
        	//session.close();
        }
	}

	public List<Usuario> findByProperty(String propertyName, Object value) {
		log.debug("finding Usuario instance with property: " + propertyName
				+ ", value: " + value);
		Session session = getSession();
		try {
			String queryString = "from Usuario as model where model."
					+ propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			return (List<Usuario>)queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		} finally {
        	//session.close();
        }
	}

	public Usuario findByCorreoElectronico(Object correoElectronico) {
		Session session = getSession();
		try {
			List<Usuario> usuarios = (List<Usuario>) session
					.createCriteria(Usuario.class)
					.add(Restrictions.eq("correoElectronico", correoElectronico))
					.list();
			if (usuarios.size() > 0) {
			    return usuarios.get(0);
			} else {
				return null;
			}
		} catch (RuntimeException re) {
			log.error("find by correoElectronico failed", re);
			throw re;
		}
	}
	
	public Usuario findByCorreoElectronicoAndActivo(Object correoElectronico, Object activo) {
		Session session = getSession();
		try {
			Usuario instance = (Usuario) session
					.createCriteria(Usuario.class)
					.add(Restrictions.eq("correoElectronico", correoElectronico))
					.add(Restrictions.eq("activo", activo))
					.uniqueResult();
			session.evict(instance);
			return instance;
		} catch (RuntimeException re) {
			log.error("find by correoElectronico and activo failed", re);
			throw re;
		}
	}

	public Usuario findByCorreoElectronicoAndTipoUsuario(Object correoElectronico, Object tipoUsuario) {
		Session session = getSession();
		try {
			TipoUsuario object = (TipoUsuario) tipoUsuario;
			Usuario instance = (Usuario) session
					.createCriteria(Usuario.class)
					.add(Restrictions.eq("correoElectronico", correoElectronico))
					.add(Restrictions.eq("tipoUsuario.tipoUsuarioId", object.getTipoUsuarioId()))
					.uniqueResult();
			session.evict(instance);
			return instance;
		} catch (RuntimeException re) {
			log.error("find by correoElectronico and tipoUsuario failed", re);
			throw re;
		}
	}
	
	public List<Usuario> findByContrasenia(Object contrasenia) {
		return findByProperty(CONTRASENIA, contrasenia);
	}

	public List<Usuario> findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}

	public List<Usuario> findByApellidos(Object apellidos) {
		return findByProperty(APELLIDOS, apellidos);
	}

	public List<Usuario> findByTelefono(Object telefono) {
		return findByProperty(TELEFONO, telefono);
	}

	public List<Usuario> findByOtroDatoContacto(Object otroDatoContacto) {
		return findByProperty(OTRO_DATO_CONTACTO, otroDatoContacto);
	}

	public List<Usuario> findByActivo(Object activo) {
		return findByProperty(ACTIVO, activo);
	}

	public List findAll() {
		log.debug("finding all Usuario instances");
		Session session = getSession();
		try {
			String queryString = "from Usuario";
			Query queryObject = session.createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		} finally {
        	//session.close();
        }
	}

	public Usuario merge(Usuario detachedInstance) {
		log.debug("merging Usuario instance");
		try {
			Usuario result = (Usuario) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Usuario instance) {
		log.debug("attaching dirty Usuario instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Usuario instance) {
		log.debug("attaching clean Usuario instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}