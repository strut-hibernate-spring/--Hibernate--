package test;

import java.util.HashSet;
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
			s1.setName("小明");
			s1.setAge(22);
			
			Student s2 = new Student();
			s2.setName("小刚");
			s2.setAge(22);
			
			Student s3 = new Student();
			s3.setName("小白");
			s3.setAge(22);
			
			Student s4 = new Student();
			s4.setName("小猪");
			s4.setAge(22);
			
			Teacher t1 = new Teacher();
			t1.setName("老王");
			t1.setAge(44);
			
			Teacher t2 = new Teacher();
			t2.setName("老张");
			t2.setAge(43);
			
			Set<Student> stus1 = new HashSet<Student>();
			stus1.add(s1);
			stus1.add(s2);
			stus1.add(s3);
			
			Set<Student> stus2 = new HashSet<Student>();
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
			
			
			Student s1 = new Student();
			s1.setName("大明");
			s1.setAge(22);
			
			Student s2 = new Student();
			s2.setName("大刚");
			s2.setAge(22);
			
			Student s3 = new Student();
			s3.setName("大白");
			s3.setAge(22);
			
			Student s4 = new Student();
			s4.setName("老猪");
			s4.setAge(22);
			
			Teacher t1 = new Teacher();
			t1.setName("老刘");
			t1.setAge(44);
			
			Teacher t2 = new Teacher();
			t2.setName("老赵");
			t2.setAge(43);
			
			Teacher t3 = new Teacher();
			t3.setName("老孙");
			t3.setAge(43);
			
			Set<Teacher> teas1 = new HashSet<Teacher>();
			teas1.add(t1);
			teas1.add(t2);
			
			Set<Teacher> teas2 = new HashSet<Teacher>();
			teas2.add(t3);
			teas2.add(t2);
			
			Set<Teacher> teas3 = new HashSet<Teacher>();
			teas3.add(t1);
			teas3.add(t2);
			teas3.add(t3);
			
			s1.setTeachers(teas1);
			s2.setTeachers(teas2);
			s3.setTeachers(teas3);
			s4.setTeachers(teas2);
			
			session.save(s1);
			session.save(s2);
			session.save(s3);
			session.save(s4);
			
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
			
			Student s = (Student)session.load(Student.class, 6);
			System.out.println(s.getName());
			
			for(Teacher t : s.getTeachers()){
				System.out.println(" --" + t.getName());
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
	
}
