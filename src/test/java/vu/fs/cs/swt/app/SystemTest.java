package vu.fs.cs.swt.app;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import vu.fs.cs.swt.beans.Customer;
import vu.fs.cs.swt.systemclasses.System.Customers;
import vu.fs.cs.swt.util.HibernateUtil;

public class SystemTest {
	private static Transaction t;
	private static Session s;
	
	@Before
	public void openSession() {
		if(s!=null){
			s.close();
			s=null;
		}
		s=HibernateUtil.getSessionFactory().openSession();
		t=s.beginTransaction();
	}

	@Test
	public void testAddCustomer() {
		
		try {
			Customer c = new Customer("John", "Doe", "johnny", "awesomePassword");
			
			long i = Customers.add(c);

			Customer equal = Customers.find((int)i);
			
			assertEquals(c.getAccountNumber(), equal.getAccountNumber());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
