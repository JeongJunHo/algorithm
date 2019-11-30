package day11;

import java.util.Arrays;

public class 퀵소트 {
	
	public static void main(String[] args) {
		int[] arr = {7,5,3,4,6,6};
		
		System.out.println(Arrays.toString(arr));
		
		quickSort(arr, 0, arr.length-1);
		
		System.out.println(Arrays.toString(arr));
	}
	
	static void quickSort(int[] arr, int l, int r) {
		if(l < r) {
			int s = partition_lomuto(arr, l, r);
			quickSort(arr, l, s-1);
			quickSort(arr, s+1, r);
		}
	}
	
	static int partition(int[] arr, int l, int r) {
		int p = arr[l];
		int i = l;
		int j = r;
		
		while (i <= j) {
			if (arr[i] <= p) {
				i++;
			}
			if (arr[j] > p) {
				j--;
			}
			if(i < j) {
				swap(arr, i, j);
			}
		}
		
		swap(arr, l, j);
		
		return j;
	}
	
	static int partition_lomuto(int[] arr, int left, int right) {
		int x = arr[right];
		
		//x보다 작은 값들 중 가장 오른쪽을 지키자.
		int i = left;
		for (int j = left; j < right; j++) {
			if(arr[j] <= x) {
				System.out.println(i + " " + j);
				swap(arr,i,j);
				i++;
			}
		}
		swap(arr, i, right);
		
		return i;
	}
	
	static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}
