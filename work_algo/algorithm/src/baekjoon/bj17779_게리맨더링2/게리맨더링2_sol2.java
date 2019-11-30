package baekjoon.bj17779_게리맨더링2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 게리맨더링2_sol2 {
	static int N, total, ans = Integer.MAX_VALUE;
	static int[][] map;
	static int[][] pos = {{1,-1},{1,1}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				total += map[i][j];
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				solved(i,j);
			}
		}
		
		System.out.println(ans);
	}
	private static void solved(int topR, int topC) {
		for (int left = 1; left < N-1; left++) {
			for (int right = 1; right < N-1; right++) {
				int leftR = topR + pos[0][0] * left;
				int leftC = topC + pos[0][1] * left;
				int rightR = topR + pos[1][0] * right;
				int rightC = topC + pos[1][1] * right;
				
				if(posCheck(leftR, leftC) && posCheck(rightR, rightC)) {
					int bottomR = leftR + pos[1][0] * right;
					int bottomC = leftC + pos[1][1] * right;
					
					if(posCheck(bottomR, bottomC)) {
						int[] sum = new int[5];
						int colSize = topC;
						for (int i = 0; i < leftR; i++) {
							if(i < topR) {
								for (int j = 0; j <= topC; j++) {
									sum[0] += map[i][j];
								}
							}else {
								for (int j = 0; j < colSize; j++) {
									sum[0] += map[i][j];
								}
								colSize--;
							}
						}
						
						colSize = topC;
						for (int i = 0; i <= rightR; i++) {
							if(i < topR) {
								for (int j = N-1; j > topC; j--) {
									sum[1] += map[i][j];
								}
							}else {
								for (int j = N-1; j > colSize; j--) {
									sum[1] += map[i][j];
								}
								colSize++;
							}
						}
						
						colSize = bottomC;
						for (int i = N-1; i >= leftR; i--) {
							if(i > bottomR) {
								for (int j = 0; j < bottomC; j++) {
									sum[2] += map[i][j];
								}
							}else {
								for (int j = 0; j < colSize; j++) {
									sum[2] += map[i][j];
								}
								colSize--;
							}
						}
						
						colSize = bottomC;
						for (int i = N-1; i > rightR; i--) {
							if(i > bottomR) {
								for (int j = N-1; j >= bottomC; j--) {
									sum[3] += map[i][j];
								}
							}else {
								for (int j = N-1; j > colSize; j--) {
									sum[3] += map[i][j];
								}
								colSize++;
							}
						}
						
						sum[4] = total - sum[0] - sum[1] - sum[2] - sum[3];
						Arrays.sort(sum);
						ans = Math.min(ans, sum[4] - sum[0]);
					}
				}
			}
		}
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}
}
