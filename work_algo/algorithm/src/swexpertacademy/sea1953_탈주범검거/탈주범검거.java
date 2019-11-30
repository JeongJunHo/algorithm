package swexpertacademy.sea1953_탈주범검거;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 탈주범검거 {
	static class Location{
		int row;
		int col;
		int time;
		boolean[] load;
		public Location(int row, int col, int time, boolean[] load) {
			super();
			this.row = row;
			this.col = col;
			this.time = time;
			this.load = load;
		}
	}
	static int N, M, R, C, L, ans;
	static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = 1;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			visited = new boolean[N][M];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Queue<Location> q = new LinkedList<Location>();
			q.add(new Location(R, C, 1, roadMaker(map[R][C])));
			visited[R][C] = true;
			
			while (!q.isEmpty()) {
				Location loc = q.poll();
				
				for (int i = 0; i < pos.length; i++) {
					int nr = loc.row + pos[i][0];
					int nc = loc.col + pos[i][1];
					
					if(posCheck(nr, nc) && !visited[nr][nc] && loc.time+1 <= L) {
						if(loc.load[i] && i == 0) {
							if("1256".contains(String.valueOf(map[nr][nc]))) {
								visited[nr][nc] = true;
								q.add(new Location(nr, nc, loc.time+1, roadMaker(map[nr][nc])));
								ans++;
							}
						}else if (loc.load[i] && i == 1) {
							if("1247".contains(String.valueOf(map[nr][nc]))) {
								visited[nr][nc] = true;
								q.add(new Location(nr, nc, loc.time+1, roadMaker(map[nr][nc])));
								ans++;
							}
						}else if (loc.load[i] && i == 2) {
							if("1345".contains(String.valueOf(map[nr][nc]))) {
								visited[nr][nc] = true;
								q.add(new Location(nr, nc, loc.time+1, roadMaker(map[nr][nc])));
								ans++;
							}
						}else if (loc.load[i] && i == 3) {
							if("1367".contains(String.valueOf(map[nr][nc]))) {
								visited[nr][nc] = true;
								q.add(new Location(nr, nc, loc.time+1, roadMaker(map[nr][nc])));
								ans++;
							}
						}
					}
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}
	
	static boolean[] roadMaker(int num) {
		switch (num) {
		case 1:
			return new boolean[] {true,true,true,true};
		case 2:
			return new boolean[] {true,true,false,false};
		case 3:
			return new boolean[] {false,false,true,true};
		case 4:
			return new boolean[] {true,false,false,true};
		case 5:
			return new boolean[] {false,true,false,true};
		case 6:
			return new boolean[] {false,true,true,false};
		case 7:
			return new boolean[] {true,false,true,false};
		default:
			return null;
		}
	}
}
