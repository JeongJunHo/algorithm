package day6;

import java.util.Stack;

public class CalPostfix {
	public static void main(String[] args) {
		String expr = "232*+13*+";
		
		Stack<Integer> st = new Stack<Integer>();
		
		for (int i = 0; i < expr.length(); i++) {
			char word = expr.charAt(i);
			if(Character.isDigit(word)) {
				st.push(word-'0');
			}else {
				int back = st.pop();
				int front = st.pop();
				
				switch (word) {
				case '+':
					st.push(front + back);
					break;
				case '-':
					st.push(front - back);
					break;
				case '*':
					st.push(front * back);
					break;
				case '/':
					st.push(front / back);
					break;
				}
			}
		}
		
		System.out.println(st.pop());
	}
}
