package jungol.jo1863_종교;

import java.util.Scanner;

public class 종교_solution {
	static int[] parents;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		parents = new int[N+1];
		
		//makeset
		for (int i = 0; i < N+1; i++) {
			parents[i] = i;
		}
		for (int i = 0; i < M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			union(x,y);
		}
		
		int cnt = 0;
		for (int i = 1; i < N+1; i++) {
			if(i == parents[i]) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
	static int find(int x) {
		if(x == parents[x]) {
			return x;
		}else {
			return parents[x] = find(parents[x]);
		}
	}
	
	static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		parents[py] = px;
	}
}
