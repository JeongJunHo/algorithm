package swexpertacademy;

import java.util.Base64;
import java.util.Scanner;

public class problem1928 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			String encode = sc.next();
			String decode = new String(Base64.getDecoder().decode(encode));
			
			System.out.println("#" + tc + " " + decode);
		}
	}
}
