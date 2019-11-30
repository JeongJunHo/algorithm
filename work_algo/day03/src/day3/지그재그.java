package day3;

public class 지그재그 {
	public static void main(String[] args) {
		int[][] arr = new int[5][5];
		int cnt = 1;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = cnt++;
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j + (arr.length - 1 - 2 * j) * (i % 2)] + " ");
			}
			System.out.println();
		}

		for (int i = 0; i < arr.length; i++) {
			if(i % 2 == 0) {
				for (int j = 0; j < arr.length; j++) {
					System.out.print(arr[i][j] + " ");
				}
			}else {
				for (int j = arr.length-1; j >= 0; j--) {
					System.out.print(arr[i][j] + " ");
				}
			}
			System.out.println();
		}
	}
}