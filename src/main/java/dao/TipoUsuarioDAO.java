package dao;

import model.TipoUsuario;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TipoUsuario entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see model.TipoUsuario
 * @author MyEclipse Persistence Tools
 */
public class TipoUsuarioDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TipoUsuarioDAO.class);
	// property constants
	public static final String NOMBRE_TIPO_USUARIO = "nombreTipoUsuario";
	public static final String ELEGIBLE = "elegible";

	public void save(TipoUsuario transientInstance) {
		log.debug("saving TipoUsuario instance");
		try {
        	Session session = getSession();
        	Transaction txt = session.beginTransaction();
			try {
				session.save(transientInstance);
				txt.commit();
				log.debug("save successful");
			} catch (HibernateException he) {
				txt.rollback();
				log.error("save failed", he);
				throw he;
			} finally {
            	session.close();
            }        	
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
	}
	
	public void update(TipoUsuario transientInstance) {
		log.debug("saving TipoUsuario instance");
		try {
        	Session session = getSession();
        	Transaction txt = session.beginTransaction();
            try {
            	session.update(transientInstance);
            	txt.commit();
				log.debug("save successful");
			} catch (HibernateException he) {
				txt.rollback();
				log.error("save failed", he);
				throw he;
			} finally {
            	session.close();
            }        	
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
	}

	public void delete(TipoUsuario persistentInstance) {
		log.debug("deleting TipoUsuario instance");
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
            	session.close();
            }        	 	
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
	}

	public TipoUsuario findById(java.lang.Integer id) {
		log.debug("getting TipoUsuario instance with id: " + id);
		Session session = getSession();
		try {
			TipoUsuario instance = (TipoUsuario) session.get("model.TipoUsuario", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		} finally {
        	session.close();
        }
	}

	public List<TipoUsuario> findByExample(TipoUsuario instance) {
		log.debug("finding TipoUsuario instance by example");
		Session session = getSession();
		try {
			List<TipoUsuario> results = (List<TipoUsuario>) session
					.createCriteria("model.TipoUsuario").add(create(instance))
					.list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		} finally {
        	session.close();
        }
	}

	public List<TipoUsuario> findByProperty(String propertyName, Object value) {
		log.debug("finding TipoUsuario instance with property: " + propertyName
				+ ", value: " + value);
		Session session = getSession();
		try {
			String queryString = "from TipoUsuario as model where model."
					+ propertyName + "= ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			return (List<TipoUsuario>)queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		} finally {
        	session.close();
        }
	}

	public List<TipoUsuario> findByNombreTipoUsuario(Object nombreTipoUsuario) {
		return findByProperty(NOMBRE_TIPO_USUARIO, nombreTipoUsuario);
	}

	public List<TipoUsuario> findByElegible(Object elegible) {
		return findByProperty(ELEGIBLE, elegible);
	}

	public List findAll() {
		log.debug("finding all TipoUsuario instances");
		Session session = getSession();
		try {
			String queryString = "from TipoUsuario";
			Query queryObject = session.createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		} finally {
        	session.close();
        }
	}

	public TipoUsuario merge(TipoUsuario detachedInstance) {
		log.debug("merging TipoUsuario instance");
		try {
			TipoUsuario result = (TipoUsuario) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TipoUsuario instance) {
		log.debug("attaching dirty TipoUsuario instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TipoUsuario instance) {
		log.debug("attaching clean TipoUsuario instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}