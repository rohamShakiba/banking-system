package banking.entity;

import exception.InsufficientFundsException;

public class BankAccount {
    private String accountNumber;
    private String accountHolderName;
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

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
        else {
            throw new IllegalArgumentException("Deposit amount must be greater than 0");
        }
    }

    public void withdraw(double amount) {
        if ((amount <= 0) || (amount > this.balance)) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }
        else {
            this.balance -= amount;
        }
    }

    public double getBalance() {
        return this.balance;
    }
}
