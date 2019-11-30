package baekjoon.bj17069_파이프옮기기2;

import java.util.Scanner;

public class 파이프옮기기2_dp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++)
				map[i][j] = sc.nextInt();
		}
		//
		long[][][] dp = new long[N][N][3];
		dp[0][1][0] = 1;
		for(int i = 2; i < N; i++)
		{
			if(map[0][i] == 0)
				dp[0][i][0] = dp[0][i-1][0];
		}
		for(int i = 1; i < N; i++) {
			for(int j = 1; j < N; j++) {
				if(map[i][j] == 1)
					continue;
				if(map[i][j] == 0) {
					dp[i][j][0] += dp[i][j-1][0] + dp[i][j-1][2];
					dp[i][j][1] += dp[i-1][j][1] + dp[i-1][j][2];
				}
				if(map[i-1][j] == 0 && map[i][j-1] == 0)
					dp[i][j][2] += dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
			}
		}
		System.out.println(dp[N-1][N-1][0]+dp[N-1][N-1][1]+dp[N-1][N-1][2]);
	}

}
