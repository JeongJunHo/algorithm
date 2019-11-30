package baekjoon.bj11559_뿌요뿌요;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//좌표 클래스
class Location{
	int row;
	int col;
	
	Location(int r, int c){
		row = r;
		col = c;
	}
}

public class PuyoPuyo {
	//뿌요뿌요 맵
	static char[][] map = new char[12][6];
	//방문체크배열
	static boolean[][] check = new boolean[12][6];
	//사방탐색 좌표 조정
	static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
	//폭발여부
	static boolean explosion;
	//연쇄
	static int chain;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//값 입력
		for (int i = 0; i < map.length; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		//폭발이 한번도 일어나지 않을때까지 반복 최초 1번은 그냥 진입
		do {
			//폭발체크 초기화
			explosion = false;
			//방문 초기화
			for (int i = 0; i < check.length; i++) {
				Arrays.fill(check[i], false);
			}
			
			//배열 순회
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					//방문하지 않았고 뿌요가 있는 위치일 경우
					if(!check[i][j] && map[i][j] != '.') {
						//너비탐색 시작
						bfs(new Location(i,j));
					}
				}
			}
			
			//폭발이 일어났을 경우
			if(explosion) {
				//연쇄하나 증가
				chain++;
				//중력 작용
				gravity();
			}
		//연쇄가 일어나지 않았다면 종료
		} while (explosion);
		
		//연쇄가 일어난 횟수 출력
		System.out.println(chain);
	}
	
	static void bfs(Location loc) {
		//폭발이 일어날 수 있는 뿌요들의 좌표를 담을 큐
		Queue<Location> save = new LinkedList<Location>();
		//너비탐색을 진행할 queue
		Queue<Location> queue = new LinkedList<Location>();
		//큐에 뿌요를 담는다.
		queue.add(loc);
		//폭발이 일어날때 뿌요를 지우기 위해 뿌요를 담는다.
		save.add(loc);
		//현재 위치를 방문 체크
		check[loc.row][loc.col] = true;
		//같은 색 수(자신을 포함하기 때문에 1로 시작)
		int cnt = 1;
		
		//큐가 빌때까지 반복
		while (!queue.isEmpty()) {
			//뿌요의 좌표값
			int row = queue.peek().row;
			int col = queue.poll().col;

			//사방탐색
			for (int i = 0; i < pos.length; i++) {
				//새로운 뿌요의 위치
				int nr = row + pos[i][0];
				int nc = col + pos[i][1];
				
				//범위안이며 방문 전이면서 컬러가 같다면
				if(posCheck(nr, nc) && !check[nr][nc] && map[nr][nc] == map[row][col]) {
					//색이 같은 뿌요 1개 증가
					cnt++;
					//방문체크
					check[nr][nc] = true;
					//양쪽의 큐에 모두 담는다.
					queue.add(new Location(nr,nc));
					save.add(new Location(nr,nc));
				}
			}
		}
		
		//같은 색의 뿌요가 4개 이상 모였다면
		if(cnt >= 4) {
			//폭발로 체크해주고
			explosion = true;
			//저장했던 모든 뿌요를 폭발시켜준다.
			while (!save.isEmpty()) {
				map[save.peek().row][save.poll().col] = '.';
			}
		} 
	}
	
	//중력작용
	static void gravity() {
		//같은사이즈의 배열을 만들어준다.
		char[][] tmp = new char[12][6];
		//모든 자리를 .으로 채운다.
		for (int i = 0; i < tmp.length; i++) {
			Arrays.fill(tmp[i], '.');
		}
		
		//원본 뿌요맵을 순회한다.
		for (int i = 0; i < map[0].length; i++) {
			//가장 밑바닥부터 채우기위해 row 선언
			int row = map.length-1;
			//뿌요맵 가장 밑에서부터 순회
			for (int j = map.length-1; j >= 0; j--) {
				//뿌요를 찾았다면
				if(map[j][i] != '.') {
					//가상지도에 가장 밑바닥부터 채운 후 row는 한층위를 바라보게 한다.
					tmp[row--][i] = map[j][i];
				}
			}
		}
		
		//중력이 적용된 지도로 치환.
		map = tmp;
	}
	
	//범위체크
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < 12 && col >= 0 && col < 6;
	}
}
