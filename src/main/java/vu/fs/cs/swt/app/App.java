package vu.fs.cs.swt.app;

import javax.swing.*;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Hello world!
 *
 */
public class App 
{
	private static void createAndShowGUI(){
		//setting up the windows
		JFrame frame = new JFrame("HelloWorld GUI");
		frame.setLocation(new Point(100, 100));
		frame.setMinimumSize(new Dimension(800, 500));
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
		customerInformation.setBounds(0, 40, 319, 149);
		frame.getContentPane().add(customerInformation);
		customerInformation.setLayout(null);
		
		JLabel lblCustomerAccount = new JLabel("Customer Account no.:");
		lblCustomerAccount.setBounds(27, 5, 172, 14);
		lblCustomerAccount.setHorizontalTextPosition(SwingConstants.LEFT);
		customerInformation.add(lblCustomerAccount);
		
		JLabel cAccountNumber = new JLabel("#12324353");
		cAccountNumber.setBounds(209, 5, 100, 14);
		cAccountNumber.setText("#002030");
		customerInformation.add(cAccountNumber);
		
		JLabel lblSavingsAccountNo = new JLabel("Savings account no:");
		lblSavingsAccountNo.setBounds(27, 24, 172, 14);
		customerInformation.add(lblSavingsAccountNo);
		
		JLabel cSavingsNumber = new JLabel("#12342345");
		cSavingsNumber.setBounds(209, 24, 100, 14);
		customerInformation.add(cSavingsNumber);
		
		JLabel lblSavingsAccountBalance = new JLabel("Savings account balance:");
		lblSavingsAccountBalance.setBounds(27, 49, 172, 14);
		customerInformation.add(lblSavingsAccountBalance);
		
		JLabel cSavingsBalance = new JLabel("\u20AC 0.00");
		cSavingsBalance.setBounds(209, 49, 46, 14);
		customerInformation.add(cSavingsBalance);
		
		final JLabel lblCurrentMonth = new JLabel("APRIL 2016");
		lblCurrentMonth.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		lblCurrentMonth.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentMonth.setOpaque(true);
		lblCurrentMonth.setBackground(new Color(144, 238, 144));
		lblCurrentMonth.setBounds(548, 51, 140, 101);
		frame.getContentPane().add(lblCurrentMonth);
		
		JPanel loansPanel = new JPanel();
		loansPanel.setBounds(0, 189, 319, 161);
		frame.getContentPane().add(loansPanel);
		loansPanel.setLayout(null);
		
		JLabel lblLoans = new JLabel("Loans");
		lblLoans.setBounds(10, 6, 50, 17);
		lblLoans.setFont(new Font("Tahoma", Font.BOLD, 14));
		loansPanel.add(lblLoans);
		
		JLabel lblLoan1 = new JLabel("1) Loan 1 Name");
		lblLoan1.setBounds(70, 5, 100, 23);
		lblLoan1.setText("1) Hi i'm a text");
		loansPanel.add(lblLoan1);
		
		JLabel lblLoan2 = new JLabel("2) Loan 2 Name");
		lblLoan2.setBounds(70, 29, 100, 14);
		loansPanel.add(lblLoan2);
		
		JLabel lblLoan3 = new JLabel("3) Loan 3 Name");
		lblLoan3.setBounds(70, 51, 100, 14);
		loansPanel.add(lblLoan3);
		
		JButton btnAdvanceToNext = new JButton("Advance To Next Month");
		btnAdvanceToNext.setBounds(39, 90, 211, 47);
		btnAdvanceToNext.setBackground(new Color(237, 62, 103));
		btnAdvanceToNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblCurrentMonth.setText("MAY 2016");
			}
		});
		loansPanel.add(btnAdvanceToNext);
		
		JPanel optionsPanel = new JPanel();
		optionsPanel.setBounds(498, 238, 255, 178);
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
		
		//finally, displaying the window
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				createAndShowGUI();
			}			
		});
		System.out.println("SWT PROject");
		
	}
}
