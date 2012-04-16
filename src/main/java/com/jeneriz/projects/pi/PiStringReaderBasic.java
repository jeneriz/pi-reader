package com.jeneriz.projects.pi;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This reader doesn't keep the state of the generation, just enough to know
 * where to continue, so all the values are regenerated every time.
 * 
 * It's provided as a reference.
 */
public class PiStringReaderBasic implements PiStringReader {

	private static final BigDecimal TWO = new BigDecimal("2");
	private static final BigDecimal FOUR = new BigDecimal("4");
	private static final BigDecimal FIVE = new BigDecimal("5");
	private static final BigDecimal TWO_THIRTY_NINE = new BigDecimal("239");

	private int numDigits = 0;

	/**
	 * {@inheritDoc}
	 */
	public String read(int numDigits) {
		int calcDigits = this.numDigits + numDigits;

		BigDecimal arccot5 = arccot(FIVE, calcDigits);
		BigDecimal arccot239 = arccot(TWO_THIRTY_NINE, calcDigits);
		BigDecimal result = FOUR.multiply(
				(FOUR.multiply(arccot5)).subtract(arccot239)).setScale(
				calcDigits, RoundingMode.DOWN);

		this.numDigits += numDigits;

		String plainString = result.toPlainString();
		return plainString.substring(plainString.length() - numDigits,
				plainString.length());
	}

	private BigDecimal arccot(BigDecimal x, int numDigits) {
		int precision = numDigits + 3; // we need to add 3 to get the sum to be precise enough
		
		BigDecimal unity = BigDecimal.ONE.setScale(precision, RoundingMode.DOWN);
		BigDecimal sum = unity.divide(x, RoundingMode.DOWN);
		BigDecimal xpower = new BigDecimal(sum.toString());
		BigDecimal term = BigDecimal.ONE;

		boolean add = false;

		for (BigDecimal n = new BigDecimal("3"); term.compareTo(BigDecimal.ZERO) != 0; n = n.add(TWO)) {
			xpower = xpower.divide(x.pow(2), RoundingMode.DOWN);
			term = xpower.divide(n, RoundingMode.DOWN);
			sum = add ? sum.add(term) : sum.subtract(term);
			add = !add;
		}
		return sum;
	}
}
