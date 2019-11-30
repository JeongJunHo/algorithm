package baekjoon.bj1600_말이되고픈원숭이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 말이되고픈원숭이 {
	static class Monkey{
		int row;
		int col;
		int move;
		int jump;
		public Monkey(int row, int col, int move, int jump) {
			super();
			this.row = row;
			this.col = col;
			this.move = move;
			this.jump = jump;
		}
	}
	static int K, W, H, ans = -1;
	static int[][] map;
	static boolean[][][] visited;
	static int[][] pos = {{-1,0},{1,0},{0,-1},{0,1}};
	static int[][] jumpPos = {{-2,-1},{-1,-2},{1,-2},{2,-1},{-2,1},{-1,2},{1,2},{2,1}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[H][W][K+1];
		Queue<Monkey> queue = new LinkedList<Monkey>();
		
		queue.add(new Monkey(0, 0, 0, 0));
		visited[0][0][0] = true;
		
		cursor:while (!queue.isEmpty()) {
			Monkey monkey = queue.poll();
			
			if(monkey.jump < K) {
				for (int i = 0; i < jumpPos.length; i++) {
					int nr = monkey.row + jumpPos[i][0];
					int nc = monkey.col + jumpPos[i][1];
					
					if(posCheck(nr, nc) && !visited[nr][nc][monkey.jump+1] && map[nr][nc] == 0) {
						if(nr == H-1 && nc == W-1) {
							ans = monkey.move+1;
							break cursor;
						}
						queue.add(new Monkey(nr, nc, monkey.move+1, monkey.jump+1));
						visited[nr][nc][monkey.jump+1] = true;
					}
				}
			}
			
			for (int i = 0; i < pos.length; i++) {
				int nr = monkey.row + pos[i][0];
				int nc = monkey.col + pos[i][1];
				
				if(posCheck(nr, nc) && !visited[nr][nc][monkey.jump] && map[nr][nc] == 0) {
					if(nr == H-1 && nc == W-1) {
						ans = monkey.move+1;
						break cursor;
					}
					queue.add(new Monkey(nr, nc, monkey.move+1, monkey.jump));
					visited[nr][nc][monkey.jump] = true;
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < H && col >= 0 && col < W;
	}
}
