package day13;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * 정점 중심의 접근으로 최소신장트리를 구하는 알고리즘(탐욕)
 * 
 * 0. 아무 정점이나 하나 확보한다.
 * 1. 현재 확보된 정점에서 이동 가능한 정점중에 가장 가까운 정점을 찾아서 확보.
 * 확보된 정점을 모두 순회하며, 아직 확보되지 않은 정점으로의 거리가 가장 짧은 간선을 찾아 그 간선에 연결된 정점을 확보
 * 2. 모두 확보되면 끝
 */
public class 프림 {
	static class Edge implements Comparable<Edge>{
		int vector;
		int cost;
		
		Edge(int vector, int cost){
			this.vector = vector;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return cost - o.cost;
		}
	}
	
	static ArrayList<Edge>[] arr;
	static int V, E;
	static boolean[] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner("7 11\n"
				+ "0 1 31\r\n"
				+ "0 2 31\r\n"
				+ "0 6 31\r\n"
				+ "0 5 50\r\n"
				+ "1 2 21\r\n"
				+ "2 4 46\r\n"
				+ "2 6 25\r\n"
				+ "3 4 34\r\n"
				+ "4 6 51\r\n"
				+ "5 3 18\r\n"
				+ "5 4 40\r\n");
		
		V = sc.nextInt();
		E = sc.nextInt();
		
		arr = new ArrayList[V];
		visited = new boolean[V];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = new ArrayList<Edge>();
		}
		
		for (int i = 0; i < E; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int cost = sc.nextInt();
			
			arr[x].add(new Edge(y, cost));
			arr[y].add(new Edge(x, cost));
		}
		
		PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
		visited[0] = true;
		queue.addAll(arr[0]);
		int cnt = 1;
		int total = 0;
		while (cnt != V) {
			Edge edge = queue.poll();
			if(visited[edge.vector])
				continue;
			total += edge.cost;
			queue.addAll(arr[edge.vector]);
			visited[edge.vector] = true;
			cnt++;
		}
		
		System.out.println(total);
	}
}

