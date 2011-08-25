package test;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import util.HibernateSessionFactory;
import vo.Room;
import vo.User;

public class TestHibernate extends TestCase {
	
	public void testSave2(){
		Session session = null;
		
		
		
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			
			Room room = new Room();
			room.setRoomName("�����");
			
			User u1 = new User();
			u1.setTruename("�ϴ�");
			
			User u2 = new User();
			u2.setTruename("�϶�");
			
			User u3 = new User();
			u3.setTruename("����");
			
			User u4 = new User();
			u4.setTruename("����");
			
			Set<User> users = new HashSet<User>();
			users.add(u1);
			users.add(u2);
			users.add(u3);
			users.add(u4);
			
			room.setUsers(users);
			
			session.save(room);
			
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
			
			Room room = new Room();
			room.setRoomName("�ͻ���");
			
			User u1 = new User();
			u1.setTruename("����");
			u1.setRoom(room);
			
			User u2 = new User();
			u2.setTruename("����");
			u2.setRoom(room);
			
			User u3 = new User();
			u3.setTruename("����");
			u3.setRoom(room);
			
			User u4 = new User();
			u4.setTruename("�ϰ�");
			u4.setRoom(room);
			
			
			
			
			session.save(u1);
			session.save(u2);
			session.save(u3);
			session.save(u4);
			
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
			
			Room room = (Room)session.load(Room.class, 1);
			System.out.println(room.getRoomName());
			
			for(User user : room.getUsers()){
				System.out.println(" ----" + user.getTruename());
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
			
			User user = (User)session.load(User.class, 5);
			System.out.println(user.getTruename());
			System.out.println(user.getRoom().getRoomName());
			
			
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
