package swexpertacademy.sea8567_약수의개수가많은수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 약수의개수가많은수 {
	static int ans;
	static int[] num = new int[100001];
	static int[] divArr = new int[100001];
	static int[] max = new int[100001];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= 100000; i++) {
			num[i] = i;
			divArr[i] = 1;
		}
		
		for (int i = 2; i <= 100000; i++) {
			for (int j = i; j <= 100000; j+=i) {
				if(num[j] % i == 0) {
					divArr[j]++;
				}
			}
		}
		
		max[1] = 1;
		for (int i = 2; i <= 100000; i++) {
			if(divArr[max[i-1]] > divArr[i]) {
				max[i] = max[i-1];
			}else {
				max[i] = i;
			}
		}
		
		
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			int N = Integer.parseInt(br.readLine());
			
			sb.append("#").append(tc).append(" ").append(max[N]).append("\n");
		}
		System.out.println(sb);
	}
}
