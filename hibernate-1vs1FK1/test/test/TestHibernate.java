package test;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import util.HibernateSessionFactory;
import vo.Login;
import vo.User;

public class TestHibernate extends TestCase {
	
	public void testSave2(){
		Session session = null;
		
		
		
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			
			User user = new User();
			user.setTruename("ÀîËÄ");
			user.setPhone("8888888");
			user.setSex(1);
			
			Login login = new Login();
			login.setUsername("lisi");
			login.setPassword("123456");
			login.setUser(user);
			
			// session.save(user);
			session.save(login);
			
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if(session.getTransaction().isActive())
				session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession(session);
		}
	}
	
}
