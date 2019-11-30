package day07;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 암호생성기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for (int tc = 1; tc <= 10; tc++) {
			int tcNum = sc.nextInt();
			Queue<Integer> q = new LinkedList<Integer>();
			
			for (int i = 0; i < 8; i++) {
				q.add(sc.nextInt());
			}
			
			boolean check = true;
			while (check) {
				for (int i = 1; i <= 5; i++) {
					int tmp = q.poll();
					tmp -= i;
					if(tmp <= 0) {
						q.add(0);
						check = false;
						break;
					}else {
						q.add(tmp);
					}
				}
			}
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("#").append(tcNum).append(" ");
			for (Integer integer : q) {
				sb.append(integer).append(" ");
			}
			
			System.out.println(sb);
		}
	}
}
