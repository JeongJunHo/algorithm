package day3;

import java.util.Scanner;

public class Ladder1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for (int tc = 0; tc < 10; tc++) {
			int tcNum = sc.nextInt();
			
			int[][] arr = new int[100][100];
			
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			
			int answer = 999;
			
			for (int i = 0; i < arr[0].length; i++) {
				//사다리 시작점을 찾았다면
				if(arr[0][i] == 1) {
					int row = 0;
					int col = i;
					
					while (true) {
						//왼쪽
						if(col > 0 && arr[row][col-1] == 1) {
							while (col > 0 && arr[row][col-1] == 1) {
								col--;
							}
							row++;
						//오른쪽
						}else if(col < arr[row].length-1 && arr[row][col+1] == 1) {
							while (col < arr[row].length-1 && arr[row][col+1] == 1) {
								col++;
							}
							row++;
						//아래
						}else if (row < arr.length-1 && arr[row+1][col] != 0) {
							row++;
						}
							
						if(row == 99) {
							if(arr[row][col] == 2) {
								answer = i;
							}
							break;
						}
					}
				}
				
				//출구를 찾았다면 종료
				if (answer != 999) {
					break;
				}
			}
			
			System.out.println("#" + tcNum + " " + answer);
		}
	}
}
