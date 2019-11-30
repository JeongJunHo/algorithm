package baekjoon.bj11048_이동하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 이동하기_recur {
	static int N, M;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		memo = new int[N][M];
		System.out.println(recur(0, 0));
		System.out.println(cnt);
	}

	static int[][] memo;
	static int cnt = 0;
	static int recur(int r, int c) {
		cnt++;
		if(r == N-1 && c == M-1) {
			return arr[r][c];
		}
		if(memo[r][c] != 0)
			return memo[r][c];
		int result = arr[r][c];
		int s1 = 0, s2 = 0;
		if(r + 1 < N) {
			s1 = recur(r + 1, c);
		}
		if(c + 1 < M) {
			s2 = recur(r, c + 1);
		}
		result += Math.max(s1, s2);
		memo[r][c] = result;
		return result;
	}
}
