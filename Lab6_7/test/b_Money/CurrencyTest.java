package b_Money;

import static org.junit.Assert.*;

import b_Money.Currency;
import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;

	/** setting up currencies with exchange rates */
	@Before
	public void setUp() {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		NOK = new Currency("NOK",1.25);
	}
	/** checks if it returns the correct currency name */
	@Test
	public void testGetName() {
		assertEquals("SEK",SEK.getName());
		assertEquals("DKK",DKK.getName());
		assertEquals("NOK",NOK.getName());
		assertEquals("EUR",EUR.getName());
	}
	/** checks if it returns the correct currency rate */
	@Test
	public void testGetRate() {
		assertEquals(0.15,SEK.getRate(),0.00001);
		assertEquals(0.20,DKK.getRate(),0.00001);
		assertEquals(1.25,NOK.getRate(),0.00001);
		assertEquals(1.5,EUR.getRate(),0.00001);
	}
	/** sets new rates, checks if the returned values correspond to the new rates */
	@Test
	public void testSetRate() {
		Double newRate = 0.157;
		Double change = 0.345;
		SEK.setRate(newRate);
		DKK.setRate(newRate + change);
		NOK.setRate(newRate*change);
		EUR.setRate(newRate - change);

		assertEquals(newRate, SEK.getRate(),0.00001);
		assertEquals(newRate + change, DKK.getRate(),0.00001);
		assertEquals(newRate * change, NOK.getRate(),0.00001);
		assertEquals(newRate - change, EUR.getRate(),0.00001);
	}
	/** checks if the returned value correspond to the universal value of 100 in some specific currency
	 * last 2 digits - decimal part, everything else - whole part */
	@Test
	public void testUniversalValue() {
		Integer universalAmount = 100;
		assertEquals(Integer.valueOf(1500),SEK.universalValue(universalAmount));
		assertEquals(Integer.valueOf(2000),DKK.universalValue(universalAmount));
		assertEquals(Integer.valueOf(12500),NOK.universalValue(universalAmount));
		assertEquals(Integer.valueOf(15000),EUR.universalValue(universalAmount));
	}
	/** checks if the value inserted in the method in the form (described above) corresponds to the value of the currency object in this method */
	@Test
	public void testValueInThisCurrency() {
		Integer checkAmount = 10000;
		assertEquals(Integer.valueOf(1200),NOK.valueInThisCurrency(checkAmount,SEK));
		assertEquals(Integer.valueOf(13333),SEK.valueInThisCurrency(checkAmount,DKK));
		assertEquals(Integer.valueOf(8333),EUR.valueInThisCurrency(checkAmount,NOK));
		assertEquals(Integer.valueOf(75000),DKK.valueInThisCurrency(checkAmount,EUR));
	}
}