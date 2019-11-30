package baekjoon.bj2589_보물섬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 보물섬 {
	static class Location{
		int row;
		int col;
		int cost;
		public Location(int row, int col, int cost) {
			super();
			this.row = row;
			this.col = col;
			this.cost = cost;
		}
	}
	
	static int L, W, ans = Integer.MIN_VALUE;
	static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
	static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new char[L][W];
		
		for (int i = 0; i < L; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j] == 'L') {
					boolean[][] visited = new boolean[L][W];
					Queue<Location> q = new LinkedList<Location>();
					visited[i][j] = true;
					q.add(new Location(i, j, 0));
					
					while (!q.isEmpty()) {
						Location loc = q.poll();
						
						for (int k = 0; k < pos.length; k++) {
							int nr = loc.row + pos[k][0];
							int nc = loc.col + pos[k][1];
							
							if(posCheck(nr, nc) && !visited[nr][nc] && map[nr][nc] == 'L') {
								ans = Math.max(ans, loc.cost + 1);
								visited[nr][nc] = true;
								q.add(new Location(nr, nc, loc.cost + 1));
							}
						}
					}
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < L && col >= 0 && col < W;
	}
}
