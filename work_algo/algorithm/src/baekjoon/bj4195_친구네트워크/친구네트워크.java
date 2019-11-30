package baekjoon.bj4195_친구네트워크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 친구네트워크 {
	static int[] parent;
	static ArrayList<String> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < t; tc++) {
			parent = new int[100000];
			list = new ArrayList<String>();
			int f = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < f; i++) {
				st = new StringTokenizer(br.readLine());
				String first = st.nextToken();
				String second = st.nextToken();
				int firstIdx = -1, secondIdx = -1;
				boolean firstCheck = true;
				boolean secondCheck = true;
				for (int j = 0; j < list.size(); j++) {
					String name = list.get(j);
					if(name.equals(first)){
						firstIdx = j;
						firstCheck = false;
						break;
					}
				}
				
				for (int j = 0; j < list.size(); j++) {
					String name = list.get(j);
					if(name.equals(second)) {
						secondIdx = j;
						secondCheck = false;
						break;
					}
				}
				
				if(firstCheck) {
					list.add(first);
					firstIdx = list.size()-1;
					makeSet(firstIdx);
				}
				if(secondCheck) {
					list.add(second);
					secondIdx = list.size()-1;
					makeSet(secondIdx);
				}
				
				unionSet(firstIdx, secondIdx);
				
				int cnt = 0;
				for (int k = 0; k < list.size(); k++) {
					if(findSet(firstIdx) == findSet(parent[k])) {
						cnt++;
					}
				}
				sb.append(cnt).append("\n");
			}
		}
		System.out.println(sb);
	}
	
	static void makeSet(int idx) {
		parent[idx] = idx;
	}
	
	static int findSet(int idx) {
		if(parent[idx] == idx) {
			return idx;
		}
		parent[idx] = findSet(parent[idx]);
		return parent[idx];
	}
	
	static void unionSet(int a, int b) {
		int pa = findSet(a);
		int pb = findSet(b);
		
		parent[pb] = pa;
	}
}
