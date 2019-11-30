package swexpertacademy.sea5672_올해의조련사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 올해의조련사 {
	static int N;
	static String ans;
	static char[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			ans = "";
			N = Integer.parseInt(br.readLine());
			arr = new char[N];
			for (int i = 0; i < N; i++) {
				arr[i] = br.readLine().charAt(0);
			}
			
			int count = 0;
			int start = 0;
			int end = N-1;
			while (count < N) {
				//앞
				if(arr[start] < arr[end]) {
					ans += arr[start++];
					count++;
				//뒤
				}else if(arr[start] > arr[end]) {
					ans += arr[end--];
					count++;
				}else {
					int nextStart = start;
					int nextEnd = end;
					while (true) {
						nextStart++;
						nextEnd--;
						if(arr[nextStart] < arr[nextEnd]) {
							ans += arr[start++];
							count++;
							break;
						}else if (arr[nextStart] > arr[nextEnd]) {
							ans += arr[end--];
							count++;
							break;
						}
						if(nextStart >= nextEnd) {
							count++;
							ans += arr[start++];
							break;
						}
					}
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
}
