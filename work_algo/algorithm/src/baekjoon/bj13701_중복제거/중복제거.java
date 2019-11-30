package baekjoon.bj13701_중복제거;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 중복제거 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Set<Integer> set = new LinkedHashSet<Integer>();
		StringBuilder sb = new StringBuilder();
		
		while (st.hasMoreTokens()) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		
		for (Integer num : set) {
			sb.append(num + " ");
		}
		
		System.out.println(sb);
	}
}
