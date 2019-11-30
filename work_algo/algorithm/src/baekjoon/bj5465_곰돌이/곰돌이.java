package baekjoon.bj5465_곰돌이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 곰돌이 {
	static int N, S, ans = -1;
	static char[][] map;
	static int[][] timeMap;
	static boolean[][] visited;
	static Queue<Location> beeQ = new LinkedList<Location>();
	static Queue<Location> bearQ = new LinkedList<Location>();
	static Location bear;
	static int[][] pos = {{0,-1},{0,1},{-1,0},{1,0}};
	static class Location{
		int row;
		int col;
		public Location(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	
	static int ey, ex;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		map = new char[N][N];
		timeMap = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'H') {
					beeQ.add(new Location(i, j));
					visited[i][j] = true;
				}else if (map[i][j] == 'M') {
					bear = new Location(i, j);
				}
			}
		}
		
		//벌을 모두 뿌린다.
		diffusion();
		
		//분단위로 시도
		int left = 0;
		int middle = timeMap[bear.row][bear.col]/2;
		int right = timeMap[bear.row][bear.col];
		
		while (true) {
			int time = middle;
			//성공여부
			boolean check = false;
			for (int i = 0; i < N; i++) {
				Arrays.fill(visited[i], false);
			}
			
			bearQ.clear();
			bearQ.add(bear);
			visited[bear.row][bear.col] = true;
			
			cursor:while (!bearQ.isEmpty()) {
				for (int i = 0; i < S; i++) {
					int qSize = bearQ.size();
					for (int k = 0; k < qSize; k++) {
						int row = bearQ.peek().row;
						int col = bearQ.poll().col;
						if(timeMap[row][col] == time) continue;
						
						for (int j = 0; j < pos.length; j++) {
							int nr = row + pos[j][0];
							int nc = col + pos[j][1];
							
							if(posCheck(nr, nc) && !visited[nr][nc] && map[nr][nc] != 'T' && map[nr][nc] != 'H') {
								if(map[nr][nc] == 'D') {
									check = true;
									bearQ.clear();
									break cursor;
								}
								
								
								if(timeMap[nr][nc] > time) {
									visited[nr][nc] = true;
									bearQ.add(new Location(nr, nc));
								}
							}
						}
					}
				}
				time++;
			}
			
			if(!check) {
				right = middle-1;
				middle = (left + right) / 2;
			}else {
				ans = middle;
				left = middle + 1;
				middle = (left + right) / 2;
			}
			
			if(left > right) {
				break;
			}
			
		}
		
		System.out.println(ans);
	}
	
	private static void diffusion() {
		int time = 0;
		while (!beeQ.isEmpty()) {
			time++;
			int qSize = beeQ.size();
			for (int i = 0; i < qSize; i++) {
				int row = beeQ.peek().row;
				int col = beeQ.poll().col;
				
				for (int j = 0; j < pos.length; j++) {
					int nr = row + pos[j][0];
					int nc = col + pos[j][1];
					
					if(posCheck(nr, nc) && !visited[nr][nc] && map[nr][nc] != 'T' && map[nr][nc] != 'D') {
						visited[nr][nc] = true;
						timeMap[nr][nc] = time;
						beeQ.add(new Location(nr, nc));
					}
				}
			}
		}
	}

	static boolean posCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}
}