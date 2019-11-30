package baekjoon.bj14889_스타트와링크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스타트와링크 {
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
		
		comb(new boolean[N], 0, 0, 0);
		
		System.out.println(ans);
	}
	private static void comb(boolean[] visited, int idx, int left, int right) {
		if(left > N/2 || right > N/2) {
			return;
		}
		if(idx == N) {
			int leftSum = 0;
			int rightSum = 0;
			for (int i = 0; i < visited.length; i++) {
				if(visited[i]) {
					for (int j = i; j < visited.length; j++) {
						if(visited[j]) {
							leftSum += adj[i][j];
							leftSum += adj[j][i];
						}
					}
				}else {
					for (int j = i; j < visited.length; j++) {
						if(!visited[j]) {
							rightSum += adj[i][j];
							rightSum += adj[j][i];
						}
					}
				}
			}
			
			ans = Math.min(ans, Math.abs(leftSum - rightSum));
			
			return;
		}
		
		visited[idx] = true;
		comb(visited, idx+1, left+1, right);
		visited[idx] = false;
		comb(visited, idx+1, left, right+1);
	}
	
	
}
