package vu.fs.cs.swt.frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import vu.fs.cs.swt.beans.*;
import vu.fs.cs.swt.systemclasses.System;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.regex.Pattern;
import java.util.List;

public class InitiateLoan {

	private JFrame frame;
	private JTextField txtAmount;
	private static Customer _c;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitiateLoan window = new InitiateLoan();
					if(_c != null) {
						window = new InitiateLoan(_c);
					}
					else {
						window = new InitiateLoan();
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
	public InitiateLoan() {
		initialize();
	}
	
	public InitiateLoan(Customer c) {
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
		
		JLabel lblInterestRate = new JLabel("Interest rate:");
		lblInterestRate.setBounds(56, 69, 79, 25);
		frame.getContentPane().add(lblInterestRate);
		
		final JLabel cInterestRate = new JLabel();
		cInterestRate.setBounds(158, 69, 200, 25);
		if(_c != null) {
			List<Loan> loans = _c.getLoans();
			Double interest_rate = 6.0;
			if(!loans.isEmpty()) {
				for(Loan l : loans) {
					interest_rate = l.getInterestRate();
					break;
				}
			}
			cInterestRate.setText(interest_rate.toString());
		}
		frame.getContentPane().add(cInterestRate);
		
		JLabel lblSavingsRate = new JLabel("Savings rate:");
		lblSavingsRate.setBounds(56, 105, 79, 25);
		frame.getContentPane().add(lblSavingsRate);
		
		JLabel cSavingsRate = new JLabel("");
		cSavingsRate.setBounds(158, 105, 200, 25);
		if(_c != null) {
			List<Loan> loans = _c.getLoans();
			Double interest_rate = 6.0;
			if(!loans.isEmpty()) {
				for(Loan l : loans) {
					interest_rate = l.getInterestRate();
					break;
				}
			}
			Double savings_rate = interest_rate / 4;
			cSavingsRate.setText(savings_rate.toString());
		}
		frame.getContentPane().add(cSavingsRate);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(56, 151, 79, 25);
		frame.getContentPane().add(lblAmount);
		
		txtAmount = new JTextField();
		txtAmount.setBounds(158, 151, 200, 25);
		frame.getContentPane().add(txtAmount);
		txtAmount.setColumns(10);
		
		JButton btnInitiateLoan = new JButton("Initiate Loan");
		btnInitiateLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtAmount.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "You must enter the amount you wish to loan");
					return;
				}
				Pattern pattern = Pattern.compile("[0-9]+(.[0-9]{1,2})?");
				if(!pattern.matcher(txtAmount.getText()).matches()) {
					JOptionPane.showMessageDialog(frame, "Only numbers are allowed: 500 or 500.0 or 500.00");
					return;
			    }
				if(Double.parseDouble(txtAmount.getText()) < 500.00 ||
						Double.parseDouble(txtAmount.getText()) > 50000.00) {
					JOptionPane.showMessageDialog(frame, "Loan must be between 500 and 50000");
					return;
				}
				if(_c != null) {
					Double interest_rate = Double.parseDouble(cInterestRate.getText());
					Double balance = Double.parseDouble(txtAmount.getText());
					Loan l;
					try {
						l = new Loan(balance, interest_rate, _c);
						Double saving_balance = _c.getSavingsAccount().getBalance();
						_c.addLoan(l);
						_c.getSavingsAccount().setInterestRate();
						_c.getSavingsAccount().setBalance(saving_balance + balance);
						System.Loans.add(l);
						System.Customers.update(_c);
						JOptionPane.showMessageDialog(frame, "You have successfully applied for a loan!");
						frame.dispose();
						CustomerMenu cm = new CustomerMenu(_c);
						cm.main(null);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});
		btnInitiateLoan.setBounds(158, 199, 200, 23);
		frame.getContentPane().add(btnInitiateLoan);
	}

}
