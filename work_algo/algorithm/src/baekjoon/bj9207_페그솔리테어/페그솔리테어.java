package baekjoon.bj9207_페그솔리테어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 페그솔리테어 {
	static int N, minPin, totalPin;
	static char[][] map;
	static int[][] pos = {{1,0},{-1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < N; tc++) {
			minPin = Integer.MAX_VALUE;
			totalPin = 0;
			map = new char[5][9];
			
			for (int i = 0; i < 5; i++) {
				String str = br.readLine();
				for (int j = 0; j < 9; j++) {
					map[i][j] = str.charAt(j);
					if(map[i][j] == 'o') totalPin++;
				}
			}
			br.readLine();
			
			backTrack(0);
			
			sb.append(minPin + " " + (totalPin-minPin) + "\n");
		}
		System.out.println(sb);
	}
	private static void backTrack(int count) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				if(map[i][j] == 'o') {
					for (int k = 0; k < pos.length; k++) {
						int nr = i + pos[k][0];
						int nc = j + pos[k][1];
						
						if(posCheck(nr, nc) && map[nr][nc] == 'o') {
							int myR = nr + pos[k][0];
							int myC = nc + pos[k][1];
							
							if(posCheck(myR,myC) && map[myR][myC] == '.') {
								map[nr][nc] = '.';
								map[i][j] = '.';
								map[myR][myC] = 'o';
								backTrack(count+1);
								map[nr][nc] = 'o';
								map[i][j] = 'o';
								map[myR][myC] = '.';
							}
						}
					}
				}
			}
		}
		
		int pin = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				if(map[i][j] == 'o') pin++;
			}
		}
		
		if(pin < minPin) minPin = pin;
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < 5 && col >= 0 && col < 9;
	}
}
