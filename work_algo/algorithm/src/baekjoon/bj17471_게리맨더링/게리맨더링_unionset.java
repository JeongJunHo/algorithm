package baekjoon.bj17471_게리맨더링;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 게리맨더링_unionset {
	static int N, ans = Integer.MAX_VALUE;
	static int[] people;
	static int[] parent;
	static boolean[][] adj;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		adj = new boolean[N+1][N+1];
		people = new int[N+1];
		parent = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			for (int j = 0; j < size; j++) {
				int num = Integer.parseInt(st.nextToken());
				adj[i][num] = true;
				adj[num][i] = true;
			}
		}
		
		powerSet(new boolean[N+1], 1);
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	
	private static void powerSet(boolean[] visited, int idx) {
		if(idx > N) {
			int tCnt = 0;
			int fCnt = 0;
			for (int i = 1; i <= N; i++) {
				if(visited[i]) tCnt+=people[i];
				if(!visited[i]) fCnt+=people[i];
			}
			if(tCnt == 0 || fCnt == 0) return;
			
			for (int i = 1; i <= N; i++) {
				makeSet(i);
			}
			for (int i = 1; i <= N; i++) {
				//A선거구
				if(visited[i]) {
					for (int j = 1; j <= N; j++) {
						if(visited[j] && adj[i][j]) {
							unionSet(i, j);
						}
					}
				//B선거구
				}else {
					for (int j = 1; j <= N; j++) {
						if(!visited[j] && adj[i][j]) {
							unionSet(i, j);
						}
					}
				}
			}
			
			boolean check = true;
			
			for (int i = 1; i <= N; i++) {
				findSet(i);
			}
			
			int tParent = 0;
			int fParent = 0;
			for (int i = 1; i <= N; i++) {
				if(visited[i]) {
					if(tParent == 0) {
						tParent = findSet(i);
					}else {
						if(tParent != findSet(i)) {
							check = false;
							break;
						}
					}
				}else {
					if(fParent == 0) {
						fParent = findSet(i);
					}else {
						if(fParent != findSet(i)) {
							check = false;
							break;
						}
					}
				}
			}
			
			if(check) {
				ans = Math.min(ans, Math.abs(tCnt-fCnt));
			}
			
			return;
		}
		
			visited[idx] = true;
			powerSet(visited, idx+1);
			visited[idx] = false;
			powerSet(visited, idx+1);
	}

	static void makeSet(int a) {
		parent[a] = a;
	}
	
	static int findSet(int a) {
		if(parent[a] == a)
			return a;
		parent[a] = findSet(parent[a]);
		return parent[a];
	}
	
	static void unionSet(int a, int b) {
		int pa = findSet(a);
		int pb = findSet(b);
		
		parent[pb] = pa;
	}
}
