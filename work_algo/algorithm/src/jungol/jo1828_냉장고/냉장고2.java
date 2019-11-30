package jungol.jo1828_냉장고;

import java.util.Arrays;
import java.util.Scanner;

public class 냉장고2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[10271];
		
		int n = sc.nextInt();
		
		for (int i = 0; i < n; i++) {
			int minTemp = sc.nextInt();
			int maxTemp = sc.nextInt();
			
			if(minTemp < 0) {
				minTemp = 270 - minTemp;
			}else {
				minTemp += 270;
			}
			
			if(maxTemp < 0) {
				maxTemp = 270 - maxTemp;
			}else {
				maxTemp += 270;
			}
			
			for (int j = minTemp; j <= maxTemp; j++) {
				arr[j] = arr[j] + 1;
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
		}
		System.out.println();
	}
}
