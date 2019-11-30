package baekjoon.bj14890_경사로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 경사로 {
	static int N, X, ans;
	static int[][] map1, map2;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		ans = 0;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		map1 = new int[N][N];
		map2 = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map2[j][i] = map1[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//같은 값을 만나면 누적하고 큰값을 만나면 비교해서 X보다 작으면 종료 작은값은 만나면 종료
		solved(map1);
		solved(map2);
			
		System.out.println(ans);
	}

	private static void solved(int[][] map) {
		for (int i = 0; i < N; i++) {
			boolean[] visited = new boolean[N];
			//정방향
			int num = map[i][0];
			int cnt = 1;
			boolean check = true;
			for (int j = 1; j < N; j++) {
				if(map[i][j] == num) {
					cnt++;
				}else if(map[i][j] < num) {
					num = map[i][j];
					cnt = 1;
				}else if(map[i][j] > num) {
					if(map[i][j] == num + 1){
						if(cnt >= X) {
							for (int k = 1; k <= X; k++) {
								visited[j-k] = true;
							}
							num = map[i][j];
							cnt = 1;
						//길이가 안될 때
						}else {
							check = false;
							break;
						}
					//높이 차이가 2이상일때
					}else {
						check = false;
						break;
					}
				}
			}
			
			if(!check) {
				continue;
			}
			
			//역방향
			num = map[i][N-1];
			cnt = 1;
			for (int j = N-2; j >= 0; j--) {
				if(map[i][j] == num) {
					cnt++;
				}else if(map[i][j] < num) {
					num = map[i][j];
					cnt = 1;
				}else if(map[i][j] > num) {
					if(map[i][j] == num + 1){
						if(cnt >= X) {
							for (int k = 1; k <= X; k++) {
								if(visited[j+k]) {
									check = false;
								}
							}
							num = map[i][j];
							cnt = 1;
						//길이가 안될 때
						}else {
							check = false;
							break;
						}
					//높이 차이가 2이상일때
					}else {
						check = false;
						break;
					}
				}
			}
			
			if(check) {
				ans++;
			}
		}
	}

	static boolean posCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}
}
