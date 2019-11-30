package baekjoon.bj2665_미로만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 미로만들기 {
	static class Location implements Comparable<Location>{
		int row;
		int col;
		int change;
		public Location(int row, int col, int change) {
			super();
			this.row = row;
			this.col = col;
			this.change = change;
		}
		
		@Override
		public int compareTo(Location o) {
			// TODO Auto-generated method stub
			return this.change-o.change;
		}
	}
	static int n, ans;
	static char[][] map;
	static int[][] visited;
	static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		
		map = new char[n][n];
		visited = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
		}
		
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		PriorityQueue<Location> q = new PriorityQueue<Location>();
		q.add(new Location(0, 0, 0));
		visited[0][0] = 0;

		while (!q.isEmpty()) {
			Location loc = q.poll();
			
			if(loc.row == n-1 && loc.col == n-1) {
				ans = loc.change;
				break;
			}
			
			for (int i = 0; i < pos.length; i++) {
				int nr = loc.row + pos[i][0];
				int nc = loc.col + pos[i][1];
				
				if(posCheck(nr, nc) && visited[nr][nc] > loc.change) {
					if(map[nr][nc] == '1') {
						visited[nr][nc] = loc.change;
						q.add(new Location(nr, nc, loc.change));
					}else {
						visited[nr][nc] = loc.change+1;
						q.add(new Location(nr, nc, loc.change+1));
					}
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < n && col >= 0 && col < n;
	}
}
