package baekjoon.bj15686_치킨_배달;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//위치 좌표를 저장하기위한 클래스
class Location{
	int r;
	int c;
	
	Location(int row, int col){
		r = row;
		c = col;
	}
}

public class 치킨_배달 {
	//맵의 크기, 선택할 치킨집의 수
	static int n, m, ans = Integer.MAX_VALUE;
	//지도
	static char[][] map;
	//치킨매장 좌표
	static List<Location> shopList = new ArrayList<Location>();
	//사용할 치킨집 선택용 배열
	static boolean[] choice;
	//집 좌표
	static List<Location> houseList = new ArrayList<Location>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		//0번은 쓰지않는다.
		map = new char[n+1][n+1];
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				char tmp = st.nextToken().charAt(0);
				//집이라면
				if(tmp == '1') {
					//집리스트에 저장
					houseList.add(new Location(i, j));
				//치킨매장이라면
				}else if(tmp == '2'){
					//매장리스트에 저장
					shopList.add(new Location(i, j));
				}
				map[i][j] =  tmp;
			}
		}
		
		//매장 수 만큼의 체크배열 생성
		choice = new boolean[shopList.size()];
		
		//조합생성
		comb(0, 0);
		
		System.out.println(ans);
	}
	
	static void comb(int idx, int cnt) {
		//유지할 치킨집을 선택했다면
		if(cnt == m) {
			int chickenDistance = 0;
			
			for (int i = 0; i < houseList.size(); i++) {
				//각 집에서 가장 가까운 매장 거리를 계산해 치킨거리에 누적
				chickenDistance += nearDistance(houseList.get(i));
			}
			
			//현재 나온 치킨 거리와 비교하여 더 짧다면 치환
			ans = Math.min(ans, chickenDistance);
			
			return;
		}
		
		if(idx == shopList.size()) {
			return;
		}
		
		choice[idx] = true;
		comb(idx+1, cnt+1);
		choice[idx] = false;
		comb(idx+1, cnt);
	}
	
	static int nearDistance(Location loc) {
		int near = Integer.MAX_VALUE;
		
		for (int i = 0; i < shopList.size(); i++) {
			if(choice[i]) {
				int distance = Math.abs(loc.r - shopList.get(i).r) + Math.abs(loc.c - shopList.get(i).c);
				near = Math.min(near, distance);
			}
		}
		
		return near;
	}
}
