package com.jeneriz.projects.pi;

import static junit.framework.Assert.*;

import org.junit.Test;

/**
 * Common test to pass by all implementations.
 */
public abstract class TestPiStringReader {

	protected PiStringReader reader;

	@Test
	public void testRead() {
		String read = reader.read(100);
		System.out.println(read);
		assertEquals("1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679", read);
		
		read = reader.read(100);
		System.out.println(read);
		assertEquals("8214808651328230664709384460955058223172535940812848111745028410270193852110555964462294895493038196", read);
		
		read = reader.read(100);
		System.out.println(read);
		assertEquals("4428810975665933446128475648233786783165271201909145648566923460348610454326648213393607260249141273", read);
	}
}
