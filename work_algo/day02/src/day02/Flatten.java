package day02;

import java.util.Arrays;
import java.util.Scanner;

public class Flatten {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for (int i = 1; i <= 10; i++) {
			//덤프 횟수 입력
			int dump_cnt = sc.nextInt();
			
			int[] box = new int[100];
			for (int j = 0; j < box.length; j++) {
				box[j] = sc.nextInt();
			}

			for (int j = 0; j < dump_cnt; j++) {
				if(box[0] != box[box.length-1]) {
					box[0]++;
					box[box.length-1]--;					
				}
				Arrays.sort(box);
			}
			
			System.out.println("#" + i + " " + (box[box.length-1]-box[0]));
		}
	}
}
