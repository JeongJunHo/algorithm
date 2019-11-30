package baekjoon.bj15657_N과M8;

import java.util.Arrays;
import java.util.Scanner;

public class N과M8 {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[] arr = new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.parallelSort(arr);
		
		comb_re(arr, new int[M], 0, 0);
		
		System.out.println(sb);
	}
	
	private static void comb_re(int[] arr, int[] sel, int idx, int s_idx) {
		if(sel.length == s_idx) {
			for (int num : sel) {
				sb.append(num + " ");
			}
			sb.append("\n");
			
			return;
		}
		
		if(arr.length == idx) {
			return;
		}
		
		sel[s_idx] = arr[idx];
		comb_re(arr, sel, idx, s_idx+1);
		comb_re(arr, sel, idx+1, s_idx);
	}
}
