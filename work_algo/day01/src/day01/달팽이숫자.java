package day01;

import java.util.Scanner;

public class 달팽이숫자 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//테스트 케이스 개수 입력
		int T = sc.nextInt();
		//테스트 케이스 반복
		for (int tc = 1; tc <= T; tc++) {
			//테스트 케이스 입력
			int N = sc.nextInt();
			//테스트 케이스만큼의  다차원 배열 생성
			int[][] array = new int[N][N];
			//배열의 x좌표
			int x = 0;
			//배열의 y좌표
			int y = 0;
			
			int num = 1;
			
			for (int i = 1; i <= N; i++) {
				array[0][i] = num;
				num++;
			}
		}
	}
}
