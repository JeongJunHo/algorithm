package swexpertacademy.sea2105_디저트카페;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 디저트카페 {
	static int N, ans;
	static int[][] map;
	static int[][] pos = {{1,1},{1,-1},{-1,-1},{-1,1}};
	static boolean[] disert;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			ans = -1;
			disert = new boolean[101];
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					backTrack(i, j, i, j, 0, 0);
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	private static void backTrack(int row, int col, int originRow, int originCol, int direction, int cnt) {
		if(cnt > 0 && row == originRow && col == originCol) {
			ans = Math.max(ans, cnt);
			
			return;
		}
		
		if(cnt > 0 && row == originRow) {
			return;
		}
		
		if(!disert[map[row][col]]) {
			int nr = row + pos[direction][0];
			int nc = col + pos[direction][1];
			if(posCheck(nr, nc)) {
				disert[map[row][col]] = true;
				if(direction < 3) {
					backTrack(nr, nc, originRow, originCol, direction+1, cnt+1);
				}
				backTrack(nr, nc, originRow, originCol, direction, cnt+1);
				disert[map[row][col]] = false;
			}
		}
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}
}
