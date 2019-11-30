package swexpertacademy.sea1251_하나로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 하나로_sol {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 섬 수
			double[] mapX = new double[N]; // 각 섬들의 x좌표
			double[] mapY = new double[N]; // 각 섬들의 y좌표

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				mapX[i] = Double.parseDouble(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				mapY[i] = Double.parseDouble(st.nextToken());
			}
			double E = Double.parseDouble(br.readLine()); // 세율
			ArrayList<Integer> list = new ArrayList<>();
			boolean[] visited = new boolean[N];

			list.add(0);
			visited[0] = true;

			double ans = 0;
			for (int i = 0; i < N - 1; i++) {
				double min = Double.MAX_VALUE;
				int minIdx = 0;
				double distance = 0;
				for (int start : list) {
					for (int j = 0; j < N; j++) {
						distance = ((mapX[start] - mapX[j]) * (mapX[start] - mapX[j]))
								+ ((mapY[start] - mapY[j]) * (mapY[start] - mapY[j]));
						if (distance < min && !visited[j]) {
							min = distance;
							minIdx = j;
						}
					}
				}
				list.add(minIdx);
				ans += min;
				visited[minIdx] = true;
			}
			ans *= E;
			ans = Math.round(ans);
			System.out.println("#" + tc + " " + (long) ans);
		}
	}
}
