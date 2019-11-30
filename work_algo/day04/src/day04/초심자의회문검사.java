package day04;

import java.util.Scanner;

public class 초심자의회문검사 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			String str = sc.next();
			
			StringBuilder sb = new StringBuilder();
			for (int i = str.length()-1; i >= 0; i--) {
				//뒤에서부터 읽어와 붙인다.
				sb.append(str.charAt(i));
			}
			
			
			if(str.equals(sb.toString())) {
				System.out.println("#" + tc + " " + 1);
			}else {
				System.out.println("#" + tc + " " + 0);
			}
		}
	}
}
