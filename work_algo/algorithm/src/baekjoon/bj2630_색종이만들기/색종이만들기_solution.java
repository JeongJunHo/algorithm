package baekjoon.bj2630_색종이만들기;

import java.util.Scanner;

public class 색종이만들기_solution {
    static int[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                map[i][j] = sc.nextInt();
        }
        check(0, 0, N);
        System.out.println(color0);
        System.out.println(color1);
    }
 
    // r,c에서 시작해서 size크기의 정사각형이 하나의 색깔인지 검사.
    // 하나의 색깔이 아닌 경우. 한 변의 크기를 반으로 쪼갠 네개의 사각형으로 재귀호출
    // 위 조건에 한번도 걸리지 않는 경우 자신의 색깔 ++
    static void check(int r, int c, int size) {
        // 하나의 색깔인지 검사 → 첫번째 색깔을 기억하고 있다가 한번이라도 다른 색이 나오면 한가지 색이 아닌 것!
        int color = map[r][c];
        // 지금 검사하는 전체 사각형 영역 중에서 한번이라도 다른색이 발견되면 하나의 색이 아닌 것.
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (map[i][j] != color) {
                    // 이 사각형은 어차피 한덩이가 아니니까. 4덩이로 쪼개서 각각에 대해 다시 검사
                    check(r, c, size / 2);
                    check(r, c + size / 2, size / 2);
                    check(r + size / 2, c, size / 2);
                    check(r + size / 2, c + size / 2, size / 2);
                    // 이 사각형은 글렀어.
                    return;
                }
            }
        }
        // 아니라면 4개의 변의 크기가 반인 사각형에 대해 재귀호출
        // 위 재귀호출에 안걸렸다면 내 색깔 ++
        if (color == 0)
            color0++;
        else
            color1++;
    }
 
    static int color0 = 0;
    static int color1 = 0;
}
