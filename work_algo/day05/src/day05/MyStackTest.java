package day05;

import java.util.EmptyStackException;
import java.util.Stack;

public class MyStackTest {
	public static void main(String[] args) {
		MyStack<Integer> stack = new MyStack<Integer>(10);
		Stack<Integer> myStack = new Stack<Integer>();
		
		stack.push(10);
		stack.push(10);
		
		try {
			System.out.println(stack.pop());
			System.out.println(stack.pop());
			System.out.println(stack.pop());
		} catch (EmptyStackException e) {
			System.out.println("스택이 비었습니다.");
		} catch (Exception e) {
			System.err.println("dd");
		}
	}
}
