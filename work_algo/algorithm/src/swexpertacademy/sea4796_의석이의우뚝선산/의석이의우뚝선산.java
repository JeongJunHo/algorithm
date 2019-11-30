package swexpertacademy.sea4796_의석이의우뚝선산;

import java.util.Scanner;

public class 의석이의우뚝선산 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			int n = sc.nextInt();
			
			int sCnt = 0;
			int current = 0;
			int eCnt = 0;
			int ans = 0;
			boolean buffer = false;
			
			for (int i = 0; i < n; i++) {
				buffer = true;
				int tmp = sc.nextInt();
				
				if(current < tmp) {
					if(eCnt != 0) {
						ans += (sCnt-1) * eCnt;
						eCnt=0;
						sCnt=1;
						buffer = false;
					}
					sCnt++;
				}else {
					eCnt++;
				}
				current = tmp;
			}
			
			if(buffer) {
				ans += (sCnt-1) * eCnt;
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
}
