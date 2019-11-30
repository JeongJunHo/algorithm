package swexpertacademy.sea3282_Knapsack;

import java.util.Scanner;

public class Knapsack {
	static int N, K;
	static int[][] arr, thing;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			K = sc.nextInt();
			thing = new int[N+1][2];
			for (int i = 1; i <= N; i++) {
				thing[i][0] = sc.nextInt();
				thing[i][1] = sc.nextInt();
			}
			arr = new int[N+1][K+1];
			for (int i = 1; i <= N; i++) {
				for (int w = 1; w <= K; w++) {
					if(thing[i][0] > w) {
						arr[i][w] = arr[i-1][w];
					}else {
						arr[i][w] = Math.max(arr[i-1][w-thing[i][0]] + thing[i][1], arr[i-1][w]);
					}
				}
			}
			
			System.out.println("#" + tc + " " + arr[N][K]);
		}
	}
}
