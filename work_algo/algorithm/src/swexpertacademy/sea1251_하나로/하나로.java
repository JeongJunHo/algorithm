package swexpertacademy.sea1251_하나로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 하나로 {
	static class Edge implements Comparable<Edge> {
		int x, y;
		long cost;
		
		Edge(int x, int y, long cost){
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.cost, o.cost);
		}
	}
	
	static int[][] arr;
	static int[] parents, rank;
	static int[] xArr, yArr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			int n = Integer.parseInt(br.readLine());
			xArr = new int[n];
			yArr = new int[n];
			
			for (int i = 0; i <= n; i++) {
				
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				xArr[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				yArr[i] = Integer.parseInt(st.nextToken());
			}
			
			double e = Double.parseDouble(br.readLine());
			
			PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
			
			for (int i = 0; i < n; i++) {
				for (int j = i+1; j < n; j++) {
					long cost = (long) Math.pow((xArr[i] - xArr[j]), 2) + (long) Math.pow((yArr[i] - yArr[j]), 2); 
					pq.add(new Edge(i, j, cost));
				}
			}
			int cnt = 0;
			while (!pq.isEmpty()) {
				if(cnt == n) {
					break;
				}
				
				Edge edge = pq.poll();
				if(findSet(edge.x) != findSet(edge.y)) {
					
				}
				
				cnt++;
			}
		}
	}
	
	static void makeSet(int x) {
		parents[x] = x;
	}
	
	static int findSet(int x) {
		if(parents[x] == x) {
			return x;
		}
		
		parents[x] = findSet(parents[x]);
		return parents[x];
	}
	
	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		
		if(rank[px] > rank[py]) {
			parents[py] = px;
		}else {
			parents[px] = py;
			if(rank[px] == rank[py]) {
				rank[py]++;
			}
		}
	}
}
