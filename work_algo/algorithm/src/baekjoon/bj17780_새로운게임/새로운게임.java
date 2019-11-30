package baekjoon.bj17780_새로운게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 새로운게임 {
	static class Dron{
		int row;
		int col;
		int direction;
		public Dron(int row, int col, int direction) {
			super();
			this.row = row;
			this.col = col;
			this.direction = direction;
		}
	}
	static int N, K, ans;
	static int[][] pos = {{0,0},{0,1},{0,-1},{-1,0},{1,0}};
	static int[][] arr;
	static Queue<Dron>[][] dronArr;
	static Stack<Dron> stack = new Stack<Dron>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N+1][N+1];
		dronArr = new Queue[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dronArr[i][j] = new LinkedList<Dron>();
			}
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<Dron> queue = new LinkedList<Dron>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			Dron dron = new Dron(row, col, direction);
			queue.add(dron);
			dronArr[row][col].add(dron);
		}
		
		boolean complete = false;
		while (!complete && ans < 1000) {
			ans++;
			int qSize = queue.size();
			for (int i = 0; i < qSize; i++) {
				Dron dron = queue.poll();
				int row = dron.row;
				int col = dron.col;
				
				if(dronArr[row][col].peek().equals(dron)) {
					int nr = row + pos[dron.direction][0];
					int nc = col + pos[dron.direction][1];
					
					if(posCheck(nr, nc)) {
						if(arr[nr][nc] == 0) {
							white(row, col, nr, nc);
						}else if (arr[nr][nc] == 1) {
							red(row, col, nr, nc);
						}else if (arr[nr][nc] == 2) {
							blueOrOut(dron, row, col);
						}
					}else {
						blueOrOut(dron, row, col);
					}
					
					if(dronArr[dron.row][dron.col].size() >= 4) {
						complete = true;
						break;
					}
				}
				
				queue.add(dron);
			}//qsize end
		}
		
		System.out.println(ans == 1000 ? -1 : ans);
	}
	
	static void white(int row, int col, int nr, int nc) {
		while(!dronArr[row][col].isEmpty()) {
			Dron tmp = dronArr[row][col].poll();
			tmp.row = nr;
			tmp.col = nc;
			dronArr[nr][nc].add(tmp);
		}
	}
	
	static void red(int row, int col, int nr, int nc) {
		while (!dronArr[row][col].isEmpty()) {
			stack.push(dronArr[row][col].poll());
		}
		while (!stack.isEmpty()) {
			Dron tmp = stack.pop();
			tmp.row = nr;
			tmp.col = nc;
			dronArr[nr][nc].add(tmp);
		}
	}
	
	static void blueOrOut(Dron dron, int row, int col) {
		if(dron.direction % 2 == 0) dron.direction--;
		else dron.direction++;
		
		int nr = row + pos[dron.direction][0];
		int nc = col + pos[dron.direction][1];
		
		if(posCheck(nr,nc) && arr[nr][nc] != 2) {
			if(arr[nr][nc] == 0) {
				white(row, col, nr, nc);
			}else {
				red(row, col, nr, nc);
			}
		}
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 1 && row <= N && col >= 1 && col <= N;
	}
}
