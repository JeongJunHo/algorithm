package day01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class 최장증가부분수열리뷰 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		//테스트 케이스 숫자 입력
		int t = sc.nextInt();
		
		for (int i = 1; i <= t; i++) {
			//수열의 길이
			int n = sc.nextInt();
			
			//수열 길이만큼의 배열 생성
			int[] numArray = new int[n];
			for (int j = 0; j < n; j++) {
				numArray[j] = sc.nextInt();
			}
			
			int[] cntArray = new int[n];
			for (int j = 0; j < cntArray.length; j++) {
				//모두 1로 초기화
				cntArray[j] = 1;
			}
			
			//수열반복
			for (int j = 0; j < numArray.length; j++) {
				//현재값
				int current = numArray[j];
				//현재최장값
				int max = 1;
				//이전값과 비교
				for (int k = 0; k < j; k++) {
					int prev = numArray[k];
					
					if (current >= prev) {
						//최대값보다 이전값이 더 크다면
						if(max <= cntArray[k]) {
							cntArray[j] = cntArray[k] + 1;
							max = cntArray[k] + 1;
						}
					}
				}
				
			}
			for (int j = 0; j < cntArray.length; j++) {
				System.out.println(cntArray[j]);
			}
			
			Arrays.sort(cntArray);
			
			System.out.println("#" + i + " " + cntArray[cntArray.length-1]);
		}
	}
}
