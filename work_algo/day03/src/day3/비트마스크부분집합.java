package day3;

public class 비트마스크부분집합 {
	public static void main(String[] args) {
		int[] arr = {1,2,3};
		for (int i = 0; i < ( 1 << arr.length); i++) {
			//마지막 3칸에 대해서 검사
			for (int j = 0; j < arr.length; j++) {
				if ( ( (1<<j) & i ) != 0 ) {
					System.out.print(arr[j]);
				}
			}
			System.out.println();
		}
		
//		System.out.println((1<<1));
		System.out.println((1<<2) & 3);
	}
}
