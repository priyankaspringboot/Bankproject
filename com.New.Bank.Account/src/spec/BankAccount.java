package spec;

import exception.InsufficientFundsException;
import exception.NegativeAmountException;

public interface BankAccount {

	public void deposit(double amt) 
			throws NegativeAmountException;

public void withdraw(double amt) 
			throws NegativeAmountException, InsufficientFundsException;

public void currentBalance();
}
