package day09;

public class 부분집합 {
	static int cnt = 0;
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5};
		powerset(arr, 0, new boolean[5]);
		
		System.out.println("=================");
		
		powerset(arr, 0, 0);
		System.out.println(cnt);
		
		System.out.println("=================");
		cnt = 0;
		powerset(arr, 0, 0, 0);
		System.out.println(cnt);
	}

	static void powerset(int[] arr, int idx, boolean[] sel) {
		if(arr.length == idx) {
			for (int i = 0; i < arr.length; i++) {
				if(sel[i])
					System.out.println(arr[i] + " ");
			}
			System.out.println();
			return;
		}
		
		sel[idx] = false;
		powerset(arr, idx+1, sel);
		sel[idx] = true;
		powerset(arr, idx+1, sel);
	}
	
	static void powerset(int[] arr, int idx, int sum) {
		if(arr.length == idx) {
			if(sum >= 10) {
				cnt++;
			}
//			System.out.println(sum);
			return;
		}
		
		powerset(arr, idx+1, sum);
		powerset(arr, idx+1, sum + arr[idx]);
	}
	
	static void powerset(int[] arr, int idx, int sum, int n) {
		if(arr.length == idx) {
			if(sum >= 10 && n == 4) {
				cnt++;
			}
//			System.out.println(sum);
			return;
		}
		
		powerset(arr, idx+1, sum, n);
		powerset(arr, idx+1, sum + arr[idx], n+1);
	}
}
