package baekjoon.bj17136_색종이붙이기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이붙이기20191115 {
	static final int SIZE = 10;
	static int ans = Integer.MAX_VALUE;
	static int[][] map = new int[SIZE][SIZE];
	static int[] paper = {0, 5, 5, 5, 5, 5};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < SIZE; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < SIZE; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		backTrack(0);
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	private static void backTrack(int cnt) {
		if(cnt > ans) return;
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if(map[i][j] == 1) {
					for (int s = 5; s >= 1; s--) {
						if(paper[s] == 0) continue;
						if(!posCheck(i+s-1, j+s-1)) continue;
						if(check(i,j,s)) {
							paper[s]--;
							fill(i, j, s, 0);
							backTrack(cnt+1);
							paper[s]++;
							fill(i, j, s, 1);
						}
					}
					return;
				}
			}
		}
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if(map[i][j] == 1) return;
			}
		}
		
		ans = Math.min(ans, cnt);
	}
	
	private static void fill(int r, int c, int s, int val) {
		for (int i = r; i < r+s; i++) {
			for (int j = c; j < c+s; j++) {
				map[i][j] = val;
			}
		}
	}
	
	private static boolean check(int r, int c, int s) {
		for (int i = r; i < r+s; i++) {
			for (int j = c; j < c+s; j++) {
				if(map[i][j] == 0) return false;
			}
		}
		
		return true;
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
	}
}
