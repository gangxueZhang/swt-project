package vu.fs.cs.swt.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import vu.fs.cs.swt.beans.Customer;

public class DepositSavings {
	private static JFrame frameDeposit;
	private static Customer _c;
	DepositSavings window;
	
	public DepositSavings(Customer c) {
		_c = c;
		deposit();
	}
	
	public void deposit(){
		System.out.println("deposittt");
		
		frameDeposit = new JFrame("Deposit to Savings");
	    //frameAdvance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameDeposit.setBounds(200, 200, 550, 400);
		frameDeposit.getContentPane().setLayout(null);
		frameDeposit.setVisible(true);
		
		JLabel lblDepositSavings = new JLabel("How much would you like to deposit?:");
	    lblDepositSavings.setBounds(29, 24, 200, 14);
	    frameDeposit.getContentPane().add(lblDepositSavings);
	    
	    final JTextField txtDeposit = new JTextField();
	    txtDeposit.setText("0.00");
	    txtDeposit.setBounds(332, 36, 86, 20);
	    frameDeposit.add(txtDeposit);
	    txtDeposit.setColumns(10);
	    
	    JButton btnDeposit = new JButton("Deposit");
	    btnDeposit.setBounds(161, 111, 89, 23);
	    btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//_c.getSavingsAccount().setBalance( (double)(txtDeposit.getText()) );
				
				JOptionPane.showMessageDialog(frameDeposit, "Will be deposited to savings account");
				frameDeposit.dispose();
			}
		});
	    frameDeposit.add(btnDeposit);
	    
	    Double currentBalance = _c.getSavingsAccount().getBalance();
	    
	    
	}
}
