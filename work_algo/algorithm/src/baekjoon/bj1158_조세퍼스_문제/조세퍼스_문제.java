package baekjoon.bj1158_조세퍼스_문제;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 조세퍼스_문제 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Queue queue = new LinkedList<Queue>();
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for (int i = 1; i <= n; i++) {
			queue.add(i);
		}
		
		while (list.size() != n) {
			for (int i = 1; i < k; i++) {
				queue.add(queue.poll());
			}
			list.add((Integer) queue.poll());
		}
		
		System.out.print("<");
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i));
			if(i != list.size()-1) {
				System.out.print(", ");
			}
		}
		System.out.println(">");
	}
}
