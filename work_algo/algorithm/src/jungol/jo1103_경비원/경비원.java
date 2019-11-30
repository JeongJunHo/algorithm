package jungol.jo1103_경비원;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 경비원 {
	static class Location{
		int row;
		int col;
		int cost;
		public Location(int row, int col, int cost) {
			super();
			this.row = row;
			this.col = col;
			this.cost = cost;
		}
	}
	
	static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
	static int W, H, ans;
	static int[][] map;
	static Location dg;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		W = sc.nextInt();
		H = sc.nextInt();
		map = new int[H+1][W+1];
		for (int i = 1; i < H; i++) {
			for (int j = 1; j < W; j++) {
				map[i][j] = -1;
			}
		}
		//마을의 수
		int storeCnt = sc.nextInt();
		//마을 등록
		for (int i = 1; i <= storeCnt; i++) {
			//1북 2남 3서 4동
			int dir = sc.nextInt();
			int distance = sc.nextInt();
			//북
			if(dir == 1) {
				map[0][distance] = i;
			//남
			}else if (dir == 2) {
				map[H][distance] = i;
			//서
			}else if (dir == 3) {
				map[distance][0] = i;
			//동
			}else if (dir == 4) {
				map[distance][W] = i;
			}
		}
		
		//동근이 위치값 추출
		int dir = sc.nextInt();
		int distance = sc.nextInt();
		
		//북
		if(dir == 1) {
			dg = new Location(0, distance, 0);
		//남
		}else if (dir == 2) {
			dg = new Location(H, distance, 0);
		//서
		}else if (dir == 3) {
			dg = new Location(distance, 0, 0);
		//동
		}else if (dir == 4) {
			dg = new Location(distance, W, 0);
		}
		
		for (int i = 1; i <= storeCnt; i++) {
			bfs(i);
		}
		
		System.out.println(ans);
	}
	private static void bfs(int store) {
		Queue<Location> q = new LinkedList<Location>();
		boolean[][] visited = new boolean[H+1][W+1];
		q.add(dg);
		
		while (!q.isEmpty()) {
			Location loc = q.poll();
			
			if(map[loc.row][loc.col] == store) {
				ans += loc.cost;
				break;
			}
			
			for (int i = 0; i < pos.length; i++) {
				int nr = loc.row + pos[i][0];
				int nc = loc.col + pos[i][1];
				
				if(posCheck(nr, nc) && !visited[nr][nc] && map[nr][nc] != -1) {
					visited[nr][nc] = true;
					q.add(new Location(nr, nc, loc.cost+1));
				}
			}
		}
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row <= H && col >= 0 && col <= W;
	}
}
