package baekjoon.bj15651_N과_M_3;

import java.util.Scanner;

//N과 M (3)
public class N과_M_3 {
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		perm_re(new int[M], 0);
		
		System.out.println(sb);
	}
	
	static void perm_re(int[] sel, int idx) {
		if (idx == M) {
			for (int i = 0; i < sel.length; i++) {
				sb.append(sel[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			sel[idx] = i;
			perm_re(sel, idx+1);
		}
	}
}