package day02;

import java.util.Arrays;

public class BabyGinGreedy {
	public static void main(String[] args) {
		
//		int[] arr = {1,2,3,4,5,6};
		int[] arr = {1,1,1,1,1,1};
		if( isBabyGin(arr)) {
			System.out.println("BabyGin입니다.");
		}else {
			System.out.println("아닙니다.");
		}
	}
	
	static boolean isBabyGin(int[] arr) {
		int[] count = new int[10];
		int triplet = 0;
		int run = 0;
		
		for (int i = 0; i < arr.length; i++) {
			int cnt = arr[i];
			count[cnt] += 1;
		}
		
		//트리플릿 검사
		for (int i = 0; i < count.length; i++) {
			if(count[i] >= 3) {
				if(count[i] == 6) {
					count[i] -= 6;
					triplet += 2;
				}else {
					count[i] -= 3;
					triplet++;
				}
			}
		}
		
		for (int i = 1; i < count.length-1; i++) {
			if (count[i-1] > 0 && count[i] > 0 && count[i+1] > 0) {
				count[i-1] -= 1;
				count[i] -= 1;
				count[i+1] -= 1;
				
				run++;
			}
		}
		
		boolean isBabyGin = false;
		if(triplet+run == 2) {
			isBabyGin = true;
		}
		
		return isBabyGin;
	}
}
