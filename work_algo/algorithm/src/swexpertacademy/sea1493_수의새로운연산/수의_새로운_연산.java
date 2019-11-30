package swexpertacademy.sea1493_수의새로운연산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수의_새로운_연산 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			
			int[] firstPos = getPos(first);
			int[] secondPos = getPos(second);
			sb.append("#")
			.append(tc).append(" ")
			.append(getValue(firstPos[0] + secondPos[0], firstPos[1] + secondPos[1])).append("\n");
		}
		System.out.println(sb);
	}
	
	static int getValue(int x, int y) {
		int result = 0; 
		for (int i = 1; i <= x; i++) {
			result += i;
		}
		
		int add = x;
		for (int i = 0; i < y-1; i++) {
			result += add++;
		}
		
		return result;
	}
	
	static int[] getPos(int num) {
		int x = 1;
		int y = 1;
		while (num > getValue(x, 1)) {
			x++;
		}
		
		while (num < getValue(x, y)) {
			x--;
			y++;
		}
		
		return new int[]{x,y};
	}
}
