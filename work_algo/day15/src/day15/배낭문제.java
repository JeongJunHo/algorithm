package day15;

import java.util.Scanner;

public class 배낭문제 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt(); //아이템의 갯수. 1 ~ 100
            int K = sc.nextInt(); //배낭의 최대부피. 1 ~ 1000
            int[][] items = new int[N+1][2];
            for(int i = 1; i <= N; i++) {
                items[i][0] = sc.nextInt();
                items[i][1] = sc.nextInt();
            }
            int[][] dp = new int[N+1][K+1];
            for(int i = 1; i < N + 1; i++) {
                //dp테이블을 1번행부터 N번행까지 채워나갑시다.
                //1번행은 첫번째 아이템 한개만 고려해서 배낭을 채우는 가치.
                //2번행은 첫번째 아이템과 두번째 아이템을 고려해서 배낭을 채우는 가치.
                //N번행은 첫번째 + ... + N번쨰 아이템을 고려해서 배낭을 채우는 가치.
                 
                //n번째 아이템을 채울때, 배낭의 무게가 아이템을 담을 수 없는 경우, 위에칸의 가치를 그대로 가져옴.
                //n번째 아이템을 담을 수 있는 경우, 위에 칸에서 지금 아이템의 부피만큼을 제외했을때 가치 + 해당 아이템의 가치와
                //그냥 위에칸의 가치 중 큰 값을 선택
                 
                //모든 배낭의무게를 고려해보면서
                for(int j = 0; j < K+1; j++) {
                    //현재 고려하는 무게가 아이템을 담을 수 없다면 위에칸을 복사
                    if( j < items[i][0] )
                        dp[i][j] = dp[i-1][j];
                    //담을 수 있다면 j부피에서의 최적해 dp[i-1][j]와 
                    //현재 물건을 담을 수 있을만큼 부피를 확보해서 해당 물건을 담았을떄의 가치 dp[i-1][j-부피] + 가치
                    else {
                        dp[i][j] = Math.max( dp[i-1][j], dp[i-1][j - items[i][0]] + items[i][1]);
                    }
                }
            }
            System.out.println("#" + tc + " " + dp[N][K]);	
        }
    }
}







