package baekjoon.bj12851_숨바꼭질2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 숨바꼭질2 {
	static int ans = Integer.MAX_VALUE, N, K, ansCnt;
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
		
		Queue<Move> q = new LinkedList<Move>();
		int[] visited = new int[100001];
		Arrays.fill(visited, 987654321);
		
		q.add(new Move(N, 0));
		
		while (!q.isEmpty()) {
			Move move = q.poll();
			
			if(move.num == K) {
				if(ans > move.time) {
					ans = move.time;
					ansCnt = 1;
				}else if(ans == move.time) {
					ansCnt++;
				}
			}
			
			if (ans < move.time) {
				break;
			}
			
			if(move.num*2 <= 100000 && visited[move.num*2] >= move.time+1) {
				q.add(new Move(move.num*2, move.time+1));
				visited[move.num*2] = move.time+1;
			}
			if(move.num+1 <= 100000 && visited[move.num+1] >= move.time+1) {
				q.add(new Move(move.num+1, move.time+1));
				visited[move.num+1] = move.time+1;
			}
			if(move.num-1 >= 0 && visited[move.num-1] >= move.time+1) {
				q.add(new Move(move.num-1, move.time+1));
				visited[move.num-1] = move.time+1;
			}
			
			
		}
		
		System.out.println(ans);
		System.out.println(ansCnt);
	}
}
