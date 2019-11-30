package baekjoon.bj3190_뱀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 뱀 {
	static class Direction{
		int sec;
		char word;
		public Direction(int sec, char word) {
			super();
			this.sec = sec;
			this.word = word;
		}
	}
	static class Location implements Cloneable{
		int row;
		int col;
		int dir;
		public Location(int row, int col, int dir) {
			super();
			this.row = row;
			this.col = col;
			this.dir = dir;
		}
		@Override
		protected Object clone() throws CloneNotSupportedException {
			// TODO Auto-generated method stub
			return super.clone();
		}
	}
	static int N, K, L;
	static int[][] map;
	static boolean[][] visited;
	static int[][] pos = {{0,1},{1,0},{0,-1},{-1,0}};
	static Queue<Direction> dQ = new LinkedList<Direction>();
	static Queue<Location> q = new LinkedList<Location>();
	public static void main(String[] args) throws NumberFormatException, IOException, CloneNotSupportedException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		visited = new boolean[N+1][N+1];
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
		}
		L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			dQ.add(new Direction(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
		}
		
		q.add(new Location(1, 1, 0));
		visited[1][1] = true;
		int sec = 0;
		end:while (true) {
			sec++;
			Location prevLoc = null;
			boolean eat = false;
			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				Location loc = q.poll();
				
				//뱀 머리라면
				if(prevLoc == null) {
					//방향전환을 해야하는 경우라면
					if(!dQ.isEmpty() && dQ.peek().sec < sec) {
						Direction dir = dQ.poll();
						if(dir.word == 'L') {
							loc.dir--;
							if(loc.dir < 0) loc.dir = 3;
						}else {
							loc.dir++;
							if(loc.dir > 3) loc.dir = 0;
						}
					}
					
					int nr = loc.row + pos[loc.dir][0];
					int nc = loc.col + pos[loc.dir][1];
					if(posCheck(nr, nc) && !visited[nr][nc]) {
						visited[nr][nc] = true;
						q.add(new Location(nr, nc, loc.dir));
						if(map[nr][nc] == 1) {
							map[nr][nc] = 0;
							eat = true;
						}
					}else {
						break end;
					}
				//몸통이라면
				}else {
					q.add(prevLoc);
				}
				
				prevLoc = (Location) loc.clone();
			}// qsize for end
			
			if(eat) {
				q.add(prevLoc);
			}else {
				visited[prevLoc.row][prevLoc.col] = false;
			}
		}// while end
		
		System.out.println(sec);
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 1 && row <= N && col >= 1 && col <= N;
	}
}