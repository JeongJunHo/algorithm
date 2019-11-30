package day01;

import java.util.Scanner;

public class 원재의메모리복구하기리뷰 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 0; tc < T; tc++) {
			String input = sc.next();
			char[] in = input.toCharArray();
			char[] sol = new char[in.length];
			
			for (int i = 0; i < sol.length; i++) {
				sol[i] = '0';
			}
			
			int cnt = 0;

			for (int i = 0; i < in.length; i++) {
				if(in[i] != sol[i]) {
					for (int j = 0; j < sol.length; j++) {
						sol[j] = in[i];
					}
					cnt++;
				}
			}
			
			System.out.println("#" + tc + " " + cnt);
		}
	}
}
