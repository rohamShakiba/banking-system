package banking;

public class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount(String accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0;
    }

    public BankAccount(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
        else {
            throw IllegalArgumentException;
        }
    }

    public void withdraw(double amount) {
        if ((amount <= 0) || (amount > this.balance)) {
            throw InsufficientFundsException;
        }
        else {
            this.balance -= amount;
        }
    }

    public double getBalance() {
        return this.balance;
    }
}
