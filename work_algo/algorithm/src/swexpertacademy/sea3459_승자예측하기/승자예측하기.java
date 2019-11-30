package swexpertacademy.sea3459_승자예측하기;

import java.util.Scanner;

public class 승자예측하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; tc++) {
			long n = sc.nextLong();
			
			String winner = "Bob";
			
			if(n > 1) {
				long winCnt = 1L;
				long sum = 1L;
				while (true) {
					winCnt *= 4L;
					sum += winCnt;
					
					if(sum >= n) {
						winner = "Alice";
						break;
					}
					
					sum += winCnt;
					if(sum >= n) {
						winner = "Bob";
						break;
					}
				}
			}
			
			System.out.println("#" + tc + " " + winner);
		}
	}
}
