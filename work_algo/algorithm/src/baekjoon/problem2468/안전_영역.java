package baekjoon.problem2468;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Location{
	int r;
	int c;
	
	Location(int r, int c){
		this.r = r;
		this.c = c;
	}
}

//안전 영역
public class 안전_영역 {
	static int max_area = 1, max_height = Integer.MIN_VALUE, min_height = Integer.MAX_VALUE, n;
	static int[][] arr, pos = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		arr = new int[n][n];
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				int tmp = sc.nextInt();
				max_height = Math.max(max_height, tmp);
				min_height = Math.min(min_height, tmp);
				arr[i][j] = tmp;
			}
		}
		
		//물 높이마다 최대 안전구역 수를 구한다.
		for (int i = min_height; i < max_height; i++) {
			bfs(i);
		}
		
		System.out.println(max_area);
	}
	
	//침수값에 대한 너비우선탐색하며 안전영역 수 연산
	static void bfs(int height) {
		//방문체크
		boolean[][] check = new boolean[n][n];
		//안전구역수
		int safe_cnt = 0;
		
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				//아직 방문하지 않았고 침수되지 않았다면
				if(!check[i][j] && arr[i][j] > height) {
					check[i][j] = true;
					//안전구역 증가
					safe_cnt++;
					//좌표값을 저장할 수 있는 타입의 큐 생성
					Queue<Location> q = new LinkedList<Location>();
					//현재 좌표를 큐에 넣는다.
					q.add(new Location(i,j));
					//탐색 시작
					while (!q.isEmpty()) {
						//좌표 값
						Location loc = q.poll();
						
						//좌표값 기준 사방을 검사한다.
						for (int k = 0; k < 4; k++) {
							int nr = loc.r + pos[k][0];
							int nc = loc.c + pos[k][1];
							
							//번위를 벗어나지않고 현재 침수높이보다 높을 경우
							if(posCheck(nr, nc) && arr[nr][nc] > height && !check[nr][nc]) {
								//같은 안전 구역이기 때문에 다시 방문하여 안전구역 수를 늘리지 않도록 체크해준다.
								check[nr][nc] = true;
								q.add(new Location(nr, nc));
							}// posCheck if end
						}// k for end
					}// while end
				}// if end
			}// j for end
		}// i for end
		
		//현재 안전 구역이 가장 많다면 기억
		max_area = Math.max(max_area, safe_cnt);
	}// bfs end
	
	static boolean posCheck(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < n;
	}
}
