package swexpertacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1233 {
	static String[] arr;
	static boolean possible;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			possible = true;
			arr = new String[Integer.parseInt(st.nextToken())+1];
			
			for (int i = 1; i < arr.length; i++) {
				st = new StringTokenizer(br.readLine());
				arr[Integer.parseInt(st.nextToken())] = st.nextToken();
			}
			
			dfs(1);
			
			System.out.println("#" + tc + " " + (possible ? 1 : 0));
		}
	}
	
	static void dfs(int idx) {
		if(!possible) {
			return;
		}
		if(idx >= arr.length) {
			return;
		}
		
		int left = idx * 2;
		if(left < arr.length) {
			if(isOperator(arr[idx])) {
				dfs(left);
			}else {
				possible = false;
			}
		}else {
			if(isOperator(arr[idx])) {
				possible = false;
			}
		}
		
		int right = idx * 2 + 1;
		if(right < arr.length) {
			if(isOperator(arr[idx])) {
				dfs(right);
			}else {
				possible = false;
			}
		}else {
			if(isOperator(arr[idx])) {
				possible = false;
			}
		}
	}
	
	static boolean isOperator(String str) {
		return "+-*/".contains(str);
	}
}
