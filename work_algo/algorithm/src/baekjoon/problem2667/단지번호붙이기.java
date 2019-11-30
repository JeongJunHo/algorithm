package baekjoon.problem2667;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class 단지번호붙이기 {
	//지도
	static char[][] arr;
	//상하좌우 좌표
	static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
	//방문체크
	static boolean[][] check;
	//단지 수
	static int group = 0;
	//단지내 아파트 수
	static int size = 0;
	static List<Integer> groupSizeList = new ArrayList<Integer>();
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		//초기화
		arr = new char[n][n];
		check = new boolean[n][n];
		
		//한줄씩 지도에 입력
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.next().toCharArray();
		}
		
		
		//지도를 순회
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				//아파트단지에 포함되지 않은 아파트를 찾았다면
				if(arr[i][j] == '1' && !check[i][j]) {
					//지도 탐색
					dfs(i,j);
					//단지 1개 추가
					group++;
					//현재 단지의 아파트 수를 기억
					groupSizeList.add(size);
					//한 단지가 끝났으므로 아파트 카운트 초기화
					size = 0;
				}
			}
		}
		
		//아파트 수가 적은 단지 먼저 출력하기 위해 정렬
		Collections.sort(groupSizeList);
		
		//단지 수 출력
		System.out.println(group);
		//단지별 아파트 수 출력
		for (Integer num : groupSizeList) {
			System.out.println(num);
		}
	}
	
	//깊이 우선 탐색(깊이(스택), 너비(큐) 중 뭘로 처리해도 상관없을 것 같다. 정답을 찾으면 멈추는 문제가 아니기 때문에)
	static void dfs(int row, int col) {
		//방문처리
		check[row][col] = true;
		//아파트 수를 카운트한다.
		size++;
		
		//상하좌우탐색
		for (int i = 0; i < pos.length; i++) {
			//새로 방문할 row
			int nrow = row + pos[i][0];
			//새로 방문할 column
			int ncol = col + pos[i][1];
			
			//지도를 초과하지않으면서 방문전인 아파트라면
			if(indexCheck(nrow, ncol) && arr[nrow][ncol] == '1' && !check[nrow][ncol]) {
				dfs(nrow, ncol);
			}
		}
	}
	
	//지도를 초과하지는 검사하는 메소드
	static boolean indexCheck(int row, int col) {
		return row >= 0 && row < arr.length && col >= 0 && col < arr.length;
	}
}
