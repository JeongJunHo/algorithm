package day6;

import java.util.Scanner;
import java.util.Stack;

public class Calc2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for (int i = 1; i <= 10; i++) {
			int size = sc.nextInt();
			String str = sc.next();
			StringBuilder sb = new StringBuilder();
			Stack<Character> charSt = new Stack<Character>();
			Stack<Integer> intSt = new Stack<Integer>();
			
			for (int j = 0; j < size; j++) {
				char word = str.charAt(j);
					
				switch (word) {
					case '+':
						while (!charSt.empty()) {
							sb.append(charSt.pop());
						}
						charSt.push(word);
						break;
					case '*':
						while (!charSt.empty() && charSt.peek() != '+') {
							sb.append(charSt.pop());
						}
						charSt.push(word);
						break;
					//숫자일 경우
					default:
						sb.append(word);
						break;
				}
			}
			
			while (!charSt.empty()) {
				sb.append(charSt.pop());
			}
			
			String result = sb.toString();
			
			for (int j = 0; j < result.length(); j++) {
				char word = result.charAt(j);
				
				if(Character.isDigit(word)) {
					intSt.push(word-'0');
				}else {
					int back = intSt.pop();
					int front = intSt.pop();
					
					switch (word) {
						case '+':
							intSt.push(front + back);
							break;
						case '*':
							intSt.push(front * back);
							break;
					}
				}
			}
			
			System.out.println("#" + i + " " + intSt.pop());
		}
	}
}
