package edu.westga.cs3110.unicoder.model;

public class Codepoint {
	public String codepointHex;
	
	public Codepoint(String codepointHex) {
		if (codepointHex == null) {
			throw new IllegalArgumentException();
		}
		this.codepointHex = codepointHex;
	}
}
