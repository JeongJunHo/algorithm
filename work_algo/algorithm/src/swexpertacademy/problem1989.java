package swexpertacademy;

import java.util.Scanner;

public class problem1989 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			String str = sc.next();
			int last = str.length()-1;
			int check = 1;
			
			for (int i = 0; i < str.length()/2; i++) {
				if(str.charAt(i) != str.charAt(last-i)) {
					check = 0;
					break;
				}
			}
			
			System.out.println("#" + tc + " " + check);
		}
	}
}
