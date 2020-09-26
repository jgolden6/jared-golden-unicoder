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
		return "00000000".substring(this.codepointHex.length()) + this.codepointHex;
	}
	
	public String toUTF16() {
		if (this.codepointHex.equals("")) {
			return "0000";
		}
		
		int codepointInt = Integer.parseUnsignedInt(this.codepointHex, 16);
		
		if (codepointInt >= 0x0000 && codepointInt <= 0xD7FF ||
				codepointInt >= 0xE000 && codepointInt <= 0xFFFF) {
			return "0000".substring(this.codepointHex.length()) + this.codepointHex;
		}
		
		String codepointBinary = Integer.toBinaryString(codepointInt - 0x10000);
		String codepointBinaryPadded = "00000000000000000000".substring(codepointBinary.length()) + codepointBinary;
		String upperSurrogateBinary = codepointBinaryPadded.substring(0, 10);
		String lowerSurrogateBinary = codepointBinaryPadded.substring(10);
		int upperSurrogate = 0xD800 + Integer.parseUnsignedInt(upperSurrogateBinary, 2);
		int lowerSurrogate = 0xDC00 + Integer.parseUnsignedInt(lowerSurrogateBinary, 2);
		String result = Integer.toHexString(upperSurrogate) + Integer.toHexString(lowerSurrogate);
		
		return result;
	}

}
