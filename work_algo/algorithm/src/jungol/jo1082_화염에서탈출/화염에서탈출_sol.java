package jungol.jo1082_화염에서탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 화염에서탈출_sol {
    static class Pos{
        int r, c, time;
        Pos(int r, int c, int time){
            this.r = r;
            this.c = c;
            this.time = time;// 이 자료구조를 지섭이나 불의 위치를 사용할때만 사용될 변수
        }
    }
    static int[][] map; //변형된 지도를 저장할 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine().trim());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        //맵은 문자열로 생겼지만.. 우리는. 정수배열로 변형해서.
        //불의 위치(시간별), 바위의 위치만 지도에 그리자.
         
        //지섭이의 위치는 따로 변수에 관리
        map = new int[R][C];
        Queue<Pos> queue = new LinkedList<>(); //지섭이 이동 큐
        Queue<Pos> fireQueue = new LinkedList<>(); //불 확살 큐
        for(int i = 0; i < R; i++) {
            String str = br.readLine().trim();
            for(int j = 0; j < C; j++) {
                switch( str.charAt(j) ) {
                case 'D':
                    //목적지
                    map[i][j] = -9;
                    break;
                case 'S':
                    //지섭이의 위치를 기억해야됨.
                    queue.add(new Pos(i,j,1));
                    break;
                case '*':
                    map[i][j] = 1; //1분째에 존재하는 불.
                    //불의 위치를 기억해야됨.
                    fireQueue.add(new Pos(i,j,1));
                    break;
                case 'X':
                    map[i][j] = -1;
                    break;
                }
            }
        }
         
        //불 먼저 확산.  
        while( ! fireQueue.isEmpty() ) {
            Pos nowFire = fireQueue.poll();
            for(int i = 0; i < 4; i++) {
                int nr = nowFire.r + dr[i];
                int nc = nowFire.c + dc[i];
                if( nr < 0 || nc < 0 || nr >= R || nc >= C)
                    continue;
                if( map[nr][nc] == 0 ) {
                    fireQueue.add(new Pos(nr, nc, nowFire.time+1));
                    map[nr][nc] = nowFire.time+1;
                }
            }
        }
        int ans = 0;
        out:while( !queue.isEmpty() ) {
            Pos nowMe = queue.poll();
             
            for(int i = 0; i < 4; i++) {
                int nr = nowMe.r + dr[i];
                int nc = nowMe.c + dc[i];
                if( nr < 0 || nc < 0 || nr >= R || nc >= C)
                    continue;
                //지섭이는.. -9(목적지)이거나 0(아무것도 없는곳)이거나 내 시간보다 큰 숫자(아직은 불이 안난곳) 만 이동가능
                if( map[nr][nc] == -9 || map[nr][nc] == 0 || map[nr][nc] > nowMe.time+1 ) {
                    if( map[nr][nc] == -9 ) {
                        ans = nowMe.time;
                        break out;
                    }
                    queue.add(new Pos(nr,nc, nowMe.time+1));
                    map[nr][nc] = nowMe.time;
                }
            }
        }
        System.out.println(ans == 0 ? "impossible" : ans);
         
//      for(int i = 0; i < R; i++) {
//          System.out.println(Arrays.toString(map[i]));
//      }
    }
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

}
