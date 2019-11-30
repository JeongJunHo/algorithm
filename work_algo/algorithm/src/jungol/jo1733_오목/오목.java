package jungol.jo1733_오목;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 오목 {
	static int[][] pos = {{0,1},{-1,1},{1,0},{1,1}};
	final static int SIZE = 19;
	static int[][] map = new int[SIZE][SIZE];
	static int ans = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < SIZE; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < SIZE; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean complete = true;
		for (int i = 0; i < SIZE && complete; i++) {
			for (int j = 0; j < SIZE && complete; j++) {
				if(map[i][j] != 0) {
					int color = map[i][j];
					for (int k = 0; k < pos.length; k++) {
						int cnt = 1;
						int nr = i;
						int nc = j;
						while (true) {
							nr += pos[k][0];
							nc += pos[k][1];
							
							if(!posCheck(nr, nc)) break;
							if(map[nr][nc] == color) {
								cnt++;
							}else {
								break;
							}
						}
						if(cnt == 5) {
							nr = i - pos[k][0];
							nc = j - pos[k][1];
							
							if(!posCheck(nr, nc) || map[nr][nc] != color) {
								System.out.println(color);
								System.out.println((i+1) + " " + (j+1));
								complete = false;
								break;
							}
						}
					}
				}
			}
		}
		if(complete) {
			System.out.println(ans);
		}
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
	}
}
