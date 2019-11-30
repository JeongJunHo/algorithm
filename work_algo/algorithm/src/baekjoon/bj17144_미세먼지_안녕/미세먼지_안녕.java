package baekjoon.bj17144_미세먼지_안녕;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Location{
	int row;
	int col;
	
	Location(int row, int col){
		this.row = row;
		this.col = col;
	}
}

public class 미세먼지_안녕 {
	//원본, 복사본
	static int[][] arr, copyArr;
	//공기청정기 위치
	static List<Location> airCleaner = new ArrayList<Location>();
	//시계방향 이동좌표(시작점 상단) 우하좌상
	static int[][] cw = {{0,1},{1,0},{0,-1},{-1,0}};
	//반시계방향 이동좌표(시작점 하단) 우상좌하
	static int[][] ccw = {{0,1},{-1,0},{0,-1},{1,0}};
	//행, 열, 초
	static int r, c, t;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		arr = new int[r][c];
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				//공기청정기라면 위치저장
				if(tmp == -1) {
					airCleaner.add(new Location(i, j));
				}
				arr[i][j] = tmp;
			}
		}
		
		for (int i = 0; i < t; i++) {
			//미세먼지를 사방으로 퍼트린 후 변화된 배열을 받는다.
			arr = split();
			//공기청정기 가동
			arr_spin();
		}
		
		//남아있는 미세먼지 합계 계산
		int ans = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if(arr[i][j] != -1) {
					ans += arr[i][j];
				}
			}
		}
		
		System.out.println(ans);
	}
	
	//배열돌리기
	static void arr_spin() {
		copyArr = deepCopy(arr);

		int row = airCleaner.get(0).row;
		int col = airCleaner.get(0).col;
		
		
		//공기청정기 상 반시계방향
		int xMove = row;
		int yMove = c - 1;
		
		for (int i = 0; i < 4; i++) {
			int cnt = (ccw[i][0] != 0 ? xMove : yMove);
			
			for (int j = 0; j < cnt; j++) {
				int nr = row + ccw[i][0];
				int nc = col + ccw[i][1];
				if(arr[row][col] != -1 && arr[nr][nc] != -1) {
					copyArr[nr][nc] = arr[row][col];
				}else if(arr[row][col] == -1){
					if(arr[nr][nc] != -1) {
						copyArr[nr][nc] = 0;
					}
				}
				row = nr;
				col = nc;
			}
		}
		
		//공기청정기 하 시계방향
		row = airCleaner.get(1).row;
		col = airCleaner.get(1).col;
		
		xMove = r - row - 1;
		yMove = c - 1;
		
		for (int i = 0; i < 4; i++) {
			int cnt = (cw[i][0] != 0 ? xMove : yMove);
			
			for (int j = 0; j < cnt; j++) {
				int nr = row + cw[i][0];
				int nc = col + cw[i][1];
				if(arr[row][col] != -1 && arr[nr][nc] != -1) {
					copyArr[nr][nc] = arr[row][col];
				}else if(arr[row][col] == -1){
					if(arr[nr][nc] != -1) {
						copyArr[nr][nc] = 0;
					}
				}
				row = nr;
				col = nc;
			}
		}
		
		arr = deepCopy(copyArr);
	}
	
	//깊은 복사
	static int[][] deepCopy(int[][] original) {
		int[][] tmp = new int[r][c];
		
		for (int i = 0; i < original.length; i++) {
			tmp[i] = original[i].clone();
		}
		
		return tmp;
	}
	
	//미세먼지 뿌리기
	static int[][] split(){
		int[][] tmp = new int[r][c];
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if(arr[i][j] >= 5) {
					int cnt = 0;
					for (int k = 0; k < 4; k++) {
						int row = i + cw[k][0];
						int col = j + cw[k][1];
						if(posCheck(row,col) && arr[row][col] != -1) {
							cnt++;
							tmp[row][col] += (arr[i][j] / 5);
						}
					}
					tmp[i][j] += (arr[i][j] - (arr[i][j] / 5) * cnt);
				}else {
					tmp[i][j] += arr[i][j];
				}
			}
		}
		
		return tmp;
	}
	
	//방위체크
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < r && col >= 0 && col < c;
	}
}
