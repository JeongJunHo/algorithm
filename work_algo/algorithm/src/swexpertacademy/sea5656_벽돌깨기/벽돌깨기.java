package swexpertacademy.sea5656_벽돌깨기;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class 벽돌깨기 {
	static class Location {
		int row;
		int col;
		
		Location(int r, int c){
			row = r;
			col = c;
		}
	}
	
	static int n, w, h, total, crash, ans;
	static int[][] arr, pos = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			ans = Integer.MAX_VALUE;
			total = 0;
			n = sc.nextInt();
			w = sc.nextInt();
			h = sc.nextInt();
			
			arr = new int[h][w];
			
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					int tmp = sc.nextInt();
					arr[i][j] = tmp;
					if(tmp > 0) {
						total++;
					}
				}
			}
			
			perm_re(new int[n], 0);
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static void perm_re(int[] select, int idx) {
		if(select.length == idx) {
			int[][] copyArr = deepCopy(arr);
			crash = 0;
			for (int i = 0; i < select.length; i++) {
				copyArr = shoot(copyArr, select[i]);
			}
			
			ans = Math.min(ans, total-crash);
			
			return;
		}
		
		for (int i = 0; i < w; i++) {
			select[idx] = i;
			perm_re(select, idx + 1);
		}
	}
	
	static int[][] deepCopy(int[][] arr){
		int[][] copyArr = new int[h][w];
		
		for (int i = 0; i < arr.length; i++) {
			copyArr[i] = arr[i].clone();
		}
		
		return copyArr;
	}
	
	static int[][] shoot(int[][] copyArr, int rail) {
		Queue<Location> q = new LinkedList<Location>();
		boolean[][] visited = new boolean[h][w];
		
		for (int i = 0; i < h; i++) {
			if(copyArr[i][rail] > 0) {
				q.add(new Location(i, rail));
				crash++;
				visited[i][rail] = true;
				
				break;
			}
		}
		
		while (!q.isEmpty()) {
			int row = q.peek().row;
			int col = q.poll().col;
			
			for (int i = 0; i < pos.length; i++) {
				for (int j = 1; j <= copyArr[row][col]-1; j++) {
					int nr = row + pos[i][0] * j;
					int nc = col + pos[i][1] * j;
					
					if(posCheck(nr, nc) && !visited[nr][nc] && copyArr[nr][nc] > 0) {
						if(copyArr[nr][nc] > 1) {
							q.add(new Location(nr, nc));
						}
						crash++;
						visited[nr][nc] = true;
					}
				}
			}
		}
		
		return gravity(copyArr, visited);
	}
	
	static int[][] gravity(int[][] copyArr, boolean[][] visited) {
		int[][] tmp = new int[h][w];
		Stack<Integer> stack = new Stack<Integer>();
		
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if(!visited[j][i] && copyArr[j][i] > 0) {
					stack.push(copyArr[j][i]);
				}
			}
			int idx = h-1;
			while (!stack.isEmpty()) {
				tmp[idx--][i] = stack.pop();
			}
		}
		
		return tmp;
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 0 && row < h && col >= 0 && col < w;
	}
}