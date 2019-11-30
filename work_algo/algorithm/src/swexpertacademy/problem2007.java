package swexpertacademy;

import java.util.Scanner;

public class problem2007 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			String str = sc.next();
			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < str.length(); i++) {
				sb.append(str.charAt(i));
				String substr = str.substring(i+1, i+1+sb.toString().length());
				if(sb.toString().equals(substr)) {
					System.out.println("#" + tc + " " + sb.toString().length());
					break;
				}
			}
		}
	}
}
