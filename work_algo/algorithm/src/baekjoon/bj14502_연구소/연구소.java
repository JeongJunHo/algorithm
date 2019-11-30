package baekjoon.bj14502_연구소;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class 연구소 {
	//세로
	static int n;
	//가로
	static int m;
	//최초의 안전구역 수
	static int safeZone;
	//안전구역 좌표 리스트
	static List<int[]> safeList = new ArrayList<int[]>();
	//바이러스 좌표 리스트
	static List<int[]> virusList = new ArrayList<int[]>();
	//상하좌우 좌표
	static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
	//최대로 안전지대를 살린 수
	static int max = Integer.MIN_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//세로크기 입력
		n = sc.nextInt();
		//가로크기 입력
		m = sc.nextInt();
		//연구소 생성
		int[][] arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int tmp = sc.nextInt();
				arr[i][j] = tmp;
				//안전구역이라면 좌표기억
				if(tmp == 0) {
					safeZone++;
					safeList.add(new int[] {i,j});
				//바이러스라면 좌표기억
				}else if(tmp == 2) {
					virusList.add(new int[] {i,j});
				}
			}
		}
		
		//3개의 기둥을 놓을 수 있는 모든 조합 실행
		powerSet(arr, 0, 0);
		
		//최대로 살린 안전지대 수 출력
		System.out.println(max);
	}
	
	static void powerSet(int[][] arr, int cnt, int idx) {
		//기둥 3개를 모두 세웠다면 실행
		if(cnt == 3) {
			//최초의 안전지역 수(기둥 3개를 세웠으므로 3을 뺀다.)
			int safeCnt = safeZone-3;
			//bfs 형태로 바이러스가 퍼져나가도록 큐를 사용
			Queue<int[]> queue = new LinkedList<int[]>();
			//큐에 바이러스 좌표를 모두 넣는다.
			for (int[] is : virusList) {
				queue.add(is);
			}
			
			//바이러스를 모두 사용할때까지(큐가 모두 빌때까지) 
			while(!queue.isEmpty()) {
				//바이러스 좌표
				int row = queue.peek()[0];
				int col = queue.peek()[1];
				
				//상하좌우 보정 좌표
				for (int i = 0; i < pos.length; i++) {
					//바이러스가 퍼질 새로운 좌표
					int nrow = row + pos[i][0];
					int ncol = col + pos[i][1];
					
					//좌표가 연구소를 넘어가지않고 안전지대라면 
					if(posCheck(nrow, ncol) && arr[nrow][ncol] == 0) {
						//바이러스 전염
						arr[nrow][ncol] = 2;
						//해당 바이러스도 확산해나가야 하기 때문에 큐에 삽입
						queue.add(new int[] {nrow,ncol});
						//안전지대 1감소
						safeCnt--;
					}
				}
				//현재 바이러스는 임무를 다했으므로 삭제
				queue.poll();
			}
			
			//현재 살린 안전지대가 가장 크다면 최대값에 치환
			if(max < safeCnt) {
				max = safeCnt;
			}
			
			return;
		}
		//기둥 3개를 세우기 전 안전지대를 모두 지나쳐버렸다면 종료
		if(idx == safeList.size()) {
			return;
		}
		
		//안전지대 좌표
		int row = safeList.get(idx)[0];
		int col = safeList.get(idx)[1];
		
		//2차원배열은 내장메소드로 깊은 복사를 할 수 없기때문에 커스텀 메소드를 사용하여 깊은 복사를 실행한다. (주소값이 다른 배열을 사용하기위해)
		int[][] copyArr = deepCopy(arr);
		//벽을 세우지않고 다음 안전지대로 이동
		powerSet(copyArr, cnt, idx+1);
		//다시한번 같은 함수에 깊은 복사 실행
		copyArr = deepCopy(arr);
		//해당 안전지대에 벽을 세운다.
		copyArr[row][col] = 1;
		//벽을 세웠으므로 횟수를 1회 카운트하고 다음 안전지대로 이동
		powerSet(copyArr, cnt+1, idx+1);
	}
	
	//깊은 복사를 하기위한 커스텀 메소드
	static int[][] deepCopy(int[][] origin){
		int[][] arr = new int[n][m];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = origin[i].clone();
		}
		
		return arr;
	}
	
	//좌표가 연구소 크기를 초과하는지 판단하는 메소드
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < n && col >= 0 && col < m;
	}
}
