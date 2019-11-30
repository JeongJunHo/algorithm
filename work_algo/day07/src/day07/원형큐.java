package day07;

import java.util.Queue;
import java.util.Scanner;

public class 원형큐 {
	public static void main(String[] args) {
		//적당한 크기의 1차원배열과 front, rear를 준비
		
		//사용자의 입력에 따라 1 삽입 2 삭제 3 isEmpty 4 isFull 로직을 수행하도록
		
		Scanner sc = new Scanner(System.in);
		
		int[] circleQueue = new int[4];
		int rear = 0;
		int front = 0;
		while(true) {
			int sel = sc.nextInt();
			
			if (sel == 1) {
				if(front == (rear+1) % circleQueue.length) {
					System.out.println("Full");
				}else {
					rear = (rear + 1) % circleQueue.length;
					circleQueue[rear] = sc.nextInt();
				}
			}else if (sel == 2) {
				if(front == rear) {
					System.out.println("empty");
				}else {
					front = (front + 1) % circleQueue.length;
					System.out.println(circleQueue[front]);
				}
			}else if (sel == 3) {
				if(rear == front) {
					System.out.println("비었습니다.");
				}
			}else if (sel == 4) {
				if(front == (rear+1) % circleQueue.length) {
					System.out.println("가득찼습니다.");
				}
			}
		}
	}
}
