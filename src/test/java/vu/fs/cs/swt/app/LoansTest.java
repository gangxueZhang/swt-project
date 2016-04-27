package vu.fs.cs.swt.app;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;
import vu.fs.cs.swt.beans.Customer;
import vu.fs.cs.swt.beans.Loan;
import vu.fs.cs.swt.beans.Saving;

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
			fail("expected exception");
		}catch (Exception e){
			assertEquals(e.getClass(), Exception.class);
		}
	}
	
	@Test
	public void testRatesLowBoundaryFail(){
		Loan l = new Loan();
		
		try {
			l.setInterestRate(5.75);
			fail("expected exception");
		}catch (Exception e){
			assertEquals(e.getClass(), Exception.class);
		}
	}
	
	@Test
	public void testRateIncrementFail(){
		Loan l = new Loan();
		
		try {
			l.setInterestRate(6.30);
			fail("expected exception");
		}catch (Exception e){
			assertEquals(e.getClass(), Exception.class);
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
			
			fail("expected exception");
		}catch (Exception e){
			assertEquals(e.getClass(), Exception.class);
		}
		
	}
	
	@Test
	public void testLoanBelow2(){
		
		Loan l = new Loan();
		
		try {
			l.setBalance(499.00);
			
			fail("expected exception");
		}catch (Exception e){
			assertEquals(e.getClass(), Exception.class);
		}
		
	}
	
	@Test
	public void testLoanBelow3(){
		
		Loan l = new Loan();
		
		try {
			l.setBalance(499.99);
			
			fail("expected exception");
		}catch (Exception e){
			assertEquals(e.getClass(), Exception.class);
		}
		
	}
	
	@Test
	public void testLoanAbove(){
		Loan l = new Loan();
		
		try {
			l.setBalance(60000.00);
			
			fail("expected exception");
		}catch (Exception e){
			assertEquals(e.getClass(), Exception.class);
		}
	}
	
	@Test
	public void testLoanAbove2(){
		Loan l = new Loan();
		
		try {
			l.setBalance(50001.00);
			
			fail("expected exception");
		} catch (Exception e) {
			assertEquals(e.getClass(), Exception.class);
		}
	}
	
	@Test
	public void testLoanAbove3(){
		Loan l = new Loan();
		
		try {
			l.setBalance(50000.01);
			
			fail("expected exception");
		} catch (Exception e) {
			assertEquals(e.getClass(), Exception.class);
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
			
			assertTrue(c.getLoans().size() == 0);
			
			c.addLoan(l);
			
			assertTrue(c.getLoans().size() == 1);
		} catch (Exception e) {

		}
		
	}

	
	@Test
	public void testLoan() {
		try {
			Customer c = new Customer("John", "Doe", "johnny", "awesomePassword");
			c.getSavingsAccount().setBalance(35.0);
			Loan l = new Loan(600.00, 10.25, c);
			
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
		
		assertEquals(l.getId(), 0);
		
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
			
			assertNull(l.getBalance());
			
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
			
			assertNull(l.getCustomer());
			
			l.setCustomer(customer);
			
			assertNotNull(l.getCustomer());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testSetMinimumPayment(){
		Loan l = new Loan();
		
		try {
			
			assertNull(l.getBalance());
			
			l.setBalance(600.00);
			
			assertNotNull(l.getBalance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInterestAmount(){
		Loan l = new Loan();
		try {
			l.setInterestRate(8.0);
			l.setBalance(2000.0);
			
			assertEquals(l.interestAmount(),13.3, 0.5 );
			
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
			fail("expected exception");
		} catch (Exception e) {
			assertEquals(e.getClass(), Exception.class);
		}
	}
	
	@Test
	public void testIncreaseBalance(){
		Loan s = new Loan();
		
		try{
			s.setBalance(2500.00);
			assertEquals(s.getBalance(), 2500.00, 0.0);
			s.increaseBalance(22.00);
			assertEquals(s.getBalance(), 2522.00, 0.0);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testIncreaseBalance2(){
		Loan s = new Loan();
		
		try{
			s.setBalance(2500.0);
			assertEquals(s.getBalance(), 2500.0, 0.0);
			s.increaseBalance(22.1);
			assertEquals(s.getBalance(), 2522.1, 0.0);
		}catch (Exception e){
			
		}
	}
	
	@Test
	public void testReduceBalanceWithNegativeNumber(){
		Loan s = new Loan();
		
		try{
			s.setBalance(2500.00);
			assertEquals(s.getBalance(), 2500.00, 0.0);
			s.reduceBalance(-2500.00);
			fail("expected exception");
		}catch (Exception e){
			assertEquals(e.getClass(), Exception.class);
		}
	}
	
	@Test
	public void testReduceBalanceToMinus(){
		Loan s = new Loan();
		
		try{
			s.setBalance(2500.00);
			assertEquals(s.getBalance(), 2500.00, 0.0);
			s.reduceBalance(2800.00);
			fail("expected exception");
		}catch (Exception e){
			assertEquals(e.getClass(), Exception.class);
		}
	}
	
	@Test
	public void testReduceBalanceAt0(){
		Loan s = new Loan();
		
		try{
			s.setBalance(2500.00);
			assertEquals(s.getBalance(), 2500.00, 0.0);
			s.reduceBalance(2500.00);
			assertEquals(s.getBalance(), 0.00, 0.0);
		}catch (Exception e){
			
		}
	}
	
	@Test
	public void testReduceBalance(){
		Loan s = new Loan();
		
		try{
			s.setBalance(2500.00);
			assertEquals(s.getBalance(), 2500.00, 0.0);
			s.reduceBalance(2400.00);
			assertEquals(s.getBalance(), 100.00, 0.0);
		}catch (Exception e){
			
		}
	}

}
