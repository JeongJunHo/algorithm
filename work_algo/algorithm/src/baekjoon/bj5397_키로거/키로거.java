package baekjoon.bj5397_키로거;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;

public class 키로거 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			Stack<Character> stack = new Stack<Character>();
			Stack<Character> nextStack = new Stack<Character>();
			StringBuilder sb = new StringBuilder();
			char[] arr = br.readLine().toCharArray();
			
			
			for (int j = 0; j < arr.length; j++) {
				switch (arr[j]) {
				case '<':
					if(!stack.empty()) nextStack.push(stack.pop());
					break;
				case '>':
					if(!nextStack.empty()) stack.push(nextStack.pop());
					break;
				case '-':
					if(!stack.empty()) stack.pop();
					break;
				default:
					stack.push(arr[j]);
					break;
				}
			}
			
			while (!stack.empty()) {
				nextStack.push(stack.pop());
			}
			
			while (!nextStack.empty()) {
				sb.append(nextStack.pop());
			}
			System.out.println(sb);
		}
	}
}
