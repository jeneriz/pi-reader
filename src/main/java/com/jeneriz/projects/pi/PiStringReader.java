package com.jeneriz.projects.pi;

/**
 * Classes implementing this interface must generate as many digits from pi as
 * requested.
 */
public interface PiStringReader {

	/**
	 * Reads the specified digits from pi.
	 * 
	 * @param numDigits digits to read
	 * @return a string with the request digits
	 */
	String read(int numDigits);
}
