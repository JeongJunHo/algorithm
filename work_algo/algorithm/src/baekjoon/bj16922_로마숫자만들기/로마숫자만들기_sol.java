package baekjoon.bj16922_로마숫자만들기;

import java.util.Arrays;
import java.util.Scanner;

public class 로마숫자만들기_sol {
	static boolean[] nums = new boolean[1001];
	static char[] numbers = { 'I', 'V', 'X', 'L' };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		comb_re(new char[N], 0, 0);
		int cnt = 0;
		for (int i = 0; i < nums.length; i++) {
			if(nums[i]) {
				
				cnt++;
			}
		}
		System.out.println(cnt);
	}

	static void comb_re(char[] sel, int idx, int sidx) {
		if (sidx == sel.length) {
			// 다고름
			System.out.println(Arrays.toString(sel));
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
				default:
					break;
				}
			}
			nums[sum] = true;
			return;
		}
		if (idx == numbers.length) {
			// 더이상 고를게 없음
			return;
		}
		for (int i = idx; i < numbers.length; i++) {
			sel[sidx] = numbers[i];
			comb_re(sel, i, sidx + 1);
		}
	}
}
