package baekjoon.bj14718_용감한용사진수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 용감한용사진수 {
	static int N, K, ans = Integer.MAX_VALUE;
	static int[][] soldier;
	static int maxStr, maxDex, maxInt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		soldier = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			soldier[i][0] = Integer.parseInt(st.nextToken());
			soldier[i][1] = Integer.parseInt(st.nextToken());
			soldier[i][2] = Integer.parseInt(st.nextToken());
			
			maxStr = Math.max(maxStr, soldier[i][0]);
			maxDex = Math.max(maxDex, soldier[i][1]);
			maxInt = Math.max(maxInt, soldier[i][2]);
		}
		
		for (int str = 0; str <= maxStr; str++) {
			for (int dex = 0; dex <= maxDex; dex++) {
				for (int intelligence = 0; intelligence <= maxInt; intelligence++) {
					int win = 0;
					for (int i = 0; i < soldier.length; i++) {
						if(soldier[i][0] <= str && soldier[i][1] <= dex && soldier[i][2] <= intelligence) {
							win++;
						}
					}
					
					if(win >= K) {
						ans = Math.min(ans, str+dex+intelligence);
					}
				}
			}
		}
		
		System.out.println(ans);
	}
}
