package swexpertacademy.sea7206_숫자게임;

import java.util.Arrays;
import java.util.Scanner;

public class 숫자게임_sol {
    public static void main(String[] args) {
        Arrays.fill(memo, -1);
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            ans = 0;
//          solve(N, 0);
            System.out.println("#" + tc + " " + solve(N));
        }
    }
     
     
    static int solve(int N) {
        if( N < 10 )
            return 0;
        String s = "" + N;
        int len = s.length() - 1;
        int maxCnt = 0;
        for(int i = 1; i < 1<<len; i++ ) {
            //0은 안쪼갬 1은 쪼갬.
            //안쪼갤때 * 10 해나가고 쪼갤때 누적곱.
            int mul = 1;//누적곱 위한 변수
            int num = s.charAt(0)-'0';//맨 앞에 자리수로 시작.
            for(int j = 0; j < len; j++) {
                if( (i & 1<<j) == 0) {
                    //안쪼개면, 기존의 수를 10배 늘리고 현재 자리수를 더해줌
                    num = num * 10 + s.charAt(j+1)-'0';
                }
                else {
                    mul *= num;
                    num = s.charAt(j+1)-'0';
                }
            }
            //마지막 자릿수 누적곱.
            mul *= num;
            int cnt;
            if( memo[mul] != -1 ) {
                cnt = memo[mul];
            }
            else {
                cnt = solve(mul);
                memo[mul] = cnt;
            }
            maxCnt = Math.max(maxCnt, cnt);
        }
        return maxCnt + 1;
    }
    static int[] memo = new int[100000];
     
     
    static int ans = 0;
    static void solve(int N, int cnt) {
        if( N < 10 ) {
            ans = Math.max(ans, cnt);
            return;
        }
        if( N >= 10 ) {
            solve(N/10 * (N%10), cnt+1);
        }
        if( N >= 100 ) {
            solve(N/100 * (N%100), cnt+1);
            solve(N/100 * (N%100/10) * (N%10), cnt+1);
        }
        if( N >= 1000 ) {
            solve(N/1000 * (N%1000), cnt+1);//  천
            solve(N/1000 * (N%1000/100) * (N%100), cnt+1); // 천과 백
            solve(N/1000 * (N%1000/10) * (N%10), cnt+1); // 천과 십
            solve(N/1000 * (N%1000/100) *  (N%100/10) * (N%10), cnt+1); // 천과 백과 십
        }
        if( N >= 10000 ) {
            //만 , 만십, 만백, 만천, 만천백, 만천십, 만백십, 만천백십
            solve(N/10000 * (N%10000), cnt+1); //만
            solve(N/10000 * (N%10000/10) * (N%10), cnt+1); //만십
            solve(N/10000 * (N%10000/100) * (N%100), cnt+1); //만백
            solve(N/10000 * (N%10000/1000) * (N%1000), cnt+1); //만천
            solve(N/10000 * (N%10000/1000) * (N%1000/100) * (N%100), cnt+1); //만천백
            solve(N/10000 * (N%10000/1000) * (N%1000/10) * (N%10), cnt+1); //만천십
            solve(N/10000 * (N%10000/100) * (N%100/10) * (N%10), cnt+1); //만백십
            solve(N/10000 * (N%10000/1000) * (N%1000/100) * (N%100/10) * (N%10), cnt+1); //만천백십
        }
    }
}
