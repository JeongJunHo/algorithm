package swexpertacademy.sea1767_프로세서연결하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class 프로세서연결하기 {
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
			return row + "," + col;
		}
	}
	static int N, ans, coreCnt;
	static int[][] map;
	static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
	static List<Location> coreList;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = Integer.MAX_VALUE;
			coreCnt = 0;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			coreList = new ArrayList<Location>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						if(i != 0 && i != N-1 && j!= 0 && j != N-1) {
							coreList.add(new Location(i, j));
						}
					}
				}
			}
			
			backTrack(0, 0, 0);
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	private static void backTrack(int idx, int line, int core) {
		if(idx == coreList.size()) {
			if(core > coreCnt) {
				ans = line;
				coreCnt = core;
			}else if (core == coreCnt) {
				if(line < ans) {
					ans = line;
				}
			}
			
			return;
		}
		
		for (int i = 0; i < pos.length; i++) {
			int size = 0;
			int nr = coreList.get(idx).row;
			int nc = coreList.get(idx).col;
			
			boolean check = true;
			while (true) {
				nr += pos[i][0];
				nc += pos[i][1];
				if(posCheck(nr, nc)) {
					if(map[nr][nc] == 0) {
						map[nr][nc] = 1;
						size++;
					}else {
						check = false;
						for (int j = 0; j < size; j++) {
							nr -= pos[i][0];
							nc -= pos[i][1];
							map[nr][nc] = 0;
						}
						size = 0;
						
						break;
					}
				}else {
					break;
				}
			}
			
			if(check) {
				backTrack(idx+1, line+size, core+1);
			}else {
				backTrack(idx+1, line, core);
			}
			
			for (int j = 0; j < size; j++) {
				nr -= pos[i][0];
				nc -= pos[i][1];
				map[nr][nc] = 0;
			}
		}
	}

	static boolean posCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}
}
