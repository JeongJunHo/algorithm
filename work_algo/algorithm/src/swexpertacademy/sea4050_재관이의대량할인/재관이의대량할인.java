package swexpertacademy.sea4050_재관이의대량할인;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 재관이의대량할인 {
	static int ans;
	static int[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			int N = Integer.parseInt(br.readLine());
			arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
			
			int count = 0;
			for (int i = N-1; i >= 0; i--) {
				if(count < 2) {
					ans += arr[i];
					count++;
				}else {
					count = 0;
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
}
