package swexpertacademy.sea4613_러시아국기같은깃발;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 러시아국기같은깃발 {
	static int N, M, ans;
	static char[][] map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new char[N][M];
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			dfs(0, 'W', 0, 0, 0, 0);
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	private static void dfs(int idx, char color, int sum, int w, int b, int r) {
		if(ans <= sum) {
			return;
		}
		
		if(idx == N) {
			if(w > 0 && b > 0 && r > 0) {
				ans = Math.min(ans, sum);
			}
			
			return;
		}
		
		int cost = 0;
		for (int i = 0; i < M; i++) {
			if(map[idx][i] != color) {
				cost++;
			}
		}
		
		if(color == 'W') {
			dfs(idx+1, 'W', sum+cost, w+1, b, r);
			dfs(idx+1, 'B', sum+cost, w+1, b, r);
		}else if (color == 'B') {
			dfs(idx+1, 'B', sum+cost, w, b+1, r);
			dfs(idx+1, 'R', sum+cost, w, b+1, r);
		}else if (color == 'R') {
			dfs(idx+1, 'R', sum+cost, w, b, r+1);
		}
	}
}
