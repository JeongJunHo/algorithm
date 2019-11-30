package day08;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 카드2_정준호 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int cnt = sc.nextInt();
		
		Queue<Integer> q = new LinkedList<Integer>();
		
		for (int i = 1; i <= cnt; i++) {
			q.add(i);
		}
		
		while (true) {
			if(q.size() > 1) {
				q.poll();
			}else {
				break;
			}
			q.add(q.poll());
			
		}
		
		System.out.println(q.poll());
	}
	
	
}
