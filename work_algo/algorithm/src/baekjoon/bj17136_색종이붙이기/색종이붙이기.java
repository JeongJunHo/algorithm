package baekjoon.bj17136_색종이붙이기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 색종이붙이기 {
	static int ans = 0;
	static int remain;
	static int[][] arr = new int[10][10];
	static int[] paper = new int[6];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 1) {
					remain++;
				}
			}
		}
		
		dfs();
		
		System.out.println(ans);
	}
	static void dfs() {
		if(remain == 0) {
			System.out.println(Arrays.toString(paper));
			
			return;
		}
		
		for (int i = 5; i >= 1; i--) {
			
			if(paper[i] > 0)
				solve(i);
			//종이를 붙인다.
			//재귀 호출한다.
			//종이를 땐다.
		}
	}
	static void solve(int paper) {
		
	}
}
