package day02;

import java.util.Arrays;

public class Bf {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4};
		
		perm(arr, 0);
	}
	
	//첫번째랑 첫번째 바꾸고 다음으로
		//두번째랑 두번째랑 바꾸고 다음으로	
			//세번째랑 세번째랑 바꾸고 다음으로
		//두번째랑 세번째랑 바꾸고 다음으로
			//세번째랑 세번째랑 바꾸고 다음으로	
	//첫번째랑 두번째 바꾸고 다음으로
		
	//첫번째랑 세번째랑 바꾸고 다음으로
	
	static void perm(int[] arr, int idx) {
		if (idx == arr.length) {
			System.out.print(Arrays.toString(arr));
			return;
		}
		for (int i = idx; i < arr.length; i++) {
			//idx(현재위치)랑 i랑 바꾸기
			swap(arr, i, idx);
			perm(arr, idx+1);
			swap(arr, i, idx);
		}
	}
	static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}
