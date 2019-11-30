package swexpertacademy.sea4727_견우와직녀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 견우와직녀 {
	static int N, M, ans;
	static int[][] arr;
	static boolean[][][][] visited;
	static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			ans = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			visited = new boolean[N][N][N][N];
			
			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			bfs();
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static class Location{
		int row;
		int col;
		int chance;
		int time;
		int or;
		int oc;
		
		public Location(int row, int col, int chance, int time, int or, int oc) {
			this.row = row;
			this.col = col;
			this.chance = chance;
			this.time = time;
			this.or = or;
			this.oc = oc;
		}
	}
	
	static void bfs() {
		Queue<Location> q = new LinkedList<Location>();
		q.add(new Location(0,0,1,0,0,0));
		visited[0][0][0][0] = true;
		
		while (!q.isEmpty()) {
			Location loc = q.poll();
			
			if(loc.row == N-1 && loc.col == N-1) {
				ans = Math.min(ans, loc.time);
				continue;
			}
			
			for (int i = 0; i < pos.length; i++) {
				int nr = loc.row + pos[i][0];
				int nc = loc.col + pos[i][1];
				
				if(posCheck(nr, nc) && !visited[nr][nc][loc.or][loc.oc]) {
					int time = loc.time + 1;
					
					if(arr[nr][nc] == 1) {
						visited[nr][nc][loc.or][loc.oc] = true;
						q.add(new Location(nr, nc, loc.chance, time, loc.or, loc.oc));
					}else if(arr[nr][nc] > 1) {
						visited[nr][nc][loc.or][loc.oc] = true;
						int interval = 0;
						if(time % arr[nr][nc] != 0) {
							interval = arr[nr][nc] - time % arr[nr][nc];
						}
						
						q.add(new Location(nr, nc, loc.chance, time + interval, loc.or, loc.oc));
					}else if(arr[nr][nc] == 0 && loc.chance == 1) {
						visited[nr][nc][nr][nc] = true;
						int interval = 0;
						if(time % M != 0) {
							interval = M - time % M;
						}
						
						q.add(new Location(nr, nc, 0, time + interval, nr, nc));
					}
				}
			}
		}
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 &&  row < N && col >= 0 && col < N;
	}
}
