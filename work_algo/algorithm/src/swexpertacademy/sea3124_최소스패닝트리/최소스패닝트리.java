package swexpertacademy.sea3124_최소스패닝트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 최소스패닝트리 {
	static int[][] arr;
	static int[] parents, rank;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			//정점의 개수
			int v = Integer.parseInt(st.nextToken());
			//간선의 개수
			int e = Integer.parseInt(st.nextToken());
			arr = new int[e][3];
			
			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
				arr[i][2] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					return Integer.compare(o1[2], o2[2]);
				}
			});
			
			parents = new int[v+1];
			rank = new int[v+1];
			
			for (int i = 1; i <= v; i++) {
				makeSet(i);
			}
			
			long sum = 0;
			for (int i = 0; i < e; i++) {
				int a = arr[i][0];
				int b = arr[i][1];
				int c = arr[i][2];
				if(findSet(a) != findSet(b)) {
					sum += c;
					union(a, b);
				}
			}
			
			System.out.println("#" + tc + " " + sum);
		}
	}
	
	static void makeSet(int x) {
		parents[x] = x;
	}
	
	static int findSet(int x) {
		if(parents[x] == x) {
			return x;
		}
		
		parents[x] = findSet(parents[x]);
		return parents[x];
	}
	
	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		
		if(rank[px] > rank[py]) {
			parents[py] = px;
		}else {
			parents[px] = py;
			if(rank[px] == rank[py]) {
				rank[py]++;
			}
		}
	}
}
