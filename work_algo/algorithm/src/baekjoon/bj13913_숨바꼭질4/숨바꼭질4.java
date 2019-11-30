package baekjoon.bj13913_숨바꼭질4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 숨바꼭질4 {
	static int ans = Integer.MAX_VALUE, N, K;
	static List<Integer> history;
	static class Move{
		int num;
		int time;
		List<Integer> history = new ArrayList<Integer>();
		public Move(int num, int time, List<Integer> history) {
			super();
			this.num = num;
			this.time = time;
			this.history = history;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		if(N <= K) {
			Queue<Move> q = new LinkedList<Move>();
			boolean[] visited = new boolean[100001];
			
			List<Integer> tmp = new ArrayList<Integer>();
			tmp.add(N);
			q.add(new Move(N, 0, tmp));
			
			while (!q.isEmpty()) {
				Move move = q.poll();
				
				if(move.num == K) {
					ans = move.time;
					history = move.history;
					break;
				}
				
				if(move.num*2 <= 100000 && !visited[move.num*2]) {
					List<Integer> history = new ArrayList<Integer>(move.history);
					history.add(move.num*2);
					q.add(new Move(move.num*2, move.time+1, history));
					visited[move.num*2] = true;
				}
				if(move.num+1 <= K && !visited[move.num+1]) {
					List<Integer> history = new ArrayList<Integer>(move.history);
					history.add(move.num+1);
					q.add(new Move(move.num+1, move.time+1, history));
					visited[move.num+1] = true;
				}
				if(move.num-1 >= 0 && !visited[move.num-1]) {
					List<Integer> history = new ArrayList<Integer>(move.history);
					history.add(move.num-1);
					q.add(new Move(move.num-1, move.time+1, history));
					visited[move.num-1] = true;
				}
			}
			
			System.out.println(ans);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < history.size(); i++) {
				sb.append(history.get(i) + " ");
			}
			System.out.println(sb);
		}else {
			String history = "";
			ans = N - K;
			
			StringBuilder sb = new StringBuilder();
			for (int i = N; i >= K; i--) {
				sb.append(i + " ");
			}
			
			System.out.println(ans);
			System.out.println(sb);
		}
	}
}
