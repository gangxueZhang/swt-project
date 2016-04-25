package vu.fs.cs.swt.frames;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vu.fs.cs.swt.beans.*;
import vu.fs.cs.swt.systemclasses.System;

import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;

public class AdvanceMonths {
	
	private JFrame frameAdvance;
	private static Customer _c;
	AdvanceMonths window;
	boolean principalFlag = false;
	Double[] principal;
	
	
	Calendar date = Calendar.getInstance();
	
	//saving the current month for yearly interest calculations
	String currentDate = new String(new SimpleDateFormat("MMMM").format(date.getTime()) + " " +
			new SimpleDateFormat("YYYY").format(date.getTime()));

	//current month
	String month = new SimpleDateFormat("MMMM").format(date.getTime());
	private JTextField txtBill1;
	private JTextField txtBill2;
	private JTextField txtBill3;

	/**
	 * @wbp.parser.constructor
	 */
	public AdvanceMonths(Customer c) {
		_c = c;
		//advance();
	}
	

	public AdvanceMonths() {
		JOptionPane.showMessageDialog(frameAdvance, "No customer attached!");
	}
	
	public String advance(){
		
		date.add(Calendar.MONTH, 1);

		//newly advanced month
		String result = new SimpleDateFormat("MMMM").format(date.getTime());
		
		CalculateInterests();
		

		if (result.equals(month)){
			JOptionPane.showMessageDialog(frameAdvance, "Time to calculate yearly interests!");
		}
		
		return result + " " +  new SimpleDateFormat("YYYY").format(date.getTime());
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void CalculateInterests(){

		frameAdvance = new JFrame("Account overview");
	    //frameAdvance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frameAdvance.setBounds(200, 200, 550, 400);
	    frameAdvance.getContentPane().setLayout(null);
	    frameAdvance.setVisible(true);
	     
	    JLabel lblBeginningBalances = new JLabel("Beginning Balances:");
	    lblBeginningBalances.setBounds(29, 24, 115, 14);
	    frameAdvance.getContentPane().add(lblBeginningBalances);
	    
	    JLabel lblInterestRateHeader = new JLabel("Interest Rates:");
	    lblInterestRateHeader.setBounds(200, 24, 115, 14);
	    frameAdvance.getContentPane().add(lblInterestRateHeader);
	    
	    JPanel panelBill = new JPanel();
	    panelBill.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
	    panelBill.setBounds(29, 205, 428, 145);
	    frameAdvance.getContentPane().add(panelBill);
	    panelBill.setLayout(null);

	    txtBill1 = new JTextField();
	    txtBill1.setText("0.00");
	    txtBill1.setBounds(332, 36, 86, 20);
	    panelBill.add(txtBill1);
	    txtBill1.setColumns(10);
	    
	    txtBill2 = new JTextField();
	    txtBill2.setText("0.00");
	    txtBill2.setBounds(332, 63, 86, 20);
	    panelBill.add(txtBill2);
	    txtBill2.setColumns(10);
	    
	    txtBill3 = new JTextField();
	    txtBill3.setText("0.00");
	    txtBill3.setBounds(332, 91, 86, 20);
	    panelBill.add(txtBill3);
	    txtBill3.setColumns(10);
	    
	    JButton btnPayBills = new JButton("Pay Bills");
	    btnPayBills.setBounds(161, 111, 89, 23);
	    btnPayBills.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frameAdvance, "Will be deducted from savings balance");
			}
		});
	    panelBill.add(btnPayBills);
	    
	    JLabel lblInsertTheAmount = new JLabel("Insert the amount you want to pay for each bill:");
	    lblInsertTheAmount.setBounds(148, 11, 270, 14);
	    panelBill.add(lblInsertTheAmount);
	    
	    
	    Double bill = 0.0;
		
		if(_c != null) {
			List<Loan> loans = _c.getLoans();
			JLabel[] labels = new JLabel[loans.size()];
			JLabel[] lblEndBalances = new JLabel[loans.size()];
			JLabel[] lblInterestRates = new JLabel[loans.size()];
			JLabel[] lblSavingsEndBalance = new JLabel[loans.size()];
			JLabel[] lblBills = new JLabel[loans.size()];

			Double[] interestRate = new Double[loans.size()];
			Double[] amountToPay = new Double[loans.size()];			
			
			
			int boundWidth = 25;

			if(!loans.isEmpty()) {
				for (int i = 0; i<loans.size(); i++){
					boundWidth += 25;
					Double currentLoanBalance = loans.get(i).getBalance();
					labels[i] = new JLabel("Loan "+(i+1)+": " + currentLoanBalance );
					labels[i].setBounds(39, boundWidth, 150, 14);
					frameAdvance.getContentPane().add(labels[i]);
					
					//principal value is the borrowed loan, so we only take that first time
					if (!principalFlag) {
						principal = new Double[loans.size()];
						for (int j=0; j<loans.size(); j++) {
							principal[j] = loans.get(j).getBalance();
						}
						principalFlag = true;
					}
					
					interestRate[i] = (loans.get(i).getInterestRate() / 100);
					amountToPay[i] = currentLoanBalance * (interestRate[i]);
					try {
						loans.get(i).setBalance(currentLoanBalance + amountToPay[i]);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					lblInterestRates[i] = new JLabel(interestRate[i].toString());
					lblInterestRates[i].setBounds(200, boundWidth, 150, 14);
					frameAdvance.getContentPane().add(lblInterestRates[i]);
					
					lblEndBalances[i] = new JLabel("End: " + (currentLoanBalance + amountToPay[i]));
					lblEndBalances[i].setBounds(300, boundWidth, 150, 14);
					frameAdvance.getContentPane().add(lblEndBalances[i]);
					
					Double onePercent = principal[i]/100;
					bill = principal[i] + onePercent;
					
					if (bill > 10.0){
						lblBills[i] = new JLabel("Bill to pay for Loan " + i +": $" + bill);
					    lblBills[i].setBounds(15, boundWidth-25, 250, 14);
					    panelBill.add(lblBills[i]);
					} else {
						lblBills[i] = new JLabel("Bill to pay for Loan " + i +": $10.0");
					    lblBills[i].setBounds(15, boundWidth-25, 250, 14);
					    panelBill.add(lblBills[i]);
					}
					
				}

				Double sCurrentBalance = _c.getSavingsAccount().getBalance();
				Double sInterestRate = _c.getSavingsAccount().getInterestRate();
				Double sEndBalance = sCurrentBalance + (sCurrentBalance * (sInterestRate/100));
				
				lblSavingsEndBalance[0] = new JLabel("Beginning balance savings: " + sCurrentBalance);
				lblSavingsEndBalance[0].setBounds(39, 125, 300, 14);
				frameAdvance.getContentPane().add(lblSavingsEndBalance[0]);
				lblSavingsEndBalance[1] = new JLabel("Interest rate applied: " + (sInterestRate/100));
				lblSavingsEndBalance[1].setBounds(39, 150, 300, 14);
				frameAdvance.getContentPane().add(lblSavingsEndBalance[1]);
				lblSavingsEndBalance[2] = new JLabel("End balance savings: " + sEndBalance);
				lblSavingsEndBalance[2].setBounds(39, 175, 300, 14);
				frameAdvance.getContentPane().add(lblSavingsEndBalance[2]);
				
				_c.getSavingsAccount().setBalance(sEndBalance);
				
				
				
			} else {
				JOptionPane.showMessageDialog(frameAdvance, "You need to initiate a loan first!");
			}
		}
		
			
	
		
		
	}
}
