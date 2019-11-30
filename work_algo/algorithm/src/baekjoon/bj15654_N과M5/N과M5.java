package baekjoon.bj15654_N과M5;

import java.util.Arrays;
import java.util.Scanner;

public class N과M5 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int S = sc.nextInt();
		
		int[] arr = new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.parallelSort(arr);
		perm(arr, new boolean[N], new int[S], 0);
	}
	
	static void perm(int[] arr, boolean[] visited, int[] sel, int idx) {
		if(sel.length == idx) {
			for (int num : sel) {
				System.out.print(num + " ");
			}
			System.out.println();
			
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			sel[idx] = arr[i];
			perm(arr, visited, sel, idx+1);
			visited[i] = false;
		}
	}
}
