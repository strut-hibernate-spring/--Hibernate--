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
			// ���Ȼ�ȡ������Ϣ
			cfg =  new Configuration().configure();
			
			// ����Session Factory			
			sf = cfg.buildSessionFactory();
			
			// ��ȡSession
			session =  sf.openSession();
			User user = new User();
			user.setUsername("����");
			user.setPassword("123456");
			user.setAddress("�����������йش�����");
			user.setAge(23);
			user.setSex(true);
			user.setPhone("010-88888888");
			
			// ��ʼ����
			ts =  session.beginTransaction();
			// �������
			session.save(user);
			// �����ύ
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
			// �������
			user.setUsername("С��");
			user.setPassword("123456");
			// �ڴ�֮ǰuser��˲ʱ״̬��
			
			// ��saveʱ��user��������Session����Ȼ���ó־û���ʶ����ʱ��user�ǳ־û�״̬
			session.save(user);
			
			// �޸�һ��user�����֣���ʱ���Զ�����SQL��䣬�޸����ݿ��е��ֶ�
			user.setUsername("С��");
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
			// �������
			user.setUsername("����");
			user.setPassword("123456");
			// �ڴ�֮ǰuser��˲ʱ״̬��
			
			// ��saveʱ��user��������Session����Ȼ���ó־û���ʶ����ʱ��user�ǳ־û�״̬
			session.save(user);
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if(session.getTransaction().isActive())
				session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			HibernateSessionFactory.closeSession(session);
		}
		
		//����session�رգ���ôuser����й�
		
		// �޸�һ��user�����֣����ڴ�ʱ���й�״̬���򲻻ᷢ��SQL���
		user.setUsername("С����");
		
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
						
			// ��saveʱ��user��������Session����user��Ϊ�־û�״̬
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
			
			// �Զ��������˳־û���ʶ����ʱ���ڴ˱�ʶ�����ݿ����Ѿ����ڣ���user��Ϊ�й�״̬
			user.setId(1);
			user.setUsername("���ƾ�");
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
			
			// �Զ��������˳־û���ʶ����ʱ���ڴ˱�ʶ�����ݿ����Ѿ�����
			user.setId(100);
			user.setUsername("���ƾ�");
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
			
			// ��get()����ִ��ʱ�����������������Ƿ���ڳ־û���ʶΪ1������������������ֱ�Ӵ�
			// �����л�ȡ�˶��󣬷���˵�������ڣ���ô��������sql�����в�ѯ
			// �˴��Ļ�����Session����ģ����ǳ�֮Ϊһ������
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
			
			// ��get()����ִ��ʱ�����������������Ƿ���ڳ־û���ʶΪ1������������������ֱ�Ӵ�
			// �����л�ȡ�˶��󣬷���˵�������ڣ���ô��������sql�����в�ѯ
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
			
			// ��get()����ִ��ʱ�����������������Ƿ���ڳ־û���ʶΪ1������������������ֱ�Ӵ�
			// �����л�ȡ�˶��󣬷���˵�������ڣ���ô��������sql�����в�ѯ
			// �˴��Ļ�����Session����ģ����ǳ�֮Ϊһ������
			
			// load�����ӳټ��أ�Ĭ�ϲ�����SQL��䣬ֻ�е����ô˶���ĳ�Աʱ�ŷ���SQL���
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
			
			// ��get()����ִ��ʱ�����������������Ƿ���ڳ־û���ʶΪ1������������������ֱ�Ӵ�
			// �����л�ȡ�˶��󣬷���˵�������ڣ���ô��������sql�����в�ѯ
			// �˴��Ļ�����Session����ģ����ǳ�֮Ϊһ������
			
			// load�����ӳټ��أ�Ĭ�ϲ�����SQL��䣬ֻ�е����ô˶���ĳ�Աʱ�ŷ���SQL���
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
			
			//  ������ɾ��֮ǰ�Ȳ�ѯ��Ȼ��ɾ��
			user = (User)session.load(User.class, 7);
			// System.out.println(user.getUsername());
			session.delete(user);
			// ɾ����Ϻ�user���˲ʱ״̬
			
			user.setId(8); // ��Ϊ�й�״̬
			
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
			
			// ��Ϊ�й�״̬
			user1.setId(12);
			user1.setUsername("����");
		   
			
			
			
			// ��Ϊ�й�״̬
			user2.setId(12);
			user2.setUsername("����");
			
			
			
			
			session.merge(user1); // ���ı�״̬��������й�״̬��Ϊ�־û�״̬			
			session.merge(user2); // ���ı�״̬��������й�״̬��Ϊ�־û�״̬			
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
