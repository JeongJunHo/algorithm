package swexpertacademy.sea6782_현주가좋아하는제곱근놀이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 현주분할정복 {
	static long[] sqrt;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		sqrt = new long[1000001];
		for (int i = 1; i < sqrt.length; i++) {
			sqrt[i] = i * i;
		}
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			long N = Long.parseLong(br.readLine());
			int ans = 0;
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	static int binarySearch(int num) {
		int left = 0;
		int right = sqrt.length-1;
		int middle = 0;
		
		while (left <= right) {
			middle = (left + right) / 2;
			if(num > sqrt[middle]) {
				left = middle + 1;
			}else {
				right = middle - 1;
			}
		}
		
		return left;
	}
}
