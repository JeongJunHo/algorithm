package day13;

import java.util.Arrays;

//배열을 통해서 자신의 부모를 저장함으로써 트리를 표현.

//makeSet은 자신의 부모를 자신으로 정해주는 것
//findSet은 자신의 부모가 자신이면 자신이 대표자. 아니면. 부모에 대해서 재귀호출. (재귀호출의 리턴결과를 자신의 부모로 지정.)
//unionSet(a,b)는 b의 대표자의 부모를 a의 대표자로 지정.
public class 상호배타집합 {
	static char[] arr = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
	static int[] parent = new int[7];
	static int[] rank = new int[7];
	
	public static void main(String[] args) {
		for (int i = 0; i < arr.length; i++) {
			makeSet(i);
		}
		System.out.println(Arrays.toString(parent));
		System.out.println(Arrays.toString(rank));
		System.out.println();
		
		unionSet(0, 2);
		System.out.println(Arrays.toString(parent));
		System.out.println(Arrays.toString(rank));
		System.out.println();
		unionSet(2, 5);
		System.out.println(Arrays.toString(parent));
		System.out.println(Arrays.toString(rank));
		System.out.println();
		unionSet(0, 3);
		System.out.println(Arrays.toString(parent));
		System.out.println(Arrays.toString(rank));
		System.out.println();
	}
	
	static void makeSet(int idx) {
		parent[idx] = idx;
	}
	
	static int findSet(int idx) {
		if(parent[idx] == idx) {
			return idx;
		}else {
			parent[idx] = findSet(parent[idx]); 
			return parent[idx];
		}
	}
	
	static void unionSet(int first, int second) {
		int firstParent = findSet(first);
		int secondParent = findSet(second);
		
		if(rank[firstParent] < rank[secondParent]) {
			parent[firstParent] = secondParent;
		}else {
			parent[secondParent] = firstParent;
			if(rank[firstParent] == rank[secondParent]) {
				rank[firstParent]++;
			}
		}
	}
}
