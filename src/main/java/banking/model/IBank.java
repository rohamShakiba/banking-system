package banking.model;

import banking.model.impl.BankAccount;

import java.util.List;

public interface IBank {

    boolean addAccount(BankAccount bankAccount);

    boolean removeAccount(String accountNumber);

    BankAccount findAccount(String accountNumber);

    List<BankAccount> listAccounts();
}