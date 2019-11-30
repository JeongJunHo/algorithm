package day04;

import java.util.Scanner;

public class 부분수열의합 {
	static int cnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//테스트숫자
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			cnt = 0;
			
			//자연수 갯수
			int n = sc.nextInt();
			//구해야하는 합
			int k = sc.nextInt();
			//숫자배열
			int[] arr = new int[n];
			//제외용 배열
			boolean[] check = new boolean[n];
			
			for (int i = 0; i < arr.length; i++) {
				arr[i] = sc.nextInt();
			}
			
			sub(arr, check, 0, k, 0);
			
			System.out.println("#" + tc + " " + cnt);
		}
	}
	
	static void sub(int[] arr, boolean[] check, int idx, int k, int sum) {
		if(sum == k) {
			cnt++;
			return;
		}
		if(idx == arr.length) {
			return;
		}
		
		check[idx] = true;
		sub(arr, check, idx+1, k, sum + arr[idx]);
		check[idx] = false;
		sub(arr, check, idx+1, k, sum);
	}
}