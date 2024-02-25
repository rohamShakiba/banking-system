package banking.model.impl;

import banking.exception.InsufficientFundsException;
import banking.exception.InvalidTransactionException;

public class CheckingAccount extends BankAccount {

    private double overdraftLimit;

    public CheckingAccount(String accountNumber,
                           String accountHolderName,
                           double overdraftLimit) {
        super(accountNumber, accountHolderName);
        setOverdraftLimit(overdraftLimit);
    }

    public CheckingAccount(String accountNumber,
                           String accountHolderName,
                           double balance,
                           double overdraftLimit) {
        super(accountNumber, accountHolderName, balance);
        setOverdraftLimit(overdraftLimit);
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        if (overdraftLimit < 0) {
            throw new IllegalArgumentException("Overdraft limit cannot be negative");
        }
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        } else if (amount > getBalance() + overdraftLimit) {
            throw new InvalidTransactionException("Invalid transaction for withdrawal");
        } else {
            if (amount > this.getBalance()) {
                double overdraftWithdrawal = amount - getBalance();
                setBalance(0);
                overdraftLimit -= overdraftWithdrawal;
            } else {
                setBalance(getBalance() - amount);
            }
        }
    }

    public void deductFees() {
        double TRANSACTION_FEE_RATE = 1.5;
        double fee = this.getBalance() * TRANSACTION_FEE_RATE;
        withdraw(fee);
    }
}