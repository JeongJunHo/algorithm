package swexpertacademy.sea5643_키순서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 키순서 {
	static int N, M, ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			N = Integer.parseInt(br.readLine());
			// 나보다 큰놈 담는 리스트
			List<Integer>[] tallList = new List[N + 1];
			for (int i = 1; i <= N; i++) {
				tallList[i] = new ArrayList<Integer>();
			}
			M = Integer.parseInt(br.readLine());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int small = Integer.parseInt(st.nextToken());
				int big = Integer.parseInt(st.nextToken());
				tallList[small].add(big);
			}

			Queue<Integer> q = new LinkedList<Integer>();
			int smallCnt[] = new int[N + 1];
			int bigCnt[] = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				boolean[] visited = new boolean[N + 1];
				q.add(i);
				while (!q.isEmpty()) {
					int now = q.poll();
					for (int next : tallList[now]) {
						if (!visited[next]) {
							visited[next] = true;
							q.add(next);
							smallCnt[next]++;
							bigCnt[i]++;
						}
					}
				}
			}

			for(int i=1;i<=N;i++) {
				if(smallCnt[i] + bigCnt[i] == N - 1) ans++;
			}

			System.out.println("#" + tc + " " + ans);
		} // tc for end
	}
}
