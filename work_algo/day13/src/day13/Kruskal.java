package day13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Kruskal {
	static int[] parents, rank;
	static String src = "7 11 \n"
						+ "0 1 31\n"
						+ "0 2 31\n"
						+ "0 6 31\n"
						+ "0 5 60\n"
						+ "1 2 21\n"
						+ "2 4 46\n"
						+ "2 6 25\n"
						+ "3 4 34\n"
						+ "4 6 51\n"
						+ "5 3 18\n"
						+ "5 4 40\n";
	public static void main(String[] args) {
		Scanner sc = new Scanner(src);
		int V = sc.nextInt();
		int E = sc.nextInt();
		
		int[][] edges = new int[E][3]; //0번칸 출발정점 1번칸 도착정점 2번칸 비용
		
		//edges배열을 탐색하면서 가장 짧은 간선의 출발-도착 정점이 서로 다른 집합이라면 간선을 선택하고 두 집합을 합친다.
		//V-1개의 간선이 선택되면 종료 :)
		for (int i = 0; i < edges.length; i++) {
			edges[i][0] = sc.nextInt();
			edges[i][1] = sc.nextInt();
			edges[i][2] = sc.nextInt();
		}
		//edges배열을 2번칸의 크기순으로 오름차순 정렬한다.
		
		Arrays.sort(edges, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1[2], o2[2]);
			}
		});
		
		//V개의 정점만큼 상호배타집합을 위한 parents배열을 생성한다.(rank는 옵션)
		parents = new int[V];
		rank = new int[V];
		//V개의 정점들을 모두 makeSet해서 각자 독립된 집합으로 만들어 준다.
		for (int i = 0; i < V; i++) {
			makeSet(i);
		}
		
	}
	private static void makeSet(int i) {
		
	}
}
