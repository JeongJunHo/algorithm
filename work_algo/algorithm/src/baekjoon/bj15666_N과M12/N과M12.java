package baekjoon.bj15666_N과M12;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class N과M12 {
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
		
		comb_re(arr, new int[M], 0, 0);
		
		for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
			String str = iterator.next();
			
			sb.append(str).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void comb_re(int[] arr, int[] sel, int idx, int s_idx) {
		if(sel.length == s_idx) {
			String str = new String();
			for (int num : sel) {
				str += num + " ";
			}
			set.add(str);
			
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

