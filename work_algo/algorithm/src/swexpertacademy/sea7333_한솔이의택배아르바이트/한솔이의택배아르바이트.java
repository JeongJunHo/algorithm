package swexpertacademy.sea7333_한솔이의택배아르바이트;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class 한솔이의택배아르바이트 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < N; i++) {
				list.add(sc.nextInt());
			}
			
			Collections.sort(list);
			Collections.reverse(list);
			
			int ans = 0;
			while (list.size() != 0) {
				Integer box = list.get(0);
				int h = 1;
				while (h * box < 50) {
					h++;
				}
				
				if(h > list.size()) {
					break;
				}
				
				list.remove(0);
				for (int i = 0; i < h-1; i++) {
					if(list.size() > 0) {
						list.remove(list.size()-1);
					}
				}
				ans++;
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
}
