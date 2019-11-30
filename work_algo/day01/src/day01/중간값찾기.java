package day01;

import java.util.Arrays;
import java.util.Scanner;

public class 중간값찾기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		//테스트케이스 갯수 입력
		int t = sc.nextInt();
		
		for (int i = 1; i <= t; i++) {
			//테스트케이스 번호 입력
			int tc = sc.nextInt();

			int[] numArray = new int[1000];
			
			//숫자 입력
			for (int j = 0; j < numArray.length; j++) {
				 numArray[j] = sc.nextInt();
			}
			
			int max = 0;
			int cnt = 0;
			for (int num = 0; num < numArray.length; num++) {
				int tmpcnt = 0;
				
				for (int inner = 0; inner < numArray.length; inner++) {
					if(numArray[num] == numArray[inner]) {
						tmpcnt++;
					}
				}
				
				if ((cnt < tmpcnt) || (cnt == tmpcnt && max < numArray[num])) {
					cnt = tmpcnt;
					max = numArray[num];
				}
			}
			
			System.out.println("#" + i + " " + max);
		}
	}
}
