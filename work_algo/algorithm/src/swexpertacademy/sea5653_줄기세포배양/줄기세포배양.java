package swexpertacademy.sea5653_줄기세포배양;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 줄기세포배양 {
	static class Location {
		int row;
		int col;
		int life;
		int delay;
		int remainLife;
		boolean active;
		
		public Location(int r, int c, int l, int d) {
			row = r;
			col = c;
			life = l;
			delay = d;
		}
	}
	
	//답, 세로, 가로, 배양시간
	static int ans, n, m, k;
	//지도, 방문이력
	static int[][] map, visited, pos = {{-1,0},{1,0},{0,-1},{0,1}};
	//초기배양세포
	static List<Location> list;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			//초기 배양용기 세로
			n = Integer.parseInt(st.nextToken());
			//초기 배양요기 가로
			m = Integer.parseInt(st.nextToken());
			//배양시간
			k = Integer.parseInt(st.nextToken());
			//답 초기화
			ans = 0;
			//리스트 초기화
			list = new ArrayList<Location>();
			//방문이력 초기화
			visited = new int[n + k * 2][m + k * 2];
			//맵 초기화
			map = new int[n + k * 2][m + k * 2];
			
			for (int i = k; i < k + n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = k; j < k + m; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					if(tmp > 0) {
						list.add(new Location(i, j, tmp, tmp));
						visited[i][j] = -1;
					}
					map[i][j] = tmp;
				}
			}
			
			bfs();
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void bfs() {
		Queue<Location> q = new LinkedList<Location>();
		q.addAll(list);
		
		int hour = 0;
		
		while (hour < k) {
			hour++;
			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				Location loc = q.poll();
				
				if(loc.active) {
					if(loc.remainLife == loc.life) {
						for (int j = 0; j < pos.length; j++) {
							int nr = loc.row + pos[j][0];
							int nc = loc.col + pos[j][1];
							
							if(visited[nr][nc] == 0 || (visited[nr][nc] == hour && map[nr][nc] < loc.life)) {
								map[nr][nc] = loc.life;
								visited[nr][nc] = hour;
								q.add(new Location(nr, nc, loc.life, loc.life));
							}
						}
					}
					if(loc.remainLife > 0) {
						loc.remainLife--;
					}
				}else {
					if(loc.delay > 0) {
						loc.delay--;
					}
					if(loc.delay == 0) {
						loc.remainLife = loc.life;
						loc.active = true;
					}
				}
				
				if((!loc.active || (loc.active && loc.remainLife > 0)) && loc.life == map[loc.row][loc.col]) {
					q.add(loc);
				}
			}
//			System.out.println("===============");
//			print(map);
//			System.out.println();
//			print(visited);
//			System.out.println("===============");
			
		}// while end
		
		int qSize = q.size();
		for (int i = 0; i < qSize; i++) {
			Location loc = q.poll();
			if(loc.life == map[loc.row][loc.col]) {
				q.add(loc);
			}
		}
		
		ans = q.size();
	}
	
	static void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println(); 
		}
		System.out.println();
	}
}
