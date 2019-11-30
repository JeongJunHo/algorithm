package baekjoon.bj15665_N과M11;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class N과M11 {
	static StringBuilder sb = new StringBuilder();
	static Set<String> set = new LinkedHashSet<String>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[] arr = new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		
		perm_re(arr, new int[M], 0);
		
		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
			String str = iterator.next();
			
			sb.append(str).append("\n");
		}
		System.out.println(sb);
	}
	private static void perm_re(int[] arr, int[] sel, int idx) {
		if(sel.length == idx) {
			String str = new String();
			for (int num : sel) {
				str += num + " ";
			}
			set.add(str);
			
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			sel[idx] = arr[i];
			perm_re(arr, sel, idx+1);
		}
	}
}
