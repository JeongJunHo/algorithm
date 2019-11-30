package jungol;

import java.util.Scanner;
import java.util.Stack;

public class tower_solution {
	static class Tower{
		int num;
		int height;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		//지금껏 만났던 타워들에 대한 기억정보
		StringBuilder sb = new StringBuilder();
		Stack<Tower> memory = new Stack<Tower>();
		int idx = 1;
		for (int i = 0; i < N; i++) {
			int num = sc.nextInt();
			if(memory.empty()) {
				sb.append(0).append(" ");
			}else {
				//뒤에서부터 앞으로 나가며, 이번에 입력받은 타워보다 작으면 버림
				while(true) {
					if(memory.empty()) {
						sb.append(0).append(" ");
						break;
					}
					else if(memory.peek().height < num) {
						memory.pop();
					}else {
						sb.append(memory.peek().num).append(" ");
						break;
					}
				}
			}
			Tower t = new Tower();
			t.num = idx++;
			t.height = num;
			memory.push(t);
		}
		System.out.println(sb.toString());
	}
}
