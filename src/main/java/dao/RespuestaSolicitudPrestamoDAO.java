package dao;

import model.RespuestaSolicitudPrestamo;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * RespuestaSolicitudPrestamo entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see hibernate.RespuestaSolicitudPrestamo
 * @author MyEclipse Persistence Tools
 */
public class RespuestaSolicitudPrestamoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RespuestaSolicitudPrestamoDAO.class);
	// property constants
	public static final String MENSAJE_RESPUESTA = "mensajeRespuesta";
	public static final String ESTADO_RESPUESTA = "estadoRespuesta";

	public void save(RespuestaSolicitudPrestamo transientInstance) {
		log.debug("saving RespuestaSolicitudPrestamo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RespuestaSolicitudPrestamo persistentInstance) {
		log.debug("deleting RespuestaSolicitudPrestamo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RespuestaSolicitudPrestamo findById(java.lang.Integer id) {
		log.debug("getting RespuestaSolicitudPrestamo instance with id: " + id);
		try {
			RespuestaSolicitudPrestamo instance = (RespuestaSolicitudPrestamo) getSession()
					.get("model.RespuestaSolicitudPrestamo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<RespuestaSolicitudPrestamo> findByExample(
			RespuestaSolicitudPrestamo instance) {
		log.debug("finding RespuestaSolicitudPrestamo instance by example");
		try {
			List<RespuestaSolicitudPrestamo> results = (List<RespuestaSolicitudPrestamo>) getSession()
					.createCriteria("model.RespuestaSolicitudPrestamo")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding RespuestaSolicitudPrestamo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from RespuestaSolicitudPrestamo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<RespuestaSolicitudPrestamo> findByMensajeRespuesta(
			Object mensajeRespuesta) {
		return findByProperty(MENSAJE_RESPUESTA, mensajeRespuesta);
	}

	public List<RespuestaSolicitudPrestamo> findByEstadoRespuesta(
			Object estadoRespuesta) {
		return findByProperty(ESTADO_RESPUESTA, estadoRespuesta);
	}

	public List findAll() {
		log.debug("finding all RespuestaSolicitudPrestamo instances");
		try {
			String queryString = "from RespuestaSolicitudPrestamo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RespuestaSolicitudPrestamo merge(
			RespuestaSolicitudPrestamo detachedInstance) {
		log.debug("merging RespuestaSolicitudPrestamo instance");
		try {
			RespuestaSolicitudPrestamo result = (RespuestaSolicitudPrestamo) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RespuestaSolicitudPrestamo instance) {
		log.debug("attaching dirty RespuestaSolicitudPrestamo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RespuestaSolicitudPrestamo instance) {
		log.debug("attaching clean RespuestaSolicitudPrestamo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}