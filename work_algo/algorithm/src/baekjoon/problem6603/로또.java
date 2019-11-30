package baekjoon.problem6603;

import java.util.Scanner;

//로또
public class 로또 {
	static int[] arr;
	static boolean[] check;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k;
		while ((k = sc.nextInt()) != 0) {
			check = new boolean[k];
			arr = new int[k];
			
			for (int i = 0; i < k; i++) {
				arr[i] = sc.nextInt();
			}
			
			comb(0, 0);
			System.out.println();
		}
	}
	
	static void comb(int idx, int cnt) {
		if(cnt == 6) {
			for (int i = 0; i < arr.length; i++) {
				if(check[i]) {
					System.out.print(arr[i] + " ");					
				}
			}
			System.out.println();
			return;
		}
		if(idx == arr.length) {
			return;
		}
		
		check[idx] = true;
		comb(idx+1, cnt+1);
		check[idx] = false;
		comb(idx+1, cnt);
	}
}
