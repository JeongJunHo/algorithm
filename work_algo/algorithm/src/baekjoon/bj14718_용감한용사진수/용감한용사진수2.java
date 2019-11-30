package baekjoon.bj14718_용감한용사진수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 용감한용사진수2 {
	static int N, K, ans = Integer.MAX_VALUE;
	static int[][] soldier;
	static int[] strArr, dexArr, intArr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		strArr = new int[N];
		dexArr = new int[N];
		intArr = new int[N];
		
		soldier = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			strArr[i] = Integer.parseInt(st.nextToken());
			dexArr[i] = Integer.parseInt(st.nextToken());
			intArr[i] = Integer.parseInt(st.nextToken());
			
			soldier[i][0] = strArr[i];
			soldier[i][1] = dexArr[i];
			soldier[i][2] = intArr[i];
		}
		
		Arrays.parallelSort(strArr);
		Arrays.parallelSort(dexArr);
		Arrays.parallelSort(intArr);
		
		for (int strIdx = 0; strIdx < N; strIdx++) {
			for (int dexIdx = 0; dexIdx < N; dexIdx++) {
				for (int intIdx = 0; intIdx < N; intIdx++) {
					int win = 0;
					for (int i = 0; i < soldier.length; i++) {
						if(soldier[i][0] <= strArr[strIdx] && soldier[i][1] <= dexArr[dexIdx] && soldier[i][2] <= intArr[intIdx]) {
							win++;
						}
					}
					
					if(win >= K) {
						ans = Math.min(ans, strArr[strIdx]+dexArr[dexIdx]+intArr[intIdx]);
					}
				}
			}
		}
		
		System.out.println(ans);
	}
}
