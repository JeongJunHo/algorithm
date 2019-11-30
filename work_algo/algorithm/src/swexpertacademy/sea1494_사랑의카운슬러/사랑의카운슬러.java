package swexpertacademy.sea1494_사랑의카운슬러;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사랑의카운슬러 {
	static class Location{
		int row;
		int col;
		
		Location(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	
	static long ans;
	static int n;
	static long sumR;
	static long sumC;
	static Location[] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			ans = Long.MAX_VALUE;
			n = Integer.parseInt(br.readLine());
			arr = new Location[n];
			sumR = 0;
			sumC = 0;
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i] = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				sumR += arr[i].row;
				sumC += arr[i].col;
			}
			
			comb(new Location[n], 0, 0);
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void comb(Location[] sel, int idx, int s_idx) {
		if(sel.length == s_idx) {
			int sx = 0;
			int sy = 0;
			for (int i = 0; i < sel.length; i++) {
				sx += sel[i].row;
				sy += sel[i].col;
			}
			long x = sumR * 2 * sx;
			long y = sumC * 2 * sy;
			long res = x*x + y*y;
			ans = Math.min(ans, res);
			return;
		}
		
		if(n == idx) {
			return;
		}
		
		sel[s_idx] = arr[idx];
		comb(sel, idx + 1, s_idx + 1);
		comb(sel, idx + 1, s_idx);
	}
}
