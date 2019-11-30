package day04;

import java.lang.reflect.Array;
import java.util.Arrays;

public class 순열re {
	public static void main(String[] args) {
		int[] arr = {1,2,3};
		
		perm(arr, 0);
	}
	
	static void perm(int[] arr, int idx) {
		if(arr.length == idx) {
			System.out.println(Arrays.toString(arr));
			return;
		}
		
		for (int i = idx; i < arr.length; i++) {
			swap(arr, idx, i);
			perm(arr, idx+1);
			swap(arr, idx, i);
		}
	}
	
	static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}
