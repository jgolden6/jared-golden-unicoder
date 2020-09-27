package edu.westga.cs3110.unicoder.model;

/**
 * This class takes in a codepoint as a hexadecimal string
 * and can convert it to UTF-8, UTF-16, or UTF-32 encoding
 * 
 * @author Jared Golden
 * @version Fall 2020
 */
public class Codepoint {
	private String codepointHex;
	
	/**
	 * Creates the Codepoint object
	 * 
	 * @param codepointHex The codepoint as a hexadecimal string
	 */
	public Codepoint(String codepointHex) {
		if (codepointHex == null || codepointHex.length() == 0 || codepointHex.length() > 8) {
			throw new IllegalArgumentException();
		}
		this.codepointHex = codepointHex;
	}
	
	/**
	 * Returns the codepoint in UTF-32 encoding
	 * 
	 * @return An 8 digit hexadecimal string in UTF-32 encoding
	 */
	public String toUTF32() {
		return "00000000".substring(this.codepointHex.length()) + this.codepointHex;
	}
	
	/**
	 * Return the codepoint in UTF-16 encoding
	 * 
	 * @return a 4 or 8 digit hexadecimal string in UTF-16 encoding
	 */
	public String toUTF16() {
		String result = "";
		int codepointInt = Integer.parseUnsignedInt(this.codepointHex, 16);
		String codepointBinary = Integer.toBinaryString(codepointInt - 0x10000);

		if (codepointInt <= 0xD7FF || codepointInt >= 0xE000 && codepointInt <= 0xFFFF) {
			result = "0000".substring(this.codepointHex.length()) + this.codepointHex;
		}
		
		if (codepointInt >= 0x10000 && codepointInt <= 0x10FFFF) {
			result = this.encodeFourByteCodepointUTF16(codepointBinary);
		}
		
		return result;
	}
	
	/**
	 * Return the codepoint in UTF-8 encoding
	 * 
	 * @return A 2, 4, 6, or 8 digit hexadecimal string in UTF-8 encoding
	 */
	public String toUTF8() {
		String result = "";
		int codepointInt = Integer.parseUnsignedInt(this.codepointHex, 16);
		String codepointBinary = Integer.toBinaryString(codepointInt);
		
		if (codepointInt <= 0x7f) {
			result = "00".substring(this.codepointHex.length()) + this.codepointHex;
		}
		
		if (codepointInt >= 0x0080 && codepointInt <= 0x07FF) {
			result = this.encodeTwoByteCodepointUTF8(codepointBinary);
		}
		
		if (codepointInt >= 0x0800 && codepointInt <= 0xFFFF) {
			result = this.encodeThreeByteCodepointUTF8(codepointBinary);
		}
		
		if (codepointInt >= 0x10000 && codepointInt <= 0x10FFFF) {
			result = this.encodeFourByteCodepointUTF8(codepointBinary);
		}
		
		return result;
	}
	
	private String encodeFourByteCodepointUTF16(String codepointBinary) {
		String result;
		String codepointBinaryPadded = "00000000000000000000".substring(codepointBinary.length()) + codepointBinary;
		String upperSurrogateBinary = codepointBinaryPadded.substring(0, 10);
		String lowerSurrogateBinary = codepointBinaryPadded.substring(10);
		int upperSurrogate = 0xD800 + Integer.parseUnsignedInt(upperSurrogateBinary, 2);
		int lowerSurrogate = 0xDC00 + Integer.parseUnsignedInt(lowerSurrogateBinary, 2);
		result = Integer.toHexString(upperSurrogate) + Integer.toHexString(lowerSurrogate);
		return result;
	}
	
	private String encodeTwoByteCodepointUTF8(String codepointBinary) {
		String result;
		String codepointBinaryPadded = "00000000000".substring(codepointBinary.length()) + codepointBinary;
		String byteOne = "110" + codepointBinaryPadded.substring(0, 5);
		String byteTwo = "10" + codepointBinaryPadded.substring(5);
		String combinedBytes = byteOne + byteTwo;
		result = Integer.toHexString(Integer.parseUnsignedInt(combinedBytes, 2));
		return result;
	}
	
	private String encodeThreeByteCodepointUTF8(String codepointBinary) {
		String result;
		String codepointBinaryPadded = "0000000000000000".substring(codepointBinary.length()) + codepointBinary;
		String byteOne = "1110" + codepointBinaryPadded.substring(0, 4);
		String byteTwo = "10" + codepointBinaryPadded.substring(4, 10);
		String byteThree = "10" + codepointBinaryPadded.substring(10);
		String combinedBytes = byteOne + byteTwo + byteThree;
		result = Integer.toHexString(Integer.parseUnsignedInt(combinedBytes, 2));
		return result;
	}
	
	private String encodeFourByteCodepointUTF8(String codepointBinary) {
		String result;
		String codepointBinaryPadded = "000000000000000000000".substring(codepointBinary.length()) + codepointBinary;
		String byteOne = "11110" + codepointBinaryPadded.substring(0, 3);
		String byteTwo = "10" + codepointBinaryPadded.substring(3, 9);
		String byteThree = "10" + codepointBinaryPadded.substring(9, 15);
		String byteFour = "10" + codepointBinaryPadded.substring(15);
		String combinedBytes = byteOne + byteTwo + byteThree + byteFour;
		result = Integer.toHexString(Integer.parseUnsignedInt(combinedBytes, 2));
		return result;
	}

	public String getCodepointHex() {
		return this.codepointHex;
	}
	
}
