package vu.fs.cs.swt.frames;

import javax.swing.*;

import vu.fs.cs.swt.beans.*;

import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

public class CustomerMenu {

	private JFrame frame;
	private static Customer _c;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerMenu window = new CustomerMenu();
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
	public CustomerMenu() {
		initialize();
	}
	
	public CustomerMenu(Customer c) {
		initialize();
		_c = c;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//setting up the windows
		JFrame frame = new JFrame("HelloWorld GUI");
		frame.setLocation(new Point(100, 100));
		frame.setMinimumSize(new Dimension(700, 500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 784, 40);
		frame.getContentPane().add(panel);
		
		JLabel lblWecheatem = new JLabel("WeCheatEm");
		panel.add(lblWecheatem);
		lblWecheatem.setHorizontalAlignment(SwingConstants.CENTER);
		lblWecheatem.setPreferredSize(new Dimension(100, 30));
		lblWecheatem.setSize(new Dimension(50, 50));
		
		JPanel customerInformation = new JPanel();
		customerInformation.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		customerInformation.setBounds(10, 64, 399, 89);
		frame.getContentPane().add(customerInformation);
		customerInformation.setLayout(null);
		
		JLabel lblCustomerAccount = new JLabel("Customer account no.:");
		lblCustomerAccount.setBounds(10, 38, 172, 14);
		lblCustomerAccount.setHorizontalTextPosition(SwingConstants.LEFT);
		customerInformation.add(lblCustomerAccount);
		
		JLabel cAccountNumber = new JLabel();
		cAccountNumber.setBounds(192, 38, 100, 14);
		if(_c != null) {
			cAccountNumber.setText(_c.getAccountNumber());
		}
		customerInformation.add(cAccountNumber);
		
		JLabel lblSavingsAccountBalance = new JLabel("Savings account balance:");
		lblSavingsAccountBalance.setBounds(10, 63, 172, 14);
		customerInformation.add(lblSavingsAccountBalance);
		
		JLabel cSavingsBalance = new JLabel();
		cSavingsBalance.setBounds(192, 63, 46, 14);
		if(_c != null) {
			cSavingsBalance.setText("\u20AC" + _c.getSavingsAccount().getBalance());
		}
		customerInformation.add(cSavingsBalance);
		
		JLabel lblBasicInformation = new JLabel("Basic Information");
		lblBasicInformation.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBasicInformation.setBounds(10, 10, 144, 17);
		customerInformation.add(lblBasicInformation);
		
		final JLabel lblCurrentMonth = new JLabel("APRIL 2016");
		lblCurrentMonth.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblCurrentMonth.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentMonth.setOpaque(true);
		lblCurrentMonth.setBackground(new Color(144, 238, 144));
		lblCurrentMonth.setBounds(419, 64, 255, 101);
		frame.getContentPane().add(lblCurrentMonth);
		
		JPanel loansPanel = new JPanel();
		loansPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		loansPanel.setBounds(10, 153, 399, 282);
		frame.getContentPane().add(loansPanel);
		loansPanel.setLayout(null);
		
		if(_c != null) {
			if(!_c.getLoans().isEmpty()) {
				JLabel lblLoans = new JLabel("Loans");
				lblLoans.setBounds(10, 6, 50, 17);
				lblLoans.setFont(new Font("Tahoma", Font.BOLD, 14));
				loansPanel.add(lblLoans);
				
				JLabel lblLoan1 = new JLabel("1) Loan 1");
				lblLoan1.setBounds(10, 43, 100, 23);
				loansPanel.add(lblLoan1);
				
				JLabel lblLoan1Balance = new JLabel("a) Balance:");
				lblLoan1Balance.setBounds(20, 63, 71, 23);
				loansPanel.add(lblLoan1Balance);
				
				JLabel cLoan1Balance = new JLabel("");
				cLoan1Balance.setBounds(94, 63, 71, 23);
				loansPanel.add(cLoan1Balance);
				
				JLabel lblLoan1Interest = new JLabel("b) Interest:");
				lblLoan1Interest.setBounds(20, 89, 71, 23);
				loansPanel.add(lblLoan1Interest);
				
				JLabel cLoan1Interest = new JLabel("");
				cLoan1Interest.setBounds(94, 89, 71, 23);
				loansPanel.add(cLoan1Interest);
				
				JLabel lblLoan1MinPayment = new JLabel("c) Minimum payment:");
				lblLoan1MinPayment.setBounds(185, 63, 123, 23);
				loansPanel.add(lblLoan1MinPayment);
				
				JLabel cLoan1MinPayment = new JLabel("");
				cLoan1MinPayment.setBounds(318, 63, 71, 23);
				loansPanel.add(cLoan1MinPayment);
				
				JLabel lblLoan1Delinquent = new JLabel("d) Delinquent:");
				lblLoan1Delinquent.setBounds(185, 89, 123, 23);
				loansPanel.add(lblLoan1Delinquent);
				
				JLabel cLoan1Delinquent = new JLabel("");
				cLoan1Delinquent.setBounds(318, 89, 71, 23);
				loansPanel.add(cLoan1Delinquent);
				
				if(_c.getLoans().size() > 1) {
					JLabel lblLoan2Interest = new JLabel("b) Interest:");
					lblLoan2Interest.setBounds(20, 166, 71, 23);
					loansPanel.add(lblLoan2Interest);
					
					JLabel lblLoan2Delinquent = new JLabel("d) Delinquent:");
					lblLoan2Delinquent.setBounds(185, 167, 123, 23);
					loansPanel.add(lblLoan2Delinquent);
					
					JLabel cLoan2MinPayment = new JLabel("");
					cLoan2MinPayment.setBounds(318, 140, 71, 23);
					loansPanel.add(cLoan2MinPayment);
					
					JLabel cLoan2Delinquent = new JLabel("");
					cLoan2Delinquent.setBounds(318, 166, 71, 23);
					loansPanel.add(cLoan2Delinquent);
					
					JLabel lblLoan2 = new JLabel("2) Loan 2");
					lblLoan2.setBounds(10, 120, 100, 14);
					loansPanel.add(lblLoan2);
					
					JLabel lblLoan2Balance = new JLabel("a) Balance:");
					lblLoan2Balance.setBounds(20, 140, 71, 23);
					loansPanel.add(lblLoan2Balance);
					
					JLabel lblLoan2MinPayment = new JLabel("c) Minimum payment:");
					lblLoan2MinPayment.setBounds(185, 140, 123, 23);
					loansPanel.add(lblLoan2MinPayment);
					
					JLabel cLoan2Balance = new JLabel("");
					cLoan2Balance.setBounds(94, 140, 71, 23);
					loansPanel.add(cLoan2Balance);
					
					JLabel cLoan2Interest = new JLabel("");
					cLoan2Interest.setBounds(101, 166, 71, 23);
					loansPanel.add(cLoan2Interest);
					
					if(_c.getLoans().size() > 2) {
						JLabel cLoan3Balance = new JLabel("");
						cLoan3Balance.setBounds(94, 221, 71, 23);
						loansPanel.add(cLoan3Balance);
						
						JLabel cLoan3Interest = new JLabel("");
						cLoan3Interest.setBounds(94, 247, 71, 23);
						loansPanel.add(cLoan3Interest);
						
						JLabel lblLoan3 = new JLabel("3) Loan 3");
						lblLoan3.setBounds(10, 201, 100, 14);
						loansPanel.add(lblLoan3);
						
						JLabel lblLoan3Balance = new JLabel("a) Balance:");
						lblLoan3Balance.setBounds(20, 221, 71, 23);
						loansPanel.add(lblLoan3Balance);
						
						JLabel lblLoan3Interest = new JLabel("b) Interest:");
						lblLoan3Interest.setBounds(20, 247, 71, 23);
						loansPanel.add(lblLoan3Interest);
						
						JLabel lblLoan3MinPayment = new JLabel("c) Minimum payment:");
						lblLoan3MinPayment.setBounds(185, 221, 123, 23);
						loansPanel.add(lblLoan3MinPayment);
						
						JLabel lblLoan3Delinquent = new JLabel("d) Delinquent:");
						lblLoan3Delinquent.setBounds(185, 248, 123, 23);
						loansPanel.add(lblLoan3Delinquent);
						
						JLabel cLoan3MinPayment = new JLabel("");
						cLoan3MinPayment.setBounds(318, 221, 71, 23);
						loansPanel.add(cLoan3MinPayment);
						
						JLabel cLoan3Delinquent = new JLabel("");
						cLoan3Delinquent.setBounds(318, 247, 71, 23);
						loansPanel.add(cLoan3Delinquent);
					}
				}
			}
		}
		
		JPanel optionsPanel = new JPanel();
		optionsPanel.setBounds(419, 257, 255, 178);
		frame.getContentPane().add(optionsPanel);
		optionsPanel.setLayout(new GridLayout(2, 2, 4, 4));
		
		JButton btnInitLoan = new JButton("<html><p style='text-align: center;'>Initiate a <br/> Loan</p></html>");
		btnInitLoan.setHorizontalTextPosition(SwingConstants.CENTER);
		btnInitLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		optionsPanel.add(btnInitLoan);
		
		JButton btnMakePayment = new JButton("<html><p style='text-align: center;'>Make a payment <br/> to a Loan</p></html>");
		btnMakePayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnWithdrawSavings = new JButton("<html><p style='text-align: center;'>Withdraw from <br/> Savings</p></html>");
		btnWithdrawSavings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnDepositToSavings = new JButton("<html><p style='text-align: center;'>Deposit to <br/> Savings</p></html>");
		btnDepositToSavings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		optionsPanel.add(btnMakePayment);
		optionsPanel.add(btnWithdrawSavings);
		optionsPanel.add(btnDepositToSavings);
		
		JButton btnAdvanceToNext = new JButton("Advance To Next Month");
		btnAdvanceToNext.setBounds(419, 176, 255, 47);
		frame.getContentPane().add(btnAdvanceToNext);
		btnAdvanceToNext.setBackground(new Color(237, 62, 103));
		btnAdvanceToNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblCurrentMonth.setText("MAY 2016");
			}
		});
		
		//finally, displaying the window
		frame.pack();
		frame.setVisible(true);
	}

}
