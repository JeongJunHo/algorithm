package baekjoon.bj13565_침투;

import java.util.Scanner;

public class 침투 {
	//상하좌우 좌표
	final static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
	//전도 비전도 배열
	static int[][] arr;
	//전류가 끝까지 흐르는지 판단하는 변수
	static boolean result = false;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//M줄
		int m = sc.nextInt();
		//N개
		int n = sc.nextInt();
		
		arr = new int[m][n];

		for (int i = 0; i < arr.length; i++) {
			String tmp = sc.next();
			for (int j = 0; j < tmp.length(); j++) {
				//문자 1은 숫자로 변환시 아스키코드로 49이다. -48을 해주어 원래 값으로 변환한다.
				arr[i][j] = tmp.charAt(j) - 48;
			}
		}
		
		//맨윗줄을 돌며 전기를 뿌린다. (재귀)
		for (int i = 0; i < arr[0].length; i++) {
			if(arr[0][i] == 0) {
				bf(0, i);
			}
		}
		
		 
		System.out.println(result ? "YES" : "NO");
	}
	
	static void bf(int row, int col) {
		arr[row][col] = 2;
		
		if(row == arr.length-1) {
			result = true;
			return;
		}

		for (int i = 0; i < pos.length && !result; i++) {
			int nrow = row + pos[i][0];
			int ncol = col + pos[i][1];
			
			if(indexCheck(nrow, ncol) && arr[nrow][ncol] == 0) {
				bf(nrow, ncol);
				
			}
		}
	}
	
	static boolean indexCheck(int row, int col) {
		if(row >= 0 && row < arr.length && col >= 0 && col < arr[0].length) {
			return true;
		}
		return false;
	}
}
