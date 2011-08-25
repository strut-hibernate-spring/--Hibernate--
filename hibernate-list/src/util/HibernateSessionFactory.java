package util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import exception.MyRuntimeException;

public class HibernateSessionFactory {
	private static Configuration cfg = null;
	private static SessionFactory sf = null;
	
	
	static {
		try {
			// ���Ȼ�ȡ������Ϣ
			cfg =  new Configuration().configure();
			
			// ����Session Factory			
			sf = cfg.buildSessionFactory();
		} catch (HibernateException e) {
			// System.out.println("SessionFactory����ʧ��");
			e.printStackTrace();
			throw new MyRuntimeException("SessionFactory����ʧ��" , e);
		}
	}
	
	public static Session getSession() {
		Session session = null;
		if(session == null || session.isOpen()==false){
			session = sf.openSession();
		}
		
		return session;
	}
	
	public static void closeSession(Session session){
		try {
			if(session!=null)
				session.close();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
