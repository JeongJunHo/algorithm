package baekjoon.bj16973_직사각형탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;



class Location{
	int row;
	int col;
	
	Location(int r, int c){
		row = r;
		col = c;
	}
}

public class 직사각형탈출 {
	//격자판 행, 열, 직사각형 높이, 너비, 시작좌표, 종료좌표
	static int n, m, h, w, sr, sc, fr, fc, ans;
	static boolean complete = false;
	static int[][] arr;
	static boolean[][] visited;
	static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n + 1][m + 1];
		visited = new boolean[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		sr = Integer.parseInt(st.nextToken());
		sc = Integer.parseInt(st.nextToken());
		fr = Integer.parseInt(st.nextToken());
		fc = Integer.parseInt(st.nextToken());
		
		ans = -1;
		bfs(new Location(sr, sc));
		if(complete) {
			System.out.println(ans);
		}else {
			System.out.println(-1);
		}
		
	}

	static void bfs(Location loc) {
		complete = false;
		Queue<Location> q = new LinkedList<Location>();
		q.add(loc);
		visited[loc.row][loc.col] = true;
		while (!complete && !q.isEmpty()) {
			ans++;
			int tmp = q.size();
			for (int i = 0; i < tmp; i++) {
				int row = q.peek().row;
				int col = q.poll().col;
				if(row == fr && col == fc) {
					complete = true;
					q.clear();
					return;
				}
				
				for (int j = 0; j < pos.length; j++) {
					int nr = row + pos[j][0];
					int nc = col + pos[j][1];
					
					if(outCheck(nr, nc) && !visited[nr][nc] && posCheck(nr, nc) ) {
						visited[nr][nc] = true;
						q.add(new Location(nr, nc));
					}
				}
			}
		}
	}
	
	//범위체크
	static boolean outCheck(int row, int col) {
		return row > 0 && row <= n-h+1 && col > 0 && col <= m-w+1;
	}

	//벽체크
	static boolean posCheck(int row, int col) {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int nr = row + i;
				int nc = col + j;
				if (arr[nr][nc] == 1) {
					return false;
				}
			}
		}
		return true;
	}
}

