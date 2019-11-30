package swexpertacademy;

import java.util.ArrayList;
import java.util.List;

public class problem4012_2 {
	static boolean[] materials;
	static int gap = Integer.MAX_VALUE;
	static int n;
	
	static ArrayList<Integer> list = new ArrayList<Integer>();
	public static void main(String[] args) {
		
		bf(4, 0, list);
	}
	
	public static void bf(int idx, int cnt, List<Integer> list) {
		if(list.size() >= n/2) {
			System.out.println(list);
			return;
		}
		
		for (int i = idx; i < n; i++) {
			list.add(i);
			bf(n, idx+1, list);
		}
	}
}
