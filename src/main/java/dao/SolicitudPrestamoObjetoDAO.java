package dao;

import model.SolicitudPrestamoObjeto;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.hibernate.Query;
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
	private static final Logger log = LoggerFactory
			.getLogger(SolicitudPrestamoObjetoDAO.class);
	// property constants
	public static final String MENSAJE_SOLICITUD_PRESTAMO = "mensajeSolicitudPrestamo";

	public void save(SolicitudPrestamoObjeto transientInstance) {
		log.debug("saving SolicitudPrestamoObjeto instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SolicitudPrestamoObjeto persistentInstance) {
		log.debug("deleting SolicitudPrestamoObjeto instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SolicitudPrestamoObjeto findById(java.lang.Integer id) {
		log.debug("getting SolicitudPrestamoObjeto instance with id: " + id);
		try {
			SolicitudPrestamoObjeto instance = (SolicitudPrestamoObjeto) getSession()
					.get("hibernate.SolicitudPrestamoObjeto", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<SolicitudPrestamoObjeto> findByExample(
			SolicitudPrestamoObjeto instance) {
		log.debug("finding SolicitudPrestamoObjeto instance by example");
		try {
			List<SolicitudPrestamoObjeto> results = (List<SolicitudPrestamoObjeto>) getSession()
					.createCriteria("hibernate.SolicitudPrestamoObjeto")
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
		log.debug("finding SolicitudPrestamoObjeto instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SolicitudPrestamoObjeto as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<SolicitudPrestamoObjeto> findByMensajeSolicitudPrestamo(
			Object mensajeSolicitudPrestamo) {
		return findByProperty(MENSAJE_SOLICITUD_PRESTAMO,
				mensajeSolicitudPrestamo);
	}

	public List findAll() {
		log.debug("finding all SolicitudPrestamoObjeto instances");
		try {
			String queryString = "from SolicitudPrestamoObjeto";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
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