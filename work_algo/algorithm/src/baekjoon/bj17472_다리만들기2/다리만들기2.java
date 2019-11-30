package baekjoon.bj17472_다리만들기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
//최대간선 갯수 44 반복횟수 2471987번 최대값 0.6초
public class 다리만들기2 {
	static class Location{
		int row;
		int col;
		
		public Location(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static class Edge{
		int x;
		int y;
		int size;
		
		public Edge(int x, int y, int size) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
		}
	}
	
	static int N, M;
	static int islandCnt, ans = Integer.MAX_VALUE;
	static int[][] map;
	static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
	static boolean[][] rel;
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
		
		//섬의 번호 체크 (bfs)
		boolean[][] visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					islandCnt++;
					visited[i][j] = true;
					Queue<Location> q = new LinkedList<Location>();
					map[i][j] = islandCnt;
					q.add(new Location(i, j));
					
					while (!q.isEmpty()) {
						int row = q.peek().row;
						int col = q.poll().col;
						
						for (int k = 0; k < pos.length; k++) {
							int nr = row + pos[k][0];
							int nc = col + pos[k][1];
							
							if(posCheck(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
								visited[nr][nc] = true;
								map[nr][nc] = islandCnt;
								q.add(new Location(nr, nc));
							}
						}
					}
				}
			}
		}
		
		//간선 찾기(bfs)
		visited = new boolean[N][M];
		List<Edge> edgeList = new ArrayList<Edge>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visited[i][j] && map[i][j] != 0) {
					visited[i][j] = true;
					Queue<Location> q = new LinkedList<Location>();
					q.add(new Location(i, j));
					
					while (!q.isEmpty()) {
						int row = q.peek().row;
						int col = q.poll().col;
						
						for (int k = 0; k < pos.length; k++) {
							int nr = row + pos[k][0];
							int nc = col + pos[k][1];
							
							if(posCheck(nr, nc) && !visited[nr][nc]) {
								if(map[nr][nc] != 0) {
									visited[nr][nc] = true;
									q.add(new Location(nr, nc));
								}else {
									int tmpSize = 0;
									
									while (posCheck(nr,nc) && map[nr][nc] == 0) {
										nr += pos[k][0];
										nc += pos[k][1];
										
										tmpSize++;
									}
									
									if(posCheck(nr, nc) && tmpSize > 1) {
										edgeList.add(new Edge(map[row][col], map[nr][nc], tmpSize));
									}
								}
								
							}
						}
					}//while end
				}//if end
			}
		}
		
		//구해진 간선으로 섬-1개 만큼의 조합 검색
		comb(edgeList, new Edge[islandCnt-1], 0, 0);
		System.out.println(cnt);
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	
	
	private static void comb(List<Edge> edgeList, Edge[] sel, int idx, int s_idx) {
		cnt++;
		if(s_idx == sel.length) {
			rel = new boolean[islandCnt+1][islandCnt+1];
			int sum = 0;
			for (int i = 0; i < sel.length; i++) {
				rel[sel[i].x][sel[i].y] = true;
				rel[sel[i].y][sel[i].x] = true;
				sum += sel[i].size;
			}
			
			if(completeCheck(1)) {
				ans = Math.min(ans, sum);
			}
			
			return;
		}
		
		if(idx == edgeList.size()) {
			return;
		}
		
		sel[s_idx] = edgeList.get(idx);
		comb(edgeList, sel, idx+1, s_idx+1);
		comb(edgeList, sel, idx+1, s_idx);
		
	}
	
	static long cnt;
	static boolean completeCheck(int island) {
		
		boolean result = false;
		int cnt = 0;
		boolean[] visited = new boolean[islandCnt+1];
		
		Queue<Integer> q = new LinkedList<Integer>();
		
		visited[island] = true;
		q.add(island);
		cnt++;
		
		while (!q.isEmpty()) {
			int num = q.poll();
			for (int i = 1; i <= islandCnt; i++) {
				if(!visited[i] && rel[num][i]) {
					visited[i] = true;
					cnt++;
					q.add(i);
				}
			}
		}
		
		if(islandCnt == cnt) {
			result = true;
		}
		
		return result;
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}
}
