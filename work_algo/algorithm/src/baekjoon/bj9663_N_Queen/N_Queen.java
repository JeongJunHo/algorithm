package baekjoon.bj9663_N_Queen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N_Queen {
	static int N, ans;
	static boolean[][] map;
	static int[][] pos = {{-1,-1},{-1,0},{-1,1}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new boolean[N][N];
		
		backTrack(0);
		
		System.out.println(ans);
	}
	private static void backTrack(int row) {
		if(row == N) {
			ans++;
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(check(row,i)) {
				map[row][i] = true;
				backTrack(row+1);
				map[row][i] = false;
			}
		}
	}
	private static boolean check(int row, int col) {
		for (int i = 0; i < pos.length; i++) {
			int nr = row;
			int nc = col;
			while (true) {
				nr += pos[i][0];
				nc += pos[i][1];
				if(!posCheck(nr, nc)) break;
				if(map[nr][nc]) return false;
			}
		}
		
		return true;
	}
	
	private static boolean posCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}
}
