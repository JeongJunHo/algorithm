package baekjoon.bj14659_한조서열정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 한조서열정리 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int ans = 0;
		int max = 0;
		int current = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int pick = Integer.parseInt(st.nextToken());
			if(max > pick) {
				current++;
			}else {
				max = pick;
				ans = Math.max(ans, current);
				current = 0;
			}
		}
		
		if(current > 0) {
			ans = Math.max(ans, current);
		}
		
		System.out.println(ans);
	}
}
