package vu.fs.cs.swt.app;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;
import vu.fs.cs.swt.beans.Customer;
import vu.fs.cs.swt.beans.Loan;

public class LoansTest {
	
//All interest rates are in increments of .25%
	@Test
	public void testRates(){
		
		Loan l = new Loan();
		
		try {
			l.setInterestRate(6.50);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void testRates2(){
		Loan l = new Loan();
		
		try {
			l.setInterestRate(6.25);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRatesLowBoundary(){
		Loan l = new Loan();
		
		try {
			l.setInterestRate(6.00);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRatesHighBoundary(){
		Loan l = new Loan();
		
		try {
			l.setInterestRate(18.00);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRatesHighBoundaryFail(){
		Loan l = new Loan();
		
		try {
			l.setInterestRate(18.25);
			Assert.fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
	}
	
	@Test
	public void testRatesLowBoundaryFail(){
		Loan l = new Loan();
		
		try {
			l.setInterestRate(5.75);
			Assert.fail();
		} catch (Exception e) {

		}
	}
	
	@Test
	public void testRateIncrementFail(){
		Loan l = new Loan();
		
		try {
			l.setInterestRate(6.30);
			Assert.fail();
		} catch (Exception e) {

		}
		
	}
	
//The maximum amount of a loan at origination is $50,000. The minimum amount is $500.
	
	@Test
	public void testLoanMinimum(){
		
		Loan l = new Loan();
		
		try {
			l.setBalance(500.00);
			
			assertEquals(l.getBalance(), 500.00, 0.0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testLoanMaximum(){
		
		Loan l = new Loan();
		
		try {
			l.setBalance(50000.00);
			
			assertEquals(l.getBalance(), 50000.00, 0.0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testLoanBelow(){
		
		Loan l = new Loan();
		
		try {
			l.setBalance(490.00);
			
			Assert.fail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
		
	}
	
	@Test
	public void testLoanAbove(){
		Loan l = new Loan();
		
		try {
			l.setBalance(60000.00);
			
			Assert.fail();
		} catch (Exception e) {

		}
	}
	
	@Test
	public void testLoanRandomValue(){
		
		Loan l = new Loan();
		
		try {
			l.setBalance(1520.00);
			
			assertEquals(l.getBalance(), 1520.00, 0.0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testAddLoanToCustomer(){
		Loan l = new Loan();
		
		try {
			l.setBalance(500.00);
			
			Customer c = new Customer();
			c.addLoan(l);
			
			assertTrue(c.getLoans().size() == 1);
		} catch (Exception e) {

		}
		
	}

	
	@Test
	public void testLoan() {
		try {
			Loan l = new Loan(600.00, 10.25, new Customer());
			
			assertEquals(l.getBalance(), 600.00, 0.0);
			assertEquals(l.getInterestRate(), 10.25, 0.0);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void testId() {
		Loan l = new Loan();
		
		l.setId(1578984651);
		
		assertEquals(l.getId(), 1578984651);
	}


	@Test
	public void testGetBalance() {
		Loan l = new Loan();
		
		try {
			l.setBalance(600.00);
			assertEquals(l.getBalance(), 600.00, 0.0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSetBalance() {
		Loan l = new Loan();
		
		try {
			l.setBalance(600.00);
			
			assertNotNull(l.getBalance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetMinimumPayment() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMinimumPayment() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetIsDelinquent() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetIsDelinquent() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCustomer() {
		try {
			Customer customer = new Customer("John", "Doe", "johnny", "awesomePassword");
			String accountNumber = customer.getAccountNumber();
			
			Loan l = new Loan();
			l.setCustomer(customer);
			
			assertEquals(l.getCustomer().getAccountNumber(), accountNumber);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void testSetCustomer() {
		
		try {
			Customer customer = new Customer("John", "Doe", "johnny", "awesomePassword");
			
			Loan l = new Loan();
			l.setCustomer(customer);
			
			assertNotNull(l.getCustomer());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
