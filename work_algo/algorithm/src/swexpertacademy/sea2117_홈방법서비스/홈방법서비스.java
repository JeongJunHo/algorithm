package swexpertacademy.sea2117_홈방법서비스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 홈방법서비스 {
	static class Location{
		int row;
		int col;
		
		public Location(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	static int N, M, ans;
	static int[][] map;
	static int[] cost;
	static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			cost = new int[N*N-1];
			for (int i = 1; i < cost.length; i++) {
				cost[i] = i * i + (i-1) * (i-1);
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					bfs(new Location(i, j));
				}
			}
			
			System.out.println("#" + tc + " " +ans);
		}
	}
	private static void bfs(Location loc) {
		Queue<Location> queue = new LinkedList<Location>();
		boolean[][] visited = new boolean[N][N];
		int house = 0;
		int distance = 0;
		queue.add(loc);
		visited[loc.row][loc.col] = true;
		
		while (!queue.isEmpty()) {
			int qSize = queue.size();
			distance++;
			for (int i = 0; i < qSize; i++) {
				int row = queue.peek().row;
				int col = queue.poll().col;
				
				if(map[row][col] == 1) house++;
				
				for (int j = 0; j < pos.length; j++) {
					int nr = row + pos[j][0];
					int nc = col + pos[j][1];
					
					if(posCheck(nr, nc) && !visited[nr][nc]) {
						visited[nr][nc] = true;
						queue.add(new Location(nr, nc));
					}
				}
			}
			
			if(cost[distance] <= (house * M)) {
				ans = Math.max(ans, house);
			}
		}
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}
}
