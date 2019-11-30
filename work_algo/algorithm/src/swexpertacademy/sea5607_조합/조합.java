package swexpertacademy.sea5607_조합;

import java.util.Scanner;

public class 조합 {
	static final long P = 1234567891;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		long fac[] = new long[1000001];
		fac[0] = 1;
		for(int i=1; i< fac.length; i++) {
			fac[i] = fac[i-1] * i % P;
		}
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int R = sc.nextInt();
//			long result = (N! * (N-R)!^(P-2) * R!^(R-2) % P);
			long result = fac[N] * power(fac[N-R] * fac[R] % P, P-2) % P;
			
			System.out.println("#" + tc + " " + result);
		}
	}
	
	static long power(long base, long ex) {
		if(ex == 0)
			return 1;
		if(ex == 1)
			return base;
		long result = power(base, ex/2);
		if(ex % 2 == 0)
			return result * result % P;
		else
			return result * result % P * base % P;
	}
}
