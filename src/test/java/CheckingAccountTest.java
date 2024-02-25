import banking.exception.InsufficientFundsException;
import banking.exception.InvalidTransactionException;
import banking.model.impl.CheckingAccount;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheckingAccountTest {
    private CheckingAccount checkingAccount;

    @Before
    public void setUp() {
        checkingAccount = new CheckingAccount("123456789", "Morteza Mohammadi", 5000);
    }

    @Test
    public void testDeposit() {
        checkingAccount.deposit(500.0);
        assertEquals(500.0, checkingAccount.getBalance(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDepositNegativeAmount() {
        checkingAccount.deposit(-500.0);
    }

    @Test
    public void testWithdraw() {
        checkingAccount.withdraw(500.0);
        assertEquals(4500.0, checkingAccount.getOverdraftLimit(), 0.01);
    }

    @Test(expected = InvalidTransactionException.class)
    public void testWithdrawInsufficientFunds() {
        checkingAccount.withdraw(50000.0);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testWithdrawNegativeAmount() {
        checkingAccount.withdraw(-500.0);
    }
}