package dao;

import model.TipoObjeto;

import java.util.List;
import java.util.Set;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TipoObjeto entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see hibernate.TipoObjeto
 * @author MyEclipse Persistence Tools
 */
public class TipoObjetoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TipoObjetoDAO.class);
	// property constants
	public static final String NOMBRE_TIPO_OBJETO = "nombreTipoObjeto";

	public void save(TipoObjeto transientInstance) {
		log.debug("saving TipoObjeto instance");
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
	
	public void update(TipoObjeto transientInstance) {
		log.debug("update TipoObjeto instance");
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

	public void delete(TipoObjeto persistentInstance) {
		log.debug("deleting TipoObjeto instance");
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

	public TipoObjeto findById(java.lang.Integer id) {
		log.debug("getting TipoObjeto instance with id: " + id);
		Session session = getSession();
		try {
			TipoObjeto instance = (TipoObjeto) session.get("model.TipoObjeto", id);
			session.evict(instance);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<TipoObjeto> findByExample(TipoObjeto instance) {
		log.debug("finding TipoObjeto instance by example");
		Session session = getSession();
		try {
			List<TipoObjeto> results = (List<TipoObjeto>) session
					.createCriteria("model.TipoObjeto").add(create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TipoObjeto instance with property: " + propertyName
				+ ", value: " + value);
		Session session = getSession();
		try {
			String queryString = "from TipoObjeto as model where model."
					+ propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<TipoObjeto> findByNombreTipoObjeto(Object nombreTipoObjeto) {
		return findByProperty(NOMBRE_TIPO_OBJETO, nombreTipoObjeto);
	}

	public List findAll() {
		log.debug("finding all TipoObjeto instances");
		Session session = getSession();
		try {
			String queryString = "from TipoObjeto";
			Query queryObject = session.createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TipoObjeto merge(TipoObjeto detachedInstance) {
		log.debug("merging TipoObjeto instance");
		try {
			TipoObjeto result = (TipoObjeto) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TipoObjeto instance) {
		log.debug("attaching dirty TipoObjeto instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TipoObjeto instance) {
		log.debug("attaching clean TipoObjeto instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}