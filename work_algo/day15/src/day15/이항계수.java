package day15;

public class 이항계수 {
	public static void main(String[] args) {
		System.out.println(bino_memo(30, 10));
	}
	
	static int[][] memo = new int[10000][10000];
	static int bino_memo(int n, int k) {
		if(k == 0 || k == n)
			return 1;
		if(memo[n][k] == 0) {
			memo[n][k] = bino_memo(n-1, k-1) + bino_memo(n-1, k);
		}
		return memo[n][k];
	}
	static int bino_dp(int n, int k) {
		int[][] dp = new int[n+1][k+1];
		dp[0][0] = 1;
		for (int i = 1; i < n + 1; i++) {
			for (int j = 0; j < k + 1; j++) {
				if (j == 0 || i == j) {
					dp[i][j] = 1;
				}else {
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
				}
			}
		}
		return dp[n][k];
	}
}
