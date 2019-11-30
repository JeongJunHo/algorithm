package swexpertacademy.sea1949_등산로조성;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class 등산로조성 {
	static class Location{
		int row;
		int col;
		
		public Location(int r, int c) {
			row = r;
			col = c;
		}
	}
	
	static int n, k, ans, high;
	static int[][] arr, pos = {{-1,0},{1,0},{0,-1},{0,1}};
	static boolean[][] visited;
	static List<Location> highList;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			highList = new ArrayList<Location>();
			high = Integer.MIN_VALUE;
			ans = Integer.MIN_VALUE;
			n = sc.nextInt();
			k = sc.nextInt();
			arr = new int[n][n];
			visited = new boolean[n][n];
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int tmp = sc.nextInt();
					arr[i][j] = tmp;
					high = Math.max(high, tmp);
				}
			}
			
			
			
			//고점삽입
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(arr[i][j] == high) {
						highList.add(new Location(i, j));
					}
				}
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					//임시보관
					int tmp = arr[i][j];
					for (int minus = 0; minus < k; minus++) {
						if(--arr[i][j] < 0) break;
						
						for (Location loc : highList) {
							if(arr[loc.row][loc.col] == high) {
								visited[loc.row][loc.col] = true;
								dfs(loc.row, loc.col, 1);
								visited[loc.row][loc.col] = false;
							}
						}
					}
					arr[i][j] = tmp;
				}// j for end
			}// i for end
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static void dfs(int row, int col, int cnt) {
		for (int j = 0; j < pos.length; j++) {
			int nr = row + pos[j][0];
			int nc = col + pos[j][1];
			
			if(posCheck(nr, nc) && !visited[nr][nc] && arr[nr][nc] < arr[row][col]) {
				visited[nr][nc] = true;
				dfs(nr, nc, cnt+1);
				visited[nr][nc] = false;
			}
		}
		
		ans = Math.max(ans, cnt);
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < arr.length && col >= 0 && col < arr.length;
	}
}
