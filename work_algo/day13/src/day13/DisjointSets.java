package day13;

import java.util.Arrays;

public class DisjointSets {
	static int[] parents;
	static int[] rank;
	public static void main(String[] args) {
		parents = new int[6];
		rank = new int[6];
		makeSet(0);
		makeSet(1);
		makeSet(2);
		makeSet(3);
		makeSet(4);
		makeSet(5);
		//0 1 2 3 4 5
		System.out.println(Arrays.toString(parents));
		
		//1에 속한 집합의 대표자를 0이 속한 집합의 대표자로 변경.
		union(0,1);
		//0 0 2 3 4 5 6
		System.out.println(Arrays.toString(parents));
		union(2,3);
		System.out.println(Arrays.toString(parents));
		union(0,3);
		System.out.println(Arrays.toString(parents));
		union(4,5);
		System.out.println(Arrays.toString(parents));
		union(2,4);
		System.out.println(Arrays.toString(parents));
	}
	
	private static void makeSet(int x) {
		// TODO Auto-generated method stub
		parents[x] = x;
		rank[x] = 0;
	}
	
	private static int findSet(int x) {
		// TODO Auto-generated method stub
		if(x == parents[x]) {
			return x;
		}else {
			parents[x] = findSet(parents[x]);
			return parents[x];
		}
	}
	
	private static void union(int x, int y) {
		// TODO Auto-generated method stub
		int px = findSet(x);
		int py = findSet(y);
		
		if(rank[px] > rank[py]) {
			parents[py] = px;
		}else {
			parents[px] = py;
			if(rank[px] == rank[py])
				rank[py]++;
		}
		
	}
}
