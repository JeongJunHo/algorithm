package day15;

public class Fibo {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		long result = recur(40);
		long end = System.currentTimeMillis();
		System.out.println("fibo : " + result);
		System.out.println(end-start);
		
//		System.out.println();
//		
//		for (int i = 1; i < 50; i++) {
//			System.out.println(i + " " + dp(i));
//		}
	}
	
	static long recur(int n) {
		if(n < 2) {
			return n;
		}else {
			return recur(n-1) + recur(n-2);
		}
	}
	
	static long[] memo = new long[100];
	static long fibo_memo(int n) {
		if(n < 2) {
			memo[n] = n;
			return memo[n];
		}
		
		if(memo[n] == 0)
			memo[n] = fibo_memo(n-1) + fibo_memo(n-2);
		return memo[n];
	}
	
	static long fibo_dp(int n) {
		long[] fibo = new long[n+1];
		fibo[0] = 0;
		fibo[1] = 1;
		for (int i = 2; i <= n; i++) {
			fibo[i] = fibo[i-1] + fibo[i-2];
		}
		return fibo[n];
	}
}