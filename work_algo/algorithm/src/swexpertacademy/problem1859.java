package swexpertacademy;

import java.util.Arrays;
import java.util.Scanner;

public class problem1859 {
	public static void main(String[] args) {
		//입력객체
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			long total = 0;
			int cnt = sc.nextInt();
			
			int[] arr = new int[cnt];
			for (int i = 0; i < cnt; i++) {
				arr[i] = sc.nextInt();
			}
			
			//시작인덱스
			int idx = 0;
			while(true) {
				//최대값
				int max = 0;
				//최대값 인덱스
				int maxIndex = 0;
				//매수액합계
				long acc = 0;
				//매수개수
				int accCnt = 0;
				for (int i = idx; i < arr.length; i++) {
					if(arr[i] > max) {
						max = arr[i];
						maxIndex = i;
					}
				}
				
				for (int i = idx; i < maxIndex; i++) {
					acc += arr[i];
					accCnt++;
				}
				
				total += max * accCnt - acc;
				if(idx != maxIndex) {
					idx = maxIndex;
				}else {
					idx++;
				}
				
				if(idx == arr.length-1) {
					break;
				}
			}
			System.out.println("#" + tc + " " + total);
		}
	}
}
