package swexpertacademy.sea3234_준환이의양팔저울;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 준환이의양팔저울_memo {
	static int N, ans;
	static int[] arr, select;
	static boolean[] visited;
	static HashMap<String,Integer> map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			select = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			map = new HashMap<String, Integer>();
			perm(0, 0, new boolean[N]);
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	
	
	private static void perm(int idx, int s_idx, boolean[] visited) {
		if(s_idx == N) {
			ans += powerSet(0, 0, 0);
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				select[s_idx] = arr[i];
				perm(i, s_idx+1, visited);
				visited[i] = false;
			}
		}
	}


	private static int powerSet(int idx, int left, int right) {
		if(right > left) {
			return 0;
		}
		if(idx == N) {
			return 1;
		}
		int result = 0;
		
		result += powerSet(idx+1, left+select[idx], right);
		result += powerSet(idx+1, left, right+select[idx]);
		
		return result;
	}
}
