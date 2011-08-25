package test;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import util.HibernateSessionFactory;
import vo.Student;
import vo.Worker;

public class TestHibernate extends TestCase {
	
	public void testSave2(){
		Session session = null;
		
		
		
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			
			Student s1 = new Student();
			s1.setName("小明");
			s1.setAge(22);
			s1.setSex("男");
			s1.setSchool("北航");
			
			session.save(s1);
			
			Worker w1 = new Worker();
			w1.setName("老王");
			w1.setAge(55);
			w1.setSex("男");
			w1.setFactory("北汽");
			session.save(w1);
			
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
