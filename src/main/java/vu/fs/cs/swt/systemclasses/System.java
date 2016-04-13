package vu.fs.cs.swt.systemclasses;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.Session;

import vu.fs.cs.swt.util.HibernateUtil;
import vu.fs.cs.swt.beans.Customer;

public final class System {
	private static Transaction t;
	private static Session s;
	
	private static void openSession() {
		if(s!=null){
			s.close();
			s=null;
		}
		s=HibernateUtil.getSessionFactory().openSession();
		t=s.beginTransaction();
	}
	
	private static void closeSession() {
		if(s!=null){
			s.close();
			s=null;
			t=null;
		}
	}
	
	private System() {}
	
	public static class Customers {
		public static Long add(Customer c) {
			openSession();
			
			Long id= (Long) s.save(c);
			t.commit();
			
			closeSession();
			return id;	
		}
		
		public static Customer find(int id) {
			openSession();
			Customer c = (Customer) s.get(Customer.class,new Long(id));
			t.commit();
			
			closeSession();
			return c;
		}
		
		public static void delete(int id) {
			openSession();
			Object instance = s.load(Customer.class, new Long(id));
			if (instance != null)
				s.delete(instance);
			
			t.commit();
			closeSession();
		}
		
		public static Customer update(Customer c) {
			openSession();
			
			s.update(c);
			
			t.commit();
			closeSession();
			
			long l=c.getId();
			int id=(int) l;
			return find(id);
		}
		
		public static List<Customer> list() {
			openSession();
			List<Customer> customers=s.createCriteria(Customer.class).list();
			t.commit();
			return customers;
		}
	}
}