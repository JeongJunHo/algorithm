package baekjoon.bj1035_조각움직이기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 조각움직이기 {
	static final int N = 5;
	static int[][] map;
	static int ans = Integer.MAX_VALUE;
	static class Location{
		int row;
		int col;
		public Location(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	static List<Location> pieceList = new ArrayList<Location>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == '*') {
					pieceList.add(new Location(i, j));
				}
			}
		}
		
		comb(new int[pieceList.size()], 0, 0);
		
		System.out.println(ans);
	}
	private static void comb(int[] sel, int idx, int s_idx) {
		if(sel.length == s_idx) {
			boolean[] visited = new boolean[sel.length];
			visited[0] = true;
			for (int k = 0; k < sel.length-1; k++) {
				for (int i = 0; i < sel.length; i++) {
					if(visited[i]) {
						int row = sel[i] / 5;
						int col = sel[i] % 5;
						for (int j = 0; j < sel.length; j++) {
							if(!visited[j] && i!=j) {
								int tRow = sel[j] / 5;
								int tCol = sel[j] % 5;
								if(row == tRow && col+1 == tCol) visited[j] = true;
								if(row == tRow && col-1 == tCol) visited[j] = true;
								if(row+1 == tRow && col == tCol) visited[j] = true;
								if(row-1 == tRow && col == tCol) visited[j] = true;
							}
						}
					}
				}
			}
			
			for (int i = 0; i < visited.length; i++) {
				if(!visited[i]) return;
			}
			
			perm(sel, new boolean[sel.length], new int[sel.length], 0);
			
			return;
		}
		
		if(25 == idx) {
			return;
		}
		
		sel[s_idx] = idx;
		comb(sel, idx+1, s_idx+1);
		comb(sel, idx+1, s_idx);
	}
	private static void perm(int[] origin, boolean[] visited, int[] sel, int idx) {
		if(origin.length == idx) {
			int sum = 0;
			for (int i = 0; i < pieceList.size(); i++) {
				Location loc = pieceList.get(i);
				int row = sel[i] / 5;
				int col = sel[i] % 5;
				sum += Math.abs(loc.row-row) + Math.abs(loc.col-col);
			}
			ans = Math.min(ans, sum);
			
			return;
		}
		
		for (int i = 0; i < origin.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				sel[idx] = origin[i];
				perm(origin, visited, sel, idx+1);
				visited[i] = false;
			}
		}
	}
}
