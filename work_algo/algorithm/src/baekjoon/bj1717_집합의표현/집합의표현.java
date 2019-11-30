package baekjoon.bj1717_집합의표현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 집합의표현 {
	static int[] parent = new int[1000001];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= n; i++) {
			makeSet(i);
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(type == 0) {
				unionSet(a, b);
			}else {
				String str = findSet(a) == findSet(b) ? "YES" : "NO";
				sb.append(str).append("\n");
			}
		}
		System.out.println(sb);
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
