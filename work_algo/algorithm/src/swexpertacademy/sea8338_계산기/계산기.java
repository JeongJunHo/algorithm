package swexpertacademy.sea8338_계산기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 계산기 {
	static int ans, n;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int t = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= t; tc++) {
			ans = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			arr = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
//			Arrays.sort(arr);
			
			int sum = 0;
			
			for (int i = 0; i < arr.length; i++) {
				sum = smartCalc(sum, arr[i]);
			}
			
			ans = Math.max(ans, sum);
			
//			perm(new boolean[n], 0, 0);
			

			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static void perm(boolean[] check, int idx, int sum) {
		if(idx == arr.length) {
			ans = Math.max(ans, sum);
			
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if(!check[i]) {
				check[i] = true;
				perm(check, idx+1, smartCalc(sum, arr[i]));
				check[i] = false;
			}
		}
	}
	
	static int smartCalc(int a, int b) {
		if(a > 1 && b > 1) {
			return a * b;
		}else {
			return a + b;
		}
	}
}
