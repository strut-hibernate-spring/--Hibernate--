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
			s1.setName("С��");
			s1.setAge(22);
			s1.setSex("��");
			s1.setSchool("����");
			
			session.save(s1);
			
			Worker w1 = new Worker();
			w1.setName("����");
			w1.setAge(55);
			w1.setSex("��");
			w1.setFactory("����");
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
