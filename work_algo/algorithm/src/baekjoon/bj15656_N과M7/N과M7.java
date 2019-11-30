package baekjoon.bj15656_N과M7;

import java.util.Arrays;
import java.util.Scanner;

public class N과M7 {
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
		
		perm_re(arr, new int[M], 0);
		
		System.out.println(sb);
	}

	private static void perm_re(int[] arr, int[] sel, int idx) {
		if(sel.length == idx) {
			for (int num : sel) {
				sb.append(num + " ");
			}
			sb.append("\n");
			
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			sel[idx] = arr[i];
			perm_re(arr, sel, idx+1);
		}
	}
}
