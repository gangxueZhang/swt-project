package vu.fs.cs.swt.frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import vu.fs.cs.swt.beans.*;
import vu.fs.cs.swt.systemclasses.System;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class Register {

	private JFrame frame;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtUsername;
	private JLabel lblPassword;
	private JPasswordField passPassword;
	private JButton btnRegister;
	private List<Customer> _customers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
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
	public Register() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		_customers = System.Customers.list();
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(85, 45, 80, 20);
		frame.getContentPane().add(lblFirstName);
		
		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(175, 45, 166, 20);
		frame.getContentPane().add(txtFirstName);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(85, 88, 80, 20);
		frame.getContentPane().add(lblLastName);
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(175, 88, 166, 20);
		frame.getContentPane().add(txtLastName);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(85, 129, 80, 20);
		frame.getContentPane().add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(175, 129, 166, 20);
		frame.getContentPane().add(txtUsername);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(85, 172, 80, 20);
		frame.getContentPane().add(lblPassword);
		
		passPassword = new JPasswordField();
		passPassword.setBounds(175, 172, 166, 20);
		frame.getContentPane().add(passPassword);
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtFirstName.getText().isEmpty() || txtLastName.getText().isEmpty() ||
						txtUsername.getText().isEmpty() || passPassword.getPassword().length == 0) {
					JOptionPane.showMessageDialog(frame, "You must provide all information");
					return;
				}
				if(txtFirstName.getText().length() < 2 || txtFirstName.getText().length() > 30) {
					JOptionPane.showMessageDialog(frame, "First name should contain only letters (min 2, max 30)");
					return;
				}
				if(txtLastName.getText().length() < 2 || txtLastName.getText().length() > 30) {
					JOptionPane.showMessageDialog(frame, "Last name should contain only letters (min 2, max 30)");
					return;
				}
				if(txtUsername.getText().length() < 5 || txtUsername.getText().length() > 25) {
					JOptionPane.showMessageDialog(frame, "Username should contain only alphanumeric characters (min 5, max 25)");
					return;
				}
				if(passPassword.getPassword().length < 6 || passPassword.getPassword().length > 25) {
					JOptionPane.showMessageDialog(frame, "Password should contain only alphanumeric characters (min 6, max 25)");
					return;
				}
				
				for(Customer c : _customers) {
					if(c.getUsername().equals(txtUsername.getText())) {
						JOptionPane.showMessageDialog(frame, "Username already exists");
						txtUsername.setText("");
						return;
					}
				}
				
				char[] pass = passPassword.getPassword();
				String passString = new String(pass);
				try {
					Customer c = new Customer(txtFirstName.getText(), txtLastName.getText(), txtUsername.getText(),
							passString);
					System.Customers.add(c);
					JOptionPane.showMessageDialog(frame, "You have successfully registered!");
					frame.dispose();
					CustomerMenu cm = new CustomerMenu(c);
					cm.main(null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnRegister.setBounds(175, 213, 166, 23);
		frame.getContentPane().add(btnRegister);
	}
}
