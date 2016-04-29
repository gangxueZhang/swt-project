package vu.fs.cs.swt.frames;

import java.awt.EventQueue;

import javax.swing.JFrame;

import vu.fs.cs.swt.beans.*;
import vu.fs.cs.swt.systemclasses.System.Customers;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.regex.Pattern;

public class WithdrawSavings {

	private JFrame frame;
	private static Customer _c;
	private JTextField txtWithdrawAmount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WithdrawSavings window = new WithdrawSavings();
					if(_c != null) {
						window = new WithdrawSavings(_c);
					}
					else {
						window = new WithdrawSavings();
					}
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
	public WithdrawSavings() {
		initialize();
	}
	
	public WithdrawSavings(Customer c) {
		initialize();
		_c = c;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCurrentBalance = new JLabel("Current balance:");
		lblCurrentBalance.setBounds(82, 72, 105, 14);
		frame.getContentPane().add(lblCurrentBalance);
		
		JLabel cCurrentBalance = new JLabel("");
		cCurrentBalance.setBounds(197, 72, 105, 14);
		if(_c != null) {
			cCurrentBalance.setText(_c.getSavingsAccount().getBalance().toString());
		}
		frame.getContentPane().add(cCurrentBalance);
		
		JLabel lblWithdrawAmount = new JLabel("Withdraw amount:");
		lblWithdrawAmount.setBounds(82, 108, 105, 14);
		frame.getContentPane().add(lblWithdrawAmount);
		
		txtWithdrawAmount = new JTextField();
		txtWithdrawAmount.setBounds(197, 105, 105, 20);
		frame.getContentPane().add(txtWithdrawAmount);
		txtWithdrawAmount.setColumns(10);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtWithdrawAmount.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "You must enter the amount you wish to withdraw");
					return;
				}
				Pattern pattern = Pattern.compile("[0-9]+(.[0-9]{1,2})?");
				if(!pattern.matcher(txtWithdrawAmount.getText()).matches()) {
					JOptionPane.showMessageDialog(frame, "Only numbers are allowed: 500 or 500.0 or 500.00");
					txtWithdrawAmount.setText("");
					return;
			    }
				if(_c != null) {
					if(_c.getSavingsAccount().getBalance() < Double.parseDouble(txtWithdrawAmount.getText())) {
						JOptionPane.showMessageDialog(frame, "You don't have that much money in your account");
						return;
					}
					try {
						_c.getSavingsAccount().reduceBalance(Double.parseDouble(txtWithdrawAmount.getText()));
						_c = Customers.update(_c);
						JOptionPane.showMessageDialog(frame, "You have succesfully withdrawn from your account");
						frame.dispose();
						CustomerMenu cm = new CustomerMenu(_c);
						cm.main(null);
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnWithdraw.setBounds(82, 164, 105, 23);
		frame.getContentPane().add(btnWithdraw);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(_c != null) {		
					frame.dispose();
					CustomerMenu cm = new CustomerMenu(_c);
					cm.main(null);
				}
				else {
					frame.dispose();
					CustomerMenu cm = new CustomerMenu();
					cm.main(null);
				}
			}
		});
		btnBack.setBounds(197, 164, 105, 23);
		frame.getContentPane().add(btnBack);
	}
}
