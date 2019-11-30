package baekjoon.bj1504_특정한최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 특정한최단경로 {
	static final int INF = 987654321;
	static int N, E, a, b;
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
	
	static List<Edge>[] adj;
	static long[][] distance;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		adj = new List[N+1];
		distance = new long[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<Edge>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			adj[start].add(new Edge(dest, cost));
			adj[dest].add(new Edge(start, cost));
		}
		
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		dijkstra(1);
		dijkstra(a);
		dijkstra(b);
		
		long case1 = distance[1][a] + distance[a][b] + distance[b][N];
		long case2 = distance[1][b] + distance[b][a] + distance[a][N];

		long ans = Math.min(case1, case2);
		System.out.println(ans >= INF ? -1 : ans);
	}
	
	static void dijkstra(int idx) {
		PriorityQueue<Edge> q = new PriorityQueue<Edge>();
		q.offer(new Edge(idx, 0));
		for (int i = 1; i <= N; i++) {
			distance[idx][i] = INF;
		}
		distance[idx][idx] = 0;
		
		while (!q.isEmpty()) {
			Edge current = q.poll();
			
			for (int i = 0; i < adj[current.dest].size(); i++) {
				Edge target = adj[current.dest].get(i);
				if(distance[idx][target.dest] > distance[idx][current.dest] + target.cost) {
					distance[idx][target.dest] = distance[idx][current.dest] + target.cost;
					q.offer(target);
				}
			}
		}
	}
}
