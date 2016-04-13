package vu.fs.cs.swt.frames;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import vu.fs.cs.swt.beans.*;
import vu.fs.cs.swt.systemclasses.System;

public class Login {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField passPassword;
	private List<Customer> _customers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Login");
		//ImageIcon img = new ImageIcon("icons/login_icon.png");
		//frame.setIconImage(img.getImage());
		frame.getContentPane().setLayout(null);
		_customers = System.Customers.list();
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setBounds(37, 76, 134, 35);
		frame.getContentPane().add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(181, 83, 120, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Šifra:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(60, 112, 111, 50);
		frame.getContentPane().add(lblPassword);
		
		passPassword = new JPasswordField();
		passPassword.setBounds(181, 127, 120, 20);
		frame.getContentPane().add(passPassword);
		
		final JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] pass = passPassword.getPassword();
				String passString = new String(pass);
				for(Customer c : _customers) {
					if(c.getUsername().equals(txtUsername.getText()) && c.correctPassword(passString)) {
						frame.dispose();
						CustomerMenu cm = new CustomerMenu(c);
						cm.main(null);
						break;
					}
					else {
						JOptionPane.showMessageDialog(frame, "Incorrect input");
					}
				}
			}
		});
		btnOk.setBounds(181, 187, 120, 23);
		frame.getContentPane().add(btnOk);
		
		btnOk.setEnabled(false);
		
		txtUsername.getDocument().addDocumentListener(new DocumentListener() {

		     public void removeUpdate(DocumentEvent e) {
		    	 String pass = new String(passPassword.getPassword());
		    	 if(txtUsername.getText().length() != 0 && pass.length() != 0) {
		    		 btnOk.setEnabled(true);
		    	 }
		    	 else {
		    		 btnOk.setEnabled(false);
		    	 }
		    	 passPassword.getDocument().addDocumentListener(new DocumentListener() {

				     public void removeUpdate(DocumentEvent e) {
				    	 String pass = new String(passPassword.getPassword());
				    	 if(txtUsername.getText().length() != 0 && pass.length() != 0) {
				    		 btnOk.setEnabled(true);
				    	 }
				    	 else {
				    		 btnOk.setEnabled(false);
				    	 }
				     }
				     
				     public void insertUpdate(DocumentEvent e) {
				    	 String pass = new String(passPassword.getPassword());
				    	 if(txtUsername.getText().length() != 0 && pass.length() != 0) {
				    		 btnOk.setEnabled(true);
				    	 }
				    	 else {
				    		 btnOk.setEnabled(false);
				    	 }
				     }
				     
				     public void changedUpdate(DocumentEvent e) {
					        // TODO add code!

					 }
				});
		     }
		     
		     public void insertUpdate(DocumentEvent e) {
		    	 String pass = new String(passPassword.getPassword());
		    	 if(txtUsername.getText().length() != 0 && pass.length() != 0) {
		    		 btnOk.setEnabled(true);
		    	 }
		    	 else {
		    		 btnOk.setEnabled(false);
		    	 }
		    	 passPassword.getDocument().addDocumentListener(new DocumentListener() {

				     public void removeUpdate(DocumentEvent e) {
				    	 String pass = new String(passPassword.getPassword());
				    	 if(txtUsername.getText().length() != 0 && pass.length() != 0) {
				    		 btnOk.setEnabled(true);
				    	 }
				    	 else {
				    		 btnOk.setEnabled(false);
				    	 }
				     }
				     
				     public void insertUpdate(DocumentEvent e) {
				    	 String pass = new String(passPassword.getPassword());
				    	 if(txtUsername.getText().length() != 0 && pass.length() != 0) {
				    		 btnOk.setEnabled(true);
				    	 }
				    	 else {
				    		 btnOk.setEnabled(false);
				    	 }
				     }
				     
				     public void changedUpdate(DocumentEvent e) {
					        // TODO add code!

					 }
				});
		     }
		     
		     public void changedUpdate(DocumentEvent e) {
			        // TODO add code!

			 }
		});
	}

}