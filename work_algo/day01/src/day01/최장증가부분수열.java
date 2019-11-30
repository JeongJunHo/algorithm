package day01;

import java.util.ArrayList;
import java.util.Scanner;

public class 최장증가부분수열 {
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
			
			ArrayList<Integer> longest = new ArrayList<Integer>();
			for (int j = 0; j < numArray.length; j++) {
				if(longest.size() == 0) {
					longest.add(numArray[j]);
				}else if(longest.get(longest.size()-1) > numArray[j]) {
					longest.set(longest.size()-1, numArray[j]);
				}else {
					longest.add(numArray[j]);
				}
			}
			
			System.out.println("#" + i + " " + longest.size());
			
			/*
			int cnt = 0;
			for (int out = 0; out < numArray.length; out++) {
				int tmpcnt = 0;
				int current = 0;
				
				for (int j = out; j < numArray.length; j++) {
					if(current <= numArray[j]) {
						tmpcnt++;
						current = numArray[j];
					}
				}
				if(cnt <= tmpcnt) {
					cnt = tmpcnt;
				}
			}
			
			System.out.println("#" + i + " " + cnt);
			*/
		}
	}
}
