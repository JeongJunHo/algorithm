package day15;

public class ColorStick {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		long result = recur(40);
//		long result = memoization(40);
//		long result = dp(40);
		long end = System.currentTimeMillis();
		System.out.println("fibo : " + result);
		System.out.println(end-start);
	}
	
	static long recur(int n) {
		if(n == 0) return 0;
		if(n == 1) return 2;
		if(n == 2) return 5;
		
		return recur(n-1) * 2 + recur(n-2);
	}
	
	static long[] arr = new long[1000];
	static long memoization(int n) {
		if(n == 0) {
			arr[n] = 0;
			return arr[n];
		}
		if(n == 1) {
			arr[n] = 2;
			return arr[n];
		}
		if(n == 2) {
			arr[n] = 5;
			return arr[n];
		}
		
		if(arr[n] == 0)
			arr[n] = memoization(n-1) * 2 + memoization(n-2);
		return arr[n];
	}
	
	static long dp(int n) {
		long[] arr = new long[n+1];
		
		arr[1] = 2;
		arr[2] = 5;
		for (int i = 3; i <= n; i++) {
			arr[i] = arr[i-1] * 2 + arr[i-2];
		}
		
		return arr[n];
	}
}
