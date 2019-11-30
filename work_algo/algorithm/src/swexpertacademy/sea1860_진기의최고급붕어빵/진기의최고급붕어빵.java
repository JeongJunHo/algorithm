package swexpertacademy.sea1860_진기의최고급붕어빵;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 진기의최고급붕어빵 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int[] time = new int[11112];
			boolean ans = true;
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				time[Integer.parseInt(st.nextToken())]++;
			}
			
			int fishBread = 0;
			int interval = 0;
			for (int i = 0; i < time.length; i++) {
				if(interval < m) {
					interval++;
				}else {
					interval = 1;
					fishBread += k;
				}
				
				fishBread -= time[i];
				
				if(fishBread < 0) {
					ans = false;
					break;
				}
			}
			
			System.out.println("#" + tc + " " + (ans ? "Possible" : "Impossible"));
		}
	}
}
