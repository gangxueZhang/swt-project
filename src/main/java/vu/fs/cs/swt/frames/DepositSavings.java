package vu.fs.cs.swt.frames;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import vu.fs.cs.swt.beans.*;
import vu.fs.cs.swt.systemclasses.System.Customers;

public class DepositSavings {

	private JFrame frame;
	private static Customer _c;
	private JTextField txtDepositAmount;

	
	DecimalFormat df = new DecimalFormat("#.##");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepositSavings window = new DepositSavings();
					if(_c != null) {
						window = new DepositSavings(_c);
					}
					else {
						window = new DepositSavings();
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
	public DepositSavings() {
		initialize();
	}
	
	public DepositSavings(Customer c) {
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
			cCurrentBalance.setText( df.format(_c.getSavingsAccount().getBalance()));
		}
		frame.getContentPane().add(cCurrentBalance);
		
		JLabel lblDepositAmount = new JLabel("Deposit amount:");
		lblDepositAmount.setBounds(82, 108, 105, 14);
		frame.getContentPane().add(lblDepositAmount);
		
		txtDepositAmount = new JTextField();
		txtDepositAmount.setBounds(197, 105, 105, 20);
		frame.getContentPane().add(txtDepositAmount);
		txtDepositAmount.setColumns(10);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtDepositAmount.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "You must enter the amount you wish to deposit");
					return;
				}
				Pattern pattern = Pattern.compile("[0-9]+(.[0-9]{1,2})?");
				if(!pattern.matcher(txtDepositAmount.getText()).matches()) {
					JOptionPane.showMessageDialog(frame, "Only numbers are allowed: 500 or 500.0 or 500.00");
					txtDepositAmount.setText("");
					return;
			    }
				if(_c != null) {
					try {
						_c.getSavingsAccount().increaseBalance(Double.parseDouble(txtDepositAmount.getText()));
						_c.setDepositSavings(true);
						_c = Customers.update(_c);
						JOptionPane.showMessageDialog(frame, "You have succesfully deposited to your account");
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
		btnDeposit.setBounds(82, 164, 105, 23);
		frame.getContentPane().add(btnDeposit);
		
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
