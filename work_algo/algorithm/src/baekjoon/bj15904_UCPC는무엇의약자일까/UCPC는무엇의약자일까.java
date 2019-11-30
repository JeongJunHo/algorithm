package baekjoon.bj15904_UCPC는무엇의약자일까;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UCPC는무엇의약자일까 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = "UCPC";
		int idx = 0;
		
		String content = br.readLine();
		
		for (int i = 0; i < content.length() && idx < 4; i++) {
			if(str.charAt(idx) == content.charAt(i)) {
				idx++;
			}
		}
		
		if(idx == 4) {
			System.out.println("I love UCPC");
		}else {
			System.out.println("I hate UCPC");
		}
	}
}
