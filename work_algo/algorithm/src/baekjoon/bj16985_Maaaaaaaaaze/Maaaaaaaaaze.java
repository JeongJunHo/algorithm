package baekjoon.bj16985_Maaaaaaaaaze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Maaaaaaaaaze {
	static class Location{
		int height;
		int row;
		int col;
		
		Location(int height, int row, int col){
			this.height = height;
			this.row = row;
			this.col = col;
		}
	}
	
	static int ans;
	//회전 및 층이동을 진행할 배열, 회전을 도울 임시 배열, 오리지널 값을 보관하는 배열
	static int[][][] arr, tmpArr, originArr;
	//방문체크배열
	static boolean[][][] visited;
	//층별 회전 수 누적배열
	static int[] floorCnt;
	//6방위탐색 배열
	static int[][] pos = {{-1,0,0},{1,0,0},{0,-1,0},{0,1,0},{0,0,-1},{0,0,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		//초기화
		arr = new int[5][5][5];
		tmpArr = new int[5][5][5];
		originArr = new int[5][5][5];
		floorCnt = new int[5];
		ans = Integer.MAX_VALUE;
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 5; k++) {
					arr[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		//초기설정값을 보관하기위해 복사
		originArr = deepCopy(arr, originArr);
		
		//각 층을 섞어 나올 수 있는 모든 순열 생성
		perm(new int[5], new boolean[5], 0);
		
		//값이 한번도 바뀌지 않았다면 탈출 실패
		if(ans == Integer.MAX_VALUE) {
			ans = -1;
		}
		
		System.out.println(ans);
	}
	
	static void perm(int[] select, boolean[] selVisited, int idx) {
		//모든 층을 선택했다면
		if(select.length == idx) {
			//셔플된 층 적용
			floorChange(select);
			//현재모양 그대로 탈출시도
			bfs();
			//위에서부터 한바퀴씩돌리면서 1024가지 모양으로 탈출 시도
			while (floorCnt[4] < 4) {
				spinFunc();
				bfs();
			}
			floorCnt[4] = 0;
			
			
			return;
		}
		
		for (int i = 0; i < 5; i++) {
			if(!selVisited[i]) {
				selVisited[i] = true;
				select[idx] = i;
				perm(select, selVisited, idx + 1);
				selVisited[i] = false;
			}
		}
	}
	
	static void floorChange(int[] select) {
		arr = deepCopy(originArr, arr);
		tmpArr = deepCopy(arr, tmpArr);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				arr[i][j] = tmpArr[select[i]][j].clone();
			}
		}
	}
	
	private static void spinFunc() {
		floorCnt[0]++;
		cwArrSpin(0);
		for (int i = 0; i < 4; i++) {
			if(floorCnt[i] == 4) {
				floorCnt[i] = 0;
				floorCnt[i+1]++;
				cwArrSpin(i+1);
			}
		}
	}

	private static void bfs() {
		if(arr[0][0][0] == 1) {
			visited = new boolean[5][5][5];
			Queue<Location> q = new LinkedList<Location>();
			visited[0][0][0] = true;
			q.add(new Location(0, 0, 0));
			
			int step = 0;
			boolean complete = false;
			while (!q.isEmpty() && !complete) {
				int qSize = q.size();
				
				for (int i = 0; !complete && i < qSize; i++) {
					Location loc = q.poll();
					
					for (int j = 0; j < pos.length; j++) {
						int nh = loc.height + pos[j][0];
						int nr = loc.row + pos[j][1];
						int nc = loc.col + pos[j][2];
						
						if(posCheck(nh, nr, nc) && !visited[nh][nr][nc] && arr[nh][nr][nc] == 1) {
							visited[nh][nr][nc] = true;
							q.add(new Location(nh, nr, nc));
							if(nh == 4 && nr == 4 && nc == 4) {
								complete = true;
								break;
							}
						}
					}
				}
				step++;
				if(step >= ans) {
					return;
				}
			}
			
			if(complete) {
				ans = Math.min(ans, step);
			}
		}
	}

	static void cwArrSpin(int floor) {
		tmpArr = deepCopy(arr, tmpArr);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				arr[floor][j][tmpArr.length-1-i] = tmpArr[floor][i][j];
			}
		}
	}
			
	static int[][][] deepCopy(int[][][] origin, int[][][] target) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				target[i][j] = origin[i][j].clone();
			}
		}
		
		return target;
	}
	
	static boolean posCheck(int height, int row, int col) {
		return height >= 0 && height < 5 && row >= 0 && row < 5 && col >= 0 && col < 5;
	}
}
