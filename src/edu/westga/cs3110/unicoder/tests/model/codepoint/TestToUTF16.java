package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3110.unicoder.model.Codepoint;

class TestToUTF16 {

	@Test
	void testNoInput() {
		Codepoint codepoint = new Codepoint("");
		
		assertEquals("0000", codepoint.toUTF16());
	}
	
	@Test
	void testOneDigitInput() {
		Codepoint codepoint = new Codepoint("1");
		
		assertEquals("0001", codepoint.toUTF16());
	}
	
	@Test
	void testThreeDigitInput() {
		Codepoint codepoint = new Codepoint("111");
		
		assertEquals("0111", codepoint.toUTF16());
	}
	
	@Test
	void testFourDigitInput() {
		Codepoint codepoint = new Codepoint("1111");
		
		assertEquals("1111", codepoint.toUTF16());
	}
	
	@Test
	void testLowerBounds1() {
		Codepoint codepoint1 = new Codepoint("0000");
		Codepoint codepoint2 = new Codepoint("0001");
		
		assertEquals("0000", codepoint1.toUTF16());
		assertEquals("0001", codepoint2.toUTF16());
	}
	
	@Test
	void testUpperBounds1() {
		Codepoint codepoint1 = new Codepoint("D7FE");
		Codepoint codepoint2 = new Codepoint("D7FF");
		
		assertEquals("D7FE", codepoint1.toUTF16());
		assertEquals("D7FF", codepoint2.toUTF16());
	}
	
	@Test
	void testLowerBounds2() {
		Codepoint codepoint1 = new Codepoint("E000");
		Codepoint codepoint2 = new Codepoint("E001");
		
		assertEquals("E000", codepoint1.toUTF16());
		assertEquals("E001", codepoint2.toUTF16());
	}
	
	@Test
	void testUpperBounds2() {
		Codepoint codepoint1 = new Codepoint("FFFE");
		Codepoint codepoint2 = new Codepoint("FFFF");
		
		assertEquals("FFFE", codepoint1.toUTF16());
		assertEquals("FFFF", codepoint2.toUTF16());
	}
}
