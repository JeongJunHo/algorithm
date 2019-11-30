package swexpertacademy;

import java.util.Scanner;

public class problem1940 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			int n = sc.nextInt();
			int length = 0;
			int speed = 0;
			
			for (int i = 0; i < n; i++) {
				int command = sc.nextInt();
				
				switch (command) {
				case 0:
					length += speed;
					break;
				case 1:
					speed += sc.nextInt();
					length += speed;
					break;
				case 2:
					speed -= sc.nextInt();
					if(speed < 0) {
						speed = 0;
					}
					length += speed;
					break;
				}
			}
			
			System.out.println("#" + tc + " " + length);
		}
	}
}
