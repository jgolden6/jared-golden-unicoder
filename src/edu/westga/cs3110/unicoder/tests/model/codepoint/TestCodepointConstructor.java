package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs3110.unicoder.model.Codepoint;

class TestCodepointConstructor {

	@Test
	void testValidConstructor() {
		Codepoint codepoint = new Codepoint("0000");
		
		assertEquals("0000", codepoint.getCodepointHex());
	}

	@Test
	void testNullConstrcutor() {
		assertThrows(IllegalArgumentException.class, ()-> {
			new Codepoint(null);
		});
	}
	
	@Test
	void testNoInput() {
		assertThrows(IllegalArgumentException.class, ()-> {
			new Codepoint("");
		});
	}
	
	@Test
	void testNineDigitInput() {
		assertThrows(IllegalArgumentException.class, ()-> {
			new Codepoint("111111111");
		});
	}
}
