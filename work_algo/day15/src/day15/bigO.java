package day15;

import java.math.BigInteger;

public class bigO {
	public static void main(String[] args) {
		System.out.println(recur(100));
	}

	private static int recur(int idx) {
		if(idx == 1) {
			return 1;
		}
		
		return 3 * recur(idx-1) + 6;
	}
}
