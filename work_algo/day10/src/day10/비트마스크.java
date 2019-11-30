package day10;

public class 비트마스크 {
	public static void main(String[] args) {
		int[] arr = {1,3,5};
		//원소가 세개이므로, 경우의수는 2^3 가지.
		for (int i = 0; i < (1 << arr.length); i++) {
			for (int j = 0; j < arr.length; j++) {
				if ((i & 1<<j) != 0) {
					System.out.print(arr[j]);
				}
			}
			System.out.println();
		}
	}
}
