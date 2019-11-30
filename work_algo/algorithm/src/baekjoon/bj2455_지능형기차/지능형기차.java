package baekjoon.bj2455_지능형기차;

import java.util.Scanner;

public class 지능형기차 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int ans = 0;
		int people = 0;
		
		for (int i = 0; i < 4; i++) {
			people -= sc.nextInt();
			people += sc.nextInt();
			
			ans = Math.max(ans, people);
		}
		
		System.out.println(ans);
	}
}
