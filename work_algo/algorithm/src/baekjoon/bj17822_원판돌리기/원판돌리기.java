package baekjoon.bj17822_원판돌리기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 원판돌리기 {
	static int N, M, T, ans;
	static int[][] arr;
	static int[] spinHelper;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][M];
		spinHelper = new int[M];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			if(d == 0) {
				spin_cw(x, k);
			}else {
				spin_ccw(x, k);
			}

			boolean check = false;
			int sum = 0;
			int count = 0;
			boolean[][] visited = new boolean[N+1][M];
			for (int r = 1; r <= N; r++) {
				for (int c = 0; c < M; c++) {
					if(arr[r][c] != 0) {
						sum += arr[r][c];
						count++;
					}
					if(c == 0) {
						colVisitedCheck(visited, r, c, M-1, c+1);
					}else if(c == M-1) {
						colVisitedCheck(visited, r, c, c-1, 0);
					}else {
						colVisitedCheck(visited, r, c, c-1, c+1);
					}
					
					if(r == 1) {
						if(arr[r][c] != 0 && arr[r][c] == arr[r+1][c]) {
							visited[r][c] = true;
							visited[r+1][c] = true;
						}
					}else if (r == N) {
						if(arr[r][c] != 0 && arr[r][c] == arr[r-1][c]) {
							visited[r][c] = true;
							visited[r-1][c] = true;
						}
					}else {
						rowVisitedCheck(visited, r, c, r-1, r+1);
					}
				}
			}
			
			for (int r = 1; r <= N; r++) {
				for (int c = 0; c < M; c++) {
					if(visited[r][c]) {
						if(!check) check = true;
						arr[r][c] = 0;
					}
				}
			}
			if(!check) {
				double avg = (double)sum / count;
				
				for (int r = 1; r <= N; r++) {
					for (int c = 0; c < M; c++) {
						if(arr[r][c] != 0) {
							if(arr[r][c] > avg) {
								arr[r][c]--;
							}else if(arr[r][c] < avg) {
								arr[r][c]++;
							}
						}
					}
				}
			}
		}//tc end
		
		
		
		for (int r = 1; r <= N; r++) {
			for (int c = 0; c < M; c++) {
				ans += arr[r][c];
			}
		}
		
		System.out.println(ans);
	}// main end
	
	static void spin_cw(int target, int cnt) {
		for (int i = 1; i <= N; i++) {
			if(i % target == 0) {
				for (int j = 0; j < M; j++) {
					spinHelper[(j+cnt) % M] = arr[i][j];
				}
				arr[i] = spinHelper.clone();
			}
		}
	}
	
	static void spin_ccw(int target, int cnt) {
		for (int i = 1; i <= N; i++) {
			if(i % target == 0) {
				for (int j = 0; j < M; j++) {
					int idx = j-cnt;
					if(idx < 0) {
						idx += M;
					}
					spinHelper[idx] = arr[i][j];
				}
				arr[i] = spinHelper.clone();
			}
		}
	}
	
	static void colVisitedCheck(boolean[][] visited, int r, int c, int a, int b) {
		if(arr[r][c] != 0 && arr[r][c] == arr[r][a]) {
			visited[r][c] = true;
			visited[r][a] = true;
		}
		if(arr[r][c] != 0 && arr[r][c] == arr[r][b]) {
			visited[r][c] = true;
			visited[r][b] = true;
		}
	}
	
	static void rowVisitedCheck(boolean[][] visited, int r, int c, int a, int b) {
		if(arr[r][c] != 0 && arr[r][c] == arr[a][c]) {
			visited[r][c] = true;
			visited[a][c] = true;
		}
		if(arr[r][c] != 0 && arr[r][c] == arr[b][c]) {
			visited[r][c] = true;
			visited[b][c] = true;
		}
	}
}
