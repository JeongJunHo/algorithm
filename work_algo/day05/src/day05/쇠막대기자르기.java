package day05;

import java.util.Scanner;
import java.util.Stack;

public class 쇠막대기자르기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//테스트케이스 수
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; tc++) {
			//파이프와 레이저 표현식
			String pipe = sc.next();
			
			Stack<Character> stack = new Stack<Character>();
			int cnt = 0;
			
			for (int i = 0; i < pipe.length(); i++) {
				char find = pipe.charAt(i);
				if(find == '(' && pipe.charAt(i+1) == ')') {
					cnt += stack.size();
					i+=1;
				}else if (find == '(') {
					stack.push(find);
					cnt++;
				}else if (find == ')') {
					stack.pop();
				}
			}
			
			System.out.println("#" + tc + " " + cnt);
		}
	}
}
