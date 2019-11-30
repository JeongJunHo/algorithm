package day07;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class QueueTest {
	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedList<>();
		int chu = 20;
		int acc = 0;

		Scanner sc = new Scanner(System.in);
		
		while (chu > 0) {
			sc.nextLine();
			
			if(queue.size() == 0) {
				queue.add(1);
				
				System.out.println(queue.size());
				System.out.println(queue.peek());
				System.out.println(0);
			}else {
				int tmp = queue.poll();
				
				int give = 0;
				if(chu - tmp < 0) {
					give = chu;
				}else {
					give = tmp;
				}
				chu -= give;
				acc += give;
				queue.add(tmp+1);
				queue.add(1);
				System.out.println(queue.size());
				for (Integer integer : queue) {
					System.out.print(integer + " ");
				}
				System.out.println();
				System.out.println(acc);
			}
		}
	}
}
