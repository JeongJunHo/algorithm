package day01;

import java.util.Scanner;

public class 햄버거다이어트리뷰 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = sc.nextInt(); //재료의수 1 <= N <= 20
			int L = sc.nextInt(); //제한 칼로리 1 <= L <= 10^4
			
			int[] score_arr = new int[N];
			int[] cal_arr = new int[N];
			for (int i = 0; i < N; i++) {
				score_arr[i] = sc.nextInt();
				cal_arr[i] = sc.nextInt();
			}
			MAX = 0;
			solve(score_arr, cal_arr, 0, 0, 0, L);
			System.out.println("#" + tc + " " + MAX);
		}
	}

	static int MAX = 0;
	static void solve(int[] score_arr, int[] cal_arr, int score_sum, int cal_sum, int idx, int L) {
		System.out.println(idx);
		System.out.println(cal_sum);
		if(cal_sum > L)
			return;
		if(idx == score_arr.length) {
			if(MAX < score_sum)
				MAX = score_sum;
			return;
		}
		
		solve(score_arr, cal_arr, score_sum + score_arr[idx], cal_sum + cal_arr[idx], idx+1, L);
		solve(score_arr, cal_arr, score_sum, cal_sum, idx+1, L);
	}
}
