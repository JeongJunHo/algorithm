package day15;

import java.util.Arrays;
import java.util.Scanner;
 
public class 배낭문제_메모 {
    static int N;
    static int K;
    static int[][] memo;
    static int[] weights;
    static int[] values;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            K = sc.nextInt();
            weights = new int[N];
            values = new int[N];
            memo = new int[N][K+1];
            for(int i = 0; i < N; i++) {
                weights[i] = sc.nextInt();
                values[i] = sc.nextInt();
            }
            for(int i = 0; i < N; i++)
                Arrays.fill(memo[i], -1);
            System.out.println("#" + tc + " " + dfs(0, 0));
            
            for (int i = 0; i < N; i++) {
				for (int j = 0; j <= K; j++) {
					System.out.print(memo[i][j] + " ");
				}
				System.out.println();
			}
        }
    }
    static int dfs(int idx, int weight) {
        if(idx == N)
            return 0;
        if(memo[idx][weight] != -1)
            return memo[idx][weight];
        if( weight + weights[idx] > K )
            memo[idx][weight] = dfs(idx+1, weight);
        else
            memo[idx][weight] = Math.max( dfs(idx+1, weight) , values[idx] + dfs(idx+1, weight + weights[idx]));
        return memo[idx][weight];
    }
}