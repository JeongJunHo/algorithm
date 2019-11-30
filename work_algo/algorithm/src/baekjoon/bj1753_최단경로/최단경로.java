package baekjoon.bj1753_최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최단경로 {
	static int V, E, K;
	static final int INF = 987654321;
	static List<Edge>[] adjList;
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
			return this.cost-o.cost;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		K = Integer.parseInt(br.readLine());
		adjList = new List[V+1];
		
		for (int i = 1; i <= V; i++) {
			adjList[i] = new ArrayList<Edge>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			adjList[idx].add(new Edge(dest, cost));
		}

		int[] distCost = new int[V+1];
		Arrays.fill(distCost, INF);
		PriorityQueue<Edge> q = new PriorityQueue<Edge>();
		q.add(new Edge(K, 0));
		System.out.println(Arrays.toString(distCost));
		while (!q.isEmpty()) {
			Edge edge = q.poll();
			System.out.println(edge.dest + " " + edge.cost);
			
			if(distCost[edge.dest] > edge.cost) {
				distCost[edge.dest] = edge.cost;
				for (Edge d : adjList[edge.dest]) {
					q.add(new Edge(d.dest, d.cost + distCost[edge.dest]));
				}
			}
			System.out.println(Arrays.toString(distCost));
		}
		
		for (int i = 1; i <= V; i++) {
			if(distCost[i] == 987654321) {
				System.out.println("INF");
			}else {
				System.out.println(distCost[i]);
			}
		}
	}
}
