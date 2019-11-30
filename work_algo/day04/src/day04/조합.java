package day04;

import java.util.Arrays;

public class 조합 {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5};
		int[] sel = new int[3];
		comb(arr, sel, 0, 0);
	}
	
	static void comb(int[] arr, int[] sel, int n, int r) {
		//r이 끝까지 가면 다 골라서 끝
		if( r == sel.length) {
			System.out.println(Arrays.toString(sel));
			return;
		}
				
		//n이 끝까지 가면 더 이상 갈데가 없어서 끝
		if( n == arr.length) {
			return;
		}
		
		//sel의 r위치에 arr의 n번째 요소를 담고.
		sel[r] = arr[n];
		//n과 r이 둘다 증가
		comb(arr, sel, n+1, r+1);
		//n만 증가
		comb(arr, sel, n+1, r);
	}
}
