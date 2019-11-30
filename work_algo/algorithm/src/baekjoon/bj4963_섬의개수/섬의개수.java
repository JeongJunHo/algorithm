package baekjoon.bj4963_섬의개수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 섬의개수 {
	static class Location{
		int row;
		int col;
		public Location(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	
	static int w, h, ans;
	static int[][] map;
	static boolean[][] visited;
	//8방체크
	static int[][] pos = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		while (true) {
			ans = 0;
			st = new StringTokenizer(br.readLine());
			
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if(w == 0 && h == 0) break;
			
			map = new int[h][w];
			visited = new boolean[h][w];
			
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if(!visited[i][j] && map[i][j] == 1) {
						Queue<Location> q = new LinkedList<Location>();
						visited[i][j] = true;
						q.add(new Location(i, j));
						while (!q.isEmpty()) {
							int row = q.peek().row;
							int col = q.poll().col;
							
							for (int k = 0; k < pos.length; k++) {
								int nr = row + pos[k][0];
								int nc = col + pos[k][1];
								
								if(posCheck(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
									visited[nr][nc] = true;
									q.add(new Location(nr, nc));
								}
							}
						}
						ans++;
					}
				}
			}
			
			System.out.println(ans);
		}// while end
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < h && col >= 0 && col < w;
	}
}
