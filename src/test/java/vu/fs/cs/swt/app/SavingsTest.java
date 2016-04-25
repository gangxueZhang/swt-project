package vu.fs.cs.swt.app;

import static org.junit.Assert.*;

import org.junit.Test;

import vu.fs.cs.swt.beans.Customer;
import vu.fs.cs.swt.beans.Loan;

public class SavingsTest {

	
//Savings rate is one fourth loan interest rate
	@Test
	public void testInterestRate(){
		
		try {
			Customer c = new Customer("John", "Doe", "johnny", "awesomePassword");
			Loan l = new Loan();
	
			l.setInterestRate(12.00);
			c.addLoan(l);
			c.getSavingsAccount().setInterestRate();;
			
			
			assertEquals(c.getSavingsAccount().getInterestRate(), l.getInterestRate() / 4, 0.0);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
//test that savings account does have an interest rate even if there are no loans
	@Test
	public void testSavingsInterest(){
		
		try {
			Customer c = new Customer("John", "Doe", "johnny", "awesomePassword");
			c.getSavingsAccount().setInterestRate();
			
			System.out.println(c.getSavingsAccount().getInterestRate());
			
			assertEquals(c.getSavingsAccount().getInterestRate(), 1.5, 0.0);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	@Test
	public void testSaving() {
		fail("Not yet implemented");
	}

	@Test
	public void testSavingCustomer() {
		fail("Not yet implemented");
	}

	@Test
	public void testSavingDoubleCustomer() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetId() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBalance() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetBalance() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetInterestRate() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetInterestRate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCustomer() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCustomer() {
		fail("Not yet implemented");
	}

}
