package swexpertacademy.sea3124_최소스패닝트리;

import java.util.PriorityQueue;
import java.util.Scanner;

public class 최소스패닝트리_prim {
	static class Node implements Comparable<Node> {
		int dest;
		int cost;
		Node(int d, int c){
			dest = d;
			cost = c;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cost, o.cost);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		int[][] arr;
		boolean[] visited;
		for (int tc = 1; tc <= t; tc++) {
			//정점의 개수
			int v = sc.nextInt();
			//간선의 개수
			int e = sc.nextInt();
			arr = new int[v+1][v+1];
			
			for (int i = 0; i < e; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				int c = sc.nextInt();
				arr[a][b] = c;
				arr[b][a] = c;
			}
			
			visited = new boolean[v+1];
			queue.clear();
			
			visited[1] = true;
			for (int i = 1; i <= v; i++) {
				if(arr[1][i] != 0) {
					queue.add(new Node(i, arr[1][i]));
				}
			}
			
			int cnt = 0;
			int sum = 0;
			while (!queue.isEmpty()) {
				if(cnt == v) {
					break;
				}
				Node node = queue.poll();
				if (visited[node.dest]) {
					continue;
				}
				
				sum += node.cost;
				for (int i = 1; i <= v; i++) {
					if(arr[node.dest][i] != 0) {
						queue.add(new Node(i, arr[node.dest][i]));
					}
				}
				visited[node.dest] = true;
				
				cnt++;
			}
			
			System.out.println("#" + tc + " " + sum);
		}
	}
}
