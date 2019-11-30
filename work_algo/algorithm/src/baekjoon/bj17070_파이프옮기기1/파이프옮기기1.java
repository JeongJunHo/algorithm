package baekjoon.bj17070_파이프옮기기1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 파이프옮기기1 {
	static class Pipe{
		int row;
		int col;
		String type;
		
		public Pipe(int row, int col, String type) {
			this.row = row;
			this.col = col;
			this.type = type;
		}
	}
	
	static int n;
	static int[][] arr;
	static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		n = Integer.parseInt(br.readLine());
		arr = new int[n+1][n+1];
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		dfs(new Pipe(1, 2, "가로"));
		
		System.out.println(ans);
	}
	static void dfs(Pipe pipe) {
		if(pipe.row == n && pipe.col == n) {
			ans++;
			return;
		}
		
		//가로진행
		if(pipe.type.equals("가로") || pipe.type.equals("대각선")) {
			if(pipe.col + 1 <= n && arr[pipe.row][pipe.col + 1] == 0) {
				dfs(new Pipe(pipe.row, pipe.col+1, "가로"));
			}
		}
		//세로진행
		if(pipe.type.equals("세로") || pipe.type.equals("대각선")) {
			if(pipe.row + 1 <= n && arr[pipe.row + 1][pipe.col] == 0) {
				dfs(new Pipe(pipe.row+1, pipe.col, "세로"));
			}
		}
		//대각선
		if(pipe.col + 1 <= n && pipe.row + 1 <= n) {
			boolean success = true;
			
			for (int i = pipe.row; i <= pipe.row + 1; i++) {
				for (int j = pipe.col; j <= pipe.col + 1; j++) {
					if(arr[i][j] == 1) {
						success = false;
					}
				}
			}
			
			if(success) {
				dfs(new Pipe(pipe.row+1, pipe.col+1, "대각선"));
			}
		}
	}
}
