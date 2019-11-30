package baekjoon.problem7569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 토마토_3차원 {
	//토마토 상자
	static int[][][] arr;
	//위층아래층상하좌우 좌표
	static int[][] pos = {{-1,0,0},{1,0,0},{0,-1,0},{0,1,0},{0,0,-1},{0,0,1}};
	//익지않은 토마토 수
	static int greenCnt;
	//익힌 토마토 수(익혀가면서 누적)
	static int ripeCnt;
	//익은 토마토의 좌표
	static Queue<int[]> ripePos = new LinkedList<int[]>();
	//익지않은 토마토 좌표
	static Queue<int[]> greenPos = new LinkedList<int[]>();
	//소요일수
	static int day;
	//박스가 꽉 차있는지
	static boolean isFull = true;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//한줄의 입력을 token형태로 변환
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//가로 길이
		int m = Integer.parseInt(st.nextToken());
		//세로 길이
		int n = Integer.parseInt(st.nextToken());
		//높이 길이
		int h = Integer.parseInt(st.nextToken());
		
		//상자 생성 n과 m 순서 주의 이번문제는 가로가 먼저 입력되었다. h는 높이이므로 가장 앞으로 넣는다.
		arr = new int[h][n][m];
		//박스에 토마토를 채운다.
		for (int z = 0; z < h; z++) {
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					arr[z][i][j] = tmp;
					//덜익은 토마토라면
					if(tmp == 0) {
						//덜익은 토마토 숫자를 기억
						greenCnt++;
						//덜익은 토마토 좌표를 큐에 삽입.
						greenPos.add(new int[]{z,i,j});
					//빈자리라면
					}else if(isFull && tmp == -1) {
						//빈자리가 있는 박스라는 걸 기억해둔다.
						isFull = false;
					//익은 토마토라면
					}else if(tmp == 1) {
						//익은 토마토의 좌표를 큐에 삽입한다.
						ripePos.add(new int[]{z,i,j});
					}
				}// j for end
			}// i for end			
		}//z for end
		
		//상자가 중간이 비어있다면 너비탐색을 하기전 불가능한 상자인지 검사
		if(!isFull && !PossibleCheck()) {
			System.out.println("-1");
		}else {
			//너비 우선 탐색 시작
			bfs();
			System.out.println(day);
		}
	}
	
	//Queue를 활용한 Breadth First Search(너비 우선 탐색) 재귀하지 않는다.
	static void bfs() {
		//토마토를 모두 익혔는지 체크(토마토가 처음부터 모두 익었을 경우를 대비해서 false 말고 삼항연사자를 넣어줬다.)
		//처음부터 모두 다 익었을 경우 어차피 큐가 비어있기 때문에 안해줘도 상관은 없다.
		boolean complete = (greenCnt == ripeCnt ? true : false);
		//토마토를 모두 익히거나 큐가 모두 비어버릴때까지 반복
		while (!complete && !ripePos.isEmpty()) {
			//현재 익은 토마토 수
			int currentRipe = ripePos.size();
			for (int i = 0; i < currentRipe; i++) {
				//아직 모두 익지 않았다면
				if(!complete) {
					//익은 토마토를 빼낸다.
					int[] ripeTomato = ripePos.poll();
					//6방위 내의 안익은 토마토를 익게 만들고 큐에 삽입한다.
					for (int j = 0; j < pos.length; j++) {
						//위층아래층상하좌우 반복하며 각각의 좌표 획득
						int nzidx = ripeTomato[0] + pos[j][0];
						int nrow = ripeTomato[1] + pos[j][1];
						int ncol = ripeTomato[2] + pos[j][2];
						//상자안이면서 안익은 토마토라면
						if(posCheck(nzidx, nrow, ncol) && arr[nzidx][nrow][ncol] == 0) {
							//토마토를 익히고
							arr[nzidx][nrow][ncol] = 1;
							//익힌 토마토 수를 누적
							ripeCnt++;
							//토마토를 모두 익혔다면 연산중지
							if(ripeCnt == greenCnt) {
								complete = true;
								break;
							}
							//익힌 토마토 큐에 삽입
							ripePos.add(new int[]{nzidx,nrow,ncol});
						}
					}// 6방위 for end
				}else {
					break;
				}
			}// 현재 익은 토마토 수 for end
			
			//모든 수행을 하면 하루가 끝이난다.
			day++;
		}// while end
		
		//모든 토마토를 확인했음에도 익지않은 토마토가 있는지 확인
		if(ripeCnt != greenCnt) {
			day = -1;
		}
	}// bfs end
	
	static boolean posCheck(int zidx, int row, int col) {
		return zidx >= 0 && zidx < arr.length && row >= 0 && row < arr[0].length && col >= 0 && col < arr[0][0].length;
	}
	
	//가능한 상자인지 판단 가능하다면 true 반환
	static boolean PossibleCheck() {
		boolean isPossible = true;
		//덜익은 토마토 모두 순회
		while (!greenPos.isEmpty()) {
			//덜익은 토마토 좌표
			int[] greenTomato = greenPos.poll();
			
			//막힌 방향
			int blocking = 0;
			//상하좌우 검사
			for (int i = 0; i < pos.length; i++) {
				//새로운 좌표
				int nzidx = greenTomato[0] + pos[i][0];
				int nrow = greenTomato[1] + pos[i][1];
				int ncol = greenTomato[2] + pos[i][2];
				//해당 좌표가 범위밖이거나 빈칸일 경우
				if (!posCheck(nzidx, nrow, ncol) || arr[nzidx][nrow][ncol] == -1) {
					//막힌 횟수 카운트
					blocking++;
				}
			}
			
			if(blocking == 6) {
				isPossible = false;
				break;
			}
		}// while end
		
		return isPossible;
	}// possibleCheck end
}// class end
