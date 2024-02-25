package banking.entity;

import banking.exception.InsufficientFundsException;

public class BankAccount implements IBankAccount {
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
        }
        else {
            throw new IllegalArgumentException("Deposit amount must be greater than 0");
        }
    }

    @Override
    public void withdraw(double amount) {
        if ((amount <= 0) || (amount > this.balance)) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }
        else {
            this.balance -= amount;
        }
    }
}
