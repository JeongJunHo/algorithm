import java.util.Arrays;

public class 재귀 {
	static int total;
	public static void main(String[] args) {
		int[] arr = {1,3,5,7};
//		recur(arr, 0 , 1);
		
//		System.out.println(total);
		
//		combination(arr, 0, new int[3], 0);
		perm_re(arr, new int[3], 0);
//		perm(arr, new int[3], 0, new boolean[arr.length]);
	}
	
	static void recur(int[] arr, int n, int val) {
		if(n == arr.length) {
			total += val;
			return;
		}
		
		recur(arr, n+1, val + arr[n]);
		recur(arr, n+1, val * arr[n]);
	}

	static void perm_re(int[] arr, int[] sel, int k) {
		if(k == sel.length) {
			System.out.println(Arrays.toString(sel));
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			sel[k] = arr[i];
			perm_re(arr, sel, k+1);
		}
	}
	
	static void perm(int[] arr, int[] sel, int k, boolean[] visited) {
		if(k == sel.length) {
			System.out.println(Arrays.toString(sel));
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				sel[k] = arr[i];
				perm(arr, sel, k+1, visited);
				visited[i] = false;
			}
		}
	}
	
	static void combination(int[] arr, int idx, int[] sel, int k) {
		if(k == sel.length) {
			System.out.println(Arrays.toString(sel));
			return;
		}
		
		if(idx == arr.length) {
			return;
		}
		
		sel[k] = arr[idx];
		combination(arr, idx+1, sel, k+1);
		
		combination(arr, idx+1, sel, k);
	}
}
