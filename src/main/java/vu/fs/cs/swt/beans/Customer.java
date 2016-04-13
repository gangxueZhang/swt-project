package vu.fs.cs.swt.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer implements Serializable {
	private static final long serialVersionUID = -2723446500566528020L;
	
	@Id
	@GeneratedValue	
	private long _id;
	private String _firstName, _lastName, _username, _password, _accountNumber;
	private Double _beginningBalance;
	private Double _endBalance;
	private Saving _savingsAccount;
	private Boolean _initiateLoan, _paymentLoan, _depositSavings, _withdrawSavings;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "_customer")
	private List<Loan> _loans;
	
	public Customer() {}
	
	public Customer(String firstName, String lastName, String username,
			String password, String accountNumber, Saving savingsAccount) throws Exception{
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setUsername(username);
		this.setPassword(password);
		this.setAccountNumber(accountNumber);
		this.setBeginningBalance(0.0);
		this.setEndBalance(0.0);
		this.setSavingsAccount(savingsAccount);
		this._loans = new ArrayList<Loan>();
		this.setInitiateLoan(false);
		this.setPaymentLoan(false);
		this.setDepositSavings(false);
		this.setWithdrawSavings(false);
	}
	
	public Customer(String username, String password)  throws Exception{
		this.setUsername(username);
		this.setPassword(password);
	}
	
	public long getId() {
		return _id;
	}
	public void setId(long id) {
		this._id = id;
	}
	public String getFirstName() {
		return _firstName;
	}
	public void setFirstName(String firstName) throws Exception{
		Pattern pattern = Pattern.compile("[a-zA-Z]{2,}");
	    if (!pattern.matcher(firstName).matches()) {
	        throw new Exception("First name should contain only letters (minimum length is 2)");
	    }
		this._firstName = firstName;
	}
	public String getLastName() {
		return _lastName;
	}
	public void setLastName(String lastName) throws Exception{
		Pattern pattern = Pattern.compile("[a-zA-Z]{2,}");
	    if (!pattern.matcher(lastName).matches()) {
	        throw new Exception("Last name should contain only letters (minimum length is 2)");
	    }
		this._lastName = lastName;
	}
	public String getUsername() {
		return _username;
	}
	public void setUsername(String username) throws Exception{
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]{6,}");
	    if (!pattern.matcher(username).matches()) {
	        throw new Exception("Username should contain only alphanumeric characters (minimum length is 5)");
	    }
		this._username = username;
	}
	public String getPassword() {
		return _password;
	}
	public void setPassword(String password) throws Exception{
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]{6,}");
	    if (!pattern.matcher(password).matches()) {
	        throw new Exception("Password should contain only alphanumeric characters (minimum length is 6)");
	    }
		this._password = password;
	}
	public String getAccountNumber() {
		return _accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this._accountNumber = accountNumber;
	}
	public Double getBeginningBalance() {
		return _beginningBalance;
	}
	public void setBeginningBalance(Double _beginningBalance) {
		this._beginningBalance = _beginningBalance;
	}
	public Double getEndBalance() {
		return _endBalance;
	}
	public void setEndBalance(Double _endBalance) {
		this._endBalance = _endBalance;
	}
	public Saving getSavingsAccount() {
		return _savingsAccount;
	}
	public void setSavingsAccount(Saving _savingsAccount) {
		this._savingsAccount = _savingsAccount;
	}
	public List<Loan> getLoans() {
		return _loans;
	}
	public void setLoans(List<Loan> _loans) throws Exception{
		if(_loans.size() > 3) {
			throw new Exception("A customer can have up to three loans.");
		}
		if(this._loans.size() >= 3) {
			throw new Exception("This customer already has three loans.");
		}
		this._loans = _loans;
	}
	public Boolean getInitiateLoan() {
		return _initiateLoan;
	}
	public void setInitiateLoan(Boolean _initiateLoan) {
		this._initiateLoan = _initiateLoan;
	}
	public Boolean getPaymentLoan() {
		return _paymentLoan;
	}
	public void setPaymentLoan(Boolean _paymentLoan) {
		this._paymentLoan = _paymentLoan;
	}
	public Boolean getDepositSavings() {
		return _depositSavings;
	}
	public void setDepositSavings(Boolean _depositSavings) {
		this._depositSavings = _depositSavings;
	}
	public Boolean getWithdrawSavings() {
		return _withdrawSavings;
	}
	public void setWithdrawSavings(Boolean _withdrawSavings) {
		this._withdrawSavings = _withdrawSavings;
	}
	
	
	public boolean correctPassword(String pass){
		return this._password.equals(pass);
	}
}
