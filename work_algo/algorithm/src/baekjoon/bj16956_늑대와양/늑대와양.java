package baekjoon.bj16956_늑대와양;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 늑대와양 {
	static int[][] pos = {{1,0},{-1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[R+2][C+2];
		for (int i = 0; i < R+2; i++) {
			Arrays.fill(map[i], '.');
		}
		
		for (int i = 1; i <= R; i++) {
			String str = br.readLine();
			for (int j = 1; j <= C; j++) {
				map[i][j] = str.charAt(j-1);
			}
		}
		
		boolean success = true;
		for (int r = 1; r <= R && success; r++) {
			for (int c = 1; c <= C && success; c++) {
				if(map[r][c] == 'W') {
					for (int i = 0; i < pos.length; i++) {
						int nr = r + pos[i][0];
						int nc = c + pos[i][1];
						
						if(map[nr][nc] == 'S') {
							success = false;
							break;
						}else if(map[nr][nc] == '.') {
							map[nr][nc] = 'D';
						}
					}
				}
			}
		}
		
		if(success) {
			System.out.println(1);
			for (int i = 1; i <= R; i++) {
				for (int j = 1; j <= C; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}else {
			System.out.println(0);
		}
	}
}
