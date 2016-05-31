package dao;

import model.SolicitudPrestamoObjeto;
import model.Objeto;
import model.Usuario;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
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
 * SolicitudPrestamoObjeto entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see hibernate.SolicitudPrestamoObjeto
 * @author MyEclipse Persistence Tools
 */
public class SolicitudPrestamoObjetoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(SolicitudPrestamoObjetoDAO.class);
	// property constants
	public static final String MENSAJE_SOLICITUD_PRESTAMO = "mensajeSolicitudPrestamo";
	public static final String MENSAJE_RESPUESTA = "mensajeRespuesta";
	public static final String SOLICITUD_ACEPTADA = "solicitudAceptada";
	public static final String ENVIO_PRESTAMO_OBJETO_PRESTADOR = "envioPrestamoObjetoPrestador";
	public static final String ENVIO_PRESTAMO_OBJETO_RECIBIDO_CONSUMIDOR = "envioPrestamoObjetoRecibidoConsumidor";
	public static final String DEVOLUCION_OBJETO_CONSUMIDOR = "devolucionObjetoConsumidor";
	public static final String DEVOLUCION_OBJETO_RECIBIDA_PRESTADOR = "devolucionObjetoRecibidaPrestador";

	public void save(SolicitudPrestamoObjeto transientInstance) {
		log.debug("saving SolicitudPrestamoObjeto instance");
		Session session = getSession();
		Transaction txt = session.beginTransaction();
		try {
			session.save(transientInstance);
			session.flush();
			txt.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			txt.rollback();
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void update(SolicitudPrestamoObjeto transientInstance) {
		log.debug("saving SolicitudPrestamoObjeto instance");
		Session session = getSession();
        Transaction txt = session.beginTransaction();
		try {
			session.update(transientInstance);
			session.flush();
			txt.commit();
			log.debug("update successful");
		} catch (RuntimeException re) {
			txt.rollback();
			log.error("update failed", re);
			throw re;
		}
	}

	public void delete(SolicitudPrestamoObjeto persistentInstance) {
		log.debug("deleting SolicitudPrestamoObjeto instance");
		Session session = getSession();
		Transaction txt = session.beginTransaction();
		try {
			session.delete(persistentInstance);
			txt.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			txt.rollback();
			log.error("delete failed", re);
			throw re;
		}
	}

	public SolicitudPrestamoObjeto findById(java.lang.Integer id) {
		log.debug("getting SolicitudPrestamoObjeto instance with id: " + id);
		Session session = getSession();
		Transaction txt = session.beginTransaction();
		try {
			SolicitudPrestamoObjeto instance = (SolicitudPrestamoObjeto) session.get("model.SolicitudPrestamoObjeto", id);
			Hibernate.initialize(instance.getObjeto());
			session.refresh(instance);
			//session.evict(instance);
			txt.commit();
			return instance;
		} catch (RuntimeException re) {
			txt.rollback();
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SolicitudPrestamoObjeto> findByExample(
			SolicitudPrestamoObjeto instance) {
		log.debug("finding SolicitudPrestamoObjeto instance by example");
		Session session = getSession();
		Transaction txt = session.beginTransaction();
		try {
			List<SolicitudPrestamoObjeto> results = (List<SolicitudPrestamoObjeto>) session
					.createCriteria("model.SolicitudPrestamoObjeto")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			txt.commit();
			return results;
		} catch (RuntimeException re) {
			txt.rollback();
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SolicitudPrestamoObjeto instance with property: "
				+ propertyName + ", value: " + value);
		Session session = getSession();
		Transaction txt = session.beginTransaction();
		try {
			String queryString = "from SolicitudPrestamoObjeto as model where model."
					+ propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			txt.commit();
			return queryObject.list();
		} catch (RuntimeException re) {
			txt.rollback();
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SolicitudPrestamoObjeto> findByMensajeSolicitudPrestamo(Object mensajeSolicitudPrestamo) {
		return findByProperty(MENSAJE_SOLICITUD_PRESTAMO, mensajeSolicitudPrestamo);
	}
	
	public List<SolicitudPrestamoObjeto> findByMensajeRespuesta(Object mensajeRespuesta) {
		return findByProperty(MENSAJE_RESPUESTA, mensajeRespuesta);
	}

	public List<SolicitudPrestamoObjeto> findBySolicitudAceptada(Object solicitudAceptada) {
		return findByProperty(SOLICITUD_ACEPTADA, solicitudAceptada);
	}

	public List<SolicitudPrestamoObjeto> findByEnvioPrestamoObjetoPrestador(Object envioPrestamoObjetoPrestador) {
		return findByProperty(ENVIO_PRESTAMO_OBJETO_PRESTADOR, envioPrestamoObjetoPrestador);
	}

	public List<SolicitudPrestamoObjeto> findByEnvioPrestamoObjetoRecibidoConsumidor(Object envioPrestamoObjetoRecibidoConsumidor) {
		return findByProperty(ENVIO_PRESTAMO_OBJETO_RECIBIDO_CONSUMIDOR, envioPrestamoObjetoRecibidoConsumidor);
	}

	public List<SolicitudPrestamoObjeto> findByDevolucionObjetoConsumidor(Object devolucionObjetoConsumidor) {
		return findByProperty(DEVOLUCION_OBJETO_CONSUMIDOR, devolucionObjetoConsumidor);
	}

	public List<SolicitudPrestamoObjeto> findByDevolucionObjetoRecibidaPrestador(Object devolucionObjetoRecibidaPrestador) {
		return findByProperty(DEVOLUCION_OBJETO_RECIBIDA_PRESTADOR, devolucionObjetoRecibidaPrestador);
	}
	
	public List<SolicitudPrestamoObjeto> findByConsumidor(Object consumidor) {
		log.debug("finding SolicitudPrestamoObjeto instance by consumidor");
		Session session = getSession();
		Transaction txt = session.beginTransaction();
		try {
			Usuario object = (Usuario) consumidor;
			List<SolicitudPrestamoObjeto> results = (List<SolicitudPrestamoObjeto>) session
					.createCriteria(SolicitudPrestamoObjeto.class)
					.add(Restrictions.eq("consumidor.usuarioId", object.getUsuarioId()))
					.list();
			log.debug("find by consumidor successful, result size: "
					+ results.size());
			txt.commit();
			return results;
		} catch (RuntimeException re) {
			txt.rollback();
			log.error("find by consumidor failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all SolicitudPrestamoObjeto instances");
		Session session = getSession();
		Transaction txt = session.beginTransaction();
		try {
			String queryString = "from SolicitudPrestamoObjeto";
			Query queryObject = session.createQuery(queryString);
			txt.commit();
			return queryObject.list();
		} catch (RuntimeException re) {
			txt.rollback();
			log.error("find all failed", re);
			throw re;
		}
	}

	public SolicitudPrestamoObjeto merge(
			SolicitudPrestamoObjeto detachedInstance) {
		log.debug("merging SolicitudPrestamoObjeto instance");
		try {
			SolicitudPrestamoObjeto result = (SolicitudPrestamoObjeto) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SolicitudPrestamoObjeto instance) {
		log.debug("attaching dirty SolicitudPrestamoObjeto instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SolicitudPrestamoObjeto instance) {
		log.debug("attaching clean SolicitudPrestamoObjeto instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}