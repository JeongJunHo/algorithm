package swexpertacademy.sea1258_행렬찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 행렬찾기 {
	static class Rectangle implements Comparable<Rectangle>{
		int width;
		int height;
		
		public Rectangle(int width, int height) {
			this.width = width;
			this.height = height;
		}

		@Override
		public int compareTo(Rectangle o) {
			int result = (this.width * this.height) - (o.width * o.height);
			if(result == 0) {
				return o.width - this.width;
			}
			
			return result;
		}
		
		
	}
	
	static List<Rectangle> ans = new ArrayList<Rectangle>();
	static int n;
	static int[][] arr;
	static boolean[][] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n][n];
			visited = new boolean[n][n];
			ans.clear();
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(!visited[i][j] && arr[i][j] > 0) {
						int height = recur(i, j, 1, 1, 0);
						int width = recur(i, j, 1, 0, 1);
						
						ans.add(new Rectangle(width, height));
						
						for (int row = i; row < i + height; row++) {
							for (int col = j; col < j + width; col++) {
								visited[row][col] = true;
							}
						}
					}
				}
			}
			
			Collections.sort(ans);
			
			sb.append("#").append(tc).append(" ").append(ans.size()).append(" ");
			for (Rectangle rectangle : ans) {
				sb.append(rectangle.height).append(" ");
				sb.append(rectangle.width).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int recur(int row, int col, int cnt, int addH, int addW) {
		int nr = row + addH;
		int nc = col + addW;
		
		if(posCheck(nr, nc) && arr[nr][nc] > 0) {
			cnt++;
			cnt = recur(nr, nc, cnt, addH, addW);
		}
		
		return cnt;
	}

	static boolean posCheck(int row, int col) {
		return row >= 0 && row < n && col >= 0 && col < n;
	}
}
