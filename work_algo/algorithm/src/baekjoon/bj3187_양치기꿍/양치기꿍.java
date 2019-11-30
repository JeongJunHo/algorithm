package baekjoon.bj3187_양치기꿍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 양치기꿍 {
	static int R, C, sheep, wolf;
	static char[][] map;
	static boolean[][] visited;
	static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
	static class Location{
		int row;
		int col;
		public Location(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		visited = new boolean[R][C];
		Queue<Location> q = new LinkedList<Location>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(!visited[i][j]) {
					int cur_sheep = 0;
					int cur_wolf = 0;
					visited[i][j] = true;
					q.add(new Location(i, j));
					while (!q.isEmpty()) {
						int row = q.peek().row;
						int col = q.poll().col;
						
						for (int k = 0; k < pos.length; k++) {
							int nr = row + pos[k][0];
							int nc = col + pos[k][1];
							
							if(posCheck(nr, nc) && !visited[nr][nc] && map[nr][nc] != '#') {
								visited[nr][nc] = true;
								q.add(new Location(nr, nc));
								if(map[nr][nc] == 'v') {
									cur_wolf++;
								}else if (map[nr][nc] == 'k') {
									cur_sheep++;
								}
							}
						}
					}
					
					if(cur_sheep > cur_wolf) {
						sheep += cur_sheep;
					}else {
						wolf += cur_wolf;
					}
				}
			}
		}
		
		System.out.println(sheep + " " + wolf);
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < R && col >= 0 && col < C;
	}
}
