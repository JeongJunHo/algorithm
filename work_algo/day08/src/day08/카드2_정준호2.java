package day08;

import java.util.Scanner;

public class 카드2_정준호2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int answer = 0;
		int cnt = sc.nextInt();
		int mul = 1;

		if(cnt > 1) {
			while(cnt >= mul) {
				mul *= 2;
			}
			mul /= 2;
		}else {
			answer = 1;
		}
		
		if(cnt-mul == 0) {
			answer = mul;
		}else {
			answer = (cnt-mul) * 2;
		}
		
		System.out.println(answer);
	}
}
