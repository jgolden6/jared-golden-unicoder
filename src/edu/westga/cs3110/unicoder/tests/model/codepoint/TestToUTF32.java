package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs3110.unicoder.model.Codepoint;

class TestToUTF32 {

	@Test
	void testOneDigitInput() {
		Codepoint codepoint = new Codepoint("1");
		
		assertEquals("00000001", codepoint.toUTF32());
	}

	@Test
	void testSevenDigitInput() {
		Codepoint codepoint = new Codepoint("1111111");
		
		assertEquals("01111111", codepoint.toUTF32());
	}
	
	@Test
	void testEightDigitInput() {
		Codepoint codepoint = new Codepoint("11111111");
		
		assertEquals("11111111", codepoint.toUTF32());
	}
}
