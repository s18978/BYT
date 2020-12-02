package b_Money;

import static org.junit.Assert.*;

import b_Money.AccountDoesNotExistException;
import b_Money.AccountExistsException;
import b_Money.Bank;
import b_Money.Currency;
import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	/** checks if it returns the correct bank name */
	@Test
	public void testGetName() {
		assertEquals("SweBank",SweBank.getName());
		assertEquals("Nordea",Nordea.getName());
		assertEquals("DanskeBank",DanskeBank.getName());
	}
	/** checks if it returns the correct bank currency */
	@Test
	public void testGetCurrency() {
		assertEquals(SEK,SweBank.getCurrency());
		assertEquals(SEK,Nordea.getCurrency());
		assertEquals(DKK,DanskeBank.getCurrency());
	}

	/** @throws AccountExistsException if account's name is already exists */
	@Test(expected = AccountExistsException.class)
	public void testOpenAccount() throws AccountExistsException{
		SweBank.openAccount("Bob");
		Nordea.openAccount("Herdrig");
	}

	/** deposits money to the banks in different currencies, checks if bank accounts have the deposited amount of money
	 * @throws AccountDoesNotExistException if the account (on which money are referred to) doesn't exist
	 */
	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(20000,SEK));
		SweBank.deposit("Ulrika", new Money(10000,DKK));
		Nordea.deposit("Bob", new Money(13000,SEK));
		DanskeBank.deposit("Gertrud", new Money(5050,SEK));

		assertEquals(Integer.valueOf(20000),SweBank.getBalance("Bob"));
		assertEquals(Integer.valueOf(13333),SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(13000),Nordea.getBalance("Bob"));
		assertEquals(Integer.valueOf(3787),DanskeBank.getBalance("Gertrud"));
	}
	/** uses the testDeposit method, withdraws the deposited money
	 * checks if the amount of money equals to the amount that was before the deposition
	 * @throws AccountDoesNotExistException if the account (on which money are referred to) doesn't exist
	 */
	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		SweBank.deposit("Bob",new Money(20000,SEK));
		SweBank.deposit("Ulrika",new Money(10000,DKK));

		SweBank.withdraw("Bob",new Money(20000,SEK));
		SweBank.withdraw("Ulrika",new Money(10000,DKK));

		assertEquals(Integer.valueOf(0),SweBank.getBalance("Bob"));
		assertEquals(Integer.valueOf(0),SweBank.getBalance("Ulrika"));
	}

	/** uses the testWithdraw method
	 * @throws AccountDoesNotExistException if the account (on which money are referred to) doesn't exist
	 */
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals(Integer.valueOf(0),SweBank.getBalance("Bob"));
		assertEquals(Integer.valueOf(0),SweBank.getBalance("Ulrika"));
	}

	/** sends money from one account to another, checks if the amount of money in both accounts has the same changes
	 * @throws AccountDoesNotExistException if the account (on which money are referred to) doesn't exist
	 */
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.transfer("Bob",Nordea,"Bob",new Money(20000,SEK));
		assertEquals(Integer.valueOf(-20000),SweBank.getBalance("Bob"));
		assertEquals(Integer.valueOf(20000),Nordea.getBalance("Bob"));

		SweBank.transfer("Ulrika",DanskeBank,"Gertrud",new Money(20000,SEK));
		assertEquals(Integer.valueOf(-20000),SweBank.getBalance("Ulrika"));
		assertEquals(Integer.valueOf(15000),DanskeBank.getBalance("Gertrud"));

		SweBank.transfer("Bob","Ulrika",new Money(20000,SEK));
		assertEquals(Integer.valueOf(-40000),SweBank.getBalance("Bob"));
		assertEquals(Integer.valueOf(0),SweBank.getBalance("Ulrika"));
	}

	/** test of the addTimedPayment, removeTimedPayment and tick methods
	 * creates time payments, checks the amount of money on accounts, disables one timed payment
	 * after running 5 more ticks checks the amount of money again
	 * @throws AccountDoesNotExistException if the account referred to does not exist
	 */
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.addTimedPayment("Bob","bobPay",5,3,new Money(20000,SEK),SweBank,"Ulrika");
		for (int i = 0; i < 14; i++) {
			SweBank.tick();
		}
		assertEquals(Integer.valueOf(-40000),SweBank.getBalance("Bob"));
		assertEquals(Integer.valueOf(40000),SweBank.getBalance("Ulrika"));
		SweBank.removeTimedPayment("Bob","bobPay");
		for (int i = 0; i < 5; i++) {
			SweBank.tick();
		}
		assertEquals(Integer.valueOf(-40000),SweBank.getBalance("Bob"));
		assertEquals(Integer.valueOf(40000),SweBank.getBalance("Ulrika"));
	}
}