package baekjoon.bj1026_보물;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class 보물 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[] a = new int[n];
		Integer[] b = new Integer[n];
		
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		
		for (int i = 0; i < n; i++) {
			b[i] = sc.nextInt();
		}
		
		Arrays.parallelSort(a);
		Arrays.parallelSort(b, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2 - o1;
			}
		});
		
		int ans = 0;
		
		for (int i = 0; i < n; i++) {
			ans += a[i] * b[i];
		}
		
		System.out.println(ans);
	}
}
