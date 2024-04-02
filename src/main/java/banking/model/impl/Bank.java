package banking.model.impl;

import banking.exception.AccountNotFoundException;
import banking.model.IBank;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bank implements IBank {
    Map<String, BankAccount> bankAccountMap;

    public Bank(Map<String, BankAccount> bankAccountMap) {
        this.bankAccountMap = bankAccountMap;
    }

    @Override
    public boolean addAccount(BankAccount bankAccount) {
        if(!bankAccountMap.containsKey(bankAccount.getAccountNumber())) {
            bankAccountMap.put(bankAccount.getAccountNumber(), bankAccount);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAccount(String accountNumber) {
        if(bankAccountMap.containsKey(accountNumber)) {
            bankAccountMap.remove(accountNumber);
            return true;
        }
        throw new AccountNotFoundException("Account with account number " + accountNumber + " not found");
    }

    @Override
    public BankAccount findAccount(String accountNumber) {
        if(bankAccountMap.containsKey(accountNumber)) {
            return bankAccountMap.get(accountNumber);
        }
        throw new AccountNotFoundException("Account with account number " + accountNumber + " not found");
    }

    @Override
    public List<BankAccount> listAccounts() {
        return new ArrayList<>(bankAccountMap.values());
    }

    public void writeBankToFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("bank.dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this.bankAccountMap);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Bank data saved to bank.dat file successfully");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    public void readBankFromFile() {
        Map<?, ?> bankAccountMap = null;

        try {
            FileInputStream fileInputStream = new FileInputStream("bank.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            bankAccountMap = (Map<?, ?>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        System.out.println("Bank data read from bank.dat file successfully:");
        System.out.println(bankAccountMap);
    }
}