package swexpertacademy;

import java.util.Scanner;

public class problem7193 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			int n = sc.nextInt();
			String x = sc.next();
			int mod = 0;
			
			for (int i = 0; i < x.length(); i++) {
				mod += x.charAt(i)-'0';
			}
			
			mod %= (n-1);
			
			System.out.println("#" + tc + " " + mod);
		}
	}
}
