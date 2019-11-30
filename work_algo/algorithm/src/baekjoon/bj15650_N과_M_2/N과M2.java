package baekjoon.bj15650_N과_M_2;

import java.util.Scanner;

public class N과M2 {
	static int[] arr;
	static int n, m;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		arr = new int[n];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i+1;
		}
		
		comb(new int[m], 0, 0);
	}
	
	static void comb(int[] select, int idx, int s) {
		if(s == select.length) {
			for (int i = 0; i < select.length; i++) {
				System.out.print(select[i] + " ");
			}
			System.out.println();
			
			return;
		}
		
		if(idx == arr.length) {
			return;
		}
		
		select[s] = arr[idx];
		comb(select, idx+1, s+1);
		select[s] = arr[idx];
		comb(select, idx+1, s);
	}
}
