package baekjoon.bj17136_색종이붙이기;

import java.util.Scanner;

public class 색종이붙이기_solution {
	static int[][] map = new int[10][10];
	//색종이는 각 크기별로 다섯개
	static int[] paper = {0, 5, 5, 5, 5, 5};
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++)
				map[i][j] = sc.nextInt();
		}
		
		dfs(0);
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	
	
	//재귀함수의 끝은 색종이가 안남거나 1이 안남거나
	//cnt는 지금까지 붙인 색종이 수
	static void dfs(int cnt) {
		if(cnt >= ans){
            return;
        }
		
		//왼쪽과 위가 1인 위치를 찾아
		int startR = -1;
		int startC = -1;
		out:for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if(map[i][j] == 1) {
					startR = i;
					startC = j;
					break out;
				}
			}
		}
		//없으면 완료. 현재 cnt를 정답에 저장(최소값 유지)
		if(startR == -1 && startC == -1) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		//startR, startC로부터 붙일 수 있는 가장 큰 색종이는 어떤것인지
		int max = 5;
		while (max > 0) {
			boolean isOk = true;
			out:for (int i = 0; i < max; i++) {
				for (int j = 0; j < max; j++) {
					if(startR + i >= 10 || startC + j >= 10 || map[startR+i][startC+j] == 0) {
						isOk = false;
						break out;
					}
				}
			}
			if(isOk)
				break;
			max--;
		}
		
		//있다면 가능한 모든 크기의 색종이로 붙이고 다음 재귀호출 해본다.
		for (int i = max; i > 0; i--) {
			if(paper[i] > 0) {
				//startR,startC부터 max크기 만큼의 정사각형을 0으로 바꾸고
				for (int r = startR; r < startR + i; r++) {
					for (int c = startC; c < startC + i; c++) {
						map[r][c] = 0;
					}
				}
				paper[i]--;
				//재귀호출
				dfs(cnt+1);
				//startR,startC부터 max크기 만큼의 정사각형을 1로 바꾸기.
				for (int r = startR; r < startR + i; r++) {
					for (int c = startC; c < startC + i; c++) {
						map[r][c] = 1;
					}
				}
				paper[i]++;
			}
		}
	}
}
