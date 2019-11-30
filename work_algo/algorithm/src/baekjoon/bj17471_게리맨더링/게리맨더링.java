package baekjoon.bj17471_게리맨더링;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 게리맨더링 {
	static int N, ans = Integer.MAX_VALUE;
	static int[] man;
	static boolean[][] rel;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		man = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N+1; i++) {
			man[i] = Integer.parseInt(st.nextToken());
		}
		
		rel = new boolean[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				rel[i][Integer.parseInt(st.nextToken())] = true;
			}
		}
		
		powerSet(1, new boolean[N+1], 0, 0);
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	private static void powerSet(int idx, boolean[] visited, int areaA, int areaB) {
		if(idx > N) {
			if(areaA == 0 || areaB == 0) return;
			
			if(Math.abs(areaA - areaB) < ans) {
				boolean check = LinkCheck(visited);
				
				if(check) {
					ans = Math.abs(areaA - areaB);
				}
			}
			return;
		}
		
		visited[idx] = true;
		powerSet(idx + 1, visited, areaA + man[idx], areaB);
		visited[idx] = false;
		powerSet(idx + 1, visited, areaA, areaB + man[idx]);
	}
	
	private static boolean LinkCheck(boolean[] visited) {
		ArrayList<Integer> townA = new ArrayList<Integer>();
		ArrayList<Integer> townB = new ArrayList<Integer>();
		
		for (int i = 1; i <= N; i++) {
			if(visited[i]) {
				townA.add(i);
			}else {
				townB.add(i);
			}
		}
		
		boolean[] tempVisited = new boolean[N+1];
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(townA.get(0));
		tempVisited[townA.get(0)] = true;
		while (!queue.isEmpty()) {
			int num = queue.poll();
			for (int i = 1; i <= N; i++) {
				if(!tempVisited[i] && rel[num][i] && visited[i]) {
					queue.add(i);
					tempVisited[i] = true;
				}
			}
		}
		
		for (int i = 1; i < tempVisited.length; i++) {
			if(tempVisited[i] != visited[i]) {
				return false;
			}
		}
		
		//거짓마을 연결 검사
		Arrays.fill(tempVisited, true);
		queue.clear();
		queue.add(townB.get(0));
		tempVisited[townB.get(0)] = false;
		while (!queue.isEmpty()) {
			int num = queue.poll();
			for (int i = 1; i <= N; i++) {
				if(tempVisited[i] && rel[num][i] && !visited[i]) {
					queue.add(i);
					tempVisited[i] = false;
				}
			}
		}
		
		for (int i = 1; i < tempVisited.length; i++) {
			if(tempVisited[i] != visited[i]) {
				return false;
			}
		}
		
		return true;
	}
}
