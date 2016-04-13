package vu.fs.cs.swt.beans;

import java.io.Serializable;
import java.util.regex.Pattern;

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
	Double _balance;
	Double _interestRate;
	Double _minimumPayment;
	Boolean _isDelinquent;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "customer")
	Customer _customer;
	public Loan() {}
	
	public Loan(Double balance, Double interestRate, Double minimumPayment) throws Exception{
		this.setBalance(balance);
		this.setInterestRate(interestRate);
		this.setMinimumPayment(minimumPayment);
		this.setIsDelinquent(false);
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
}