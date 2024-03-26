package banking.model.impl;

import banking.exception.InsufficientFundsException;
import banking.exception.InvalidTransactionException;

import java.util.Objects;

public class CheckingAccount extends BankAccount {

    private double overdraftLimit;

    private static final double DEPOSIT_FEE_RATE = 0.01;
    private static final double WITHDRAW_FEE_MIN = 20;
    private static final double WITHDRAW_FEE_STEP = 5;

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
    public void deposit(double amount) {
        if (amount > 0) {
            this.deductFees(amount, "deposit");
            super.deposit(amount);
        } else {
            throw new IllegalArgumentException("Deposit amount must be greater than 0!");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than 0!");
        } else {
            double balance = this.getBalance();
            double fee = this.calculateFees(amount, "withdraw");
            if (amount + fee > balance + this.overdraftLimit) {
                throw new InsufficientFundsException("Insufficient funds for withdrawal!");
            } else {
                super.deductFees(fee);
                balance = this.getBalance();
                if (amount > balance) {
                    this.setBalance(balance - amount);
                } else {
                    super.withdraw(amount);
                }
            }
        }
    }

    public double calculateFees(double amount,
                                String transactionType) {
        double fee = 0;

        if (Objects.equals(transactionType, "deposit")) {
            fee = amount * DEPOSIT_FEE_RATE;

            if (fee < 10) {
                fee = 10;
            } else if (fee > 100) {
                fee = 100;
            }
        } else if (Objects.equals(transactionType, "withdraw")) {
            fee = WITHDRAW_FEE_MIN + (Math.ceil(amount/1000) - 1) * WITHDRAW_FEE_STEP;
        }
        return fee;
    }

    public void deductFees(double amount,
                           String transactionType) {
        double fee = this.calculateFees(amount, transactionType);
        super.deductFees(fee);
    }
}