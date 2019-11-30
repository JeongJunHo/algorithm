package swexpertacademy.sea1257_K번째문자열;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class K번째문자열 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int k = sc.nextInt();
	        String s = sc.next();
	        //s문자열의 모든 접미사 생성 - 실제 문자열을 생성하지 않고 시작 문자의 위치를 기록
	        Integer[] sa = new Integer[s.length()];
	        for(int i = 0; i < sa.length; i++)
	            sa[i] = i;
	        //sa[i]번째 부터 시작하는 문자열들이 각각 사전순으로 오름차순이 되도록 정렬.
	        //sa[0] : banana  ↔ sa[1] : anana 
	        //s.subString(sa[0])     s.subString(sa[1])
	        Arrays.sort(sa, new Comparator<Integer>() {
	            @Override
	            public int compare(Integer o1, Integer o2) {
	                // TODO Auto-generated method stub
	                return s.substring(o1).compareTo(s.substring(o2));
	            }
	        });
	        int[] LCP = new int[s.length()];
	        //LCP를 구해보시오.
	        for(int i = 1; i < LCP.length; i++) {
	            String s1 = s.substring(sa[i-1]);
	            String s2 = s.substring(sa[i]);
	            while( s1.length() > LCP[i] && s2.length() > LCP[i]&&s1.charAt(LCP[i]) == s2.charAt(LCP[i]) ) {
	                LCP[i]++;
	            }
	        }
	         
	        //k번째 부분문자열 찾기...
	        String ans = "none";
	        for(int i = 0; i < sa.length; i++) {
	            String str = s.substring(sa[i]);
	            if( k - (str.length() - LCP[i]) > 0) {
	                k = k- (str.length() - LCP[i]);
	            }else {
	                ans = str.substring(0, k + LCP[i]);
	                break;
	            }
	        }
	        
	        System.out.println("#" + tc + " " + ans);
		}
	}
}
