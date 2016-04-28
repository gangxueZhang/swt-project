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
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "customer")
	Customer _customer;
	
	public Loan() {	}
	
	public Loan(Double balance, Double interestRate, Customer customer) throws Exception{
		this.setBalance(balance);
		this.setInterestRate(interestRate);
		this.setIsDelinquent(false);
		_customer = customer;
		this.interestAmount();
		this.setMinimumPayment();
		
		_customer.addLoan(this);
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
	public void setBalance(Double _balance) throws Exception {
		if(_balance < 500 || _balance > 50000) {
			throw new Exception("Loan must be between 500 and 50000");
		}
		this._balance = _balance;
	}
	public Double getInterestRate() {
		return _interestRate;
	}
	public void setInterestRate(Double _interestRate) throws Exception {
		if(_interestRate < 6 || _interestRate > 18) {
			throw new Exception("Interest rate must be between 6% and 18%");
		}
		if((_interestRate % 0.25) != 0) {
			throw new Exception("Interest rate must be increment of .25%");
		}
		this._interestRate = _interestRate;
	}
	public Double getMinimumPayment() {
		return _minimumPayment;
	}
	public void setMinimumPayment() {
		if((this.interestAmount() + 
				0.01*this._customer.getSavingsAccount().getBalance()) > 10.0) {
			this._minimumPayment = this.interestAmount() + 0.01*this._customer.getSavingsAccount().getBalance();
		}
		else {
			this._minimumPayment = 10.0;
		}
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
	
	public Double interestAmount() {
		Double interest_amount = (this.getBalance() * this.getInterestRate()/100)/12;
		return interest_amount;
	}
	public void increaseBalance(Double balance) throws Exception {
		//it makes no sense to increase a balance with a negative number
		if(balance < 0) {
			throw new Exception("The balance cannot be negative");
		}
		this._balance += balance;
	}
	public void reduceBalance(Double balance) throws Exception {
		//subtracting with a negative number will result in +, so again ambiguous in regards to the method name
		if(balance < 0) {
			throw new Exception("The balance cannot be negative");
		}
		if((this._balance - balance) < 0) {
			throw new Exception("The balance cannot be negative");
		}
		this._balance -= balance;
	}
}