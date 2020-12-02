package b_Money;

import b_Money.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank SweBank;
	Account testAccount;

	/** Set up currencies and create accounts,
	 * @throws Exception if it tries to create an account that is already created
	 */
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK",0.20);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));
	}

	/**
	 * this method tests TimePayment class, addTimedPayment and removeTimedPayment methods;
	 * timePayments are added to the testAccount
	 * checks if they exist and are added correctly, checks if money on all accounts are reduced or increased correctly
	 * @throws AccountDoesNotExistException if bank doesn't have an account that was requested
	 */
	@Test
	public void testAddRemoveTimedPayment() throws AccountDoesNotExistException{
		testAccount.addTimedPayment("alicePay",5,0, new Money(205000, SEK),SweBank,"Alice");

		assertTrue("should already exist",testAccount.timedPaymentExists("alicePay"));

		for (int i = 0; i < 12; i++) {
			testAccount.tick();
		}
		assertEquals(Integer.valueOf(9590000),testAccount.getBalance().getAmount());
		assertEquals(Integer.valueOf(410000),SweBank.getBalance("Alice"));
	}

	/**
	 * Test of deposit and withdraw methods
	 * withdraws money from existing accounts, checks if amount is the same as should be, deposits the same amount of money as it was withdrawn,
	 * checks if the amount on these accounts is the same as it was before starting the withdrawal operation
	 */
	@Test
	public void testDepositWithdraw() {
		testAccount.withdraw(new Money(10000, SEK));
		assertEquals(Integer.valueOf(9990000),testAccount.getBalance().getAmount());
		testAccount.deposit(new Money(10000, SEK));
		assertEquals(Integer.valueOf(10000000),testAccount.getBalance().getAmount());
	}

	/**
	 * test of getBalance method
	 * checks if each account has the amount of money that it has been created with
	 * @throws AccountDoesNotExistException if bank doesn't have an account that was requested
	 */
	@Test
	public void testGetBalance() throws AccountDoesNotExistException{
		assertEquals(Integer.valueOf(10000000),testAccount.getBalance().getAmount());
		assertEquals(Integer.valueOf(0),SweBank.getBalance("Alice"));
	}
}