package baekjoon.bj1193_분수찾기;

import java.util.Scanner;

public class 분수찾기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int cnt = 0;
		int step = 1;
		
		int X = sc.nextInt();
		while (true) {
			if(step % 2 == 1) {
				for (int i = step; i >= 1; i--) {
					cnt++;
					if(cnt == X) {
						System.out.println(i + "/" + (step-i+1));
						return;
					}
				}
			}else {
				for (int i = 1; i <= step; i++) {
					cnt++;
					if(cnt == X) {
						System.out.println(i + "/" + (step-i+1));
						return;
					}
				}
			}
			step++;
		}
	}
}
