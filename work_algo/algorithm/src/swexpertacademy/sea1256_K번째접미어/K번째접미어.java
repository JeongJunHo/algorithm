package swexpertacademy.sea1256_K번째접미어;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class K번째접미어 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int k = sc.nextInt();
	        String s = sc.next();
	        
	        Integer[] sa = new Integer[s.length()];
	        for(int i = 0; i < sa.length; i++)
	            sa[i] = i;
	        Arrays.sort(sa, new Comparator<Integer>() {
	            @Override
	            public int compare(Integer o1, Integer o2) {
	                // TODO Auto-generated method stub
	                return s.substring(o1).compareTo(s.substring(o2));
	            }
	        });
	        
	        String ans = "none";
	        
        	if(sa.length > k) {
        		ans = s.substring(sa[k-1]);
        	}
        
	        System.out.println("#" + tc + " " + ans);
		}
	}
}
