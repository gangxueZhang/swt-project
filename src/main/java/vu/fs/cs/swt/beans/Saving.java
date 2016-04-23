package vu.fs.cs.swt.beans;

import java.io.Serializable;
import java.util.regex.Pattern;

import javax.persistence.*;
import javax.swing.JOptionPane;

import java.util.List;

@Entity
public class Saving implements Serializable {
	private static final long serialVersionUID = -2723446500566528020L;
	
	@Id
	@GeneratedValue
	private long _id;
	@Column(name = "balance", nullable=false)
	Double _balance;
	@Column(name = "interest_rate", nullable=false)
	Double _interestRate;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer")
	Customer _customer;
	
	public Saving() {}
	
	public Saving(Customer customer) {
		this.setBalance(0.0);
		_interestRate = 0.0;
		this.setCustomer(customer);
	}
	
	public Saving(Double balance, Customer customer) throws Exception{
		this.setBalance(balance);
		this.setInterestRate();
		this.setCustomer(customer);
	}
	
	public long getId() {
		return _id;
	}
	public void setId(long _id) {
		this._id = _id;
	}
	public Double getBalance() {
		return _balance;
	}
	public void setBalance(Double _balance) {
		this._balance = _balance;
	}
	public Double getInterestRate() {
		return _interestRate;
	}
	public void setInterestRate() throws Exception {
		List<Loan> loans = this.getCustomer().getLoans();
		Double loan_interest = 6.0;
		if(!loans.isEmpty()) {
			for(Loan l : loans) {
				loan_interest = l.getInterestRate();
				break;
			}
		}
		this._interestRate = loan_interest/4;
	}
	public Customer getCustomer() {
		return _customer;
	}
	public void setCustomer(Customer _customer) {
		this._customer = _customer;
	}
}