package vu.fs.cs.swt.beans;

import java.io.Serializable;
import java.util.regex.Pattern;

import javax.persistence.*;

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
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer")
	Customer _customer;
	
	public Saving() {}
	
	public Saving(Customer customer) {
		this.setBalance(0.0);
		this.setInterestRate(0.0);
		this.setCustomer(customer);
	}
	
	public Saving(Double balance, Double interestRate, Customer customer) throws Exception{
		this.setBalance(balance);
		this.setInterestRate(interestRate);
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
	public void setInterestRate(Double _interestRate) {
		this._interestRate = _interestRate;
	}
	public Customer getCustomer() {
		return _customer;
	}
	public void setCustomer(Customer _customer) {
		this._customer = _customer;
	}
}