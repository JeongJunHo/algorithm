package swexpertacademy;

import java.util.Scanner;

public class problem1979 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			int cnt = 0;
			
			int[][] arr = new int[n][n];
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			
			for (int i = 0; i < arr.length; i++) {
				int horizontal = 0;
				int vertical = 0;
				for (int j = 0; j < arr.length; j++) {
					if(horizontal < 3) {
						if(arr[i][j] == 1) {
							horizontal++;
						}
					}else {
//						if()
					}
					
					if(arr[j][i] == 1) {
						vertical++;
					}
				}
				if(horizontal == 3) {
					cnt++;
				}
				if(vertical == 3){
					cnt++;
				}
			}// arr for end
			
			System.out.println("#" + tc + " " + cnt);
		}// tc for end
	}
}
