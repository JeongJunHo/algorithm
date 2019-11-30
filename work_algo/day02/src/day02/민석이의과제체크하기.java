package day02;

import java.util.Scanner;

public class 민석이의과제체크하기 {
	//테스트케이스 수 = T
	//수강생의 수 = N
	//과제를 제출한 사람의 수 = K
	//과제를 제출한 사람의 번호 K개만큼 공백으로 구분하여 입력
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//테스트케이스 수
		int T = sc.nextInt();
		
		for (int i = 1; i <= T; i++) {
			//수강생의 수
			int N = sc.nextInt();
			//과제를 제출한 사람의 수
			int K = sc.nextInt();
			//과제를 제출한 사람의 번호 K개
			int[] arr = new int[K];
			for (int j = 0; j < K; j++) {
				arr[j] = sc.nextInt();
			}

			System.out.print("#" + i);
			//전체 수강생 순회
			for (int j = 1; j <= N; j++) {
				//과제를 제출했는지 조회
				boolean comp = false;
				for (int num = 0; num < arr.length; num++) {
					//같은 번호가 있다면 완료
					if(j == arr[num]) {
						comp = true;
						break;
					}
				}
				if (!comp) {
					System.out.print(" " + j);
				}
			}
			System.out.println();
		}
	}
}
