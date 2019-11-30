package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class tower {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		int[] receive = new int[n];
		
		Stack<Integer> st = new Stack<Integer>();
		
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(tokenizer.nextToken());
		}
		
		for (int i = arr.length-1; i >= 0; i--) {
			//스택이 비었다면 입력
			if(st.empty()) {
				st.push(i);
			//스택이 채워져 있다면
			}else {
				//스택이 비워질때까지
				while (!st.empty()) {
					//더 높은 탑을 만났다면
					if(arr[st.peek()] < arr[i]) {
						//수신타워로 등록 후 스택에서 삭제
						receive[st.pop()] = i+1;
					}else {
						break;
					}
				}
				st.push(i);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (Integer i : receive) {
			sb.append(i).append(" ");
		}
		System.out.println(sb.toString());
	}
}
