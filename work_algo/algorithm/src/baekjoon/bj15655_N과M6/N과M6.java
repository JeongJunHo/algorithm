package baekjoon.bj15655_N과M6;

import java.util.Arrays;
import java.util.Scanner;

public class N과M6 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[] arr = new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.parallelSort(arr);
		
		comb(arr, new int[M], 0, 0);
	}
	
	private static void comb(int[] arr, int[] sel, int idx, int s_idx) {
		if(sel.length == s_idx) {
			for (int num : sel) {
				System.out.print(num + " ");
			}
			System.out.println();
			
			return;
		}
		if(arr.length == idx) {
			return;
		}
		
		sel[s_idx] = arr[idx];
		comb(arr, sel, idx+1, s_idx+1);
		comb(arr, sel, idx+1, s_idx);
	}
}
