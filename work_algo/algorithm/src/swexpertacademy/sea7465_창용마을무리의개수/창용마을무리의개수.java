package swexpertacademy.sea7465_창용마을무리의개수;

import java.util.Scanner;

public class 창용마을무리의개수 {
	static int N, M;
	static int[] parent;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for(int tc=1; tc <= t; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			
			parent = new int[N + 1];
			
			for (int i = 1; i < parent.length; i++) {
				makeSet(i);
			}
			
			for (int i = 0; i < M; i++) {
				unionSet(sc.nextInt(), sc.nextInt());
			}
			
			int cnt = 0;
			for (int i = 1; i < parent.length; i++) {
				if(i == parent[i]) {
					cnt++;
				}
			}
			
			System.out.println("#" + tc + " " + cnt);
		}
	}
	
	static void makeSet(int idx) {
		parent[idx] = idx;
	}
	
	static int findSet(int idx) {
		if(parent[idx] == idx) {
			return idx;
		}else {
			parent[idx] = findSet(parent[idx]);
			return parent[idx];
		}
	}
	
	static void unionSet(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		
		parent[py] = px;
	}
}
