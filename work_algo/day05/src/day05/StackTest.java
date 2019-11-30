package day05;

import java.util.Scanner;

public class StackTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int top = -1;
		int[] stack = new int[10];
		
		while (true) {
			System.out.println("1. Push(입력 예 : 1 10) \n 2. Pop (입력 예 : 1 ) \n 0. 종료");
			int sel = sc.nextInt();
			if(sel == 0) {
				break;
			}else if(sel == 1) {
				int num = sc.nextInt();
				
				if(top < 9) {
					top++;
					stack[top] = num;
				}else {
					System.out.println("StackOverflowException");
				}
			}else if(sel == 2) {
				if (top >= 0) {
					System.out.println(top);
					top--;	
				}else {
					System.out.println("EmptyStackException");
				}
			}
		}
	}
}
