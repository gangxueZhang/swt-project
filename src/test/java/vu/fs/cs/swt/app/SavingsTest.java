package vu.fs.cs.swt.app;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import vu.fs.cs.swt.beans.Customer;
import vu.fs.cs.swt.beans.Loan;
import vu.fs.cs.swt.beans.Saving;

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
			
			assertEquals(c.getSavingsAccount().getInterestRate(), 1.5, 0.0);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}


	@Test
	public void testSavingCustomer() {
		try {
			Customer c = new Customer("John", "Doe", "johnny", "awesomePassword");
			Saving s = new Saving(c);
			
			assertEquals(s.getCustomer().getAccountNumber(), c.getAccountNumber());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSavingDoubleCustomer() {
		try {
			Customer customer = new Customer("John", "Doe", "johnny", "awesomePassword");
			Saving s = new Saving(30.00, customer);
			assertEquals(s.getBalance(), 30.0, 0.0);
			assertEquals(s.getCustomer().getAccountNumber(), customer.getAccountNumber());
		} catch (Exception e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetBalance() {
		
		Saving s = new Saving();
		try {
			s.setBalance(30.00);
			assertNotNull(s.getBalance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void testSetBalance() {
		Saving s = new Saving();
		assertNull(s.getBalance());
		try {
			s.setBalance(30.00);
			assertEquals(s.getBalance(), 30.00, 0.0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testSetNegativeBalance(){
		Saving s = new Saving();
		try {
			s.setBalance(-15.00);
			Assert.fail();
		} catch (Exception e){
			
		}
	}

	@Test
	public void testGetInterestRate() {
		try {
			Customer customer = new Customer("John", "Doe", "johnny", "awesomePassword");
			Saving s = new Saving(customer);
			assertEquals(s.getInterestRate(), 0.0, 0.0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void testSetInterestRate() {
		try {
			Customer customer = new Customer("John", "Doe", "johnny", "awesomePassword");
			Saving s = new Saving(customer);
			assertEquals(s.getInterestRate(), 0.0, 0.0);
			s.setInterestRate();
			assertEquals(s.getInterestRate(), 1.5, 0.0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetCustomer() {
		try {
			Customer customer = new Customer("John", "Doe", "johnny", "awesomePassword");
			String accountNumber = customer.getAccountNumber();
			
			Saving s = new Saving();
			s.setCustomer(customer);
			
			assertEquals(s.getCustomer().getAccountNumber(), accountNumber);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void testSetCustomer() {
		
		try {
			Customer customer = new Customer("John", "Doe", "johnny", "awesomePassword");
			
			Saving s = new Saving();
			
			assertNull(s.getCustomer());
			
			s.setCustomer(customer);
			
			assertNotNull(s.getCustomer());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void setIncreaseBalanceWithNegativeNumber() {
		Saving s = new Saving();
		
		try{
			s.increaseBalance(-22.00);
			Assert.fail();
		}catch (Exception e){
			
		}
	}
	
	@Test
	public void testIncreaseBalance(){
		Saving s = new Saving();
		
		try{
			s.setBalance(25.00);
			assertEquals(s.getBalance(), 25.00, 0.0);
			s.increaseBalance(22.00);
			assertEquals(s.getBalance(), 47.00, 0.0);
		}catch (Exception e){
			
		}
	}
	
	@Test
	public void testIncreaseBalance2(){
		Saving s = new Saving();
		
		try{
			s.setBalance(25.0);
			assertEquals(s.getBalance(), 25.0, 0.0);
			s.increaseBalance(22.1);
			assertEquals(s.getBalance(), 47.1, 0.0);
		}catch (Exception e){
			
		}
	}
	
	@Test
	public void testReduceBalanceWithNegativeNumber(){
		Saving s = new Saving();
		
		try{
			s.setBalance(25.00);
			assertEquals(s.getBalance(), 25.00, 0.0);
			s.reduceBalance(-22.00);
			Assert.fail();
		}catch (Exception e){
			
		}
	}
	
	@Test
	public void testReduceBalanceToMinus(){
		Saving s = new Saving();
		
		try{
			s.setBalance(25.00);
			assertEquals(s.getBalance(), 25.00, 0.0);
			s.reduceBalance(28.00);
			Assert.fail();
		}catch (Exception e){
			
		}
	}
	
	@Test
	public void testReduceBalanceAt0(){
		Saving s = new Saving();
		
		try{
			s.setBalance(25.00);
			assertEquals(s.getBalance(), 25.00, 0.0);
			s.reduceBalance(25.00);
			assertEquals(s.getBalance(), 0.00, 0.0);
		}catch (Exception e){
			
		}
	}
	
	@Test
	public void testReduceBalance(){
		Saving s = new Saving();
		
		try{
			s.setBalance(25.00);
			assertEquals(s.getBalance(), 25.00, 0.0);
			s.reduceBalance(24.00);
			assertEquals(s.getBalance(), 1.00, 0.0);
		}catch (Exception e){
			
		}
	}

}
