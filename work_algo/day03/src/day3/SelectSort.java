package day3;

import java.util.Arrays;

public class SelectSort {
	public static void main(String[] args) {
		//배열
		int[] arr = {3,1,0,4,5,2,3,7};
		//배열을 순회
		for (int i = 0; i < arr.length; i++) {
			//해당 순서부터 마지막까지 비교해가며 나보다 작은 수가 보일 경우 자리교체
			for (int j = i; j < arr.length; j++) {
				if(arr[i] > arr[j]) {
					int tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
			}
		}
		
		System.out.println(Arrays.toString(arr));
	}
}
