package baekjoon.bj2573_빙산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 빙산 {
	static class Location{
		int row;
		int col;
		public Location(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
	static int ans;
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
		
		int group = 1;
		while (group == 1) {
			int[][] copyMap = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(map[i][j] > 0) {
						int water = 0;
						for (int d = 0; d < pos.length; d++) {
							int nr = i + pos[d][0];
							int nc = j + pos[d][1];
							
							if(map[nr][nc] == 0) water++;
						}
						
						copyMap[i][j] = map[i][j] - water;
						if(copyMap[i][j] < 0) copyMap[i][j] = 0;
					}
				}
			}
			
			group = bfs(copyMap);
			map = copyMap;
			ans++;
		}
		
		System.out.println(group > 1 ? ans : 0);
	}
	private static int bfs(int[][] copyMap) {
		int group = 0;
		
		Queue<Location> q = new LinkedList<Location>();
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visited[i][j] && copyMap[i][j] > 0) {
					visited[i][j] = true;
					q.add(new Location(i, j));
					
					while (!q.isEmpty()) {
						int row = q.peek().row;
						int col = q.poll().col;
						for (int d = 0; d < pos.length; d++) {
							int nr = row + pos[d][0];
							int nc = col + pos[d][1];
							
							if(posCheck(nr, nc) && !visited[nr][nc] && copyMap[nr][nc] > 0) {
								visited[nr][nc] = true;
								q.add(new Location(nr, nc));
							}
						}
					}
					group++;
				}
			}
		}
		
		return group;
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}
}
