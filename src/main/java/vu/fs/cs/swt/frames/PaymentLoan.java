package vu.fs.cs.swt.frames;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import vu.fs.cs.swt.beans.*;

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
		int i=0;
		for(Loan l : _c.getLoans()) {
			i++;
			String s = "Loan " + i;
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
		
		JList<String> list = new JList<String>(model);
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