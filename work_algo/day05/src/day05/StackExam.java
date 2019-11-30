package day05;

import java.util.Stack;

public class StackExam {
	public static void main(String[] args) {
		String[] ex1 = {"()()((()))","((()((((()()((()())((())))))"};
		
		Stack<String> stack = new Stack<String>();
		
		for (int i = 0; i < ex1.length; i++) {
			String s = ex1[i];
			for (int j = 0; j < s.length(); j++) {
				String tmp = String.valueOf(s.charAt(j));
				if(tmp.equals("(")) {
					stack.push(tmp);
				}else if(tmp.equals(")")) {
					String pop = stack.pop();
					if(!pop.equals("(") || stack.empty()) {
						System.out.println(i + "번째 문자열은 잘못된 괄호묶음");
						break;
					}
				}
			}
			if(stack.empty()) {
				System.out.println("정상");
			}else {
				System.out.println(i + "번째 문자열은 잘못된 괄호! 괄호가 " + stack.size() + "개 남음");
			}
		}
	}
}
