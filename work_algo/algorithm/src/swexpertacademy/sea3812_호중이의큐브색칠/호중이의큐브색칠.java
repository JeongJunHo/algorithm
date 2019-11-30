package swexpertacademy.sea3812_호중이의큐브색칠;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 호중이의큐브색칠 {
	static int X, Y, Z, A, B, C, N;
	static int[] color;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= null;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			Z = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			color = new int[N];
			
			for (int i = 0; i < X; i++) {
				for (int j = 0; j < Y; j++) {
					for (int k = 0; k < Z; k++) {
						System.out.println((Math.abs(i-A) + Math.abs(j-B) + Math.abs(k-C)));
						int num = (Math.abs(i-A) + Math.abs(j-B) + Math.abs(k-C)) % N;
						color[num]++;
					}
				}
			}
			
			System.out.println(Arrays.toString(color));
		}
	}
}
