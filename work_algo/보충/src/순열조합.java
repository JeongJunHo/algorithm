import java.util.Arrays;

public class 순열조합 {
	static int[] arr = {1,3,5,7,9};
	static boolean[] check;
	static int n = 5, r = 3;
	public static void main(String[] args) {
		check = new boolean[n];
		
//		perm(new int[r], 0);
		Arrays.fill(check, false);
		perm2(0, r);
	}
	
	static void perm(int[] select, int idx) {
		if(idx == select.length) {
			System.out.println(Arrays.toString(select));
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if(!check[i]) {
				check[i] = true;
				select[idx] = arr[i];
				perm(select, idx+1);
				check[i] = false;
			}
		}
	}
	
	static void perm2(int idx, int r) {
		if(idx == r) {
			for (int i = 0; i < arr.length; i++) {
				if(check[i]) {
					System.out.print(arr[i]);
				}
			}
			System.out.println();
			
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if(check[i]) {
				check[i] = true;
				perm2(idx+1, r);
				check[i] = false;
			}
		}
	}
}
