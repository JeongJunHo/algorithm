package baekjoon.bj13460_구슬_탈출_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 구슬_탈출_2 {
	static class Location{
		int row;
		int col;
		
		public Location(int r, int c) {
			row = r;
			col = c;
		}
	}
	static int n, m, ans = Integer.MAX_VALUE;
	static char[][] map;
	static int[] pr = {-1,1,0,0};
	static int[] pc = {0,0,-1,1};
	static boolean[] usePos = new boolean[4];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new char[n][m];
		
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		dfs(map, usePos, 0, false, false);
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	static void dfs(char[][] arr, boolean[] usePos, int turn, boolean bGoal, boolean rGoal) {
		//현재 최소값보다 더 진행 중이라면 종료
		if(ans <= turn) {
			return;
		}
		
		//11번째까지 와버렸다면 종료
		if(turn > 10) {
			return;
		}
		
		//빨간공만 빠져나갔다면 답안 비교 후 최소값 저장
		if(!bGoal && rGoal) {
			ans = Math.min(ans, turn);
			return;
		}
		
		//파란볼이 빠져나갔다면 종료
		if(bGoal) {
			return;
		}
		
		//4가지 상태공간트리를 만든다. 상하좌우
		for (int i = 0; i < 4; i++) {
			//상태공간트리마다 새로운 진행경로를 만든다.
			boolean[] copyPos = usePos.clone();
			//진행해야할 방향이라면
			if(!copyPos[i]) {
				if(i == 0) {
					copyPos[0] = true;
					copyPos[1] = false;
					copyPos[2] = false;
					copyPos[3] = false;
				}else if(i == 1) {
					copyPos[0] = false;
					copyPos[1] = true;
					copyPos[2] = false;
					copyPos[3] = false;
				}else if(i == 2) {
					copyPos[0] = false;
					copyPos[1] = false;
					copyPos[2] = true;
					copyPos[3] = false;
				}else if(i == 3) {
					copyPos[0] = false;
					copyPos[1] = false;
					copyPos[2] = false;
					copyPos[3] = true;
				}
				
				//상태공간마다 새로운 배열을 가지고 들어간다.
				char[][] copyArr = deepCopy(arr);
				Queue<Location> q = new LinkedList<Location>();
				
				//파란공과 빨간공을 찾는다.
				for (int r = 0; r < n; r++) {
					for (int c = 0; c < m; c++) {
						if(copyArr[r][c] == 'B' || copyArr[r][c] == 'R') {
							q.add(new Location(r, c));
						}
					}
				}
				
				//굴리기 실행
				while (!q.isEmpty()) {
					//현재공의위치
					int row = q.peek().row;
					int col = q.poll().col;
					//진행방향으로 1칸앞의 위치
					int nr = row + pr[i];
					int nc = col + pc[i];
					//진행방향이 공백이라면 자리 스왑 후 다시 진행하기위해 큐에 삽입
					if(copyArr[nr][nc] == '.') {
						copyArr[nr][nc] = copyArr[row][col];
						copyArr[row][col] = '.';
						q.add(new Location(nr,nc));
					//도착지점이라면
					}else if(copyArr[nr][nc] == 'O') {
						//빨간공일 경우 삭제 후 도착체크 후 계속 진행(이후에 파란공도 들어올 수 있기 때문에)
						if(copyArr[row][col] == 'R') {
							copyArr[row][col] = '.';
							rGoal = true;
						//파란공일 경우 삭제 후 도착체크 후 종료(파란공이 들어간 시점에서 이미 끝났기 때문에)
						}else if(copyArr[row][col] == 'B') {
							copyArr[row][col] = '.';
							bGoal = true;
							break;
						}
					//다음 지점에 공이 있으면서 진행방향 2칸 앞이 벽이 아니라면 이후에 진행하기 위해 큐에 삽입
					}else if((copyArr[nr][nc] == 'R' || copyArr[nr][nc] == 'B') && copyArr[nr+pr[i]][nc+pc[i]] != '#') {
						q.add(new Location(row, col));
					}
				}
				//한턴이 진행되었다. 검사를 하러갑시다.
				dfs(copyArr, copyPos, turn+1, bGoal, rGoal);
				
				//빨간공 파란공 탈출여부 초기화
				bGoal = false;
				rGoal = false;
			}
		}
	}
	
	static char[][] deepCopy(char[][] arr){
		char[][] tmp = new char[n][m];
		
		for (int i = 0; i < n; i++) {
			tmp[i] = arr[i].clone();
		}
		
		return tmp;
	}
}
