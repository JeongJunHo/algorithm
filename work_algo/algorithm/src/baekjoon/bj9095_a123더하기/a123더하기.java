package baekjoon.bj9095_a123더하기;

import java.util.Scanner;

public class a123더하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n = sc.nextInt();
			int[] dp = new int[n];
			
			dp[0] = 1;
			if(n>=2)dp[1] = 2;
			if(n>=3)dp[2] = 4;
			
			if(n > 3) {
				for (int i = 3; i < dp.length; i++) {
					dp[i] = dp[i-3] + dp[i-2] + dp[i-1];
				}
			}
			
			System.out.println(dp[n-1]);
		}
	}
}
