package day3;

import java.util.Arrays;

public class 부분집합 {
	public static void main(String[] args) {
		int[] arr = {1,2,3};
		boolean[] selected = new boolean[3];
		recur2(arr, selected, 0);
	}
	
	public static void recur2(int[] arr, boolean[] selected, int idx) {
		if (idx == arr.length) {
			System.out.println(Arrays.toString(selected));
			return;
		}
		
		selected[idx] = true;
		recur2(arr, selected, idx+1);
		selected[idx] = false;
		recur2(arr, selected, idx+1);
	}
	
	public static void recur(int[] arr, int idx, int sum) {
		if (idx == arr.length) {
			return;
		}
		
		System.out.println(arr[idx]);
		System.out.println(sum);
		recur(arr, idx+1, sum+arr[idx]);
	}
}
