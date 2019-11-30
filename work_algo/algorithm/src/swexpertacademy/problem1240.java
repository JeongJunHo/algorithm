package swexpertacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class problem1240 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int t = Integer.parseInt(st.nextToken());
		
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			char[][] arr = new char[n][m]; 
			
			for (int i = 0; i < n; i++) {
				arr[i] = br.readLine().toCharArray();
			}
			
			for (int i = 0; i < n; i++) {
				String str = String.valueOf(arr[i]);
				int end = str.lastIndexOf("1");
				if(end != -1) {
					Integer start = end - 55;
					String[] pwd = new String[8];
					for (int j = 0; j < 8; j++) {
						pwd[j] = str.substring(start, start+7);
						start += 7;
					}
					
					//홀수
					int odd = 0;
					//짝수
					int even = 0;
					for (int j = 0; j < pwd.length; j++) {
						if(j % 2 == 0) {
							odd += decode(pwd[j]);
						}else {
							even += decode(pwd[j]);
						}
					}
					
					int sum = 0;
					if((odd * 3 + even) % 10 == 0) {
						for (int j = 0; j < pwd.length; j++) {
							sum += decode(pwd[j]);
						}
					}
					System.out.println("#" + tc + " " + sum);
					break;
				}
			}
		}
	}
	
	static Integer decode(String pwd){
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		map.put("0001101", 0);
		map.put("0011001", 1);
		map.put("0010011", 2);
		map.put("0111101", 3);
		map.put("0100011", 4);
		map.put("0110001", 5);
		map.put("0101111", 6);
		map.put("0111011", 7);
		map.put("0110111", 8);
		map.put("0001011", 9);
		
		return map.get(pwd);
	}
}
