package vu.fs.cs.swt.systemclasses;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.Session;

import vu.fs.cs.swt.util.HibernateUtil;
import vu.fs.cs.swt.beans.*;

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
			closeSession();
			return customers;
		}
	}
	
	public static class Loans {
		public static Long add(Loan l) {
			openSession();
			
			Long id= (Long) s.save(l);
			t.commit();
			
			closeSession();
			return id;	
		}
		
		public static Loan find(int id) {
			openSession();
			Loan l = (Loan) s.get(Loan.class,new Long(id));
			t.commit();
			
			closeSession();
			return l;
		}
		
		public static void delete(int id) {
			openSession();
			Object instance = s.load(Loan.class, new Long(id));
			if (instance != null)
				s.delete(instance);
			
			t.commit();
			closeSession();
		}
		
		public static Loan update(Loan l) {
			openSession();
			
			s.update(l);
			
			t.commit();
			closeSession();
			
			long lo=l.getId();
			int id=(int) lo;
			return find(id);
		}
		
		public static List<Loan> list() {
			openSession();
			List<Loan> loans=s.createCriteria(Loan.class).list();
			t.commit();
			closeSession();
			return loans;
		}
	}
	
	public static class Savings {
		public static Long add(Saving sa) {
			openSession();
			
			Long id= (Long) s.save(sa);
			t.commit();
			
			closeSession();
			return id;	
		}
		
		public static Saving find(int id) {
			openSession();
			Saving sa = (Saving) s.get(Saving.class,new Long(id));
			t.commit();
			
			closeSession();
			return sa;
		}
		
		public static void delete(int id) {
			openSession();
			Object instance = s.load(Saving.class, new Long(id));
			if (instance != null)
				s.delete(instance);
			
			t.commit();
			closeSession();
		}
		
		public static Saving update(Saving sa) {
			openSession();
			
			s.update(sa);
			
			t.commit();
			closeSession();
			
			long l=sa.getId();
			int id=(int) l;
			return find(id);
		}
		
		public static List<Saving> list() {
			openSession();
			List<Saving> savings=s.createCriteria(Saving.class).list();
			t.commit();
			closeSession();
			return savings;
		}
	}
}