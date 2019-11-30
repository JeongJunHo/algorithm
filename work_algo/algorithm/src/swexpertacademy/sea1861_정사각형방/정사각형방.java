package swexpertacademy.sea1861_정사각형방;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 정사각형방 {
	static class Location{
		int row;
		int col;
		
		public Location(int r, int c) {
			row = r;
			col = c;
		}
	}
	
	static int n;
	static int ans;
	static int ansCnt;
	static int[][] arr;
	static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
	static Queue<Location> q = new LinkedList<Location>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			ans = Integer.MAX_VALUE;
			ansCnt = Integer.MIN_VALUE;
			
			n = Integer.parseInt(br.readLine());
			arr = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					bfs(new Location(i, j));
				}
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append(" ").append(ansCnt).append("\n");
		}
		System.out.println(sb);
	}
	
	static void bfs(Location loc) {
		q.clear();
		q.add(loc);
		
		int cnt = 0;
		while (!q.isEmpty()) {
			cnt++;
			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				int row = q.peek().row;
				int col = q.poll().col;
				
				for (int j = 0; j < pos.length; j++) {
					int nr = row + pos[j][0];
					int nc = col + pos[j][1];
					
					if(posCheck(nr, nc) && arr[nr][nc] == (arr[row][col]+1)) {
						q.add(new Location(nr, nc));
					}
				}
			}
		}
		
		if(ansCnt < cnt || (ansCnt == cnt && ans > arr[loc.row][loc.col])) {
			ans = arr[loc.row][loc.col];
			ansCnt = cnt;
		}
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < n && col >= 0 && col < n;
	}
}
