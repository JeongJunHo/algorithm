package baekjoon.bj17244_아맞다우산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Location{
	int row;
	int col;
	
	Location(int r, int c){
		row = r;
		col = c;
	}
	
	@Override
	public String toString() {
		return row + " " + col;
	}
}

public class 아맞다우산 {
	static int ans = Integer.MAX_VALUE, n, m, middleMinStep;
	static List<Location> thing = new ArrayList<Location>();
	static char[][] arr;
	static Location start;
	static Location end;
	static int[][] pos = {{1,0},{0,1},{-1,0},{0,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new char[m][n];
		
		for (int i = 0; i < m; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < n; j++) {
				//물건이라면 위치 기억
				if(tmp.charAt(j) == 'X') {
					thing.add(new Location(i,j));
				}else if(tmp.charAt(j) == 'S'){
					start = new Location(i, j);
				}else if(tmp.charAt(j) == 'E') {
					end = new Location(i, j);
				}
				arr[i][j] = tmp.charAt(j);
			}
		}
		
		perm(new Location[thing.size()], new boolean[thing.size()], 0);
		
		System.out.println(ans);
	}
	
	static int cnt;
	static void perm(Location[] select, boolean[] visited, int idx) {
		if(select.length == idx) {
			int total = 0;
			Location[] totalArr = new Location[select.length + 2];
			totalArr[0] = start;
			for (int i = 1; i < totalArr.length-1; i++) {
				totalArr[i] = select[i-1];
			}
			totalArr[totalArr.length-1] = end;
			
			for (int i = 0; i < totalArr.length-1; i++) {
				middleMinStep = Integer.MAX_VALUE;
				bfs(totalArr[i], totalArr[i+1]);
				total += middleMinStep;
			}
			ans = Math.min(ans, total);
			
			return;
		}
		
		for (int i = 0; i < thing.size(); i++) {
			if(!visited[i]) {
				visited[i] = true;
				select[idx] = thing.get(i);
				perm(select, visited, idx + 1);
				visited[i] = false;
			}
		}
	}
	
	static void bfs(Location a, Location b) {
		Queue<Location> q = new LinkedList<Location>();
		boolean[][] visited = new boolean[m][n];
		q.add(a);
		visited[a.row][a.col] = true;
		
		int step = 0;
		while (!q.isEmpty()) {
			step++;
			int qSize = q.size();
			for (int idx = 0; idx < qSize; idx++) {
				int row = q.peek().row;
				int col = q.poll().col;
				for (int i = 0; i < pos.length; i++) {
					int nr = row + pos[i][0];
					int nc = col + pos[i][1];
					
					if(posCheck(nr, nc) && !visited[nr][nc] && arr[nr][nc] != '#') {
						if(nr == b.row && nc == b.col) {
							middleMinStep = step;
							return;
						}
						
						visited[nr][nc] = true;
						q.add(new Location(nr, nc));
					}
				}	
			}
		}
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < m && col >= 0 && col < n;
	}
}
