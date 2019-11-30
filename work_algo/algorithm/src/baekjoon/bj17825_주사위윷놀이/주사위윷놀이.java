package baekjoon.bj17825_주사위윷놀이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 주사위윷놀이 {
	static int[][] map = {{},{0,13,16,19,25,30,35,40},{0,22,24,25,30,35,40},{0,28,27,26,25,30,35,40}};
	static boolean[] visited;
	static int[] move = new int[10];
	static int ans;
	static class Horse{
		int mapType;
		int place;
		public Horse(int mapType, int idx) {
			super();
			this.mapType = mapType;
			this.place = idx;
			
		}
		@Override
		public String toString() {
			return "Horse [mapType=" + mapType + ", place=" + place + "]";
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < move.length; i++) {
			move[i] = Integer.parseInt(st.nextToken());
		}
		
		map[0] = new int[21];
		for (int i = 1, num = 2; i < map[0].length; i++, num+=2) {
			map[0][i] = num;
		}
		
		perm_re(new int[10], 0);
		
		System.out.println(ans);
	}


	private static void perm_re(int[] sel, int idx) {
		if(sel.length == idx) {
			Horse[] arr = new Horse[4];
			visited = new boolean[41];
			int sum = 0;
			for (int i = 0; i < arr.length; i++) {
				arr[i] = new Horse(0, 0);
			}
			
			for (int i = 0; i < sel.length; i++) {
				Horse horse = arr[sel[i]];
				if(horse.place == map[horse.mapType].length-1) return;
				visited[map[horse.mapType][horse.place]] = false;
				if(horse.mapType == 0 && horse.place != 0 && horse.place % 5 == 0) {
					horse.mapType = horse.place / 5;
					horse.place = 0;
				}
				horse.place += move[i];
				if(horse.place >= map[horse.mapType].length-1) {
					horse.place = map[horse.mapType].length-1;
					sum += map[horse.mapType][horse.place];
					continue;
				}
				if(visited[map[horse.mapType][horse.place]]) {
					
					continue;
				}
				visited[map[horse.mapType][horse.place]] = true;
//				System.out.println(horse);
//				System.out.println("바닥점수 : " + map[horse.mapType][horse.place]);
				sum += map[horse.mapType][horse.place];
			}
			if(sum == 212) {
				System.out.println(Arrays.toString(sel));
				for (int i = 0; i < arr.length; i++) {
					System.out.println(arr[i]);
				}
			}
			ans = Math.max(ans, sum);
			
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			sel[idx] = i;
			perm_re(sel, idx+1);
		}
	}
}
