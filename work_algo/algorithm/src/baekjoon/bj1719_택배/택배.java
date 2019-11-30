package baekjoon.bj1719_택배;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 택배 {
	static int n, m, INF = 987654321;
	static List<Edge>[] adj;
	static int[] distance;
	static int[] trace;
	static int[][] ans;
	static class Edge implements Comparable<Edge>{
		int dest;
		int cost;
		public Edge(int dest, int cost) {
			super();
			this.dest = dest;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		adj = new List[n+1];
		ans = new int[n+1][n+1];
		
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<Edge>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			adj[start].add(new Edge(dest, cost));
			adj[dest].add(new Edge(start, cost));
		}
		
		for (int i = 1; i <= n; i++) {
			dijkstra(i);
		}
		
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if(ans[i][j] == 0) {
					sb.append("- ");
				}else {
					sb.append(ans[i][j] + " ");
				}
				
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void dijkstra(int idx) {
		PriorityQueue<Edge> q = new PriorityQueue<Edge>();
		q.offer(new Edge(idx, 0));
		distance = new int[n+1];
		trace = new int[n+1];
		Arrays.fill(distance, INF);
		distance[idx] = 0;
		trace[idx] = idx;
		
		while (!q.isEmpty()) {
			Edge current = q.poll();
			
			for (int i = 0; i < adj[current.dest].size(); i++) {
				Edge target = adj[current.dest].get(i);
				if(distance[target.dest] > target.cost + distance[current.dest]) {
					distance[target.dest] = target.cost + distance[current.dest];
					trace[target.dest] = current.dest;
					
					q.offer(target);
				}
			}
		}
		
		for (int i = 1; i <= n; i++) {
			if(distance[i] != 0) {
				ans[idx][i] = findFirst(i, idx);
			}
		}
	}
	
	static int findFirst(int dest, int idx) {
		if(trace[dest] != idx) {
			return findFirst(trace[dest], idx);
		}else {
			return dest;
		}
	}
}
