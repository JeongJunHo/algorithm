package baekjoon.bj2579_계단오르기;

import java.util.Scanner;

public class 계단오르기 {
	
	static int[][] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		int[][] dp = new int[n][2];
		
		dp[0][0] = arr[0];
		dp[1][0] = arr[0] + arr[1];
		dp[1][1] = arr[1];
		
		for (int i = 2; i < arr.length; i++) {
			dp[i][0] = dp[i-1][1] + arr[i];
			dp[i][1] = dp[i-2][0] + arr[i];
			dp[i][1] = Math.max(dp[i][1], dp[i-2][1] + arr[i]);
		}

		System.out.println(Math.max(dp[n-1][0], dp[n-1][1]));
	}
}
