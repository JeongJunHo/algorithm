package swexpertacademy.sea1494_사랑의카운슬러;

import java.util.Scanner;

public class 사랑의카운슬러_solution {
    static class Pos{
        int x;
        int y;
        Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static Pos[] worms;
    static long sumX;
    static long sumY;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            worms = new Pos[N];
            sumX = 0;
            sumY = 0;
            for(int i = 0; i < N; i++) {
                worms[i] = new Pos(sc.nextInt(), sc.nextInt());
                sumX += worms[i].x;
                sumY += worms[i].y;
            }
            comb(new Pos[N/2], 0, 0);
            System.out.println(min);
        }
    }
    static long min = Long.MAX_VALUE;
    static void comb(Pos[] sel, int idx, int s_idx) {
        if( sel.length == s_idx ) {
            //다 고른거
            int sx = 0;
            int sy = 0;
            for(int i = 0; i < sel.length; i++) {
                sx += sel[i].x;
                sy += sel[i].y;
            }
            long x = sumX - 2*sx;
            long y = sumY - 2*sy;
            long res = x*x + y*y;
            min = Math.min(min, res);
            return;
        }
        if( idx == N ) {
            //끝난거
            return;
        }
        sel[s_idx] = worms[idx];
        comb(sel, idx+1, s_idx+1);
        comb(sel, idx+1, s_idx);
    }
}
