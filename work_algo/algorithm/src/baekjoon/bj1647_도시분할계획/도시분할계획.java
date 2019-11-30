package baekjoon.bj1647_도시분할계획;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 도시분할계획 {
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
			// TODO Auto-generated method stub
			return this.cost - o.cost;
		}
	}
	
	static int N, M, ans;
	static List<Edge>[] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adj = new List[N+1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<Edge>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			adj[start].add(new Edge(dest, cost));
			adj[dest].add(new Edge(start, cost));
		}
		
		PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
		boolean[] visited = new boolean[N+1];
		visited[1] = true;
		queue.addAll(adj[1]);
		int[] cost = new int[N-1];
		
		//노드 -1 개 만큼 반복
		int cnt = 0;
		while(cnt != N-1) {
			Edge edge = queue.poll();
			if(visited[edge.dest]) {
				continue;
			}
			cost[cnt] = edge.cost;
			visited[edge.dest] = true;
			queue.addAll(adj[edge.dest]);
			cnt++;
		}
		
		Arrays.sort(cost);
		
		for (int i = 0; i < cost.length-1; i++) {
			ans += cost[i];
		}
		
		System.out.println(ans);
	}
}
