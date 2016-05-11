package dao;

import model.PrestamoObjeto;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * PrestamoObjeto entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see hibernate.PrestamoObjeto
 * @author MyEclipse Persistence Tools
 */
public class PrestamoObjetoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(PrestamoObjetoDAO.class);
	// property constants
	public static final String OBJETO_PRESTADO = "objetoPrestado";
	public static final String OBJETO_RECIBIDO = "objetoRecibido";
	public static final String PRESTADOR_ID = "prestadorId";

	public void save(PrestamoObjeto transientInstance) {
		log.debug("saving PrestamoObjeto instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PrestamoObjeto persistentInstance) {
		log.debug("deleting PrestamoObjeto instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PrestamoObjeto findById(java.lang.Integer id) {
		log.debug("getting PrestamoObjeto instance with id: " + id);
		try {
			PrestamoObjeto instance = (PrestamoObjeto) getSession().get(
					"hibernate.PrestamoObjeto", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<PrestamoObjeto> findByExample(PrestamoObjeto instance) {
		log.debug("finding PrestamoObjeto instance by example");
		try {
			List<PrestamoObjeto> results = (List<PrestamoObjeto>) getSession()
					.createCriteria("hibernate.PrestamoObjeto")
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
		log.debug("finding PrestamoObjeto instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PrestamoObjeto as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<PrestamoObjeto> findByObjetoPrestado(Object objetoPrestado) {
		return findByProperty(OBJETO_PRESTADO, objetoPrestado);
	}

	public List<PrestamoObjeto> findByObjetoRecibido(Object objetoRecibido) {
		return findByProperty(OBJETO_RECIBIDO, objetoRecibido);
	}

	public List<PrestamoObjeto> findByPrestadorId(Object prestadorId) {
		return findByProperty(PRESTADOR_ID, prestadorId);
	}

	public List findAll() {
		log.debug("finding all PrestamoObjeto instances");
		try {
			String queryString = "from PrestamoObjeto";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PrestamoObjeto merge(PrestamoObjeto detachedInstance) {
		log.debug("merging PrestamoObjeto instance");
		try {
			PrestamoObjeto result = (PrestamoObjeto) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PrestamoObjeto instance) {
		log.debug("attaching dirty PrestamoObjeto instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PrestamoObjeto instance) {
		log.debug("attaching clean PrestamoObjeto instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}