package swexpertacademy;

import java.util.Scanner;

public class problem1285 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		//테스트케이스
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			int cnt = 0;
			int gap = 100000;
			
			//돌을 던지는 사람의 수
			int n = sc.nextInt();
			
			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = sc.nextInt();
			}
			
			for (int i : arr) {
				if(Math.abs(i) < gap) {
					gap = Math.abs(i);
				}
			}
			
			for (int i : arr) {
				if(gap == Math.abs(i)) {
					cnt++;
				}
			}
			
			System.out.println("#" + tc + " " + gap + " " + cnt);
		}
	}
}
