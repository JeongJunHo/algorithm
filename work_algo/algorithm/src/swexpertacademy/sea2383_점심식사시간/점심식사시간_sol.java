package swexpertacademy.sea2383_점심식사시간;

import java.util.PriorityQueue;
import java.util.Scanner;

public class 점심식사시간_sol {
    static class Person implements Comparable<Person>{
        int r, c;
        public Person(int r, int c) {
            this.r = r;
            this.c = c;
            dist = new int[2];
        }
        //계단으로부터의 거리
        int[] dist;
        int sel;//자신에게 배정된 계단 번호
        @Override
        public int compareTo(Person o) {
        	//현재 배정된 계단과의 거리와 비교대상 사람의 배정된 계단과의 거리 오름차순
            return dist[sel] - o.dist[o.sel];
        }
    }
    static class Stair{
        int r, c, height;
        public Stair(int r, int c, int height) {
            this.r = r;
            this.c = c;
            this.height = height;
        }
    }
    static int[][] map;
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            map = new int[N][N];
            int sCnt = 0;
            Stair[] stairs = new Stair[2];
            int pCnt = 0;
            Person[] person = new Person[10];
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                    if( map[i][j] == 0 )
                        continue;
                    else if( map[i][j] == 1 ) 
                        person[pCnt++] = new Person(i,j);
                    else
                        stairs[sCnt++] = new Stair(i, j, map[i][j]);
                }
            }
            for(int i = 0; i < pCnt; i++) {
                for(int j = 0; j < sCnt; j++)
                	//계단과의 거리 계산
                    person[i].dist[j] = Math.abs(person[i].r - stairs[j].r) + Math.abs(person[i].c - stairs[j].c) + 1;
            }
            ans = 987654321;
            
            //사람배열, 계단배열, 인덱스, 사람수
            powerSet(person, stairs, 0, pCnt);
            System.out.println("#" + tc + " " + ans);
        }
    }
    static int ans = 987654321;
    static void powerSet( Person[] person, Stair[] stairs, int idx, int pCnt ) {
        if(idx == pCnt) {
        	//각 계단의 타임테이블
            int[][] timeTable = new int[2][200];
            PriorityQueue<Person> pq = new PriorityQueue<>();
            int max = 0;
            for(int i = 0; i < pCnt; i++) {
                pq.add(person[i]);
            }
            while(!pq.isEmpty()) {
                Person p = pq.poll();
                int from = p.dist[p.sel];
                int to = from + stairs[p.sel].height;
                for(int j = from; j < to; j++){
                    if( timeTable[p.sel][j] == 3) {
                        to++;
                        continue;
                    }
                    timeTable[p.sel][j]++;
                }
                if( max < to )
                    max = to;
            }
            ans = Math.min(ans, max);
            return;
        }
         
        person[idx].sel = 0;
        powerSet(person, stairs, idx+1, pCnt);
        person[idx].sel = 1;
        powerSet(person, stairs, idx+1, pCnt);
    }
}
