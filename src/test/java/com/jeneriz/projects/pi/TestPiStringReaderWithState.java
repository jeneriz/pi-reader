package com.jeneriz.projects.pi;

import org.junit.Before;

public class TestPiStringReaderWithState extends TestPiStringReader {

	@Before
	public void setup() {
		reader = new PiStringReaderWithState();
	}
}
