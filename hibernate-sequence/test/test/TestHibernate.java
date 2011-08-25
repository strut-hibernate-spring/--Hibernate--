package test;

import junit.framework.TestCase;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import util.HibernateSessionFactory;
import vo.User;

public class TestHibernate extends TestCase {
	public void testSave1(){
		
		Configuration cfg = null;
		SessionFactory sf = null;
		Session session = null;
		Transaction ts = null;
		
		try {
			// 首先获取配置信息
			cfg =  new Configuration().configure();
			
			// 创建Session Factory			
			sf = cfg.buildSessionFactory();
			
			// 获取Session
			session =  sf.openSession();
			User user = new User();
			user.setUsername("李四");
			user.setPassword("123456");
			user.setAddress("北京海腚区中关村天桥");
			user.setAge(23);
			user.setSex(true);
			user.setPhone("010-88888888");
			
			// 开始事务
			ts =  session.beginTransaction();
			// 保存对象
			session.save(user);
			// 事务提交
			ts.commit();
		} catch (HibernateException e) {
			if(ts.isActive())
				ts.rollback();
			e.printStackTrace();
		}finally{
			try {
				if(session!=null)
					session.close();
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	public void testSave2(){
		Session session = null;
		
		User user = new User();
		
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			// 保存对象
			user.setUsername("小明");
			user.setPassword("123456");
			// 在此之前user是瞬时状态，
			
			// 在save时，user对象纳入Session管理，然后获得持久化标识，此时的user是持久化状态
			session.save(user);
			
			// 修改一下user的名字，此时会自动发出SQL语句，修改数据库中的字段
			user.setUsername("小明");
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if(session.getTransaction().isActive())
				session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession(session);
		}
	}
	
	public void testSave3(){
		Session session = null;
		
		User user = new User();
		
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			// 保存对象
			user.setUsername("王八");
			user.setPassword("123456");
			// 在此之前user是瞬时状态，
			
			// 在save时，user对象纳入Session管理，然后获得持久化标识，此时的user是持久化状态
			session.save(user);
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if(session.getTransaction().isActive())
				session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession(session);
		}
		
		//由于session关闭，那么user变成托管
		
		// 修改一下user的名字，由于此时是托管状态，则不会发出SQL语句
		user.setUsername("小王八");
		
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
						
			// 在save时，user对象纳入Session管理，user变为持久化状态
			session.update(user);
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if(session.getTransaction().isActive())
				session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession(session);
		}
		
		
	}
	
	public void testSave4(){
		Session session = null;
		
		User user = new User();
		
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			
			// 对对象设置了持久化标识，此时由于此标识在数据库中已经存在，则user变为托管状态
			user.setId(1);
			user.setUsername("老牌九");
			user.setPassword("123456");
			
			
			session.update(user);
			
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if(session.getTransaction().isActive())
				session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession(session);
		}
		
		
	}
	
	public void testSave5(){
		Session session = null;
		
		User user = new User();
		
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			
			// 对对象设置了持久化标识，此时由于此标识在数据库中已经存在
			user.setId(100);
			user.setUsername("老牌九");
			user.setPassword("123456");
			
			
			session.saveOrUpdate(user);
			
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if(session.getTransaction().isActive())
				session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession(session);
		}
		
		
	}
	
	public void testQuery1(){
		Session session = null;
		
		User user = new User();
		
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			
			// 在get()方法执行时，首先搜索缓存中是否存在持久化标识为1的这个对象，如果存在则直接从
			// 缓存中获取此对象，否则说明不存在，那么立即发送sql语句进行查询
			// 此处的缓存是Session级别的，我们称之为一级缓存
			user = (User)session.get(User.class, 1);
			
			System.out.println(user.getUsername() + ":" + user.getPassword());
			
					
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if(session.getTransaction().isActive())
				session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession(session);
		}
		
		
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			
			// 在get()方法执行时，首先搜索缓存中是否存在持久化标识为1的这个对象，如果存在则直接从
			// 缓存中获取此对象，否则说明不存在，那么立即发送sql语句进行查询
			user = (User)session.get(User.class, 1);
			
			System.out.println(user.getUsername() + ":" + user.getPassword());
			
					
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
		
		User user = new User();
		
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			
			// 在get()方法执行时，首先搜索缓存中是否存在持久化标识为1的这个对象，如果存在则直接从
			// 缓存中获取此对象，否则说明不存在，那么立即发送sql语句进行查询
			// 此处的缓存是Session级别的，我们称之为一级缓存
			
			// load属于延迟加载，默认不发送SQL语句，只有当调用此对象的成员时才发送SQL语句
			user = (User)session.load(User.class, 5);
			
			System.out.println(user.getUsername() + ":" + user.getPassword());
			
					
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if(session.getTransaction().isActive())
				session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession(session);
		}	
		
	}
	
	public void testQuery3(){
		Session session = null;
		
		User user = new User();
		
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			
			// 在get()方法执行时，首先搜索缓存中是否存在持久化标识为1的这个对象，如果存在则直接从
			// 缓存中获取此对象，否则说明不存在，那么立即发送sql语句进行查询
			// 此处的缓存是Session级别的，我们称之为一级缓存
			
			// load属于延迟加载，默认不发送SQL语句，只有当调用此对象的成员时才发送SQL语句
			user = (User)session.load(User.class, 500);
			
			System.out.println(user.getUsername() + ":" + user.getPassword());
			
					
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if(session.getTransaction().isActive())
				session.getTransaction().rollback();
			e.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
			
		}finally{
			HibernateSessionFactory.closeSession(session);
		}			
	}
	
	public void testDelete(){
		Session session = null;
		
		User user = new User();
		
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			
			//  建议在删除之前先查询，然后删除
			user = (User)session.load(User.class, 7);
			// System.out.println(user.getUsername());
			session.delete(user);
			// 删除完毕后user变成瞬时状态
			
			user.setId(8); // 变为托管状态
			
			session.load(User.class, user.getId());
			
			user.setUsername("asdfasdfsadf");	
			
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if(session.getTransaction().isActive())
				session.getTransaction().rollback();
			e.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
			
		}finally{
			HibernateSessionFactory.closeSession(session);
		}			
	}
	
	public void testMerge(){
		Session session = null;
		
		User user1 = new User();
		User user2 = new User();
		
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			
			// 变为托管状态
			user1.setId(12);
			user1.setUsername("大明");
		   
			
			
			
			// 变为托管状态
			user2.setId(12);
			user2.setUsername("二明");
			
			
			
			
			session.merge(user1); // 不改变状态，不会从托管状态变为持久化状态			
			session.merge(user2); // 不改变状态，不会从托管状态变为持久化状态			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if(session.getTransaction().isActive())
				session.getTransaction().rollback();
			e.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
			
		}finally{
			HibernateSessionFactory.closeSession(session);
		}			
	}
	
}
