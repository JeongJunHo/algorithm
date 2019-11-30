package baekjoon.bj16236_아기상어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아기상어 {
	static class Location{
		int row;
		int col;
		int size;
		int grow;
		int sec;
		public Location(int row, int col, int size, int grow, int sec) {
			super();
			this.row = row;
			this.col = col;
			this.size = size;
			this.grow = grow;
			this.sec = sec;
		}
	}
	static int N;
	static int[][] map;
	static Location shark;
	static int[][] pos = {{-1,0},{0,-1},{0,1},{1,0}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					shark = new Location(i, j, 2, 2, 0);
					map[i][j] = 0;
				}
			}
		}
		
		boolean check = true;
		while(check) {
			check = feedCheck();
		}
		
		System.out.println(shark.sec);
	}
	
	private static boolean feedCheck() {
		boolean check = false;
		boolean[][] visited = new boolean[N][N];
		Queue<Location> q = new LinkedList<Location>();
		Location feed = null;
		q.add(new Location(shark.row, shark.col, shark.size, shark.grow, shark.sec));
		visited[shark.row][shark.col] = true;
		while (!q.isEmpty()) {
			Location loc = q.poll();
			if(feed != null && feed.sec < loc.sec) continue;
			
			if(map[loc.row][loc.col] > 0 && shark.size > map[loc.row][loc.col]) {
				if(feed == null) {
					feed = loc;
				}else {
					if(feed.row > loc.row) {
						feed = loc;
					}else if (feed.row == loc.row) {
						if(feed.col > loc.col) {
							feed = loc;
						}
					}
				}
				check = true;
			}
			
			for (int i = 0; i < pos.length; i++) {
				int nr = loc.row + pos[i][0];
				int nc = loc.col + pos[i][1];
				
				if(posCheck(nr, nc) && !visited[nr][nc] && shark.size >= map[nr][nc]) {
					visited[nr][nc] = true;
					q.add(new Location(nr, nc, loc.size, loc.grow, loc.sec+1));
				}
			}
		}
		
		if(check) {
			shark.grow -= 1;
			if(shark.grow == 0) {
				shark.grow = shark.size += 1;
			}
			shark.row = feed.row;
			shark.col = feed.col;
			shark.sec = feed.sec;
			
			map[feed.row][feed.col] = 0;
		}
		
		return check;
	}

	public static boolean posCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}
}
