package edu.westga.cs3110.unicoder.model;

public class Codepoint {
	public String codepointHex;
	
	public Codepoint(String codepointHex) {
		if (codepointHex == null || codepointHex.length() > 8) {
			throw new IllegalArgumentException();
		}
		this.codepointHex = codepointHex;
	}
	
	public String toUTF32() {
		if (this.codepointHex.length() == 8) {
			return this.codepointHex;
		}
		
		return "00000000".substring(this.codepointHex.length()) + this.codepointHex;
	}
}
