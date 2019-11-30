package swexpertacademy;

import java.util.Scanner;

public class problem7965 {
	final static long MOD_NUM = 1000000007;
	static long[] nums = new long[1000001];
	public static void main(String[] args) {
		for (int i = 1; i <= 1000000; i++) {
			nums[i] = (nums[i-1] + powerSet(i,i)) % MOD_NUM;
		}
		
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			int n = sc.nextInt();
			System.out.println("#" + tc + " " + nums[n]);
		}
	}
	
	static long powerSet(int num, int pow) {
		if(pow == 0) {
			return 1;
		}
		if(pow == 1) {
			return num;
		}
		
		long tmp = powerSet(num, pow / 2);
		if(pow % 2 == 0) {
			return (tmp * tmp) % MOD_NUM;
		}else {
			return ((tmp * tmp % MOD_NUM) * num) % MOD_NUM;
		}
	}
}
