package baekjoon.bj1941_소문난칠공주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 소문난칠공주 {
	static char[][] map;
	static boolean[][] visited;
	static final int N = 5;
	static int ans;
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
		
		map = new char[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		comb(0, 0);
		
		System.out.println(ans);
	}
	private static void comb(int cnt, int idx) {
		if(cnt == 7) {
			Queue<Location> q = new LinkedList<Location>();
			boolean[][] innerVisit = new boolean[N][N];
			cursor:for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(visited[i][j]) {
						q.add(new Location(i, j));
						innerVisit[i][j] = true;
						break cursor;
					}
				}
			}
			
			int link = 1;
			while (!q.isEmpty()) {
				if(link >= 7) break; 
				Location loc = q.poll();
				
				for (int i = 0; i < pos.length; i++) {
					int nr = loc.row + pos[i][0];
					int nc = loc.col + pos[i][1];
					
					if(posCheck(nr,nc) && visited[nr][nc] && !innerVisit[nr][nc]) {
						innerVisit[nr][nc] = true;
						q.add(new Location(nr, nc));
						link++;
					}
				}
			}
			
			if(link >= 7) {
				int y = 0;
				int s = 0;
				
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if(visited[i][j]) {
							if(map[i][j] == 'Y') {
								y++;
							}else {
								s++;
							}
						}
					}
				}
				
				if(s > y) {
//					for (int i = 0; i < N; i++) {
//						for (int j = 0; j < N; j++) {
//							if(visited[i][j]) {
//								System.out.print("1 ");
//							}else {
//								System.out.print("0 ");
//							}
//						}
//						System.out.println();
//					}
//					System.out.println();
					
					ans++;
				}
			}
			
			return;
		}
		
		if(idx == 25) {
			return;
		}

		visited[idx/N][idx%N] = true;
		comb(cnt+1, idx+1);
		visited[idx/N][idx%N] = false;
		comb(cnt, idx+1);
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}
}
