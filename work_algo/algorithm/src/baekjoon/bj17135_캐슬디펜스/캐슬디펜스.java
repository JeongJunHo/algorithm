package baekjoon.bj17135_캐슬디펜스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 캐슬디펜스 {
	static class Location{
		int row;
		int col;
		
		public Location(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		@Override
		public int hashCode() {
			return (row + col + "").hashCode();
		}
		
		@Override
		public boolean equals(Object obj) {
			Location loc = (Location) obj;
			return this.row == loc.row && this.col == loc.col;
		}
	}
	
	//행의 수
	static int N;
	//열의 수
	static int M;
	//공격거리 제한
	static int D;
	//답
	static int ans;
	static int enemy;
	static int simulEnemy;
	static int simulAns;
	static int[][] map;
	static int[][] simulMap;
	static boolean[][] visited;
	static int[][] pos = {{0,-1},{-1,0},{0,1}};
	static HashSet<Location> shot = new HashSet<Location>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		ans = 0;
		
		map = new int[N][M];
		simulMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					enemy++;
				}
			}
		}
		
		comb(new int[3], 0, 0);
		
		System.out.println(ans);
	}
	
	static void comb(int[] sel, int idx, int s_idx) {
		if(sel.length == s_idx) {
			simulationInit();
			while (simulEnemy > 0) {
				bfs(sel);
				enemyMove();
			}
			
			ans = Math.max(ans, simulAns);
			
			return;
		}
		
		if(idx == M) {
			return;
		}
		
		sel[s_idx] = idx;
		comb(sel, idx + 1, s_idx + 1);
		comb(sel, idx + 1, s_idx);
	}

	static void enemyMove() {
		for (int i = 0; i < M; i++) {
			if(simulMap[N-1][i] == 1) {
				simulEnemy--;
			}
		}
		
		for (int i = N-2; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				simulMap[i+1][j] = simulMap[i][j];
			}
		}
		Arrays.fill(simulMap[0], 0);
	}
	
	static void bfs(int[] sel) {
		shot.clear();
		
		for (int i = 0; i < sel.length; i++) {
			if(simulMap[N-1][sel[i]] == 1) {
				shot.add(new Location(N-1, sel[i]));
			}else {
				Queue<Location> q = new LinkedList<Location>();
				visited = new boolean[N][M];
				q.add(new Location(N-1, sel[i]));
				visited[N-1][sel[i]] = true;
				int range = 1;
				
				boolean complete = false;
				while (!q.isEmpty() && range < D && !complete) {
					range++;
					int qSize = q.size();
					for (int j = 0; !complete && j < qSize; j++) {
						Location loc = q.poll();
						
						for (int k = 0; k < pos.length; k++) {
							int nr = loc.row + pos[k][0];
							int nc = loc.col + pos[k][1];
							
							if(posCheck(nr, nc) && !visited[nr][nc]) {
								if(simulMap[nr][nc] == 1) {
									shot.add(new Location(nr, nc));
									complete = true;
									break;
								}else {
									q.add(new Location(nr, nc));
									visited[nr][nc] = true;
								}
							}
						}// k for end
					}// j for end
				}
			}// else end
		}
		
		for (Location loc : shot) {
			simulMap[loc.row][loc.col] = 0;
			simulEnemy--;
			simulAns++;
		}
	}
	
	static void simulationInit() {
		for (int i = 0; i < map.length; i++) {
			simulMap[i] = map[i].clone();
		}
		simulEnemy = enemy;
		simulAns = 0;
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}
}
