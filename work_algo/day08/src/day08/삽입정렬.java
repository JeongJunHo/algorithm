package day08;

import java.util.Arrays;

public class 삽입정렬 {
	public static void main(String[] args) {
		int[] arr = {69, 10, 30, 2, 16, 8, 31, 22};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	static void sort(int[] arr) {
//		int idx = 0;
//		
//		while (idx < arr.length) {
//			int tmp = arr[idx];
//			for (int i = idx; i >= 0 ; i--) {
//				if(idx != i) {
//					if(tmp < arr[i]) {
//						arr[i+1] = arr[i];
//						arr[i] = tmp;
//					}else {
//						break;
//					}
//				}
//			}
//			idx++;
//		}
		
		for (int i = 1; i < arr.length; i++) {
			int tmp = arr[i];
			int j = 0;
			for (j = i-1; j >= 0 && tmp < arr[j]; j--) {
				arr[j+1] = arr[j];
			}
			arr[j+1] = tmp;
		}
	}
}
