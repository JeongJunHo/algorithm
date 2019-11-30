package swexpertacademy.sea6782_현주가좋아하는제곱근놀이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 현주가좋아하는제곱근놀이 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			long N = Long.parseLong(br.readLine());
			int ans = 0;
			
			while (N != 2) {
				double tmp = Math.sqrt(N);
				if(tmp % 1 == 0) {
					N = (long) tmp;
					ans++;
				}else{
					long cut = (long)tmp+1;
					ans += (cut * cut - N);
					N = cut * cut;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
}
