package day13;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 크루스칼
 * 간선중심의 최소신장트리생성 알고리즘(탐욕)
 * 아이디어 : 각 정점을 상호배타집합으로 생각하고 간선으로 연결되는 경우 두 집합을 유니온한다.
 * 모든 집합이 하나로 유니온 되면 최소신장트리 완성.
 * 
 * 1.가중치가 가장 작은 간선을 찾는다.
 * 2.해당 간선으로 연결되는 두 정점이 다른 집합에 속해 있다면 간선을 선택.
 * 아니라면 다시 작은 간선을 찾아 반복.
 * 3. 1~2의 작업을 정점이 N개 일때 N-1번 수행하면 최소신장트리 완성.
 * 
 * 첫번째 줄에 정점의 갯수 N과 간선의 갯수 M이 주어지고. 그 다음부터 M개의 줄에 걸쳐서 정점 정점 간선비용의 정보가 주어진다. (무향그래프)
 * 최소 신장 트리에서의 경로총합을 출력하시오.
 */
public class 크루스칼 {
	static class Edge implements Comparable<Edge>{
		int x;
		int y;
		int cost;
		
		Edge(int x, int y, int cost){
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
		
		@Override
			public String toString() {
				return this.cost + "";
			}
	}
	
	static int N, M;
	static int[] parent;
	static Edge[] arr;
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
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		parent = new int[N];
		for (int i = 0; i < parent.length; i++) {
			makeSet(i);
		}
		
		arr = new Edge[M];
		for (int i = 0; i < M; i++) {
			Edge edge = new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt());
			arr[i] = edge;
		}
		
		Arrays.sort(arr);
		
		int cnt = 0;
		int total = 0;
		for (int i = 0; i < arr.length; i++) {
			int px = findSet(arr[i].x);
			int py = findSet(arr[i].y);
			
			if(px != py) {
				total += arr[i].cost;
				unionSet(arr[i].x, arr[i].y);
				cnt++;
			}
			
			if(cnt == N-1 ) {
				break;
			}
		}
		
		System.out.println(total);
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
