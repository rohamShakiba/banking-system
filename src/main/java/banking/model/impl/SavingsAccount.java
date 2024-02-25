package banking.model.impl;

import banking.exception.InsufficientFundsException;
import banking.exception.InvalidTransactionException;

public class SavingsAccount extends BankAccount {
    private final double interestRate;
    private final double minimumBalance;

    public SavingsAccount(String accountNumber,
                          String accountHolderName,
                          double interestRate,
                          double minimumBalance) {
        super(accountNumber, accountHolderName);
        this.interestRate = interestRate;
        this.minimumBalance = minimumBalance;
    }

    public SavingsAccount(String accountNumber,
                          String accountHolderName,
                          double balance,
                          double interestRate,
                          double minimumBalance) {
        super(accountNumber, accountHolderName, balance);
        this.interestRate = interestRate;
        this.minimumBalance = minimumBalance;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        } else if (getBalance() - amount < minimumBalance) {
            throw new InvalidTransactionException("Invalid transaction for withdrawal");
        } else {
            setBalance(getBalance() - amount);
        }
    }

    public void applyInterest() {
        deposit(this.getBalance() * interestRate);
    }
}