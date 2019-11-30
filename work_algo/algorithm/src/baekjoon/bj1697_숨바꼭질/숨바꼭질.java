package baekjoon.bj1697_숨바꼭질;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 숨바꼭질 {
	static int ans, N, K;
	static class Move{
		int num;
		int time;
		public Move(int num, int time) {
			super();
			this.num = num;
			this.time = time;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(N != K) {
			boolean[] visited = new boolean[100001];
			Queue<Move> q = new LinkedList<Move>();
			
			q.add(new Move(N, 0));
			
			while (!q.isEmpty()) {
				Move move = q.poll();
				
				int n = move.num + 1;
				if(n >= 0 && n <= 100000 && !visited[n]) {
					if(n == K) {
						ans = move.time+1;
						break;
					}
					
					visited[n] = true;
					q.add(new Move(n, move.time+1));
				}
				
				n = move.num - 1;
				if(n >= 0 && n <= 100000 && !visited[n]) {
					if(n == K) {
						ans = move.time+1;
						break;
					}
					
					visited[n] = true;
					q.add(new Move(n, move.time+1));
				}
				
				n = move.num * 2;
				if(n >= 0 && n <= 100000 && !visited[n]) {
					if(n == K) {
						ans = move.time+1;
						break;
					}
					
					visited[n] = true;
					q.add(new Move(n, move.time+1));
				}
			}
		}
		
		System.out.println(ans);
	}
}
