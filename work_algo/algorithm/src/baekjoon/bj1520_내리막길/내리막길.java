package baekjoon.bj1520_내리막길;

import java.util.Arrays;
import java.util.Scanner;

public class 내리막길 {
	static int H, W;
	static int[][] arr;
	static int[][] memo;
	static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static void main(String[] args) {
		int result = -1;
		Scanner sc = new Scanner(System.in);
		
		H = sc.nextInt();
		
		W = sc.nextInt();
		arr = new int[H][W];
		memo = new int[H][W];
		
		for (int i = 0; i < H; i++) {
			Arrays.fill(memo[i], -1);
		}
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		result = dfs(0, 0);
		
		System.out.println(result);
	}
	
	private static int dfs(int row, int col) {
		int total = 0;
		
		if(memo[row][col] != -1) {
			return memo[row][col];
		}
		
		if(row == H-1 && col == W-1) {
			return 1;
		}
		
		for (int i = 0; i < pos.length; i++) {
			int nr = row + pos[i][0];
			int nc = col + pos[i][1];
			
			if(posCheck(nr, nc) && arr[row][col] > arr[nr][nc]) {
				total += dfs(nr, nc);
			}
		}
		
		memo[row][col] = total;
		
		return total;
	}
	
	private static boolean posCheck(int row, int col) {
		return row >= 0 && row < H && col >= 0 && col < W;
	}
}
