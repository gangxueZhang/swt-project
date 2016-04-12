package vu.fs.cs.swt.HibernateExample;

import java.util.Scanner;
import org.hibernate.Transaction;
import org.hibernate.Session;
import vu.fs.cs.swt.util.HibernateUtil;
import vu.fs.cs.swt.beans.Customer;

public class Main {
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		addCustomer(session);
		findCustomer(session);
		session.close();
	}
	
	private static void addCustomer(Session session) {
		Transaction t = session.beginTransaction();
		Customer c = new Customer();
		c.setFirstName("John");
		c.setLastName("Doe");
		Long id = (Long) session.save(c);
		System.out.println("Added customer with ID: "+id);
		t.commit();
	}
	
	private static void findCustomer(Session session) {
		Transaction t = session.beginTransaction();
		System.out.println("Enter customer ID: ");
		long id = sc.nextLong();
		Customer c = (Customer) session.get(Customer.class, id);
		if (c==null) {
			System.out.println("There is no customer with that ID.");
		}
		else {
			System.out.println("Customer: "+c.getFirstName()+" "+c.getLastName());
		}
		t.commit();
	}
}