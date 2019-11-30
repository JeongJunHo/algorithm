package swexpertacademy.sea1251_하나로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 하나로_20190916_크루스칼 {
	static int N;
	static long[][] arr;
	static double E;
	static int[] parent;
	
	static class Edge implements Comparable<Edge>{
		long x;
		long y;
		double cost;
		
		Edge(long x, long y, double cost){
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Double.compare(cost, o.cost);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new long [N][2];
			parent = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i][0] = Long.parseLong(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i][1] = Long.parseLong(st.nextToken());
			}
			
			E = Double.parseDouble(br.readLine());
			
			PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
			
			for (int i = 0; i < N; i++) {
				makeSet(i);
			}
			
			
			for (int i = 0; i < N; i++) {
				for (int j = i+1; j < N; j++) {
					double dist = (Math.pow(arr[i][0] - arr[j][0], 2) + Math.pow(arr[i][1] - arr[j][1], 2)) * E;
					queue.add(new Edge(i,j,dist));
				}
			}
			
			int cnt = 0;
			double result = 0;
			while (cnt != N-1) {
				Edge edge = queue.poll();
				if(findSet((int) edge.x) != findSet((int) edge.y)) {
					unionSet((int)edge.x, (int)edge.y);
					result += edge.cost;
					cnt++;
				}
			}
			
			System.out.println("#" + tc + " " + Math.round(result));
		}// tc for end
	}
	
	static void makeSet(int idx) {
		parent[idx] = idx;
	}
	
	static int findSet(int idx) {
		if(parent[idx] == idx)
			return idx;
		parent[idx] = findSet(parent[idx]);
		return parent[idx];
	}
	
	static void unionSet(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		
		parent[py] = px;
	}
}