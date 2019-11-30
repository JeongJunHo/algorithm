package swexpertacademy.sea1249_보급로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 보급로 {
	static class Location implements Comparable<Location>{
		int row;
		int col;
		int cost;
		
		public Location(int row, int col, int cost) {
			super();
			this.row = row;
			this.col = col;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Location o) {
			return this.cost - o.cost;
		}
	}
	
	static int N, ans;
	static int[][] map;
	static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < str.length(); j++) {
					map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
				}
			}
			
			PriorityQueue<Location> q = new PriorityQueue<Location>();
			int[][] visited = new int[N][N];
			for (int i = 0; i < visited.length; i++) {
				Arrays.fill(visited[i], Integer.MAX_VALUE);
			}
			
			q.add(new Location(0, 0, 0));
			visited[0][0] = 0;
			
			while (!q.isEmpty()) {
				Location loc = q.poll();

				if(loc.row == N-1 && loc.col == N-1) {
					ans = Math.min(ans, loc.cost);
					continue;
				}
				
				for (int i = 0; i < pos.length; i++) {
					int nr = loc.row + pos[i][0];
					int nc = loc.col + pos[i][1];
					
					if(posCheck(nr, nc) && visited[nr][nc] > loc.cost + map[nr][nc]) {
						q.add(new Location(nr, nc, loc.cost + map[nr][nc]));
						visited[nr][nc] = loc.cost + map[nr][nc];
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}
}
