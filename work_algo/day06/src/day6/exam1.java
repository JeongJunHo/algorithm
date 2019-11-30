package day6;

import java.util.Stack;

public class exam1 {
	public static void main(String[] args) {
//		String str = "2+3*4/5";
//		String str = "2+3+3+3+2+2+2";
//		String str = "2+3*2+1*10";
		String str = "(6+5*(2-8)/2)";
		StringBuilder result = new StringBuilder();
		Stack<Character> st = new Stack<Character>();
		
		//계산식의 길이 만큰 반복
		for (int i = 0; i < str.length(); i++) {
			//문자열 1개 추출 (피연산자,연산자,괄호)
			char word = str.charAt(i);
			
			//열린 괄호라면 스택에 넣는다.
			if(word == '(') {
				st.push(word);
			//닫힌 괄호라면 열린괄호를 만날때까지 스택에서 뺀 값을 출력에 더한다.
			//열린 괄호는 버린다.
			}else if(word == ')') {
				while (true) {
					if(st.peek() != '(') {
						result.append(st.pop());
					}else {
						st.pop();
						break;
					}
				}
			//더하기나 빼기를 만난다면
			}else if(word == '+' || word == '-') {
				//스택이 비지않고 스택의 top이 (일때까지 결과에 스택 pop
				while (!st.empty() && st.peek() != '(') {
					result.append(st.pop());
				}
				//더하기 또는 빼기 push
				st.push(word);
			//곱셈 나눗셈을 만난다면
			}else if(word == '*' || word == '/') {
				//스택이 비지않고 스택의 top이 +,-,( 일때까지 결과에 스택 pop
				while (!st.empty() && st.peek() != '+' && st.peek() != '-' && st.peek() != '(') {
					result.append(st.pop());
				}
				//곱셈 또는 나눗셈 push
				st.push(word);
			//숫자라면 결과값에 붙이기
			}else {
				result.append(word);
			}
		}
		//수식이 끝나면 스택에 남은 잔여 연산자 모두 반환
		while (!st.empty()) {
			result.append(st.pop());
		}
		System.out.println(result.toString());
	}
}
