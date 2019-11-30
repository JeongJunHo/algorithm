package swexpertacademy.sea4013_특이한자석;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 특이한자석 {
	static int K, ans;
	static int[][] mag;
	static int[] score = {0,1,2,4,8};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			K = Integer.parseInt(br.readLine());
			
			mag = new int[5][8];
			
			for (int i = 1; i <= 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					mag[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < K; i++) {
				ans = 0;
				st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken());
				int wise = Integer.parseInt(st.nextToken());
				
				if(posCheck(idx-1) && mag[idx][6] != mag[idx-1][2]) {
					recur(idx-1, wise*-1, -1, 6, 2);
				}
				
				if(posCheck(idx+1) && mag[idx][2] != mag[idx+1][6]) {
					recur(idx+1, wise*-1, +1, 2, 6);
				}
				
				if(wise == 1) {
					cw(mag[idx]);
				}else {
					ccw(mag[idx]);
				}
			}
			
			for (int i = 1; i <= 4; i++) {
				if(mag[i][0] == 1) {
					ans += score[i];
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	private static void recur(int idx, int wise, int direction, int myBlade, int targetBlade) {
		int target = idx+direction;
		if(posCheck(target)) {
			if(mag[idx][myBlade] != mag[target][targetBlade]) {
				recur(target, wise*-1, direction, myBlade, targetBlade);
			}
		}
		
		if(wise == 1) {
			cw(mag[idx]);
		}else {
			ccw(mag[idx]);
		}
	}
	
	static boolean posCheck(int idx) {
		return idx >= 1 && idx <= 4;
	}

	static void cw(int[] arr) {
		int[] tmp = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			int idx = (i+1) % 8;
			tmp[idx] = arr[i];
		}
		
		for (int i = 0; i < tmp.length; i++) {
			arr[i] = tmp[i];
		}
	}
	
	static void ccw(int[] arr) {
		int[] tmp = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			int idx = i-1;
			if(idx < 0) idx += 8;
			tmp[idx] = arr[i];
		}
		
		for (int i = 0; i < tmp.length; i++) {
			arr[i] = tmp[i];
		}
	}
}
