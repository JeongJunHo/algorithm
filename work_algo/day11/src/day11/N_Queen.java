package day11;

import java.util.Scanner;

public class N_Queen {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();//체스판의 크기이자 퀸의 수
        int[][] map = new int[N][N];
        backtrack(map, 0);
        System.out.println(result);
        System.out.println("호출횟수 : " +cnt);
    }
    static int result = 0;
    static int cnt = 0;
    static void backtrack(int[][] map, int line) {
        cnt++;
        if(line == map.length) {
            result++;
            return;
        }
         
        //해당 행의 열의 갯수만큼
        for(int i = 0; i < map[line].length; i++) {
            if( isPossible(map, line, i) ) {
                //열의 자리에 퀸을 세워보자.
                map[line][i] = 1;
                backtrack(map, line+1);
                map[line][i] = 0;
            }
        }
    }
    static boolean isPossible(int[][] map, int r, int c) {
        //내 위로 퀸이 있었는지.
        for(int i = r; i >= 0; i--) {
            if(map[i][c] == 1)
                return false;
        }
        //내 왼쪽위로 있는지.
        for(int i = r, j = c; i >= 0 && j >= 0; i--,j--) {
            if(map[i][j]== 1)
                return false;
        }
        //내 오른쪽위로 있는지.  
        for(int i = r, j = c; i >= 0 && j < map[i].length; i--,j++) {
            if(map[i][j]== 1)
                return false;
        }
        return true;
    }
}
