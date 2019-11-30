package day01;

import java.util.Scanner;

public class 원재의메모리복구하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//테스트 케이스 입력
		int T = sc.nextInt();
		for (int i = 1; i <= T; i++) {
			String tc = sc.next();
			char[] arr = tc.toCharArray();
			
			int cnt = 0;
			char prev = '0';
			
			for (int j = 0; j < arr.length; j++) {
				//비트가 바뀌었다면 카운트 1증가
				if(arr[j] == '1' && prev == '0') {
					cnt++;
					prev = '1';
				}else if(arr[j] == '0' && prev == '1') {
					cnt++;
					prev = '0';
				}
			}
			
			System.out.println("#" + i + " " + cnt);
		}
	}
}
