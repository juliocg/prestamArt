package dao;

import model.CalificacionObjeto;

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
 * CalificacionObjeto entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see hibernate.CalificacionObjeto
 * @author MyEclipse Persistence Tools
 */
public class CalificacionObjetoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(CalificacionObjetoDAO.class);
	// property constants
	public static final String CALIFICACION_OBJETO = "calificacionObjeto";
	public static final String RESENIA_OBJETO = "reseniaObjeto";

	public void save(CalificacionObjeto transientInstance) {
		log.debug("saving CalificacionObjeto instance");
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
	
	public void update(CalificacionObjeto transientInstance) {
		log.debug("updating CalificacionObjeto instance");
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

	public void delete(CalificacionObjeto persistentInstance) {
		log.debug("deleting CalificacionObjeto instance");
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

	public CalificacionObjeto findById(java.lang.Integer id) {
		log.debug("getting CalificacionObjeto instance with id: " + id);
		Session session = getSession();
		try {
			CalificacionObjeto instance = (CalificacionObjeto) session.get("model.CalificacionObjeto", id);
			session.evict(instance);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<CalificacionObjeto> findByExample(CalificacionObjeto instance) {
		log.debug("finding CalificacionObjeto instance by example");
		Session session = getSession();
		try {
			List<CalificacionObjeto> results = (List<CalificacionObjeto>) session
					.createCriteria("model.CalificacionObjeto")
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
		log.debug("finding CalificacionObjeto instance with property: "
				+ propertyName + ", value: " + value);
		Session session = getSession();
		try {
			String queryString = "from CalificacionObjeto as model where model."
					+ propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<CalificacionObjeto> findByCalificacionObjeto(
			Object calificacionObjeto) {
		return findByProperty(CALIFICACION_OBJETO, calificacionObjeto);
	}

	public List<CalificacionObjeto> findByReseniaObjeto(Object reseniaObjeto) {
		return findByProperty(RESENIA_OBJETO, reseniaObjeto);
	}

	public List findAll() {
		log.debug("finding all CalificacionObjeto instances");
		Session session = getSession();
		try {
			String queryString = "from CalificacionObjeto";
			Query queryObject = session.createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CalificacionObjeto merge(CalificacionObjeto detachedInstance) {
		log.debug("merging CalificacionObjeto instance");
		Session session = getSession();
		try {
			CalificacionObjeto result = (CalificacionObjeto) session
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CalificacionObjeto instance) {
		log.debug("attaching dirty CalificacionObjeto instance");
		Session session = getSession();
		try {
			session.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CalificacionObjeto instance) {
		log.debug("attaching clean CalificacionObjeto instance");
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