package swexpertacademy.sea1249_보급로;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 보급로_sol {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[][] map = new int[N][N];
			for(int i= 0; i < N; i++) {
				String line = sc.next();
				for(int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j) - 48;
				}
			}
			boolean[][] visited = new boolean[N][N];
			PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return Integer.compare(o1[2], o2[2]);
				}
			});
			queue.add(new int[]{0,0,0});
			visited[0][0] = true;
			while(!queue.isEmpty()) {
				int[] cur = queue.poll();
				int r = cur[0];
				int c = cur[1];
				int cnt = cur[2];
				if( r == N-1 && c == N-1 ) {
					System.out.println("#" + tc + " " + cnt);
				}
				for(int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					if(nr >= 0 && nc >= 0 && nr < N && nc < N && !visited[nr][nc] ) {
						visited[nr][nc] = true;
						queue.add(new int[] {nr,nc,cnt + map[nr][nc]});
					}
				}
			}
		}
	}
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
}
