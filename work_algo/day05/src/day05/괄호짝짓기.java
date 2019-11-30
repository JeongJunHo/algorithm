package day05;

import java.util.Scanner;
import java.util.Stack;

public class 괄호짝짓기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for (int tc = 1; tc <= 10; tc++) {
			int length = sc.nextInt();
			String str = sc.next();
			int check = 1;
			String start = "({[<";
			Stack<Character> stack = new Stack<Character>();
			
			for (int i = 0; i < length; i++) {
				char bracket = str.charAt(i);
				if(start.indexOf(bracket) != -1) {
					stack.push(bracket);
				}else {
					char popc = stack.pop();
					//짝이 맞지 않는다면
					if(popc + 1 != bracket && popc + 2 != bracket) {
						check = 0;
						break;
					}
				}
			}
			
			if(!stack.empty()) {
				check = 0;
			}
			
			System.out.println("#" + tc + " " + check);
		}
	}
}
