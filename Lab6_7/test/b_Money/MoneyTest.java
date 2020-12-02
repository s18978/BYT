package b_Money;
import static org.junit.Assert.*;

import b_Money.Currency;
import b_Money.Money;
import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100, DKK120;

	/** setting up currencies and the money instances */
	@Before
	public void setUp() {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
		DKK120 = new Money(12000,DKK);
	}
	/** checks if the returned value is the same as the one with which an object was initialized */
	@Test
	public void testGetAmount() {
		assertEquals(Integer.valueOf(10000),SEK100.getAmount());
		assertEquals(Integer.valueOf(1000),EUR10.getAmount());
		assertEquals(Integer.valueOf(20000),SEK200.getAmount());
		assertEquals(Integer.valueOf(2000),EUR20.getAmount());
		assertEquals(Integer.valueOf(0),SEK0.getAmount());
		assertEquals(Integer.valueOf(0),EUR0.getAmount());
		assertEquals(Integer.valueOf(-10000),SEKn100.getAmount());
		assertEquals(Integer.valueOf(12000),DKK120.getAmount());
	}
	/** checks if the currency equals to the one with which an object was created */
	@Test
	public void testGetCurrency() {
		assertEquals(SEK,SEK0.getCurrency());
		assertEquals(SEK,SEK100.getCurrency());
		assertEquals(SEK,SEK200.getCurrency());
		assertEquals(SEK,SEKn100.getCurrency());
		assertEquals(EUR, EUR10.getCurrency());
		assertEquals(EUR,EUR0.getCurrency());
		assertEquals(EUR,EUR20.getCurrency());
		assertEquals(DKK, DKK120.getCurrency());
	}
	/** checks if the returned string is the same as the one it should return */
	@Test
	public void testToString() {
		assertEquals("100.0 SEK",SEK100.toString());
		assertEquals("10.0 EUR", EUR10.toString());
		assertEquals("200.0 SEK",SEK200.toString());
		assertEquals("20.0 EUR", EUR20.toString());
		assertEquals("0.0 EUR", EUR0.toString());
		assertEquals("0.0 SEK",SEK0.toString());
		assertEquals("-100.0 SEK",SEKn100.toString());
		assertEquals("120.0 DKK", DKK120.toString());
	}
	/** checks if the universal value is the same as the amount of this money in the universal value */
	@Test
	public void testGlobalValue() {
		assertEquals(Integer.valueOf(150000),SEK100.universalValue());
		assertEquals(Integer.valueOf(150000),EUR10.universalValue());
		assertEquals(Integer.valueOf(300000),SEK200.universalValue());
		assertEquals(Integer.valueOf(300000),EUR20.universalValue());
		assertEquals(Integer.valueOf(0),EUR0.universalValue());
		assertEquals(Integer.valueOf(0),SEK0.universalValue());
		assertEquals(Integer.valueOf(-150000),SEKn100.universalValue());
		assertEquals(Integer.valueOf(240000),DKK120.universalValue());
	}
	/** test of the equals method */
	@Test
	public void testEqualsMoney() {
		assertTrue("must be equal", EUR10.equals(SEK100));
		assertTrue("must be equal", EUR20.equals(SEK200));
		assertFalse("mustn't be equal", SEK200.equals(DKK120));
	}
	/** checks if the addition works correctly */
	@Test
	public void testAdd() {
		Money result_1 = EUR10.add(SEK100);
		Money result_2 = EUR20.add(SEK200);
		assertEquals(Integer.valueOf(2000), result_1.getAmount());
		assertEquals(Integer.valueOf(4000), result_2.getAmount());
	}
	/** checks if the subtraction works correctly */
	@Test
	public void testSub() {
		Money result_1 = EUR10.sub(SEK100);
		Money result_2 = EUR20.sub(SEK200);
		assertEquals(Integer.valueOf(0),result_1.getAmount());
		assertEquals(Integer.valueOf(0),result_2.getAmount());
	}
	/** checks if the returned value correctly tells which amount is zero */
	@Test
	public void testIsZero() {
		String s_1 = "should be zero";
		String s_2 = "shouldn't be zero";
		assertFalse(s_2,SEK100.isZero());
		assertFalse(s_2,SEK200.isZero());
		assertFalse(s_2,DKK120.isZero());
		assertTrue(s_1,SEK0.isZero());
		assertTrue(s_1,EUR0.isZero());
	}
	/** checks if the negation of amount is correct */
	@Test
	public void testNegate() {
		assertEquals(Integer.valueOf(-10000),SEK100.negate().getAmount());
		assertEquals(Integer.valueOf(-1000),EUR10.negate().getAmount());
		assertEquals(Integer.valueOf(-20000),SEK200.negate().getAmount());
		assertEquals(Integer.valueOf(-2000),EUR20.negate().getAmount());
		assertEquals(Integer.valueOf(10000),SEKn100.negate().getAmount());
		assertEquals(Integer.valueOf(-0),SEK0.negate().getAmount());
		assertEquals(Integer.valueOf(-0),EUR0.negate().getAmount());
		assertEquals(Integer.valueOf(-12000),DKK120.negate().getAmount());
	}
	/** checks if the amounts of money is correctly compared */
	@Test
	public void testCompareTo() {
		assertEquals("should be equal",0, EUR10.compareTo(SEK100));
		assertEquals("should be equal",0, EUR20.compareTo(SEK200));
		assertTrue("should be >",0 > EUR10.compareTo(SEK200));
		assertTrue("should be >",0 > EUR0.compareTo(SEK100));
		assertTrue("should be >",0 > EUR0.compareTo(DKK120));
	}
}