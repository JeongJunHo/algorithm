package swexpertacademy;

import java.util.Scanner;

public class problem1204 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			int[] jumsu = new int[101];
			
			int tcNum = sc.nextInt();
			
			int[] arr = new int[1000];
			
			for (int i = 0; i < arr.length; i++) {
				arr[i] = sc.nextInt();
			}
			
			for (int j = 0; j < arr.length; j++) {
				jumsu[arr[j]] = jumsu[arr[j]]+1;
			}
			
			int max = 0;
			int maxJumsu = 0;
			
			for (int j = 0; j < jumsu.length; j++) {
				if(max < jumsu[j]) {
					max = jumsu[j];
					maxJumsu = j;
				}else if(max == jumsu[j]) {
					if(maxJumsu < j) {
						maxJumsu = j;
					}
				}
			}
			
			System.out.println("#" + tcNum + " " + maxJumsu);
		}
	}
}
