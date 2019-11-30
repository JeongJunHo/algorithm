package day08;

import java.util.ArrayList;
import java.util.List;

//부분집합의 합
public class 부분집합 {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4};
		powersetSum(arr, 0, 0, new ArrayList<Integer>());
	}
	
	static void powersetSum(int[] arr, int idx, int sum, List<Integer> list) {
		if (idx == arr.length) {
			if(list.size() == 3) {
				System.out.println(sum + " " + list);
			}
			return;
		}
		
		//2가지 경우로 호출
		//한가지는 idx번째의 값을 더하는 경우
		list.add(arr[idx]);
		powersetSum(arr, idx+1, sum + arr[idx], list);
		list.remove((Object)arr[idx]);
		//한가지는 안더하는 경우
		powersetSum(arr, idx+1, sum, list);
	}
}