package day04;

import java.util.Arrays;
import java.util.Scanner;

public class sudoku {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			boolean isSudoku = true;
			int arr[][] = new int[9][9];
			
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr.length; j++) {
					arr[i][j] = sc.nextInt();
				}
			}

			//가로 세로 동시 검증
			for (int i = 0; i < arr.length; i++) {
				//숫자 사용여부
				boolean[] row_exist = new boolean[10];
				boolean[] col_exist = new boolean[10];
				for (int j = 0; j < arr.length; j++) {
					//처음나온 숫자라면
					if(row_exist[arr[i][j]] == false) {
						row_exist[arr[i][j]] = true;
					//중복된 숫자가 있다면 이미 에러이기때문에 중단
					}else {
						isSudoku = false;
						break;
					}
					
					//처음나온 숫자라면
					if(col_exist[arr[j][i]] == false) {
						col_exist[arr[j][i]] = true;
					//중복된 숫자가 있다면 이미 에러이기때문에 중단
					}else {
						isSudoku = false;
						break;
					}
				}
				//검증 도중 에러를 찾았다면 검사 중단
				if(isSudoku == false) {
					break;
				}
			}
			
			//큐브 검증 (상위 검증을 통과했을 경우에만)
			if(isSudoku) {
				//바깥 큰틀의 시작 인덱스 관리
				for (int row = 0; row < 9; row+=3) {
					for (int col = 0; col < 9; col+=3) {
						//숫자 사용여부
						boolean[] exist = new boolean[10];
						//내부 큐브에서 검사
						for (int i = 0; i < 3; i++) {
							for (int j = 0; j < 3; j++) {
								//큐브 내의 인덱스 조합(기준이 되는 큐브는 계속 움직이기 때문에)
								int nr = row + i;
								int nc = col + j;
								//처음나온 숫자라면
								if(exist[arr[nr][nc]] == false) {
									exist[arr[nr][nc]] = true;
								//중복된 숫자가 있다면 중단
								}else {
									isSudoku = false;
									break;
								}
							}
							//검증 도중 에러를 찾았다면 검사 중단
							if(isSudoku == false) {
								break;
							}
						}// 내부 큐브 i for end
						//검증 도중 에러를 찾았다면 검사 중단
						if(isSudoku == false) {
							break;
						}
					}// 바깥 큰틀 col for end
					//검증 도중 에러를 찾았다면 검사 중단
					if(isSudoku == false) {
						break;
					}
				}// 바깥 큰틀 row for end
			}
			
			if(isSudoku) {
				System.out.println("#" + tc + " " + 1);
			}else {
				System.out.println("#" + tc + " " + 0);
			}
		}// tc for end
	}
}
