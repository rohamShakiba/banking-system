package banking.entity;

public class SavingsAccount extends BankAccount {
    private double interestRate;
    private double minimumBalance;

    public SavingsAccount(String accountNumber,
                          String accountHolderName) {
        super(accountNumber, accountHolderName);
    }

    public SavingsAccount(String accountNumber,
                          String accountHolderName,
                          double balance) {
        super(accountNumber, accountHolderName, balance);
    }
}
