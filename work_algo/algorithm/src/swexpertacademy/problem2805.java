package swexpertacademy;

import java.util.Scanner;

public class problem2805 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			int result = 0;
			int n = sc.nextInt();
			
			int arr[][] = new int[n][n];
			
			for (int i = 0; i < arr.length; i++) {
				String tmp = sc.next();
				for (int j = 0; j < tmp.length(); j++) {
					arr[i][j] = tmp.charAt(j) - 48;
				}
			}
			
			int div = arr.length / 2;

			int start = div;
			int end = (arr.length-1) - div;
			
			for (int i = 0; i < arr.length; i++) {
				for (int j = start; j <= end; j++) {
					result += arr[i][j];
				}
				if(i < div) {
					start--;
					end++;
				}else if(i >= div) {
					start++;
					end--;
				}
			}
			
//			for (int i = 0; i < n; i++) {
//				ans += map[i][n/2];
//				if(i<=n/2) {
//					for (int j = 1; j <= i; j++) {
//						ans += map[i][n/2+j];
//						ans += map[i][n/2-j];
//					}
//				}
//				else {
//					for (int j = 1; j < n-i; j++) {
//						ans += map[i][n/2+j];
//						ans += map[i][n/2-j];
//					}
//				}
//			}
			
			System.out.println("#" + tc + " " + result);
		}
	}
}
