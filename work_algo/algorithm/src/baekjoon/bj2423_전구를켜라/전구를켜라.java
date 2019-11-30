package baekjoon.bj2423_전구를켜라;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 전구를켜라 {
	static class Location implements Comparable<Location>{
		int row;
		int col;
		int change;
		boolean current;
		public Location(int row, int col, int change, boolean current) {
			super();
			this.row = row;
			this.col = col;
			this.change = change;
			this.current = current;
		}
		
		@Override
		public int compareTo(Location o) {
			return this.change - o.change;
		}

		@Override
		public String toString() {
			return "Location [row=" + row + ", col=" + col + ", change=" + change + ", current=" + current + "]";
		}
	}
	static int N, M, ans = Integer.MAX_VALUE;
	static boolean[][] map;
	static boolean[][] visited;
	static int[][] pos1 = {{1,0},{0,1},{-1,0},{0,-1}};
	static int[][] pos2 = {{1,1},{-1,-1}};
	static int[][] pos3 = {{-1,1},{1,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new boolean[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				if(str.charAt(j) == '\\') {
					map[i][j] = true;
				}
			}
		}
		
		bfs();
		
		if(ans == Integer.MAX_VALUE) {
			System.out.println("NO SOLUTION");
		}else {
			System.out.println(ans);
		}
	}
	
	private static void bfs() {
		PriorityQueue<Location> q = new PriorityQueue<Location>();
		if(map[0][0]) {
			q.add(new Location(0, 0, 0, true));
		}else {
			q.add(new Location(0, 0, 1, true));
		}
		
		while (!q.isEmpty()) {
			Location loc = q.poll();
			System.out.println(loc);
			
			if(loc.row == N-1 && loc.col == M-1) {
				if(loc.current) {
					ans = loc.change;
					break;
				}
			}
			
			if(visited[loc.row][loc.col]) continue;
			visited[loc.row][loc.col] = true;
			
			for (int i = 0; i < pos1.length; i++) {
				int nr = loc.row + pos1[i][0];
				int nc = loc.col + pos1[i][1];
				
				if(posCheck(nr, nc)) {
					if(loc.current == map[nr][nc]) {
						q.add(new Location(nr, nc, loc.change+1, !map[nr][nc]));
					}else {
						q.add(new Location(nr, nc, loc.change, map[nr][nc]));
					}
				}
			}
			
			//true
			for (int i = 0; i < pos2.length; i++) {
				int nr = loc.row + pos2[i][0];
				int nc = loc.col + pos2[i][1];
				
				if(posCheck(nr, nc)) {
					if(loc.current) {
						if(loc.current == map[nr][nc]) {
							q.add(new Location(nr, nc, loc.change, map[nr][nc]));
						}else {
							q.add(new Location(nr, nc, loc.change+1, !map[nr][nc]));
						}
					}
				}
			}
			
			//false
			for (int i = 0; i < pos3.length; i++) {
				int nr = loc.row + pos3[i][0];
				int nc = loc.col + pos3[i][1];
				
				if(posCheck(nr, nc)) {
					if(!loc.current) {
						if(loc.current == map[nr][nc]) {
							q.add(new Location(nr, nc, loc.change, map[nr][nc]));
						}else {
							q.add(new Location(nr, nc, loc.change+1, !map[nr][nc]));
						}
					}
				}
			}
		}
	}

	static boolean posCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}
}
