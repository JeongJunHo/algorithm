package swexpertacademy.sea7701_염라대왕의이름정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.TreeSet;

public class 염라대왕의이름정렬 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			TreeSet<String> set = new TreeSet<String>(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					// TODO Auto-generated method stub
					if(o1.length()-o2.length() != 0) {
						return o1.length()-o2.length();
					}else {
						return o1.compareTo(o2);
					}
				}
			});
			int N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				set.add(br.readLine());
			}
			
			sb.append("#").append(tc).append("\n");
			for (String str : set) {
				sb.append(str).append("\n");
			}
		}
		System.out.println(sb);
	}
}
