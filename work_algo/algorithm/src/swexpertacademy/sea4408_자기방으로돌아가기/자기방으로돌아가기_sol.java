package swexpertacademy.sea4408_자기방으로돌아가기;

import java.util.Scanner;

public class 자기방으로돌아가기_sol {
	static int[] map = new int[401];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			for (int i = 0; i < map.length; i++)
				map[i] = 0;
			int N = sc.nextInt();
			
			for (int n = 0; n < N; n++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				if(from % 2 == 1)
					from++;
				if(to % 2 == 1)
					to++;
				
				if(from > to) {
					int tmp = from;
					from = to;
					to = tmp;
				}
				
				for (int i = from; i <= to; i+=2) {
					map[i]++;
				}
			}
			
			int max = 0;
			for (int i = 2; i <= 400; i+=2) {
				max = Math.max(max, map[i]);
			}
			System.out.println("#" + tc + " " + max);
		}
	}
}
