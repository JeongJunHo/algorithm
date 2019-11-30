package baekjoon.bj17143_낚시왕;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 낚시왕 {
	static class Shark{
		//속도
		int s;
		//방향 1상 2하 3우 4좌
		int d;
		//크기
		int z;

		public Shark(int s, int d, int z) {
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public String toString() {
			return "Shark [s=" + s + ", d=" + d + ", z=" + z + "]";
		}
	}
	
	//행, 열, 상어 수
	static int R, C, M, ans;
	static Shark[][] map;
	//0번 안씀
	static int[][] pos = {{0,0},{-1,0},{1,0},{0,1},{0,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new Shark[R+1][C+1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			map[r][c] = new Shark(s, d, z);
		}

		//낚시왕이동
		for (int i = 1; i <= C; i++) {
			//상어낚기
			for (int j = 1; j <= R; j++) {
				if(map[j][i] != null) {
					ans += map[j][i].z;
					map[j][i] = null;
					break;
				}
			}
			
			Shark[][] moveMap = new Shark[R+1][C+1];
			for (int tr = 1; tr <= R; tr++) {
				for (int tc = 1; tc <= C; tc++) {
					if(map[tr][tc] != null) {
						Shark shark = map[tr][tc];
						//상어이동
						int cnt = shark.s;
						//왕복비용
						int roundTripCost = 0;
						//위 아래
						if(shark.d == 1 || shark.d == 2) {
							roundTripCost = (R-1) * 2;
						//오른쪽 왼쪽
						}else if(shark.d == 3 || shark.d == 4) {
							roundTripCost = (C-1) * 2;
						}
						//만약 속력이 왕복을 1회 이상 할 수 있다면 간소화
						if(cnt >= roundTripCost) {
							cnt %= roundTripCost;
						}
						
						int nr = tr;
						int nc = tc;
						while(cnt > 0) {
							nr += pos[shark.d][0];
							nc += pos[shark.d][1];
							
							//초과한 경우 다시 반대로 방향 바꿔서 한칸 + 새로 한칸 더 
							if(!posCheck(nr, nc)) {
								if(shark.d % 2 == 0) shark.d -= 1;
								else shark.d += 1;
								
								nr += pos[shark.d][0]*2;
								nc += pos[shark.d][1]*2;
							}
							
							//한턴 종료
							cnt--;
						}
						
						//이미 상어가 있을 경우 더 큰경우에만 삽입
						if(moveMap[nr][nc] != null) {
							if(moveMap[nr][nc].z < shark.z) {
								moveMap[nr][nc] = shark;
							}
						//아닐 경우 그냥 삽입
						}else {
							moveMap[nr][nc] = shark;
						}
					}
				}
			}
			
			map = moveMap;
		}
		
		System.out.println(ans);
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 1 && row <= R && col >= 1 && col <= C;
	}
}
