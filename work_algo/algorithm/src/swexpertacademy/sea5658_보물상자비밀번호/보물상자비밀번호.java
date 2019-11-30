package swexpertacademy.sea5658_보물상자비밀번호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class 보물상자비밀번호 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			String str = br.readLine();
			int quarter = str.length() / 4;
			TreeSet<Integer> set = new TreeSet<Integer>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2-o1;
				}
			});
			
			for (int i = 0; i < quarter; i++) {
				for (int j = 0; j < 4; j++) {
					int start = quarter*j;
					String subStr = str.substring(start, start+quarter);
					set.add(Integer.parseInt(subStr, 16));
				}
				
				str = str.charAt(str.length()-1) + str.substring(0, str.length()-1);
			}
	
			int idx = 1;
			for (Iterator<Integer> iterator = set.iterator(); iterator.hasNext();) {
				Integer num = iterator.next();
				if(idx == K) {
					sb.append("#").append(tc).append(" ").append(num).append("\n");
					break;
				}
				idx++;
			}
		}
		System.out.println(sb);
	}
}
