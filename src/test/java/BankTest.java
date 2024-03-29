import banking.model.impl.Bank;
import banking.model.impl.BankAccount;
import banking.model.impl.SavingsAccount;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BankTest {
    private Bank bank;
    @Before
    public void setUp() {
        Map<String, BankAccount> bankAccountMap = new HashMap<>();
        bank = new Bank(bankAccountMap);
        BankAccount account = new BankAccount("123456789", "Morteza Mohammadi", 1000.0);
        bank.addAccount(account);
    }
    @Test
    public void testAddAccount() {
        SavingsAccount savingsAccount = new SavingsAccount("213456789", "Roham Shakiba", 2.0, 2000.0);
        boolean result = bank.addAccount(savingsAccount);
        assertTrue(result);
    }

    @Test
    public void testAddAccountFalse() {
        SavingsAccount savingsAccount = new SavingsAccount("123456789", "Roham Shakiba", 2.0, 2000.0);
        boolean result = bank.addAccount(savingsAccount);
        assertFalse(result);
    }

    @Test
    public void testReadAndWriteBankFromAndToFile() {
        SavingsAccount savingsAccount = new SavingsAccount("213456789", "Roham Shakiba", 2.0, 2000.0);
        bank.addAccount(savingsAccount);
        bank.writeBankToFile();
        bank.readBankFromFile();
    }


}
