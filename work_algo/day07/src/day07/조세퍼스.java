package day07;

import java.util.ArrayList;

// N 과 K가 주어짐.
// N 은 출력되어져야 되는 전체 숫자. 1부터 N까지의 숫자가 한번씩 출력되어야함.
// N이 7 이라면
//1 2 3 4 5 6 7
//둘이 한번씩은 출력되어야 하는데, 규칙은
//K번 건너뛰고 하나씩 출력
//K가 3이라면
//3 6 2 7 5 1 4

public class 조세퍼스 {
	public static void main(String[] args) {
		int cnt = 24;
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> josep = new ArrayList<Integer>();
		
		for (int i = 0; i < cnt; i++) {
			list.add(i);
		}
		
		
		
		list.remove(0);
		josep.add(0);
		int idx = 0;
		while (list.size() > 2) {
			idx = (idx+2) % list.size();
			josep.add(list.get(idx));
			list.remove(idx);
		}
		
		System.out.println(josep.toString());
	}
}