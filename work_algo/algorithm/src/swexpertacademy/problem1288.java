package swexpertacademy;

import java.util.Scanner;

public class problem1288 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		boolean[] num = new boolean[10];
		
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			int n = sc.nextInt();
			int total = 0;
			boolean[] isUse = new boolean[10];
			
			while (true) {
				boolean complete = true;
				
				total += n;
				String str = Integer.toString(total);
				
				for (int i = 0; i < str.length(); i++) {
					isUse[str.charAt(i)-48] = true;
				}
				
				for (int i = 0; i < isUse.length; i++) {
					if(!isUse[i]) {
						complete = false;
					}
				}
				
				if(complete) {
					break;
				}
			}
			
			System.out.println("#" + tc + " " + total);
		}
	}
}
