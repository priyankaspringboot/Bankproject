package banklogic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import exception.InsufficientFundsException;
import exception.NegativeAmountException;
import exception.NotInitializedException;
import spec.BankAccount;

public class HDFCBankAccount implements BankAccount {
	private static String bankName;
	private static String branchName;
	private static String ifsc;
	
	private long accNum;
	private String accHName;
	private double balance;
	
	static {
		try (BufferedReader br = 
				new BufferedReader(new FileReader("hdfcdetails.txt"))) {
			
			bankName 	= br.readLine();
			branchName 	= br.readLine();
			ifsc 		= br.readLine();
		
		}catch(FileNotFoundException e) {
			System.out.println("hdfcdetails.txt file is not found");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	
	public HDFCBankAccount(long accNum, String accHName, double balance) throws NotInitializedException {
		verifySFI();
		
		this.accNum = accNum;
		this.accHName = accHName;
		this.balance = balance;
	}

	private void verifySFI() throws NotInitializedException {
		if(bankName == null)
			throw new NotInitializedException("hdfcdetails.txt file is not found");
	}

	@Override
	public void deposit(double amt) 
					throws NegativeAmountException {
		if(amt<=0)
			throw  new NegativeAmountException("Do not pass negative amount");
		
		balance = balance + amt;
	}

	@Override
	public void withdraw(double amt) 
					throws NegativeAmountException, InsufficientFundsException {
		if(amt<=0)
			throw  new NegativeAmountException("Do not pass negative amount");

		if(amt>balance)
			throw new InsufficientFundsException("Insufficient Funds");
		
		balance = balance - amt;
	}

	@Override
	public void currentBalance() {
		System.out.println(balance);

}
}
