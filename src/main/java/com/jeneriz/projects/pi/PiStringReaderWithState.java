package com.jeneriz.projects.pi;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This reader keeps the state of the generation, just enough to know where to
 * continue, so all the values don't need to be regenerated every time.
 */
public class PiStringReaderWithState implements PiStringReader {

	private static final BigDecimal TWO = new BigDecimal("2");
	private static final BigDecimal THREE = new BigDecimal("3");
	private static final BigDecimal FOUR = new BigDecimal("4");
	private static final BigDecimal FIVE = new BigDecimal("5");
	private static final BigDecimal TWO_THIRTY_NINE = new BigDecimal("239");

	private int numDigits = 0;

	/**
	 * The calculated value of pi, so far.
	 */
	private BigDecimal result;
	
	/**
	 * {@inheritDoc}
	 */
	public String read(int numDigits) {
		int calcDigits = this.numDigits + numDigits;

		BigDecimal unity = BigDecimal.ONE.setScale(calcDigits + 3,
				RoundingMode.DOWN);
		BigDecimal arccot5 = unity.divide(FIVE, RoundingMode.DOWN);
		BigDecimal xpower = new BigDecimal(arccot5.toString());
		BigDecimal term = BigDecimal.ONE;
		BigDecimal arccot239 = unity.divide(TWO_THIRTY_NINE, RoundingMode.DOWN);
		BigDecimal xpower2 = new BigDecimal(arccot239.toString());
		BigDecimal term2 = BigDecimal.ONE;

		boolean add = false;

		for (BigDecimal n = THREE; term.add(term2).compareTo(BigDecimal.ZERO) != 0; n = n.add(TWO)) {
			xpower = xpower.divide(FIVE.pow(2), RoundingMode.DOWN);
			term = xpower.divide(n, RoundingMode.DOWN);
			arccot5 = add ? arccot5.add(term) : arccot5.subtract(term);

			xpower2 = xpower2.divide(TWO_THIRTY_NINE.pow(2), RoundingMode.DOWN);
			term2 = xpower2.divide(n, RoundingMode.DOWN);
			arccot239 = add ? arccot239.add(term2) : arccot239.subtract(term2);

			add = !add;
		}

		result = FOUR.multiply(
				(FOUR.multiply(arccot5)).subtract(arccot239)).setScale(
				calcDigits, RoundingMode.DOWN);

		this.numDigits += numDigits;

		String plainString = result.toPlainString();
		return plainString.substring(plainString.length() - numDigits,
				plainString.length());
	}
}
