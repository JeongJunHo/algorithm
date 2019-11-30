package jungol.jo1681_해밀턴순환회로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 해밀턴순환회로 {
	static int N, ans = Integer.MAX_VALUE;
	static int[][] adj;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		adj = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		backTrack(0,0,0, new boolean[N]);
		
		System.out.println(ans);
	}
	private static void backTrack(int current, int cnt, int cost, boolean[] visited) {
		if(ans <= cost) return;
		if(cnt == N-1) {
			if(adj[current][0] > 0) {
				ans = Math.min(ans, cost + adj[current][0]);
			}
			return;
		}
		
		for (int i = 1; i < N; i++) {
			if(!visited[i] && adj[current][i] > 0) {
				visited[i] = true;
				backTrack(i, cnt+1, cost+adj[current][i], visited);
				visited[i] = false;
			}
		}
	}
}
