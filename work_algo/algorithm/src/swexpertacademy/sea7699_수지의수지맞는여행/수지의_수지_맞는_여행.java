package swexpertacademy.sea7699_수지의수지맞는여행;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수지의_수지_맞는_여행 {
	static int ans, r, c;
	static char[][] arr;
	static boolean[] visited;
	static int[][] pos = {{1,0},{0,1},{0,-1},{-1,0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			visited = new boolean[26];
			ans = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			arr = new char[r][c];
			
			for (int i = 0; i < r; i++) {
				arr[i] = br.readLine().toCharArray();
			}
			
			visited[alphaNum(arr[0][0])] = true;
			dfs(0,0,1);
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(int row, int col, int cnt) {
		if(ans == 26) {
			return;
		}
		
		for (int i = 0; i < pos.length; i++) {
			int nr = row + pos[i][0];
			int nc = col + pos[i][1];
			
			
			if(posCheck(nr, nc) && !visited[alphaNum(arr[nr][nc])]) {
				visited[alphaNum(arr[nr][nc])] = true;
				dfs(nr, nc, cnt + 1);
				visited[alphaNum(arr[nr][nc])] = false;
			}
		}
		
		ans = Math.max(ans, cnt);
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < r && col >= 0 && col < c;
	}
	
	static int alphaNum(char alpha) {
		return alpha - 'A';
	}
}
