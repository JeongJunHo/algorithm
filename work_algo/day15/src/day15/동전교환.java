package day15;

import java.util.Arrays;

public class 동전교환 {
	public static void main(String[] args) {
		int N = 85;
		int[] dp = new int[N+1];
		int[] coins = {1,4,6};
		//1원으로만 거스름돈을 주는 경우
		for (int i = coins[0]; i < N+1; i++) {
			dp[i] = dp[i-1]+1;
		}
		//1원과 4원으로 주는 경우
		for (int i = coins[1]; i < N+1; i++) {
			dp[i] = Math.min(dp[i], dp[i-coins[1]] + 1);
		}
		//1원과 4원,6원으로 주는 경우
		for (int i = coins[2]; i < N+1; i++) {
			dp[i] = Math.min(dp[i], dp[i - coins[2]] + 1);
		}
		
		System.out.println(Arrays.toString(dp));
	}
}
