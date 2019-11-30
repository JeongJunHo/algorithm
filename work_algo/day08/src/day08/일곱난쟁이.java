package day08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class 일곱난쟁이 {
	static boolean play = true;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] arr = new int[9];
		
		for (int i = 0; i < 9; i++) {
			arr[i] = sc.nextInt();
		}
		
		powerSet(arr, 0, 0, new ArrayList<Integer>());
	}
	
	static void powerSet(int[] arr, int idx, int sum, List<Integer> list) {
		if(idx >= arr.length) {
			if(sum == 100 && play && list.size() == 7) {
				play = false;
				Collections.sort(list);
				for (Integer i : list) {
					System.out.println(i);
				}
			}
			return;
		}
		
		list.add(arr[idx]);
		powerSet(arr, idx+1, sum + arr[idx], list);
		list.remove((Object)arr[idx]);
		powerSet(arr, idx+1, sum, list);
	}
}
