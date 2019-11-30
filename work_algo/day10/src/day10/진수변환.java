package day10;

import java.math.BigInteger;

public class 진수변환 {
	public static void main(String[] args) {
		String str = "01D06079861D79F99F";
		
		String binary = "";
		
		for (int i = 0; i < str.length(); i++) {
			binary += parseHexToBinary(str.charAt(i));
		}
		

		System.out.println(binary);

		int start = 0;
		int end = 7;

		while (end < binary.length()) {
			System.out.println(Integer.parseInt(binary.substring(start, end), 2));
			start += 7;
			end += 7;
		}

		System.out.println(Integer.parseInt(binary.substring(start), 2));
	}

	static String parseHexToBinary(char target) {
		String binary = null;

		switch (target) {
		case '0':
			binary = "0000";
			break;
		case '1':
			binary = "0001";
			break;
		case '2':
			binary = "0010";
			break;
		case '3':
			binary = "0011";
			break;
		case '4':
			binary = "0100";
			break;
		case '5':
			binary = "0101";
			break;
		case '6':
			binary = "0110";
			break;
		case '7':
			binary = "0111";
			break;
		case '8':
			binary = "1000";
			break;
		case '9':
			binary = "1001";
			break;
		case 'A':
			binary = "1010";
			break;
		case 'B':
			binary = "1011";
			break;
		case 'C':
			binary = "1100";
			break;
		case 'D':
			binary = "1101";
			break;
		case 'E':
			binary = "1110";
			break;
		case 'F':
			binary = "1111";
			break;
		}

		return binary;
	}
}
