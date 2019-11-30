package baekjoon.bj16922_로마숫자만들기;

import java.util.HashSet;
import java.util.Scanner;

public class 로마숫자만들기 {
	static int N;
	static char[] word = { 'I', 'V', 'X', 'L' };
	static HashSet<Integer> number = new HashSet<Integer>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		comb_re(new char[N], 0, 0);
		
		System.out.println(number.size());
	}

	static void comb_re(char[] sel, int idx, int s_idx) {
		if (sel.length == s_idx) {
			int sum = 0;
			for (int i = 0; i < sel.length; i++) {
				switch (sel[i]) {
				case 'I':
					sum += 1;
					break;
				case 'V':
					sum += 5;
					break;
				case 'X':
					sum += 10;
					break;
				case 'L':
					sum += 50;
					break;
				}
			}
				
			number.add(sum);

			return;
		}

		if (idx == word.length) {
			return;
		}

		for (int i = idx; i < word.length; i++) {
			sel[s_idx] = word[i];
			comb_re(sel, i, s_idx + 1);
		}
	}
}
