package baekjoon.bj13549_숨바꼭질3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 숨바꼭질3 {
	static int ans = Integer.MAX_VALUE, N, K;
	static class Move implements Comparable<Move>{
		int num;
		int time;
		public Move(int num, int time) {
			super();
			this.num = num;
			this.time = time;
		}
		
		@Override
		public int compareTo(Move o) {
			return this.time - o.time;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Move> q = new PriorityQueue<Move>();
		boolean[][] visited = new boolean[100001][3];
		
		q.add(new Move(N, 0));
		
		while (!q.isEmpty()) {
			Move move = q.poll();
			
			if(move.num == K) {
				if(ans > move.time) {
					ans = move.time;
				}
			}
			
			if (ans < move.time) {
				break;
			}
			
			if(move.num*2 <= 100000 && !visited[move.num*2][0]) {
				q.add(new Move(move.num*2, move.time));
				visited[move.num*2][0] = true;
			}
			if(move.num+1 <= K && !visited[move.num+1][1]) {
				q.add(new Move(move.num+1, move.time+1));
				visited[move.num+1][1] = true;
			}
			if(move.num-1 >= 0 && !visited[move.num-1][2]) {
				q.add(new Move(move.num-1, move.time+1));
				visited[move.num-1][2] = true;
			}
		}
		
		System.out.println(ans);
	}
}
