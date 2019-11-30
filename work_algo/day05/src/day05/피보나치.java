package day05;

public class 피보나치 {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		System.out.println(fibo(60));
		long end = System.currentTimeMillis();
		
		System.out.println(end-start + "ms");
	}

	static int cnt = 0;
	static int[] memo = new int[100000];
	static int fibo(int n) {
		cnt++;
		if(n == 0) {
			return 0;
		}
		if(n == 1) {
			return 1;
		}
		if(memo[n-1] == 0) {
			memo[n-1] = fibo(n-1);
		}
		if(memo[n-2] == 0) {
			memo[n-2] = fibo(n-1);
		}
		
		return memo[n-1] + memo[n-2];
	}
}
