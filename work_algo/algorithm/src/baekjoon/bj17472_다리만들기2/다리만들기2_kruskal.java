package baekjoon.bj17472_다리만들기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 다리만들기2_kruskal {
	static int N, M, ans;
	static int[][] map;
	static boolean[][] visited;
	static int island = 0;
	static int[][] pos = {{1,0},{-1,0},{0,-1},{0,1}};
	static PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
	static int[] parent;
	static class Location{
		int row;
		int col;
		public Location(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	static class Edge implements Comparable<Edge>{
		int start;
		int dest;
		int cost;
		public Edge(int start, int dest, int cost) {
			super();
			this.start = start;
			this.dest = dest;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
		@Override
		public String toString() {
			return "Edge [start=" + start + ", dest=" + dest + ", cost=" + cost + "]";
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					island++;
					Queue<Location> q = new LinkedList<Location>();
					q.add(new Location(i, j));
					visited[i][j] = true;
					map[i][j] = island;
					
					while (!q.isEmpty()) {
						Location loc = q.poll();
						
						for (int k = 0; k < pos.length; k++) {
							int nr = loc.row + pos[k][0];
							int nc = loc.col + pos[k][1];
							
							if(posCheck(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
								visited[nr][nc] = true;
								map[nr][nc] = island;
								q.add(new Location(nr, nc));
							}
						}
					}
				}
			}
		}
		
		parent = new int[island+1];
		for (int i = 1; i < parent.length; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < visited.length; i++) {
			Arrays.fill(visited[i], false);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visited[i][j] && map[i][j] != 0) {
					Queue<Location> q = new LinkedList<Location>();
					q.add(new Location(i, j));
					visited[i][j] = true;
					
					while (!q.isEmpty()) {
						Location loc = q.poll();
						
						for (int k = 0; k < pos.length; k++) {
							int nr = loc.row + pos[k][0];
							int nc = loc.col + pos[k][1];
							
							if(posCheck(nr, nc) && !visited[nr][nc]) {
								if(map[nr][nc] == 0) {
									int nnr = nr;
									int nnc = nc;
									int cost = 1;
									while (true) {
										nnr += pos[k][0];
										nnc += pos[k][1];
										if(!posCheck(nnr, nnc)) break;
										if(map[nnr][nnc] != 0) {
											if(cost >= 2) {
												pq.add(new Edge(map[i][j], map[nnr][nnc], cost));	
											}
											break;
										}
										cost++;
									}
								}else {
									visited[nr][nc] = true;
									q.add(new Location(nr, nc));
								}
							}
						}
					}
				}
			}
		}
		
		int choice = 0;
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			
			if(findSet(edge.start) != findSet(edge.dest)) {
				choice++;
				ans += edge.cost;
				unionSet(edge.start, edge.dest);
			}
			if(choice == island-1) break;
		}
		
		if(choice != island-1) ans = -1;
		
		System.out.println(ans);
	}
	
	static void print(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}
	
	static int findSet(int idx) {
		if(parent[idx] == idx) return idx;
		parent[idx] = findSet(parent[idx]);
		return parent[idx];
	}
	
	static void unionSet(int a, int b) {
		int aParent = findSet(a);
		int bParent = findSet(b);
		
		parent[bParent] = aParent;
	}
}
