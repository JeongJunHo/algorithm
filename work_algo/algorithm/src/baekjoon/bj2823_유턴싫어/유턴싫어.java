package baekjoon.bj2823_유턴싫어;

import java.util.Scanner;

public class 유턴싫어 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int r = sc.nextInt();
		int c = sc.nextInt();
		
		char[][] arr = new char[r+2][c+2];
		
		for (int i = 1; i <= r; i++) {
			String str = sc.next();
			for (int j = 1; j <= c; j++) {
				arr[i][j] = str.charAt(j-1);
			}
		}
		
		int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
		int ans = 0; 
		for (int i = 1; ans == 0 && i <= r; i++) {
			for (int j = 1; ans == 0 && j <= c; j++) {
				if(arr[i][j] == '.') {
					int cnt = 0;
					for (int k = 0; k < pos.length; k++) {
						int nr = i + pos[k][0];
						int nc = j + pos[k][1];
						
						if(arr[nr][nc] == '.') {
							cnt++;
						}
					}
					
					if(cnt < 2) {
						ans = 1;
					}
				}
			}
		}
		
		System.out.println(ans);
	}
}
