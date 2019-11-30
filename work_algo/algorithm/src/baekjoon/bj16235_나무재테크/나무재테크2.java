package baekjoon.bj16235_나무재테크;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 나무재테크2 {
	static class Tree implements Comparable<Tree>{
		int row;
		int col;
		int age;
		
		public Tree(int row, int col, int age) {
			super();
			this.row = row;
			this.col = col;
			this.age = age;
		}
		
		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}
	static int N, M, K;
	static int[][] A;
	static int[][] map;
	//좌상 상 우상 좌 우 좌하 하 우하
	static int[][] pos = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		//인덱스 1부터
		A = new int[N+1][N+1];
		map = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		PriorityQueue<Tree> pq = new PriorityQueue<Tree>();
		Queue<Tree> deadQ = new LinkedList<Tree>();
		Queue<Tree> liveQ = new LinkedList<Tree>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			pq.add(new Tree(row, col, age));
		}
		//처음에는 모든자리에 양분이 5씩 있다.
		for (int i = 1; i <= N; i++) {
			Arrays.fill(map[i], 5);
		}
		
		for (int k = 1; k <= K; k++) {
			//봄 - 나이만큼 양분을 먹는다. 한칸에 여러 나무가 있다면 어린 순으로 먹고 양분이 나이보다 부족하면 안먹고 죽는다.
			while(!pq.isEmpty()) {
				Tree tree = pq.poll();
				if(map[tree.row][tree.col] >= tree.age) {
					map[tree.row][tree.col] -= tree.age; 
					tree.age += 1;
					
					liveQ.add(tree);
				}else {
					deadQ.add(tree);
				}
			}
			//여름 - 죽은 나무의 나이 / 2 (버림) 만큼 자리에 양분이 생긴다.
			while (!deadQ.isEmpty()) {
				Tree tree = deadQ.poll();
				map[tree.row][tree.col] += (tree.age / 2);
			}
			//가을 - 번식한다. 8방에 나이 1인 나무를 만든다.
			while(!liveQ.isEmpty()) {
				Tree tree = liveQ.poll();
				if(tree.age % 5 == 0) {
					for (int l = 0; l < pos.length; l++) {
						int nr = tree.row + pos[l][0];
						int nc = tree.col + pos[l][1];
						
						if(posCheck(nr, nc)) {
							pq.add(new Tree(nr, nc, 1));
						}
					}
				}
				pq.add(tree);
			}
			//겨울 - s2d2로봇이 양분을 추가한다 A배열참조
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j] += A[i][j];
				}
			}
		}
		
		System.out.println(pq.size());
	}
	
	static boolean posCheck(int row, int col) {
		return row >= 1 && row <= N && col >= 1 && col <= N;
	}
}
