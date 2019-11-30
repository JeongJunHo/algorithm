package swexpertacademy.sea2806_NQueen;

import java.util.Scanner;

public class N_Queen {
	static int n, ans;
	static int[][] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; tc++) {
			ans = 0;
			n = sc.nextInt();
			
			arr = new int[n][n];
			
			backTrac(0);
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static void backTrac(int line) {
		if(line == n) {
			ans++;
			
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if(isPossible(line,i)) {
				arr[line][i] = 1;
				backTrac(line + 1);
				arr[line][i] = 0;
			}
		}
	}
	
	static boolean isPossible(int row, int col) {
		//좌상
		for (int i = row-1, j = col-1; i >= 0 && j >= 0 ; i--, j--) {
			if(arr[i][j] == 1) {
				return false;
			}
		}
		
		//상
		for (int i = row-1, j = col; i >= 0; i--) {
			if(arr[i][j] == 1) {
				return false;
			}
		}
		
		//우상
		for (int i = row-1, j = col+1; i >= 0 && j < n ; i--, j++) {
			if(arr[i][j] == 1) {
				return false;
			}
		}
		
		return true;
	}
}
