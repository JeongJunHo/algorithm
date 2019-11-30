package baekjoon.bj15649_N과_M_1;

import java.util.Scanner;

public class N과M1 {
	static boolean[] visited;
	static int[] arr;
	static int n, m;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = i+1;
		}
		visited = new boolean[n];
		
		perm(new int[m], 0);
	}
	
	static void perm(int[] select, int idx) {
		if(select.length == idx) {
			for (int i = 0; i < select.length; i++) {
				System.out.print(select[i] + " ");
			}
			System.out.println();
			
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				select[idx] = arr[i];
				perm(select, idx+1);
				visited[i] = false;
			}
		}
	}
}
