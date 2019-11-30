package day10;

public class 재귀함수 {
	public static void main(String[] args) {
		
	}
	
	static int fact(int n) {
		if(n <= 1) {
			return 1;
		}
		return n * fact(n-1);
	}
	
	static int power_of_2(int k) {
		if(k == 0) {
			return 1;
		}
		if(k == 1) {
			return 2;
		}
		int tmp = power_of_2(k/2);
		if(k % 2 == 0) {
			return tmp * tmp;
		}else {
			return tmp * tmp * 2;
		}
	}
}
