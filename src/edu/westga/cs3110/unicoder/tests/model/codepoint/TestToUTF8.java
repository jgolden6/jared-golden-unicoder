package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3110.unicoder.model.Codepoint;

class TestToUTF8 {

	@Test
	void testOneByteLowerBounds() {
		Codepoint codepoint1 = new Codepoint("00");
		Codepoint codepoint2 = new Codepoint("01");

		assertTrue("00".equalsIgnoreCase(codepoint1.toUTF8()));
		assertTrue("01".equalsIgnoreCase(codepoint2.toUTF8()));
	}
	
	@Test
	void testOneByteUpperBounds() {
		Codepoint codepoint1 = new Codepoint("7E");
		Codepoint codepoint2 = new Codepoint("7F");

		assertTrue("7E".equalsIgnoreCase(codepoint1.toUTF8()));
		assertTrue("7F".equalsIgnoreCase(codepoint2.toUTF8()));
	}
	
	@Test
	void testTwoByteLowerBounds() {
		Codepoint codepoint1 = new Codepoint("0080");
		Codepoint codepoint2 = new Codepoint("0081");

		assertTrue("C280".equalsIgnoreCase(codepoint1.toUTF8()));
		assertTrue("C281".equalsIgnoreCase(codepoint2.toUTF8()));
	}

	@Test
	void testTwoByteUpperBounds() {
		Codepoint codepoint1 = new Codepoint("07FE");
		Codepoint codepoint2 = new Codepoint("07FF");

		assertTrue("DFBE".equalsIgnoreCase(codepoint1.toUTF8()));
		assertTrue("DFBF".equalsIgnoreCase(codepoint2.toUTF8()));
	}

}
