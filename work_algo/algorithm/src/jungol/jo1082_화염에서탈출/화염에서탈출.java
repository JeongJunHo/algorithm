package jungol.jo1082_화염에서탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 화염에서탈출 {
	static class Location {
		int row;
		int col;
		int minute;
		
		public Location(int row, int col, int minute) {
			this.row = row;
			this.col = col;
			this.minute = minute;
		}
	}
	
	static int R, C;
	static int ans = Integer.MAX_VALUE;
	static char[][] map;
	static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited = new boolean[R][C];
		
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		bfs();
		
		System.out.println(ans == Integer.MAX_VALUE ? "impossible" : ans);
	}
	private static void bfs() {
		Location hero = null;
		Location end = null;
		Queue<Location> queue = new LinkedList<Location>();
		Queue<Location> heroQ = new LinkedList<Location>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(map[i][j] == '*') {
					queue.add(new Location(i, j, 0));
					visited[i][j] = true;
				}else if(map[i][j] == 'S') {
					hero = new Location(i, j, 0);
				}else if(map[i][j] == 'D') {
					end = new Location(i, j, 0);
				}
			}
		}
		heroQ.add(hero);
		visited[hero.row][hero.col] = true;
		while(true) {
			int qSize = queue.size();
			
			if(qSize > 0) {
				for (int i = 0; i < qSize; i++) {
					int row = queue.peek().row;
					int col = queue.poll().col;
					
					for (int j = 0; j < pos.length; j++) {
						int nr = row + pos[j][0];
						int nc = col + pos[j][1];
						
						if(posCheck(nr,nc) && !visited[nr][nc] && map[nr][nc] == '.') {
							//분은 의미없음
							visited[nr][nc] = true;
							queue.add(new Location(nr, nc, 0));
						}
					}
				}
			}

			boolean pass = false;
			boolean find = false;
			int hqSize = heroQ.size();
			for (int i = 0; !find && i < hqSize; i++) {
				Location tmpHero = heroQ.poll();
				
				for (int j = 0; j < pos.length; j++) {
					int nr = tmpHero.row + pos[j][0];
					int nc = tmpHero.col + pos[j][1];
					
					if(posCheck(nr, nc) && !visited[nr][nc] && (map[nr][nc] == '.' || map[nr][nc] == 'D')) {
						if(end.row == nr && end.col == nc) {
							find = true;
							pass = false;
							ans = tmpHero.minute + 1;
							break;
						}else {
							pass = true;
							visited[nr][nc] = true;
							heroQ.add(new Location(nr, nc, tmpHero.minute+1));
						}
					}
				}
			}
			
			if(!pass) {
				break;
			}
		}
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < R && col >= 0 && col < C;
	}
}
