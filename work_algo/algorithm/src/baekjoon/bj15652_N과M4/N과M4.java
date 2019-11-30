package baekjoon.bj15652_N과M4;

import java.util.Scanner;

public class N과M4 {
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		comb_re(new int[M], 1, 0);
		
		System.out.println(sb);
	}
	static void comb_re(int[] sel, int idx, int s_idx) {
		if(sel.length == s_idx) {
			for (int i = 0; i < sel.length; i++) {
				sb.append(sel[i] + " ");
			}
			sb.append("\n");
			
			return;
		}
		
		for (int i = idx; i <= N; i++) {
			sel[s_idx] = i;
			comb_re(sel, i, s_idx + 1);
		}
	}
}
