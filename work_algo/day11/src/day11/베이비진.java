package day11;

import java.util.Arrays;
import java.util.Scanner;

public class 베이비진 {
	static boolean isbaby;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = 4;
		
		for (int tc = 0; tc < t; tc++) {
			isbaby = false;
			char[] arr = sc.next().toCharArray();
			
			perm(arr, new char[arr.length], 0, new boolean[arr.length]);
			
			System.out.println(isbaby);
		}
		
	}
	
	static void perm(char[] arr, char[] sel, int k, boolean[] visited){
		if(isbaby) {
			return;
		}
		if(k == sel.length) {
			System.out.println(Arrays.toString(sel));
			if(isBabyJin(sel)) {
				isbaby = true;
			}
			
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if(visited[i]) {
				continue;
			}
			visited[i] = true;
			sel[k] = arr[i];
			perm(arr, sel, k+1, visited);
			visited[i] = false;
		}
	}
	
	static boolean isTriple(char[] arr) {
		return arr[0] == arr[1] && arr[0] == arr[2];
	}
	
	static boolean isRun(char[] arr) {
		System.out.println(arr[0] + 1);
		System.out.println(arr[1] + 1);
		return arr[0] + 1 == arr[1] && arr[1] + 1 == arr[2];
	}
	
	static boolean isBabyJin(char[] arr) {
		return (isTriple(Arrays.copyOfRange(arr, 0, 3)) || isRun(Arrays.copyOfRange(arr, 0, 3))) && (isTriple(Arrays.copyOfRange(arr, 3, 6)) || isRun(Arrays.copyOfRange(arr, 3, 6)));
	}
}
