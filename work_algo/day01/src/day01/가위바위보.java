package day01;

import java.util.Scanner;

public class 가위바위보 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//두 사용자의 입력을 받는다. 가위 1 바위 2 보 3
		int A = sc.nextInt();
		int B = sc.nextInt();
		
		if(A > B) {
			if(A == 3) {
				if(B == 1) {
					System.out.println("B");
				}else {
					System.out.println("A");
				}
			}else {
				System.out.println("A");				
			}
		}else {
			if(B == 3) {
				if(A == 1) {
					System.out.println("A");
				}else {
					System.out.println("B");
				}
			}else {
				System.out.println("B");
			}
		}
	}
}
