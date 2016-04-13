package vu.fs.cs.swt.beans;

import java.io.Serializable;
import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Saving implements Serializable {
	private static final long serialVersionUID = -2723446500566528020L;
	
	@Id
	@GeneratedValue
	private long _id;
	Double _balance;
	Double _interestRate;
	public Saving() {}
	
	public Saving(Double balance, Double interestRate) throws Exception{
		this.setBalance(balance);
		this.setInterestRate(interestRate);
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
}