package baekjoon.bj16234_인구이동;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 인구이동 {
	static class Location{
		int row;
		int col;
		
		public Location(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	static int N, L, R, ans;
	static int[][] map;
	static int[][] visited;
	static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Queue<Location> q = new LinkedList<Location>();
		Queue<Location> updateQ = new LinkedList<Location>();
		//반복
		while (true) {
			//쭉 순회하며 bfs로 성문개방하며 값 더해놓는다. 숫자도 카운트 해둔다.
			boolean check = false;
			visited = new int[N][N];
			int next = 1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(visited[i][j] == 0) {
						visited[i][j] = next;
						int people = map[i][j];
						int castleCnt = 1;
						q.add(new Location(i,j));
						while (!q.isEmpty()) {
							int row = q.peek().row;
							int col = q.peek().col;
							updateQ.add(q.poll());
							
							for (int k = 0; k < pos.length; k++) {
								int nr = row + pos[k][0];
								int nc = col + pos[k][1];
								
								if(posCheck(nr, nc) && visited[nr][nc] == 0 && openCheck(map[row][col], map[nr][nc])) {
									check = true;
									people += map[nr][nc];
									castleCnt++;
									visited[nr][nc] = next;
									q.add(new Location(nr, nc));
								}
							}
						}
						//개방된(true) 지역을 더한값 / 숫자로 나눠 지정한다. (소수버림)
						while (!updateQ.isEmpty()) {
							Location loc = updateQ.poll();
							map[loc.row][loc.col] = people / castleCnt;
						}
						
						next++;
					}
				}
			}
			
			//인구이동이 안일어났을 경우 break 일어났나면 ans++
			if(!check) {
				break;
			}else {
				ans++;
			}
		}
		
		System.out.println(ans);
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}
	
	static boolean openCheck(int a, int b) {
		return Math.abs(a-b) >= L && Math.abs(a-b) <= R;
	}
}
