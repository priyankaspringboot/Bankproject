package main;

import java.util.Scanner;

import banklogic.HDFCBankAccount;
import banklogic.ICICIBankAccount;
import banklogic.SBIBankAccount;
import exception.InsufficientFundsException;
import exception.NegativeAmountException;
import exception.NotInitializedException;
import spec.BankAccount;

public class Bank {

public static void main(String[] args) {
		
		Scanner scn = new Scanner(System.in);
		
		System.out.print("Enter accNum: ");
		long accNum = scn.nextLong(); scn.nextLine();

		System.out.print("Enter accHName: ");
		String accHName = scn.nextLine();
		
		System.out.print("Enter balance: ");
		double balance = scn.nextDouble();
		
		BankAccount acc;
		try {
			acc = new HDFCBankAccount(accNum, accHName, balance);
			acc = new ICICIBankAccount(accNum, accHName, balance);
			acc = new SBIBankAccount(accNum, accHName, balance);
			loop:while(true) {
				System.out.println("\nChoose one option");
				System.out.println("  1. Deposit");
				System.out.println("  2. Withdraw");
				System.out.println("  3. Current Balance");
				System.out.println("  4. Exit");
				
				System.out.print("Enter one option: ");
				int option = scn.nextInt(); 
				
				switch(option) {
				
					case 1: //deposit case
						try {
							System.out.print("Enter deposit amt: ");
							double amt = scn.nextDouble();
							acc.deposit(amt);
							System.out.print(amt + " is credited");
						} catch (NegativeAmountException e) {
							System.out.println(e.getMessage());
						}
						break;
						
					case 2: //withdraw case
						try {
							System.out.print("Enter withdraw amt: ");
							double amt = scn.nextDouble();
							acc.withdraw(amt);
							System.out.print(amt + " is debited");
						} catch (NegativeAmountException | InsufficientFundsException e) {
							System.out.println("Error: "+ e.getMessage());
						}
						break;
						
					case 3: //current balance
						System.out.print("Current Balace: ");
						acc.currentBalance();
						break;
						
					case 4: //exit
						System.out.println("Thank You, Visit Again :-)");
						break loop;
						
					default: //wrong input
						System.out.println("Wrong option");
						
				}//switch close
			}
		}catch (NotInitializedException e) {
			//no-op
		}		
	}//main close
}
