import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import banking.model.impl.BankAccount;
import banking.exception.InsufficientFundsException;

public class BankAccountTest {
    private BankAccount account;

    @Before
    public void setUp() {
        account = new BankAccount("123456789",
                "Morteza Mohammadi",
                1000.0);
    }

    @Test
    public void testDeposit() {
        account.deposit(500.0);
        assertEquals(1500.0, account.getBalance(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDepositNegativeAmount() {
        account.deposit(-500.0);
    }

    @Test
    public void testWithdraw() {
        account.withdraw(500.0);
        assertEquals(500.0, account.getBalance(), 0.01);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testWithdrawInsufficientFunds() {
        account.withdraw(1500.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawNegativeAmount() {
        account.withdraw(-500.0);
    }
}