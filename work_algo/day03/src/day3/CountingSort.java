package day3;

import java.util.Arrays;

public class CountingSort {
	public static void main(String[] args) {
		//자료는 7이하
		int[] arr = {3,1,0,4,5,2,3,7};
		
		//0. 최대치인 자료를 인덱스로 담을 수 있는 크기의 넉넉한 카운트배열을 준비
		int[] count = new int[8];
		//1. 각 자료들의 빈도를 카운트배열에 작서
		for (int i = 0; i < arr.length; i++) {
			//숫자
			int num = arr[i];
			//해당 숫자 카운트
			count[num]++;
		}
		//2. 1을 누적합으로 변경
		int total = 0;
		for (int i = 0; i < count.length; i++) {
			total += count[i];
			count[i] = total;
		}
		//3. 원래 자료배열크기의 배열을 하나 준비 -> 정렬배열 이라고 합시다
		int[] sortArr = new int[arr.length];
		//4. 원래 자료배열을 순회하며, 해당 인덱스의 카운트배열의 값을 1깍고 그 위치의 정렬배열에 배치
		for (int i = 0; i < arr.length; i++) {
			//정렬할 숫자
			int num = arr[i];
			//정렬할 숫자의 누적을 감소(배열의 인덱스는 실제보다 1작음)
			count[num]--;
			//새로운 배열에 현재 선택된 숫자를 카운트배열에서 검색해 
			sortArr[count[num]] = num;
		}
		
		System.out.println(Arrays.toString(sortArr));
	}
}