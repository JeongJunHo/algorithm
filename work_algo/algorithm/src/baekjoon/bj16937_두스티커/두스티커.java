package baekjoon.bj16937_두스티커;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 두스티커 {
	static int H, W, N, ans;
	static boolean[][] map;
	static Location[] sticker;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new boolean[H][W];
		N = Integer.parseInt(br.readLine());
		sticker = new Location[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			sticker[i] = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			sticker[i].area = sticker[i].row * sticker[i].col;
		}
		
		Arrays.sort(sticker);
		
		comb(new Location[2], 0, 0);
		
		System.out.println(ans);
	}
	
	private static void comb(Location[] sel, int idx, int s_idx) {
		if(sel.length == s_idx) {
			//현재 나온 답과 비교하여 큰 값으로 치환
			ans = Math.max(ans, firstSt(sel));
			//돌려서 한번더 진행
			int tmp = sel[0].row;
			sel[0].row = sel[0].col;
			sel[0].col = tmp;
			ans = Math.max(ans, firstSt(sel));
			
			return;
		}
		
		if(idx == N) {
			return;
		}
		
		sel[s_idx] = sticker[idx];
		comb(sel, idx+1, s_idx+1);
		comb(sel, idx+1, s_idx);
	}
	
	static int firstSt(Location[] sel) {
		int area = 0;
		
		int height = H - sel[0].row;
		int width = W - sel[0].col;
		//초과하지않는다면
		if(height >= 0 && width >= 0) {
			//넓이 저장
			area = sel[0].area;
			
			//남은 공간으로 두번째장 진행
			int secondArea = secondSt(height, width, sel[1].row, sel[1].col);
			if(secondArea == 0) {
				secondArea = secondSt(height, width, sel[1].col, sel[1].row);
			}
			
			//뒤집어서 진행해도 0이라면
			if(secondArea == 0) {
				area = 0;
			}else {
				area += secondArea;
			}
		}
		
		return area;
	}
	
	static int secondSt(int remainH, int remainW, int row, int col) {
		//밑부분과 오른쪽에 붙일 수 있는지 계산
		int bottomH = remainH - row;
		int bottomW = W - col;
		int rightH = H - row;
		int rightW = remainW - col;
		
		//밑부분 검사
		if((bottomH >= 0 && bottomW >= 0) || (rightH >= 0 && rightW >= 0)) {
			return row * col;
		}
		
		return 0;
	}

	static class Location implements Comparable<Location>{
		int row;
		int col;
		int area;
		
		public Location(int r, int c) {
			row = r;
			col = c;
		}
		
		@Override
		public String toString() {
			return row + " " + col + " " + area;
		}
		
		@Override
		public int compareTo(Location o) {
			return o.area - this.area;
		}
	}
}
