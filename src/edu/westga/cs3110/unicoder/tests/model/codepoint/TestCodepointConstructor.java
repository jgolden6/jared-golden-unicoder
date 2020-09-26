package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3110.unicoder.model.Codepoint;

class TestCodepointConstructor {

	@Test
	void testValidConstructor() {
		Codepoint codepoint = new Codepoint("0000");
		
		assertEquals("0000", codepoint.codepointHex);
	}
	
	@Test
	void testInvalidConstrcutor() {
		assertThrows(IllegalArgumentException.class, ()-> {
			new Codepoint(null);
		});
	}

}
