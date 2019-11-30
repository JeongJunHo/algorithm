package day07;

import java.util.Scanner;

public class 선형큐 {
	public static void main(String[] args) {
		//1차원 배열 하나와 front, rear 커서로 사용할 변수를 준비.
		//enQueue는 rear+1 시키고 rear 위치에 원소 삽입 rear는 지금까지 중 마지막에 들어온 원소의 위치.
		//deQueue는 front+1 시키고 front 위치의 원소 인출 front는 지금까지 중 마지막에 인출했던 위치.
		
		Scanner sc = new Scanner(System.in);
		
		int[] queue = new int[10000000];
		int front = -1;
		int rear = -1;
		
		while (true) {
			System.out.println("1.enQueue\n2.deQueue\n0.end");
			int sel = sc.nextInt();
			switch (sel) {
				case 1:
					int num = sc.nextInt();
					queue[++rear] = num;
					break;
				case 2:
					if(rear != front) {
						System.out.println(queue[++front]);
					}else {
						System.out.println("큐가 비었습니다.");
					}
					break;
				case 0:
					return;
			}
		}
	}
}
