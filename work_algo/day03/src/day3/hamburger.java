package day3;

import java.util.Scanner;

public class hamburger {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		//테스트 케이스 수
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			//재료 갯수
			int materialCnt = sc.nextInt();
			//칼로리제한
			int calorie = sc.nextInt();
			
			int[] material_score = new int[materialCnt];
			int[] material_calorie = new int[materialCnt];
			
			for (int i = 0; i < materialCnt; i++) {
				material_score[i] = sc.nextInt();
				material_calorie[i] = sc.nextInt();
			}
			 
			int max_score = 0;
			for (int i = 0; i < (1<<materialCnt); i++) {
				int sum_cal = 0;
				int sum_score = 0;
				for (int j = 0; j < material_calorie.length; j++) {
					if(((1<<j) & i) != 0) {
						sum_cal += material_calorie[j];
						sum_score += material_score[j];
					}
					
					if(sum_cal >= calorie) {
						break;
					}
				}
				
				if (max_score < sum_score && sum_cal <= calorie) {
					max_score = sum_score;
				}
			}
			
			System.out.println("#" + tc + " " + max_score);
		}

	}
}
