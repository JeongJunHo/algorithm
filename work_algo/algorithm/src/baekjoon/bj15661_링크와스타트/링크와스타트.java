package baekjoon.bj15661_링크와스타트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 링크와스타트 {
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
		
		powerSet(new boolean[N], 0, 0, 0);
		
		System.out.println(ans);
	}
	private static void powerSet(boolean[] visited, int idx, int t, int f) {
		if(visited.length == idx) {
			if(t == 0 || f == 0) return;
			
			List<Integer> tList = new ArrayList<Integer>();
			List<Integer> fList = new ArrayList<Integer>();
			int tScore = 0;
			int fScore = 0;
			
			for (int i = 0; i < visited.length; i++) {
				if(visited[i]) {
					tList.add(i);
				}else {
					fList.add(i);
				}
			}

			for (int i = 0; i < tList.size(); i++) {
				for (int j = i+1; j < tList.size(); j++) {
					tScore += adj[tList.get(i)][tList.get(j)];
					tScore += adj[tList.get(j)][tList.get(i)];
				}
			}
			
			for (int i = 0; i < fList.size(); i++) {
				for (int j = i+1; j < fList.size(); j++) {
					fScore += adj[fList.get(i)][fList.get(j)];
					fScore += adj[fList.get(j)][fList.get(i)];
				}
			}
			
			ans = Math.min(ans, Math.abs(tScore - fScore));
			
			return;
		}
		
		visited[idx] = true;
		powerSet(visited, idx+1, t+1, f);
		visited[idx] = false;
		powerSet(visited, idx+1, t, f+1);
	}
}
