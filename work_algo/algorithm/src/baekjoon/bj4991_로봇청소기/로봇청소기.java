package baekjoon.bj4991_로봇청소기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 로봇청소기 {
	static class Location{
		int row;
		int col;
		public Location(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
		@Override
		public String toString() {
			return "Location [row=" + row + ", col=" + col + "]";
		}
		
	}
	static int w, h, ans;
	static char[][] map;
	static int[][] pos = {{1,0},{-1,0},{0,-1},{0,1}};
	static boolean[][] visited;
	static List<Location> dirtyList;
	static Location robot;
	static int[][] robotMap;
	static int[][][] dirtyMap;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			ans = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if(w == 0 && h == 0) break;
			
			map = new char[h][w];
			visited = new boolean[h][w];
			dirtyList = new ArrayList<Location>();
			
			for (int i = 0; i < h; i++) {
				String str = br.readLine();
				for (int j = 0; j < w; j++) {
					map[i][j] = str.charAt(j);
					if(map[i][j] == '*') {
						dirtyList.add(new Location(i, j));
					}else if(map[i][j] == 'o') {
						robot = new Location(i, j);
					}
				}
			}
			
			robotMap = new int[h][w];
			dirtyMap = new int[dirtyList.size()][h][w];
			
			bfs(robot, robotMap);
			for (int i = 0; i < dirtyList.size(); i++) {
				bfs(dirtyList.get(i), dirtyMap[i]);
			}
			
			perm(new Location[dirtyList.size()], new int[dirtyList.size()][h][w], new boolean[dirtyList.size()], 0);
			
			System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
		}
	}
	private static void perm(Location[] sel, int[][][] map, boolean[] visited, int idx) {
		if(sel.length == idx) {
			int sum = 0;
			int result = 0;
			result = robotMap[sel[0].row][sel[0].col];
			if(result == 0) return;
			sum += result;
			for (int i = 0; i < sel.length-1; i++) {
				result = map[i][sel[i+1].row][sel[i+1].col];
				if(result == 0) return;
				sum += result;
			}
			
			ans = Math.min(ans, sum);
			
			return;
		}
		
		for (int i = 0; i < dirtyList.size(); i++) {
			if(!visited[i]) {
				sel[idx] = dirtyList.get(i);
				map[idx] = dirtyMap[i];
				visited[i] = true;
				perm(sel, map, visited, idx+1);
				visited[i] = false;
			}
		}
	}
	private static void bfs(Location target, int[][] tmpMap) {
		int time = 0;
		Queue<Location> q = new LinkedList<Location>();
		for (int i = 0; i < visited.length; i++) {
			Arrays.fill(visited[i], false);
		}
		q.add(target);
		visited[target.row][target.col] = true;
		while (!q.isEmpty()) {
			time++;
			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				Location loc = q.poll();
				
				for (int j = 0; j < pos.length; j++) {
					int nr = loc.row + pos[j][0];
					int nc = loc.col + pos[j][1];
					
					if(posCheck(nr, nc) && !visited[nr][nc] && map[nr][nc] != 'x') {
						tmpMap[nr][nc] = time;
						q.add(new Location(nr, nc));
						visited[nr][nc] = true;
					}
				}
			}
		}
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < h && col >= 0 && col <w;
	}
}
