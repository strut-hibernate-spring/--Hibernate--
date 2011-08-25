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
			// 首先获取配置信息
			cfg =  new Configuration().configure();
			
			// 创建Session Factory			
			sf = cfg.buildSessionFactory();
		} catch (HibernateException e) {
			// System.out.println("SessionFactory创建失败");
			e.printStackTrace();
			throw new MyRuntimeException("SessionFactory创建失败" , e);
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
