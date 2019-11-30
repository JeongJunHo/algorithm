package day3;

import java.util.Arrays;

public class example1 {
	public static void main(String[] args) {
		int[][] arr = new int[5][5];
		int total = 0;
		int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = (int) (Math.random() * 100) + 1;
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				//좌표 (상하좌우) 비교해서 합계에 더하기
				for (int k = 0; k < pos.length; k++) {
					int x = i + pos[k][0];
					int y = i + pos[k][1];
					
					//배열이내인지 검사
					if(x >= 0 && x < arr.length && y >= 0 && y < arr.length) {
						int minus = arr[i][j] - arr[x][y];
						//절대값 합계에 더하기
						total += Math.abs(minus);
					}
				}
			}
		}
		
		System.out.println(total);
	}
}
