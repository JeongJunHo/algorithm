package swexpertacademy.sea3307_최장증가부분수열;

import java.util.Arrays;
import java.util.Scanner;

public class 최장증가부분수열 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; tc++) {
			int n = sc.nextInt();
			int[] arr = new int[n];
			int[] size = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = sc.nextInt();
			}
			
			for (int i = 0; i < n; i++) {
				int cnt = 1;
				for (int j = 0; j < i; j++) {
					if(arr[j] < arr[i] && cnt <= size[j]) {
						cnt = size[j] + 1;
					}
				}
				size[i] = cnt;
			}
			
			Arrays.sort(size);
			System.out.println("#" + tc + " " + (size[size.length-1]));
		}
	}
}
