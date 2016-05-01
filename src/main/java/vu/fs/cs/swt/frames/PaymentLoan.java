package vu.fs.cs.swt.frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vu.fs.cs.swt.beans.*;
import vu.fs.cs.swt.systemclasses.System.Customers;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.regex.Pattern;

import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;

public class PaymentLoan {

	private JFrame frame;
	private static Customer _c; 
	private JTextField txtAmount;
	private DefaultListModel<String> model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentLoan window = new PaymentLoan();
					if(_c != null) {
						window = new PaymentLoan(_c);
					}
					else {
						window = new PaymentLoan();
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
	public PaymentLoan() {
		initialize();
	}
	
	public PaymentLoan(Customer c) {
		_c = c;
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
		
		JLabel lblSelectLoan = new JLabel("Select loan:");
		lblSelectLoan.setBounds(110, 55, 99, 14);
		frame.getContentPane().add(lblSelectLoan);
		
		model = new DefaultListModel<String>();		
		int counter=0;
		final Integer[] ids = new Integer[_c.getLoans().size()];
		for(Loan l : _c.getLoans()) {
			ids[counter] = (int)l.getId();
			counter++;
			String s = "Loan " + counter;
			model.addElement(s);
		}
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(110, 145, 99, 14);
		frame.getContentPane().add(lblAmount);
		
		txtAmount = new JTextField();
		txtAmount.setBounds(219, 142, 99, 20);
		frame.getContentPane().add(txtAmount);
		txtAmount.setColumns(10);
		
		JPanel loansPanel = new JPanel();
		loansPanel.setBounds(219, 43, 99, 88);
		frame.getContentPane().add(loansPanel);
		
		final JList<String> list = new JList<String>(model);
		list.setBackground(Color.LIGHT_GRAY);
		list.setVisibleRowCount(3);
		list.setBounds(219, 43, 99, 88);
		list.setSelectedIndex(0);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		loansPanel.add(list);
		
		JButton btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtAmount.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "You must enter the amount you wish to pay");
					return;
				}
				Pattern pattern = Pattern.compile("[0-9]+(.[0-9]{1,2})?");
				if(!pattern.matcher(txtAmount.getText()).matches()) {
					JOptionPane.showMessageDialog(frame, "Only numbers are allowed: 500 or 500.0 or 500.00");
					txtAmount.setText("");
					return;
			    }
				if(_c != null) {
					for(int i = 0; i < 3; i++) {
						if(list.isSelectedIndex(i)) {
							for(Loan l : _c.getLoans()) {
								if((int)l.getId() == ids[i]) {									
									if(Double.parseDouble(txtAmount.getText()) > l.getBalance()) {
										JOptionPane.showMessageDialog(frame, "You cannot pay more than the current balance. "
												+ "Your current balance is: " + l.getBalance());
										txtAmount.setText("");
										return;
									}
									try {
										l.reduceBalance(Double.parseDouble(txtAmount.getText()));
										if(l.getBalance() == 0) {
											_c.removeLoan(l);
										}
										
										//set delinquent if the minimum payment is less 
										if((Double.parseDouble(txtAmount.getText()) < l.getMinimumPayment())
												&& (l.getBalance() > l.getMinimumPayment())) {
											l.setIsDelinquent(true);
										} else {
											l.setIsDelinquent(false);
										}
										_c.setPaymentLoan(true);
										_c = Customers.update(_c);
										JOptionPane.showMessageDialog(frame, "You have successfully paid for your loan!");
										
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
						}
					}
				}
			}
		});
		btnPay.setBounds(110, 190, 99, 23);
		frame.getContentPane().add(btnPay);
		
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
		btnBack.setBounds(219, 190, 99, 23);
		frame.getContentPane().add(btnBack);
	}
}
