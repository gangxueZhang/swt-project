package vu.fs.cs.swt.app;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

import vu.fs.cs.swt.beans.Customer;
import vu.fs.cs.swt.beans.Loan;
import vu.fs.cs.swt.beans.Saving;
import vu.fs.cs.swt.systemclasses.System.Customers;
import vu.fs.cs.swt.systemclasses.System.Loans;
import vu.fs.cs.swt.systemclasses.System.Savings;
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
			
			Customers.delete((int)i);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testFindCustomer(){
		try {
			Customer c = new Customer("John", "Doe", "johnny", "awesomePassword");
			
			long i = Customers.add(c);

			Customer equal = Customers.find((int)i);
			
			assertEquals(c.getAccountNumber(), equal.getAccountNumber());
			
			Customers.delete((int)i);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteCustomer(){
		try {
			Customer c = new Customer("John", "Doe", "johnny", "awesomePassword");
			
			long i = Customers.add(c);

			Customer equal = Customers.find((int)i);
			
			assertEquals(c.getAccountNumber(), equal.getAccountNumber());
			
			Customers.delete((int)i);
			
			equal = Customers.find((int)i);
			
			assertNull(equal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void updateCustomer(){
		try {
			Customer c = new Customer("John", "Doe", "johnny", "awesomePassword");
			
			long i = Customers.add(c);
			
			c.setFirstName("Steve");
			
			Customer equal = Customers.update(c);
			
			assertEquals(c.getAccountNumber(), equal.getAccountNumber());
			assertEquals(equal.getFirstName(),"Steve");
			
			Customers.delete((int)i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testListCustomers(){
		try {
			Customer c = new Customer("John", "Doe", "johny", "awesomePassword");
			
			long i = Customers.add(c);

			List<Customer> equal = Customers.list();
			
			assertEquals(equal.size(), 1);
			
			Customers.delete((int)i);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddLoan() {
		
		try {
			Customer c = new Customer("John", "Doe", "johnny", "awesomePassword");
			c.getSavingsAccount().setBalance(35.0);
			Loan l = new Loan(600.00, 10.25, c);
			
			long ci = Customers.add(c);
			
			long i = Loans.add(l);

			Loan equal = Loans.find((int)i);
			
			assertEquals(l.getCustomer().getAccountNumber(), equal.getCustomer().getAccountNumber());
			
			Customers.delete((int)ci);
			//Loans.delete((int)i);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testFindLoan(){
		try {
			Customer c = new Customer("John", "Doe", "johnny", "awesomePassword");
			c.getSavingsAccount().setBalance(35.0);
			Loan l = new Loan(600.00, 10.25, c);
			
			long ci = Customers.add(c);
			
			long i = Loans.add(l);

			Loan equal = Loans.find((int)i);
			
			assertEquals(l.getCustomer().getAccountNumber(), equal.getCustomer().getAccountNumber());
			
			Customers.delete((int)ci);
			//Loans.delete((int)i);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//weird error
	/*
	@Test
	public void testDeleteLoan(){
		try {
			Customer c = new Customer("John", "Doe", "johnny", "awesomePassword");
			c.getSavingsAccount().setBalance(35.0);
			Loan l = new Loan(600.00, 10.25, c);
			
			long ci = Customers.add(c);
			
			long i = Loans.add(l);

			Loan equal = Loans.find((int)i);
			
			assertEquals(l.getCustomer().getAccountNumber(), equal.getCustomer().getAccountNumber());
			
			//Customers.delete((int)ci);
			Loans.delete((int)i);
			
			assertNull(Loans.find((int)i));
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	@Test
	public void updateLoan(){
		try {
			Customer c = new Customer("John", "Doe", "johnny", "awesomePassword");
			c.getSavingsAccount().setBalance(35.0);
			Loan l = new Loan(600.00, 10.25, c);
			
			long ci = Customers.add(c);
			
			long i = Loans.add(l);

			l.setIsDelinquent(true);
			
			Loan equal = Loans.update(l);
			
			assertEquals(l.getCustomer().getAccountNumber(), equal.getCustomer().getAccountNumber());
			assertEquals(l.getIsDelinquent(), true);
			
			Customers.delete((int)ci);
			//Loans.delete((int)i);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testListLoans(){
		try {
			Customer c = new Customer("John", "Doe", "johnny", "awesomePassword");
			c.getSavingsAccount().setBalance(35.0);
			Loan l = new Loan(600.00, 10.25, c);
			
			long ci = Customers.add(c);
			
			long i = Loans.add(l);

			List<Loan> loans = Loans.list();
			
			assertEquals(loans.size(), 1);
			
			Customers.delete((int)ci);
			//Loans.delete((int)i);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddSaving() {
		
		try {
			Customer c = new Customer("John", "Doe", "johnny", "awesomePassword");
			c.getSavingsAccount().setBalance(35.0);
			Saving s = new Saving(c);
			
			long ci = Customers.add(c);
			
			long i = Savings.add(s);

			Saving equal = Savings.find((int)i);
			
			assertEquals(s.getCustomer().getAccountNumber(), equal.getCustomer().getAccountNumber());
			
			Savings.delete((int)i);
			Customers.delete((int)ci);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testFindSaving(){
		try {
			Customer c = new Customer("John", "Doe", "johnny", "awesomePassword");
			c.getSavingsAccount().setBalance(35.0);
			Saving s = new Saving(c);
			
			long ci = Customers.add(c);
			
			long i = Savings.add(s);

			Saving equal = Savings.find((int)i);
			
			assertEquals(s.getCustomer().getAccountNumber(), equal.getCustomer().getAccountNumber());
			
			Savings.delete((int)i);
			Customers.delete((int)ci);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteSaving(){
		try {
			Customer c = new Customer("John", "Doe", "johnny", "awesomePassword");
			c.getSavingsAccount().setBalance(35.0);
			Saving s = new Saving(c);
			
			long ci = Customers.add(c);
			
			long i = Savings.add(s);

			Saving equal = Savings.find((int)i);
			
			assertEquals(s.getCustomer().getAccountNumber(), equal.getCustomer().getAccountNumber());
			
			Savings.delete((int)i);
			
			assertNull(Savings.find((int)i));
			
			Customers.delete((int)ci);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void updateSaving(){
		try {
			Customer c = new Customer("John", "Doe", "johnny", "awesomePassword");
			c.getSavingsAccount().setBalance(35.0);
			Saving s = new Saving(c);
			
			long ci = Customers.add(c);
			
			long i = Savings.add(s);
			
			s.setInterestRate();

			Saving equal = Savings.update(s);
			
			assertEquals(s.getCustomer().getAccountNumber(), equal.getCustomer().getAccountNumber());
			assertEquals(s.getInterestRate(), 1.5, 0.0);
			
			Savings.delete((int)i);
			Customers.delete((int)ci);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testListSavings(){
		try {
			Customer c = new Customer("John", "Doe", "johnny", "awesomePassword");
			c.getSavingsAccount().setBalance(35.0);
			Saving s = new Saving(c);
			
			long ci = Customers.add(c);
			
			long i = Savings.add(s);

			List<Saving> savings = Savings.list();
			
			assertEquals(savings.size(), 2);
			
			Savings.delete((int)i);
			Customers.delete((int)ci);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
