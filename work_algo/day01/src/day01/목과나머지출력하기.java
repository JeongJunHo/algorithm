package day01;

import java.util.Scanner;

public class 목과나머지출력하기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//첫번째 줄에는 테스트케이스의 갯수가 들어온다
		int T = sc.nextInt();
		//테스트케이스의 갯수만큼 문제가 주어진다
		for (int tc = 1; tc <= T; tc++) {
			//각 테스트케이스에는 두개의 숫자가 띄어쓰기로 구분되어 주어진다
			int num1 = sc.nextInt();
			int num2 = sc.nextInt();

			int q = num1 / num2;
			int r = num1 % num2;
			System.out.println("#" + tc + " " + q + " " + r);
		}
	}
}
