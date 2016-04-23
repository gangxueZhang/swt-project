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
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setBounds(87, 71, 74, 35);
		frame.getContentPane().add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(171, 78, 175, 20);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setBounds(87, 107, 74, 50);
		frame.getContentPane().add(lblPassword);
		
		passPassword = new JPasswordField();
		passPassword.setBounds(171, 122, 175, 20);
		frame.getContentPane().add(passPassword);
		
		final JButton btnOk = new JButton("Login");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] pass = passPassword.getPassword();
				String passString = new String(pass);
				for(Customer c : _customers) {
					if(c.getUsername().equals(txtUsername.getText()) && c.correctPassword(passString)) {
						frame.dispose();
						CustomerMenu cm = new CustomerMenu(c);
						cm.main(null);
						return;
					}
				}
				JOptionPane.showMessageDialog(frame, "Incorrect input");
			}
		});
		btnOk.setBounds(87, 168, 120, 23);
		frame.getContentPane().add(btnOk);
		
		btnOk.setEnabled(false);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Register r = new Register();
				r.main(null);
			}
		});
		btnRegister.setBounds(226, 168, 120, 23);
		frame.getContentPane().add(btnRegister);
		
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