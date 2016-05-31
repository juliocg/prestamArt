package dao;

import model.CalificacionConsumidor;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * CalificacionConsumidor entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see hibernate.CalificacionConsumidor
 * @author MyEclipse Persistence Tools
 */
public class CalificacionConsumidorDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(CalificacionConsumidorDAO.class);
	// property constants
	public static final String CALIFICACION_CONSUMIDOR = "calificacionConsumidor";
	public static final String RESENIA_CONSUMIDOR = "reseniaConsumidor";

	public void save(CalificacionConsumidor transientInstance) {
		log.debug("saving CalificacionConsumidor instance");
		Session session = getSession();
		Transaction txt = session.beginTransaction();
		try {
			session.save(transientInstance);
			txt.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			txt.rollback();
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void update(CalificacionConsumidor transientInstance) {
		log.debug("updating CalificacionConsumidor instance");
		Session session = getSession();
		Transaction txt = session.beginTransaction();
		try {
			session.update(transientInstance);
			txt.commit();
			log.debug("update successful");
		} catch (RuntimeException re) {
			txt.rollback();
			log.error("update failed", re);
			throw re;
		}
	}

	public void delete(CalificacionConsumidor persistentInstance) {
		log.debug("deleting CalificacionConsumidor instance");
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

	public CalificacionConsumidor findById(java.lang.Integer id) {
		log.debug("getting CalificacionConsumidor instance with id: " + id);
		Session session = getSession();
		try {
			CalificacionConsumidor instance = (CalificacionConsumidor) session.get("model.CalificacionConsumidor", id);
			session.evict(instance);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<CalificacionConsumidor> findByExample(CalificacionConsumidor instance) {
		log.debug("finding CalificacionConsumidor instance by example");
		Session session = getSession();
		try {
			List<CalificacionConsumidor> results = (List<CalificacionConsumidor>) session
					.createCriteria("model.CalificacionConsumidor")
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
		log.debug("finding CalificacionConsumidor instance with property: "
				+ propertyName + ", value: " + value);
		Session session = getSession();
		try {
			String queryString = "from CalificacionConsumidor as model where model."
					+ propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<CalificacionConsumidor> findByCalificacionConsumidor(
			Object calificacionConsumidor) {
		return findByProperty(CALIFICACION_CONSUMIDOR, calificacionConsumidor);
	}

	public List<CalificacionConsumidor> findByReseniaConsumidor(
			Object reseniaConsumidor) {
		return findByProperty(RESENIA_CONSUMIDOR, reseniaConsumidor);
	}

	public List findAll() {
		log.debug("finding all CalificacionConsumidor instances");
		Session session = getSession();
		try {
			String queryString = "from CalificacionConsumidor";
			Query queryObject = session.createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CalificacionConsumidor merge(CalificacionConsumidor detachedInstance) {
		log.debug("merging CalificacionConsumidor instance");
		Session session = getSession();
		try {
			CalificacionConsumidor result = (CalificacionConsumidor) session
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CalificacionConsumidor instance) {
		log.debug("attaching dirty CalificacionConsumidor instance");
		Session session = getSession();
		try {
			session.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CalificacionConsumidor instance) {
		log.debug("attaching clean CalificacionConsumidor instance");
		Session session = getSession();
		try {
			session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}