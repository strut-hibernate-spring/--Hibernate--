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
			
			
			
			Login login = new Login();
			login.setUsername("zhangsan");
			login.setPassword("123456");
			
			User user = new User();
			user.setTruename("ÕÅÈý");
			user.setPhone("8888888");
			user.setSex(1);
			user.setLogin(login);
			
			
			
			// session.save(user);
			session.save(user);
			
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if(session.getTransaction().isActive())
				session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession(session);
		}
	}
	
	public void testQuery(){
		Session session = null;
		
		
		
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			
			/*Login login = (Login)session.get(Login.class, 3);*/
			
			User user = (User)session.load(User.class, 4);
			
			System.out.println(user.getTruename());
			System.out.println(user.getLogin().getUsername());
			
			
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
