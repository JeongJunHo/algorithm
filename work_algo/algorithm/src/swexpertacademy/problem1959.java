package swexpertacademy;

import java.util.Scanner;

public class problem1959 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//테스트 케이스 수
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			//최대값 저장
			int max = 0;
			//첫번째 숫자열 길이
			int n = sc.nextInt();
			//두번째 숫자열 길이
			int m = sc.nextInt();
			//첫번째 배열
			int[] a = new int[n];
			//두번째 배열
			int[] b = new int[m];
			
			//첫번째 배열 숫자 입력
			for (int i = 0; i < a.length; i++) {
				a[i] = sc.nextInt();
			}
			
			//두번째 배열 숫자 입력
			for (int i = 0; i < b.length; i++) {
				b[i] = sc.nextInt();
			}
			
			//배열간의 길이 차이
			int gap = Math.abs(a.length-b.length);
			
			//큰 배열과 작은 배열 구분
			int[] big, small;
			
			//a배열이 더 길 경우
			if(a.length > b.length) {
				big = a;
				small = b;
			//a배열이 더 짧거나 같을 경우
			}else {
				big = b;
				small = a;
			}
			
			//길이 차이만큼 반복
			for (int i = 0; i <= gap; i++) {
				//합계 계산용 임시변수
				int tmp = 0;
				//짧은배열 접근 인덱스
				int idx = 0;
				//두개의 배열 중 긴 배열을 순회
				for (int j = 0; j < big.length; j++) {
					//값이 마주보고 현재 짧은배열인덱스값이 배열길이 이내라면
					if(i <= j && idx < small.length) {
						//현재 값과 마주보는 짧은 배열의 수를 곱한다
						int mul = small[idx] * big[j];
						//합계에 누적
						tmp += mul;
						//짧은 배열 인덱스 증가
						idx++;
					//마주보는 값이 없다면 긴 배열의 현재 값을 누적
					}
				}// big for end
				//현재 합계가 최대치보다 크다면 치환
				if(max < tmp) {
					max = tmp;
				}
			}// gap for end
			
			System.out.println("#" + tc + " " + max);
		}// tc for end
	}// main end
}// class end
