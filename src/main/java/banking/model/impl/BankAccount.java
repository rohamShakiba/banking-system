package banking.model.impl;

import banking.exception.InsufficientFundsException;
import banking.model.IBankAccount;

public class BankAccount implements IBankAccount {
    private final String accountNumber;
    private final String accountHolderName;
    private double balance;

    public BankAccount(String accountNumber,
                       String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0;
    }

    public BankAccount(String accountNumber,
                       String accountHolderName,
                       double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        } else {
            throw new IllegalArgumentException("Deposit amount must be greater than 0");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than 0!");
        }
        else if (amount > this.balance) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }
        else {
            this.balance -= amount;
        }
    }

    public boolean deductFees(double fee) {
        boolean isSuccessful = false;
        if (fee <= 0) {
            throw new IllegalArgumentException("Invalid fee for deduction!");
        } else if (this.balance < fee){
            throw new InsufficientFundsException("Insufficient funds for deducting fees!");
        } else {
            this.balance -= fee;
            isSuccessful = true;
        }
        return isSuccessful;
    }
}