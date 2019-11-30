package baekjoon.bj1063_킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 킹 {
	static int[][] map = new int[8][8];
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String kingStr = st.nextToken();
		String rockStr = st.nextToken();
		int[] king = new int[2];
		int[] rock = new int[2];
		king[0] = kingStr.charAt(1)-48;
		king[1] = kingStr.charAt(0)-'A';
		rock[0] = rockStr.charAt(1)-48;
		rock[1] = rockStr.charAt(0)-'A';
		
		N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			int[] pos = translate(br.readLine());
			
			int nr = pos[0] + king[0];
			int nc = pos[1] + king[1];
			
			if(posCheck(nr, nc)) {
				if(rock[0] == nr && rock[1] == nc) {
					if(posCheck(rock[0]+pos[0],rock[1]+pos[1])) {
						rock[0] = rock[0]+pos[0];
						rock[1] = rock[1]+pos[1];
						king[0] = nr;
						king[1] = nc;
					}
				}else {
					king[0] = nr;
					king[1] = nc;
				}
			}
		}
		
		System.out.println(((char)(king[1]+'A')) + "" + king[0]);
		System.out.println(((char)(rock[1]+'A')) + "" + rock[0]);
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 1 && row <= 8 && col >= 0 && col < 8;
	}
	
	static int[] translate(String action) {
		int[] pos = null;
		switch (action) {
		case "R":
			pos = new int[] {0,1};
			break;
		case "L":
			pos = new int[] {0,-1};
			break;
		case "B":
			pos = new int[] {-1,0};
			break;
		case "T":
			pos = new int[] {1,0};
			break;
		case "RT":
			pos = new int[] {1,1};
			break;
		case "LT":
			pos = new int[] {1,-1};
			break;
		case "RB":
			pos = new int[] {-1,1};
			break;
		case "LB":
			pos = new int[] {-1,-1};
			break;
		default:
			break;
		}
		
		return pos;
	}
}
