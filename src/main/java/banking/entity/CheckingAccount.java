package banking.entity;

import banking.exception.InsufficientFundsException;

public class CheckingAccount extends BankAccount{

    private double overdraftLimit;

    public CheckingAccount(String accountNumber,
                           String accountHolderName,
                           double overdraftLimit) {
        super(accountNumber, accountHolderName);
        this.overdraftLimit = overdraftLimit;
    }

    public CheckingAccount(String accountNumber,
                           String accountHolderName,
                           double balance,
                           double overdraftLimit) {
        super(accountNumber, accountHolderName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if(amount <= 0) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        } else if(amount > getBalance() + overdraftLimit) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        } else {
            if(amount > this.getBalance()) {
                double overdraftWithdrawal = amount - getBalance();
                setBalance(0);
                overdraftLimit -= overdraftWithdrawal;
            } else {
                setBalance(getBalance() - amount);
            }
        }
    }
}
