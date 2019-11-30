package swexpertacademy.sea7206_숫자게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 숫자게임 {
	static int turn;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			turn = 0;
			String n = br.readLine();
			turn = powerSet(n, new boolean[n.length()], 0, 0);
			
			sb.append("#").append(tc).append(" ").append(turn).append("\n");
		}
		System.out.println(sb);
	}
	private static int powerSet(String n, boolean[] visited, int idx, int t) {
		if(idx == n.length()) {
			ArrayList<String> strList = new ArrayList<String>();
			String str = "";
			for (int i = 0; i < visited.length; i++) {
				str += n.charAt(i);
				if(!visited[i]) {
					strList.add(str);
					str = "";
				}
			}
			
			if(strList.size() > 1) {
				int total = 1;
				for (String s : strList) {
					total *= Integer.parseInt(s);
				}
				if(total >= 10) {
					String tmp = String.valueOf(total);
					t += powerSet(tmp, new boolean[tmp.length()], 0, t);
				}
				
				turn = Math.max(turn, t);
			}
			
			return t;
		}
		
		visited[idx] = true;
		t = Math.max(t, powerSet(n, visited, idx+1, t));
		visited[idx] = false;
		t = Math.max(t, powerSet(n, visited, idx+1, t));
		
		return t;
	}
}
