package baekjoon.bj1194_달이차오른다가자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 달이차오른다가자 {
	static class Location{
		int row;
		int col;
		int time;
		int key;
		public Location(int row, int col, int time, int key) {
			super();
			this.row = row;
			this.col = col;
			this.time = time;
			this.key = key;
		}
	}
	static int N, M, ans = -1;
	static char[][] map;
	static boolean[][][] visited;
	static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
	static Queue<Location> queue = new LinkedList<Location>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new boolean[N][M][64];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == '0') {
					queue.add(new Location(i, j, 0, 0));
					visited[i][j][0] = true;
				}
			}
		}
		
		while (!queue.isEmpty()) {
			Location loc = queue.poll();
			
			if(map[loc.row][loc.col] == '1') {
				ans = loc.time;
				break;
			}
			
			for (int i = 0; i < pos.length; i++) {
				int nr = loc.row + pos[i][0];
				int nc = loc.col + pos[i][1];
				
				if(posCheck(nr, nc) && !visited[nr][nc][loc.key]) {
					if(map[nr][nc] == '.' || map[nr][nc] == '1' || map[nr][nc] == '0') {
						visited[nr][nc][loc.key] = true;
						queue.add(new Location(nr, nc, loc.time+1, loc.key));
					}else if (map[nr][nc] == 'A' || map[nr][nc] == 'B' || map[nr][nc] == 'C' || map[nr][nc] == 'D' || map[nr][nc] == 'E' || map[nr][nc] == 'F') {
						int shift = 1 << (map[nr][nc] - 65);
						if((shift & loc.key) > 0) {
							visited[nr][nc][loc.key] = true;
							queue.add(new Location(nr, nc, loc.time+1, loc.key));
						}
					}else if (map[nr][nc] == 'a' || map[nr][nc] == 'b' || map[nr][nc] == 'c' || map[nr][nc] == 'd' || map[nr][nc] == 'e' || map[nr][nc] == 'f') {
						int shift = 1 << (map[nr][nc] - 97);
						int key = loc.key | shift;
						visited[nr][nc][key] = true;
						queue.add(new Location(nr, nc, loc.time+1, key));
					}
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}
}
