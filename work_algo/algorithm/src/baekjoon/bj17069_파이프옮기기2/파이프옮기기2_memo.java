package baekjoon.bj17069_파이프옮기기2;

import java.util.Scanner;

public class 파이프옮기기2_memo {
	static class Point {
		int r;
		int c;
		int dir;

		Point(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}

	static int N;
	static boolean[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				map[i][j] = sc.nextInt() == 0 ? true : false;
		}
		memo = new long[N][N][3];
		System.out.println(dfs(new Point(0,1,0)));
	}

	static long[][][] memo;

	static long dfs(Point p) {
		if (p.r == N - 1 && p.c == N - 1) {
			return 1;
		}
		if( memo[p.r][p.c][p.dir] != 0 )
			return memo[p.r][p.c][p.dir];
		long result = 0;

		switch (p.dir) {
		case 0:
			if( p.c + 1 < N && map[p.r][p.c+1] )
				result += dfs(new Point(p.r, p.c + 1, 0));
			if( p.r + 1 < N && p.c + 1 < N && map[p.r + 1][p.c] && map[p.r][p.c + 1] && map[p.r + 1][p.c + 1])
				result += dfs(new Point(p.r + 1, p.c + 1 , 2));
			break;
		case 1:
			if( p.r + 1 < N && map[p.r + 1][p.c] )
				result += dfs(new Point(p.r + 1, p.c, 1));
			if( p.r + 1 < N && p.c + 1 < N && map[p.r + 1][p.c] && map[p.r][p.c + 1] && map[p.r + 1][p.c + 1])
				result += dfs(new Point(p.r + 1, p.c + 1 , 2));
			break;
		case 2:
			if( p.c + 1 < N && map[p.r][p.c+1] )
				result += dfs(new Point(p.r, p.c + 1, 0));
			if( p.r + 1 < N && map[p.r + 1][p.c] )
				result += dfs(new Point(p.r + 1, p.c, 1));
			if( p.r + 1 < N && p.c + 1 < N && map[p.r + 1][p.c] && map[p.r][p.c + 1] && map[p.r + 1][p.c + 1])
				result += dfs(new Point(p.r + 1, p.c + 1 , 2));
			break;
		}
		memo[p.r][p.c][p.dir] = result;
		return result;
	}
}




