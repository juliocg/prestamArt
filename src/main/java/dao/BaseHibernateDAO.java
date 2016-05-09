package dao;

import hibernate.HibernateSessionFactory;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	/*@Autowired
	private SessionFactory sessionFactory;*/

	
	public Session getSession() {
		return HibernateSessionFactory.getSession();
		//return sessionFactory.getCurrentSession();

	}
	
}