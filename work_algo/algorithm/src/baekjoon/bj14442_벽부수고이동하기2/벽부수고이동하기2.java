package baekjoon.bj14442_벽부수고이동하기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽부수고이동하기2 {
	static int N, M, K, ans = Integer.MAX_VALUE;
	static char[][] arr;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static boolean[][][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		visited = new boolean[N][M][K+1];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		bfs(new Location(0, 0, K, 1));
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}//main
	
	static void bfs(Location loc) {
		Queue<Location> q = new LinkedList<Location>();
		q.add(loc);
		visited[loc.row][loc.col][loc.chance] = true;
		
		while (!q.isEmpty()) {
			loc = q.poll();
			
			if(loc.row == N-1 && loc.col == M-1) {
				ans = Math.min(ans, loc.step);
				continue;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = loc.row + dr[i];
				int nc = loc.col + dc[i];
				
				if(posCheck(nr, nc) && !visited[nr][nc][loc.chance]) {
					if(arr[nr][nc] == '1' && loc.chance > 0) {
						q.add(new Location(nr, nc, loc.chance-1, loc.step + 1));
						visited[nr][nc][loc.chance-1] = true;
					}else if(arr[nr][nc] == '0') {
						q.add(new Location(nr, nc, loc.chance, loc.step + 1));
						visited[nr][nc][loc.chance] = true;
					}
				}
			}
		}
	}

	static class Location{
		int row;
		int col;
		int chance;
		int step;
		
		public Location(int row, int col, int chance, int step) {
			this.row = row;
			this.col = col;
			this.chance = chance;
			this.step = step;
		}
		
		@Override
		public String toString() {
			return row + " " + col + " " + chance + " " + step;
		}
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}
}
