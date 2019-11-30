package swexpertacademy.sea3074_입국심사;

import java.util.Scanner;

public class 입국심사 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			//심사대수
			int N = sc.nextInt();
			//사람수
			int M = sc.nextInt();
			//최대심사시간
			int max = Integer.MIN_VALUE;
			//심사대별 시간
			int[] arr = new int[N];
			//심사대 순회
			for (int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
				max = Math.max(max, arr[i]);
			}
			//최소값
			long left = 0;
			//최대값(최대심사시간 * 사람수)
			long right = (long) max * M;
			
			//left가 커질때 까지 (멈춘 순간의 left가 최적의 시간)
			while (left<=right) {
				//중간값
				long mid = (left + right) / 2;
				//인원 누적용 임시 변수
				long tmp = 0;
				//심사대 순회
				for (int i = 0; i < N; i++) {
					//각각의 심사대가 중간값으로 처리할 수 있는 인원수를 누적
					tmp += mid / arr[i];
				}
				//수용할 수 있는 인원이 같거나 초과했다면 최대값 감소
				if(tmp >= M) {
					right = mid - 1;
				//수용할 수 있는 인원이 적다면 최소값 증가
				}else {
					left = mid + 1;
				}
			}
			System.out.println("#" + tc + " " + left);
		}
	}
}