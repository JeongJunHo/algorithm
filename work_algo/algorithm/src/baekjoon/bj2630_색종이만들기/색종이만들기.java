package baekjoon.bj2630_색종이만들기;

import java.util.Scanner;

public class 색종이만들기 {
	static int N;
	static int[][] arr;
	static boolean[][] visited;
	static int w, b;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		arr = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		recur(0, 0, N);
		System.out.println(w);
		System.out.println(b);
	}
	
	static void recur(int row, int col, int n) {
		int div = n / 2;
		
		//좌상
		count(row, row + div, col, col + div, div);
		//우상
		count(row, row + div, col + div, col + n, div);
		//좌하
		count(row + div, row + n, col, col + div, div);
		//우하
		count(row + div, row + n, col + div, col + n, div);
	}
	
	static void count(int r_start, int r_end, int c_start, int c_end, int n) {
		
		boolean check = true;

		int tmp = -1;
		for (int i = r_start; check && i < r_end; i++) {
			for (int j = c_start; j < c_end; j++) {
				if(tmp == -1) {
					tmp = arr[i][j];
					continue;
				}
				if(tmp != arr[i][j]) {
					check = false;
					break;
				}
			}
		}
		
		if(check) {
			if(tmp == 1) b++;
			else w++;
		}else {
			recur(r_start, c_start, n);
		}
	}
}
