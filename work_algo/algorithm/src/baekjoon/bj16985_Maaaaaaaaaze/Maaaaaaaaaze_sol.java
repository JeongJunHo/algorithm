package baekjoon.bj16985_Maaaaaaaaaze;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Maaaaaaaaaze_sol {
    static int[][][] map = new int[5][5][5];
    //idx번째 층의 2차원배열을 90도 회전시킨다.
    static void rotate(int idx) {
        int[][] tmp = new int[5][5];
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                tmp[j][4-i] = map[idx][i][j];
            }
        }
        map[idx] = tmp;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int h = 0; h < 5; h++) {
            for(int r = 0; r < 5; r++) {
                for(int c = 0; c < 5; c++)
                    map[h][r][c] = sc.nextInt();
            }
        }
        dfs(0);
        System.out.println(min == 987654321 ? -1 : min);
    }
    //idx층을 4번에 걸쳐서 돌려보자.
    static void dfs(int idx) {
        if( idx ==  5) {
            //마지막 층 까지 다해봤다 ㅎㅎ
            //이제 층별로 순서를 마구 바꿔볼까? 순열ㄱ
            perm(0);
            return;
        }
         
        for(int i = 0; i < 4; i++) {
            dfs(idx+1);
            rotate(idx);
        }
    }
    static void perm(int idx) {
        if(idx == 5) {
            //길찾기 시작ㅎ
            gogo();
            return;
        }
        for(int i = idx; i < 5; i++) {
            swap(i, idx);
            perm(idx+1);
            swap(i, idx);
        }
    }
    static void swap(int a, int b) {
        int[][] tmp = map[a];
        map[a] = map[b];
        map[b] = tmp;
    }
     
    static void gogo() {
        //BFS구현. 0,0,0 ~ 4,4,4를 찾아 출동.
        //출발지가 길이 아니라면 못감.
        if( map[0][0][0] != 1 )
            return;
        Queue<Point> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[5][5][5];
        //시작점을 큐에 담고, 방문체크.
        queue.add(new Point(0,0,0,0));
        visited[0][0][0] = true;
        //큐가 빌때까지 반복.
        while( !queue.isEmpty() ) {
            //꺼내서 도착지이면 그 상태의 cnt값들 중 최소값을 기억.
            Point point = queue.poll();
            if(point.h == 4 && point.r == 4 && point.c == 4) {
                min = Math.min(min, point.cnt);
                break;
            }
            //6방으로 이동가능하다면 방문체크하고 큐에 담기.
            for(int i = 0; i < 6; i++) {
                int nh = point.h + dh[i];
                int nr = point.r + dr[i];
                int nc = point.c + dc[i];
                //범위 밖이면 패스
                if( nh < 0 || nr < 0 || nc < 0 || nh >= 5 || nr >= 5 || nc >= 5)
                    continue;
                //길이 있으면서 방문하지 않았다면 큐에 삽입
                if(map[nh][nr][nc] == 1 && !visited[nh][nr][nc]) {
                    visited[nh][nr][nc] = true;
                    queue.add(new Point(nh,nr,nc,point.cnt+1));
                }
            }
        }
    }
    static int min = 987654321;
    //상층 하층 상행 하행 좌열 우열
    static int[] dh = {-1, 1, 0, 0, 0, 0};
    static int[] dr = {0, 0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, 0, -1, 1};
    static class Point{
        int h,r,c;//몇층 몇행 몇열
        int cnt;//몇번 이동했는지.
        Point(int h, int r, int c, int cnt){
            this.h = h;
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
}