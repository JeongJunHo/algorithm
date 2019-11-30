package baekjoon.bj1043_거짓말;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 거짓말 {
	static int N, M, ans;
	static boolean[] people;
	static List<Integer>[] party;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		people = new boolean[N+1];
		
		st = new StringTokenizer(br.readLine());
		int trueKnow = Integer.parseInt(st.nextToken());
		for (int i = 0; i < trueKnow; i++) {
			people[Integer.parseInt(st.nextToken())] = true;
		}
		
		party = new List[M];
		for (int i = 0; i < M; i++) {
			party[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int peopleCnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < peopleCnt; j++) {
				int idx = Integer.parseInt(st.nextToken());
				party[i].add(idx);
			}
		}
		
		for (int k = 0; k < party.length; k++) {
			for (int i = 0; i < party.length; i++) {
				boolean check = false; 
				for (int j = 0; j < party[i].size(); j++) {
					if(people[party[i].get(j)]) {
						check = true;
						break;
					}
				}
				if(check) {
					for (int num : party[i]) {
						people[num] = true;
					}
				}
			}
		}
		
		for (List<Integer> list : party) {
			boolean check = false;
			for (Integer num : list) {
				if(people[num]) {
					check = true;
					break;
				}
			}
			
			if(!check) {
				ans++;
			}
		}
		
		System.out.println(ans);
	}
}
