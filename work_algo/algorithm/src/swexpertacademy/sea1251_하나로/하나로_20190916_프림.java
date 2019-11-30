package swexpertacademy.sea1251_하나로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 하나로_20190916_프림 {
	static int N;
	static long[][] arr;
	static double E;
	static List[] edgeListArr;
	
	static class Edge implements Comparable<Edge>{
		int x;
		double cost;
		
		Edge(int x, double cost){
			this.x = x;
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
			edgeListArr = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				edgeListArr[i] = new ArrayList<Edge>();
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i][0] = Long.parseLong(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i][1] = Long.parseLong(st.nextToken());
			}
			
			E = Double.parseDouble(br.readLine());
			
			for (int i = 0; i < N; i++) {
				for (int j = i+1; j < N; j++) {
					double dist = (Math.pow(arr[i][0] - arr[j][0], 2) + Math.pow(arr[i][1] - arr[j][1], 2)) * E;
					edgeListArr[i].add(new Edge(j, dist));
					edgeListArr[j].add(new Edge(i, dist));
				}
			}
			
			PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
			queue.addAll(edgeListArr[0]);
			boolean[] visited = new boolean[N];
			visited[0] = true;
			
			double result = 0;
			int cnt = 1;
			while (cnt != N) {
				Edge edge = queue.poll();
				if(visited[edge.x])
					continue;
				result += edge.cost;
				queue.addAll(edgeListArr[edge.x]);
				visited[edge.x] = true;
				cnt++;
			}
			
			System.out.println("#" + tc + " " + Math.round(result));
		}
	}
}
