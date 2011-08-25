package test;

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
			room.setId(1);
			room = (Room)session.load(Room.class, room.getId());
			
			User u1 = new User();
			u1.setTruename("ÍõÎå");
			u1.setRoom(room);
			
			User u2 = new User();
			u2.setTruename("ÂíÁù");
			u2.setRoom(room);
			
			session.save(u1);
			session.save(u2);
			
			
			
			
			
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
			
			/*User user = (User)session.load(User.class, 1);
			System.out.println(user.getTruename());
			System.out.println(user.getRoom().getRoomName());*/
			
			Room room = (Room)session.load(Room.class, 1);
			System.out.println(room.getRoomName());
			Set<User> users = room.getUsers();
			
			for(User user : users){
				System.out.println(user.getTruename());
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
