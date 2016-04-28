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
	
	@Test
	public void testAddCustomer() {
		
		try {
			Customer c = new Customer("John", "Doe", "johnny", "awesomePassword");
			
			long i = Customers.add(c);

			Customer equal = Customers.find((int)i);
			
			Customers.delete((int)i);
			
			assertNotNull(equal);
			
			
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
			
			Customers.delete((int)i);	
			
			assertEquals(c.getAccountNumber(), equal.getAccountNumber());
			
			
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
			
			Customers.delete((int)i);
			
			Customer equal = Customers.find((int)i);
			
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
			String acc = c.getAccountNumber();
			
			Customers.delete((int)i);
			
			assertEquals(acc, equal.getAccountNumber());
			assertEquals(equal.getFirstName(),"Steve");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testListCustomers(){
		try {
			Customer c = new Customer("John", "Doe", "johnny", "awesomePassword");
			
			long i = Customers.add(c);

			List<Customer> equal = Customers.list();
			
			Customers.delete((int)i);
			
			assertEquals(equal.size(), 1);

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
			//you no longer need to explicitly add a loan to a customer's list of loans,
			//since this is now done in the constructor
			
			long ci = Customers.add(c);
			long i = l.getId();

			Loan equal = Loans.find((int)i);

			Customers.delete((int)ci);
			
			assertNotNull(equal);	
			
			
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
			
			long i = l.getId();

			Loan equal = Loans.find((int)i);
			String acc = l.getCustomer().getAccountNumber();

			Customers.delete((int)ci);
			
			assertEquals(acc, equal.getCustomer().getAccountNumber());	
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteLoan(){
		try {
			Customer c = new Customer("John", "Doe", "johnny", "awesomePassword");
			c.getSavingsAccount().setBalance(35.0);
			Loan l = new Loan(600.00, 10.25, c);
			
			long ci = Customers.add(c);
			long i = l.getId();
			
			//you no longer need to delete the loan itself, just remove it from the customer's list and update him
			//these two lines can also be viewed as redundant, because when you delete the customer, his loans and
			//savings will be automatically deleted with him, but just so we know how to delete a loan itself
			//i've left these two lines, so don't remove them :)
			c.removeLoan(l);
			c = Customers.update(c);

			Customers.delete((int)ci);			
			
			Loan equal = Loans.find((int)i);

			assertNull(equal);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void updateLoan(){
		try {
			Customer c = new Customer("John", "Doe", "johnny", "awesomePassword");
			c.getSavingsAccount().setBalance(35.0);
			Loan l = new Loan(600.00, 10.25, c);
			
			long ci = Customers.add(c);

			l.setIsDelinquent(true);
			
			Loan equal = Loans.update(l);
			String acc = l.getCustomer().getAccountNumber();
			Boolean del = l.getIsDelinquent();

			Customers.delete((int)ci);
			
			assertEquals(acc, equal.getCustomer().getAccountNumber());
			assertEquals(del, true);	
			
			
			
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

			List<Loan> loans = Loans.list();

			Customers.delete((int)ci);
			
			assertEquals(loans.size(), 1);				
			
			
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
			
			long ci = Customers.add(c);
			
			long i = c.getSavingsAccount().getId();

			Saving equal = Savings.find((int)i);
			
			Customers.delete((int)ci);
			
			assertNotNull(equal);		
			
			
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
			
			long ci = Customers.add(c);
			
			long i = c.getSavingsAccount().getId();

			Saving equal = Savings.find((int)i);
			String acc = c.getAccountNumber();

			Customers.delete((int)ci);
			
			assertEquals(acc, equal.getCustomer().getAccountNumber());			
			
			
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
			
			long ci = Customers.add(c);
			
			long i = c.getSavingsAccount().getId();
			
			Customers.delete((int)ci);
			
			Saving equal = Savings.find((int)i);
			
			assertNull(equal);
			
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
			
			long ci = Customers.add(c);
			
			long i = c.getSavingsAccount().getId();
			
			c.getSavingsAccount().setInterestRate();

			Saving equal = Savings.update(c.getSavingsAccount());
			String acc = c.getAccountNumber();
			Double interest = c.getSavingsAccount().getInterestRate();

			Customers.delete((int)ci);
			
			assertEquals(acc, equal.getCustomer().getAccountNumber());
			assertEquals(interest, 1.5, 0.0);			
			
			
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
			
			long ci = Customers.add(c);
			
			long i = c.getSavingsAccount().getId();

			List<Saving> savings = Savings.list();

			Customers.delete((int)ci);
			
			assertEquals(savings.size(), 1);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
