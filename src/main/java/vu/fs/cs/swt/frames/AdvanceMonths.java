package vu.fs.cs.swt.frames;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vu.fs.cs.swt.beans.*;
import vu.fs.cs.swt.systemclasses.System;
import vu.fs.cs.swt.systemclasses.System.Customers;

import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JTextField;

public class AdvanceMonths {
	
	private static JFrame frameAdvance;
	private static Customer _c;
	AdvanceMonths window;
	static int count = -1;
	static String result;
	Calendar date = Calendar.getInstance();

	DecimalFormat df = new DecimalFormat("#.##");

	public AdvanceMonths(Customer c) {
		_c = c;
		//advance();
	}
	

	public AdvanceMonths() {
		JOptionPane.showMessageDialog(frameAdvance, "No customer attached!");
	}
	
	public String advance() throws Exception{
		
		CalculateInterests();
		return " ";
	}
	
	/**
	 * @throws Exception  
	 * @wbp.parser.entryPoint
	 */
	public void CalculateInterests() throws Exception {
		
		count++;
		Calendar date = Calendar.getInstance();
		date.add(Calendar.MONTH, count);
		result = new SimpleDateFormat("MMMM").format(date.getTime()) + " " +  new SimpleDateFormat("YYYY").format(date.getTime());

		frameAdvance = new JFrame("Account overview for month " + result);
	    //frameAdvance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frameAdvance.setBounds(200, 200, 550, 600);
	    frameAdvance.getContentPane().setLayout(null);
	    frameAdvance.setVisible(true);
	     
	    JLabel lblBeginningBalances = new JLabel("Beginning Balances:");
	    lblBeginningBalances.setBounds(29, 24, 115, 14);
	    frameAdvance.getContentPane().add(lblBeginningBalances);
	    
	    JLabel lblInterestRateHeader = new JLabel("Interest Amount:");
	    lblInterestRateHeader.setBounds(200, 24, 115, 14);
	    frameAdvance.getContentPane().add(lblInterestRateHeader);
	    
	    JPanel panelBill = new JPanel();
	    panelBill.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
	    panelBill.setBounds(29, 205, 428, 145);
	    frameAdvance.getContentPane().add(panelBill);
	    panelBill.setLayout(null);
	    
	    JLabel lblWarnings = new JLabel("Warnings:");
	    lblWarnings.setBounds(10, 11, 372, 14);
	    panelBill.add(lblWarnings);
	    
	    JButton btnBack = new JButton("Back");
	    btnBack.setBounds(368, 171, 89, 23);
	    frameAdvance.getContentPane().add(btnBack);
	    
	    JPanel panel = new JPanel();
	    panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	    panel.setBounds(29, 374, 428, 155);
	    frameAdvance.getContentPane().add(panel);
	    panel.setLayout(null);
	    
	    JLabel lblActionsTaken = new JLabel("Actions taken:");
	    lblActionsTaken.setBounds(10, 11, 173, 14);
	    panel.add(lblActionsTaken);
	    
	    JLabel lblInitiateALoan = new JLabel("Initiate a Loan: False");
	    lblInitiateALoan.setBounds(10, 36, 217, 14);
	    panel.add(lblInitiateALoan);
	    
	    JLabel lblMakeAPayment = new JLabel("Make a payment on a loan: False");
	    lblMakeAPayment.setBounds(10, 61, 217, 14);
	    panel.add(lblMakeAPayment);
	    
	    JLabel lblWithdrawSavings = new JLabel("Withdraw savings: False");
	    lblWithdrawSavings.setBounds(10, 86, 173, 14);
	    panel.add(lblWithdrawSavings);
	    
	    JLabel lblDepositToSavings = new JLabel("Deposit to savings: False");
	    lblDepositToSavings.setBounds(10, 111, 173, 14);
	    panel.add(lblDepositToSavings);
	    btnBack.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				frameAdvance.dispose();
				CustomerMenu cm = new CustomerMenu(_c);
				cm.main(null);
			}
		});
	    
		if(_c != null) {
			List<Loan> loans = _c.getLoans();
			
			//UPDATE
			if (_c.getInitiateLoan() == true){
				lblInitiateALoan.setText("Initiate a Loan: True");
				_c.setInitiateLoan(false);
			}
			
			if (_c.getPaymentLoan() == true){
				lblMakeAPayment.setText("Make a payment on a loan: True");
				_c.setPaymentLoan(false);
			}
			
			if (_c.getWithdrawSavings() == true){
				lblWithdrawSavings.setText("Withdraw savings: True");
				_c.setWithdrawSavings(false);
			}
			
			if(_c.getDepositSavings() == true){
				lblDepositToSavings.setText("Deposit to savings: True");
				_c.setDepositSavings(false);
			}
			_c = Customers.update(_c);
			
			JLabel[] labels = new JLabel[loans.size()];
			JLabel[] lblEndBalances = new JLabel[loans.size()];
			JLabel[] lblInterestRates = new JLabel[loans.size()];
			JLabel[] lblSavingsEndBalance = new JLabel[3];
			JLabel lblBills = new JLabel();

			Double[] interestRate = new Double[loans.size()];		
			
			int boundWidth = 25;
			
			lblWarnings.setText("Warnings:");

			if(!loans.isEmpty()) {
				for (int i = 0; i<loans.size(); i++){
					
					boundWidth += 25;
					Double currentLoanBalance = loans.get(i).getBalance();
					labels[i] = new JLabel("Loan "+(i+1)+": " + df.format(currentLoanBalance ));
					labels[i].setBounds(39, boundWidth, 150, 14);
					frameAdvance.getContentPane().add(labels[i]);
					
					interestRate[i] = (loans.get(i).interestAmount());
					
					lblInterestRates[i] = new JLabel(df.format(interestRate[i]));
					lblInterestRates[i].setBounds(200, boundWidth, 150, 14);
					frameAdvance.getContentPane().add(lblInterestRates[i]);
					
					lblEndBalances[i] = new JLabel("End: " + df.format( (currentLoanBalance + loans.get(i).interestAmount()) ) );
					lblEndBalances[i].setBounds(300, boundWidth, 150, 14);
					frameAdvance.getContentPane().add(lblEndBalances[i]);
					
					//updating the balances
					if ( loans.get(i).getIsDelinquent() == true ) {
						lblBills = new JLabel("Minimum not payed for Loan " + (i+1));
					    lblBills.setBounds(15, boundWidth-25, 250, 14);
					    panelBill.add(lblBills);
					    
					    //UPDATE
					    loans.get(i).increaseBalance(loans.get(i).interestAmount() + 50.0);
					    loans.get(i).setIsDelinquent(false);
					} else {
						//UPDATE
						loans.get(i).increaseBalance(loans.get(i).interestAmount());
					}
					_c = Customers.update(_c);
				}

				Double sCurrentBalance = _c.getSavingsAccount().getBalance();
				Double sInterestRate = _c.getSavingsAccount().getInterestRate();
				Double sEndBalance = sCurrentBalance + (sCurrentBalance * (sInterestRate/100));
				
				lblSavingsEndBalance[0] = new JLabel("Beginning balance savings: " + df.format(sCurrentBalance));
				lblSavingsEndBalance[0].setBounds(39, 125, 300, 14);
				frameAdvance.getContentPane().add(lblSavingsEndBalance[0]);
				lblSavingsEndBalance[1] = new JLabel("Interest rate applied: " + (sInterestRate/100) );
				lblSavingsEndBalance[1].setBounds(39, 150, 300, 14);
				frameAdvance.getContentPane().add(lblSavingsEndBalance[1]);
				lblSavingsEndBalance[2] = new JLabel("End balance savings: " + df.format(sEndBalance));
				lblSavingsEndBalance[2].setBounds(39, 175, 300, 14);
				frameAdvance.getContentPane().add(lblSavingsEndBalance[2]);
				
				//UPDATE
				_c.getSavingsAccount().setBalance(sEndBalance);
				_c = Customers.update(_c);
				
			} else {
				JOptionPane.showMessageDialog(frameAdvance, "You need to initiate a loan first!");
			}
		}
		
	}
}
