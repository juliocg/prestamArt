package dao;

import model.ImagenObjeto;

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
 * ImagenObjeto entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see hibernate.ImagenObjeto
 * @author MyEclipse Persistence Tools
 */
public class ImagenObjetoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ImagenObjetoDAO.class);
	// property constants
	public static final String NOMBRE_IMAGEN = "nombreImagen";

	public void save(ImagenObjeto transientInstance) {
		log.debug("saving ImagenObjeto instance");
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
	
	public void update(ImagenObjeto transientInstance) {
		log.debug("update ImagenObjeto instance");
		Session session = getSession();
		Transaction txt = session.beginTransaction();
		try {
			session.save(transientInstance);
			txt.commit();
			log.debug("update successful");
		} catch (RuntimeException re) {
			txt.rollback();
			log.error("update failed", re);
			throw re;
		}
	}

	public void delete(ImagenObjeto persistentInstance) {
		log.debug("deleting ImagenObjeto instance");
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

	public ImagenObjeto findById(java.lang.Integer id) {
		log.debug("getting ImagenObjeto instance with id: " + id);
		Session session = getSession();
		try {
			ImagenObjeto instance = (ImagenObjeto) session.get("hibernate.ImagenObjeto", id);
			session.evict(instance);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ImagenObjeto> findByExample(ImagenObjeto instance) {
		log.debug("finding ImagenObjeto instance by example");
		Session session = getSession();
		try {
			List<ImagenObjeto> results = (List<ImagenObjeto>) session
					.createCriteria("hibernate.ImagenObjeto").add(create(instance))
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
		log.debug("finding ImagenObjeto instance with property: "
				+ propertyName + ", value: " + value);
		Session session = getSession();
		try {
			String queryString = "from ImagenObjeto as model where model."
					+ propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ImagenObjeto> findByNombreImagen(Object nombreImagen) {
		return findByProperty(NOMBRE_IMAGEN, nombreImagen);
	}

	public List findAll() {
		log.debug("finding all ImagenObjeto instances");
		Session session = getSession();
		try {
			String queryString = "from ImagenObjeto";
			Query queryObject = session.createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ImagenObjeto merge(ImagenObjeto detachedInstance) {
		log.debug("merging ImagenObjeto instance");
		try {
			ImagenObjeto result = (ImagenObjeto) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ImagenObjeto instance) {
		log.debug("attaching dirty ImagenObjeto instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ImagenObjeto instance) {
		log.debug("attaching clean ImagenObjeto instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}