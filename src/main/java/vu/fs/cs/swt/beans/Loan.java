package vu.fs.cs.swt.beans;

import java.io.Serializable;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Loan implements Serializable {
	private static final long serialVersionUID = -2723446500566528020L;
	
	@Id
	@GeneratedValue
	private long _id;
	@Column(name = "balance", nullable=false)
	Double _balance;
	@Column(name = "interest_rate", nullable=false)
	Double _interestRate;
	@Column(name = "minimum_payment", nullable=false)
	Double _minimumPayment;
	@Column(name = "is_delinquent", nullable=false)
	Boolean _isDelinquent;	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "customer")
	Customer _customer;
	
	public Loan() {	}
	
	public Loan(Double balance, Double interestRate, Double minimumPayment,
			Customer customer) throws Exception{
		this.setBalance(balance);
		this.setInterestRate(interestRate);
		this.setMinimumPayment(minimumPayment);
		this.setIsDelinquent(false);
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
	public Double get_minimumPayment() {
		return _minimumPayment;
	}
	public void setMinimumPayment(Double _minimumPayment) {
		this._minimumPayment = _minimumPayment;
	}
	public Boolean getIsDelinquent() {
		return _isDelinquent;
	}
	public void setIsDelinquent(Boolean _isDelinquent) {
		this._isDelinquent = _isDelinquent;
	}
	public Customer getCustomer() {
		return _customer;
	}
	public void setCustomer(Customer _customer) {
		this._customer = _customer;
	}
}