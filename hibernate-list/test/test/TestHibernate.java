package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import util.HibernateSessionFactory;
import vo.Student;
import vo.Teacher;

public class TestHibernate extends TestCase {
	
	public void testSave2(){
		Session session = null;
		
		
		
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			
			Student s1 = new Student();
			s1.setName("С��");
			s1.setAge(22);
			
			Student s2 = new Student();
			s2.setName("С��");
			s2.setAge(22);
			
			Student s3 = new Student();
			s3.setName("С��");
			s3.setAge(22);
			
			Student s4 = new Student();
			s4.setName("С��");
			s4.setAge(22);
			
			Teacher t1 = new Teacher();
			t1.setName("����");
			t1.setAge(44);
			
			Teacher t2 = new Teacher();
			t2.setName("����");
			t2.setAge(43);
			
			List<Student> stus1 = new ArrayList<Student>();
			stus1.add(s4);
			stus1.add(s3);
			stus1.add(s2);
			stus1.add(s1);
			
			List<Student> stus2 = new ArrayList<Student>();
			stus2.add(s4);
			stus2.add(s3);
			
			t1.setStudents(stus1);
			t2.setStudents(stus2);
			
			session.save(t1);
			session.save(t2);
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if(session.getTransaction().isActive())
				session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession(session);
		}
	}
	
	public void testSave1(){
		Session session = null;
		
		
		
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			
			
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
			
			Teacher t1 = (Teacher)session.load(Teacher.class, 1);
			System.out.println(t1.getName());
			
			for(Student s : t1.getStudents()){
				System.out.println("  --" + s.getName());
			}
			
			
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if(session.getTransaction().isActive())
				session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession(session);
		}
	}
	
	public void testQuery2(){
		Session session = null;
		
		
		
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			
						
			
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
