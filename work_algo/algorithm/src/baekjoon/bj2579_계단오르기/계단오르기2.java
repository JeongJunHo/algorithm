package baekjoon.bj2579_계단오르기;

import java.util.Scanner;

public class 계단오르기2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] floor = new int[n+1];
		int[][] dp = new int[n+1][2];
		
		for (int i = 1; i <= n; i++) {
			floor[i] = sc.nextInt();
		}
		dp[1][0] = floor[1];
		dp[1][1] = floor[1];
		dp[2][0] = floor[2] + floor[1];
		dp[2][1] = floor[2];
		
		for (int i = 3; i <= n; i++) {
			dp[i][0] = dp[i-1][1] + floor[i];
			dp[i][1] = Math.max(dp[i-2][0], dp[i-2][1]) + floor[i];
		}
		
		System.out.println(Math.max(dp[n][0], dp[n][1]));
	}
}
