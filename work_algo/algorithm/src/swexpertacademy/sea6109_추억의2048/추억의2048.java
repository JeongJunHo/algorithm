package swexpertacademy.sea6109_추억의2048;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 추억의2048 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			String str = st.nextToken();
			
			int[][] map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Queue<Integer> q = new LinkedList<Integer>();
			int[][] drawMap = new int[N][N];
			switch (str) {
			case "up":
				//위
				for (int col = 0; col < N; col++) {
					int num = -1;
					for (int row = 0; row < N; row++) {
						if(map[row][col] > 0) {
							if(num == -1) {
								num = map[row][col];
							}else {
								if(num == map[row][col]) {
									q.add(num*2);
									num = -1;
								}else {
									q.add(num);
									num = map[row][col];
								}
							}
						}
					}
					if(num != -1) {
						q.add(num);
					}
					
					int r = 0;
					while (!q.isEmpty()) {
						drawMap[r++][col] = q.poll();
					}
				}
				
				break;
			case "down":
				//아래
				for (int col = 0; col < N; col++) {
					int num = -1;
					for (int row = N-1; row >= 0; row--) {
						if(map[row][col] > 0) {
							if(num == -1) {
								num = map[row][col];
							}else {
								if(num == map[row][col]) {
									q.add(num*2);
									num = -1;
								}else {
									q.add(num);
									num = map[row][col];
								}
							}
						}
					}
					if(num != -1) {
						q.add(num);
					}
					
					int r = N-1;
					while (!q.isEmpty()) {
						drawMap[r--][col] = q.poll();
					}
				}
				
				break;
			case "left":
				//왼쪽
				for (int row = 0; row < N; row++) {
					int num = -1;
					for (int col = 0; col < N; col++) {
						if(map[row][col] > 0) {
							if(num == -1) {
								num = map[row][col];
							}else {
								if(num == map[row][col]) {
									q.add(num*2);
									num = -1;
								}else {
									q.add(num);
									num = map[row][col];
								}
							}
						}
					}
					if(num != -1) {
						q.add(num);
					}
					
					int c = 0;
					while (!q.isEmpty()) {
						drawMap[row][c++] = q.poll();
					}
				}
				
				break;
			case "right":
				//오른쪽
				for (int row = 0; row < N; row++) {
					int num = -1;
					for (int col = N-1; col >= 0; col--) {
						if(map[row][col] > 0) {
							if(num == -1) {
								num = map[row][col];
							}else {
								if(num == map[row][col]) {
									q.add(num*2);
									num = -1;
								}else {
									q.add(num);
									num = map[row][col];
								}
							}
						}
					}
					if(num != -1) {
						q.add(num);
					}
					
					int c = N-1;
					while (!q.isEmpty()) {
						drawMap[row][c--] = q.poll();
					}
				}
				
				break;
			}
			
			sb.append("#").append(tc).append("\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(drawMap[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}//tc end
		System.out.println(sb);
	}
}
