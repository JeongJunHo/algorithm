package day07;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Paper {
	int idx;
	int score;
	
	Paper(int i, int s){
		idx = i;
		score = s;
	}
}

public class 프린터큐2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			//나의 순서
			int seq = 1;
			//문서의 수
			int n = sc.nextInt();
			//문서의 위치
			int m = sc.nextInt();
			//문서의 중요도
			Queue<Paper> q = new LinkedList<Paper>();
			//문서 삽입
			int idx = 0;
			for (int i = 0; i < n; i++) {
				q.add(new Paper(idx++, sc.nextInt()));
			}
			
			while (!q.isEmpty()) {
				int max = 0;
				
				for (int i = 0; i < q.size(); i++) {
					if(max < q.peek().score) {
						max = q.peek().score;
					}
					q.add(q.poll());
				}

				while (true) {
					if(q.peek().score == max) {
						if(q.peek().idx == m) {
							q.clear();
							break;
						}else {
							seq++;
							q.poll();
							break;
						}
					}else {
						q.add(q.poll());
					}
				}
			}
			
			System.out.println(seq);
		}
	}
}
