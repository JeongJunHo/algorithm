package day05;

import java.util.EmptyStackException;
/*
 * 
 */
public class MyStack<T> {
	static class MyStackOverflowException extends RuntimeException{
		
	}
	private int top;
	private T[] stack;
	
	public MyStack(int size) {
		top = -1;
		stack = (T[])new Object[size];
	}
	
	public void push(T data) {
		if (top < stack.length-1) {
			top++;
			stack[top] = data;
		}else {
			throw new MyStackOverflowException();
		}
	}
	
	public T pop() {
		if(top < 0) {
			throw new EmptyStackException();
		}
		return stack[top--];
	}
}
