package dao;

import model.Objeto;
import model.Usuario;
import model.TipoObjeto;

import java.util.List;
import java.util.Set;

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
 * Objeto entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see hibernate.Objeto
 * @author MyEclipse Persistence Tools
 */
public class ObjetoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ObjetoDAO.class);
	// property constants
	public static final String NOMBRE_OBJETO = "nombreObjeto";
	public static final String DESCRIPCION = "descripcion";
	public static final String BENEFICIO_ESPERADO = "beneficioEsperado";
	public static final String PERIODO_TIEMPO_PRESTAMO = "periodoTiempoPrestamo";
	public static final String ACTIVO = "activo";
	public static final String TIPOOBJETO = "tipoObjetoId";
	public static final String PRESTADOR = "prestador";

	public void save(Objeto transientInstance) {
		log.debug("saving Objeto instance");
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
	
	public void update(Objeto transientInstance) {
		log.debug("update Objeto instance");
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

	public void delete(Objeto persistentInstance) {
		log.debug("deleting Objeto instance");
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

	public Objeto findById(java.lang.Integer id) {
		log.debug("getting Objeto instance with id: " + id);
		Session session = getSession();
		try {
			Objeto instance = (Objeto) session.get("hibernate.Objeto", id);
			session.evict(instance);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Objeto> findByExample(Objeto instance) {
		log.debug("finding Objeto instance by example");
		Session session = getSession();
		try {
			List<Objeto> results = (List<Objeto>) session
					.createCriteria("hibernate.Objeto")
					.add(create(instance))
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
		log.debug("finding Objeto instance with property: " + propertyName
				+ ", value: " + value);
		Session session = getSession();
		try {
			String queryString = "from Objeto as model where model."
					+ propertyName + " = ?";
			Query queryObject = session.createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Objeto> findByNombreObjeto(Object nombreObjeto) {
		return findByProperty(NOMBRE_OBJETO, nombreObjeto);
	}

	public List<Objeto> findByDescripcion(Object descripcion) {
		return findByProperty(DESCRIPCION, descripcion);
	}

	public List<Objeto> findByBeneficioEsperado(Object beneficioEsperado) {
		return findByProperty(BENEFICIO_ESPERADO, beneficioEsperado);
	}

	public List<Objeto> findByPeriodoTiempoPrestamo(Object periodoTiempoPrestamo) {
		return findByProperty(PERIODO_TIEMPO_PRESTAMO, periodoTiempoPrestamo);
	}

	public List<Objeto> findByActivo(Object activo) {
		return findByProperty(ACTIVO, activo);
	}
	
	public List<Objeto> findByTipoObjeto(Object tipoObjeto) {
		TipoObjeto object = (TipoObjeto) tipoObjeto;
		return findByProperty(TIPOOBJETO, object.getTipoObjetoId());
	}

	public List<Objeto> findByPrestador(Object prestador) {
		log.debug("finding Objeto instance by prestador");
		Session session = getSession();
		try {
			Usuario object = (Usuario) prestador;
			List<Objeto> results = (List<Objeto>) session
					.createCriteria(Objeto.class)
					.add(Restrictions.eq("prestador.usuarioId", object.getUsuarioId()))
					.list();
			log.debug("find by prestador successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by prestador failed", re);
			throw re;
		}
	}
	
	public List<Objeto> findByPrestadorAndActivo(Object prestador, Object activo) {
		Session session = getSession();
		try {
			Usuario object = (Usuario) prestador;
			List<Objeto> results = (List<Objeto>) session
					.createCriteria(Objeto.class)
					.add(Restrictions.eq("prestador.usuarioId", object.getUsuarioId()))
					.add(Restrictions.eq("activo", activo))
					.list();
			log.debug("find by prestador and activo successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by prestador and activo failed", re);
			throw re;
		}
	}
	
	public List<Objeto> findByQuery(String queryString) {
		Session session = getSession();
		try {
			Query queryObject = session.createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by query failed", re);
			throw re;
		}
	}
	
	public List findAll() {
		log.debug("finding all Objeto instances");
		Session session = getSession();
		try {
			String queryString = "from Objeto";
			Query queryObject = session.createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Objeto merge(Objeto detachedInstance) {
		log.debug("merging Objeto instance");
		try {
			Objeto result = (Objeto) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Objeto instance) {
		log.debug("attaching dirty Objeto instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Objeto instance) {
		log.debug("attaching clean Objeto instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}