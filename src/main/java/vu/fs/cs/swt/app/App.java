package vu.fs.cs.swt.app;

import vu.fs.cs.swt.beans.*;
import vu.fs.cs.swt.systemclasses.System;

/**
 * Hello world!
 *
 */
public class App 
{	
	public static void main(String[] args) {
		java.lang.System.out.println("SWT PROject");
		Customer c;
		try {
			c = new Customer("testuser", "testpass");
			System.Customers.add(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
