package baekjoon.bj16987_계란으로계란치기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 계란으로계란치기 {
	static class Egg{
		int s;
		int w;
		public Egg(int s, int w) {
			super();
			this.s = s;
			this.w = w;
		}
		@Override
		public String toString() {
			return "Egg [s=" + s + ", w=" + w + "]";
		}
		
		
	}
	static int N, ans;
	static Egg[] eggArr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		eggArr = new Egg[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			eggArr[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		backTrack(0);
		
		System.out.println(ans);
	}
	private static void backTrack(int idx) {
		if(idx == N) {
			int cnt = 0;
			for (int j = 0; j < N; j++) {
				if(eggArr[j].s <= 0) cnt++;
			}
			ans = Math.max(ans, cnt);
			
			return;
		}
		
		boolean check = false;
		Egg myEgg = eggArr[idx];
		for (int j = 0; j < N; j++) {
			if(idx == j) continue;
			if(myEgg.s <= 0) break;
			if(eggArr[j].s > 0) {
				check = true;
				eggArr[j].s -= myEgg.w;
				myEgg.s -= eggArr[j].w;
				backTrack(idx+1);
				eggArr[j].s += myEgg.w;
				myEgg.s += eggArr[j].w;
			}
		}
		
		if(!check) {
			backTrack(idx+1);
		}
	}
}

