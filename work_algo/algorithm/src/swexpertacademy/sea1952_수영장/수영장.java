package swexpertacademy.sea1952_수영장;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수영장 {
	static int day, month, threeMonth, year, ans;
	static int[] plan;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			plan = new int[12];
			st = new StringTokenizer(br.readLine());
			day = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken());
			threeMonth = Integer.parseInt(st.nextToken());
			year = Integer.parseInt(st.nextToken());
			ans = year;
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < plan.length; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			
			backTrack(0, 0);
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	private static void backTrack(int m, int sum) {
		if(m >= 12) {
			ans = Math.min(ans, sum);
			return;
		}
		//1일
		backTrack(m+1, sum + plan[m] * day);
		//1개월
		backTrack(m+1, sum + month);
		//3개월
		backTrack(m+3, sum + threeMonth);
	}
}
