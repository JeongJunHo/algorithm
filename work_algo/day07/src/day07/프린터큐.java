package day07;

import java.util.Scanner;

public class 프린터큐 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			//나의 순서
			int seq = 1;
			//문서의 수
			int n = sc.nextInt();
			//문서의 위치
			int m = sc.nextInt();
			//문서의 중요도
			int[] arr = new int[n];
			
			for (int i = 0; i < arr.length; i++) {
				arr[i] = sc.nextInt();
			}
			
			//확인할 문서의 중요도
			int score = arr[m];
			
			//나보다 앞에 있는 같거나 큰 우선순위 검사
			for (int i = 0; i < m; i++) {
				if(arr[i] >= score) {
					seq++;
				}
			}
			
			//나보다 뒤에 있는 큰 우선순위 검사
			for (int i = m+1; i < arr.length; i++) {
				if(arr[i] > score) {
					seq++;
					for (int j = m+1; j < i; j++) {
						if(arr[j] == score) {
							seq--;
						}
					}
				}else if(arr[i] == score) {
					seq++;
				}
			}
			
			System.out.println(seq);
		}
	}
}
