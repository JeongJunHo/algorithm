package day11;

import java.util.Arrays;

public class 재귀 {
	public static void main(String[] args) {
		int[] arr = {1,2,3};
		
		recur(arr,0);
		System.out.println("=====powerset=======");
		powerset(arr, new boolean[arr.length], 0);
		System.out.println("======combination======");
		combination(arr, new int[2], 0, 0);
		System.out.println("======comb_dup=======");
		comb_dup(arr, new int[2], 0, 0);
		System.out.println("======dupPerm========");
		dupPerm(arr, new int[2], 0);
		System.out.println("======perm========");
		perm(arr, new int[2], 0, new boolean[arr.length]);
	}
	
	static void perm(int[] arr, int[] sel, int k, boolean[] visited) {
		if(k == sel.length) {
			System.out.println(Arrays.toString(sel));
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if(visited[i]) {
				continue;
			}
			sel[k] = arr[i];
			visited[i] = true;
			perm(arr, sel, k+1, visited);
			visited[i] = false;
		}
	}
	
	static void dupPerm(int[] arr, int[] sel, int k) {
		if(k == sel.length) {
			System.out.println(Arrays.toString(sel));
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			sel[k] = arr[i];
			dupPerm(arr, sel, k+1);
		}
	}
	
	static void combination(int[] arr, int[] sel, int idx, int k) {
		if(k == sel.length) {
			System.out.println(Arrays.toString(sel));
			return;
		}
		
		if(idx == arr.length) {
			return;
		}
		
		sel[k] = arr[idx];
		combination(arr, sel, idx+1, k+1);
		combination(arr, sel, idx+1, k);
	}
	
	static void comb_dup(int[] arr, int[] sel, int idx, int k) {
		if(k == sel.length) {
			System.out.println(Arrays.toString(sel));
			return;
		}
		
		if(idx == arr.length) {
			return;
		}
		
		sel[k] = arr[idx];
		comb_dup(arr, sel, idx, k+1); //뽑는경우
		comb_dup(arr, sel, idx+1, k); //안뽑는경우
	}
	
	static void powerset(int[] arr, boolean[] sel, int idx) {
		if(arr.length == idx) {
			for (int i = 0; i < arr.length; i++) {
				if(sel[i]) {
					System.out.print(arr[i]);
				}
			}
			System.out.println();
			return;
		}
		
		sel[idx] = true;
		powerset(arr, sel, idx+1);
		sel[idx] = false;
		powerset(arr, sel, idx+1);
	}
	
	static void recur(int[] arr, int idx) {
		if(arr.length == idx) {
			return;
		}
		
		System.out.println(arr[idx]);
		recur(arr, idx+1); 
	}
}
