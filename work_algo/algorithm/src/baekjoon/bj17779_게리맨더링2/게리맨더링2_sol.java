package baekjoon.bj17779_게리맨더링2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 게리맨더링2_sol {
	static int N, ans = Integer.MAX_VALUE, total;
	static int[][] arr;
	static int[][] pos = {{1,-1},{1,1}};
	static int[] rank = new int[5];
	static int[][] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new int[N][N];

		int cnt = 1;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
//				arr[i][j] = cnt++;
				total += arr[i][j];
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				visited[i][j] = 1;
				backTrack(i,j,1,1);
				visited[i][j] = 0;
			}
		}
		
		System.out.println(ans);
	}

	private static void backTrack(int tr, int tc, int lCnt, int rCnt) {
		//왼쪽
		int lr = tr + ( pos[0][0] * lCnt );
		int lc = tc + ( pos[0][1] * lCnt );
		if(!posCheck(lr, lc)) return;
		//오른쪽
		int rr = tr + ( pos[1][0] * rCnt );
		int rc = tc + ( pos[1][1] * rCnt );
		if(!posCheck(rr, rc)) return;
		//밑
		int br = lr;
		int bc = lc;
		while (rr + rc != br + bc) {
			br += pos[1][0];
			bc += pos[1][1];
			if(!posCheck(br, bc)) return;
		}
		
		visited[lr][lc] = 1;
		visited[rr][rc] = 1;
		//왼쪽부터 아래 채우기
		fill(lr, lc, br, bc, pos[1], 1);
		//오른쪽부터 아래 채우기
		fill(rr, rc, br, bc, pos[0], 1);
		
		//왼쪽위
		int leftTopPeople = 0;
		for (int i = 0; i < lr; i++) {
			for (int j = 0; j <= tc; j++) {
				if(visited[i][j] == 1) break;
				leftTopPeople += arr[i][j];
			}
		}
		rank[1] = leftTopPeople;
		
		//오른쪽위
		int rightTopPeople = 0;
		for (int i = 0; i <= rr; i++) {
			for (int j = N-1; j > tc; j--) {
				if(visited[i][j] == 1) break;
				rightTopPeople += arr[i][j];
			}
		}
		rank[2] = rightTopPeople;
		
		//왼쪽아래
		int leftBottomPeople = 0;
		for (int i = lr; i < N; i++) {
			for (int j = 0; j < bc; j++) {
				if(visited[i][j] == 1) break;
				leftBottomPeople += arr[i][j];
			}
		}
		rank[3] = leftBottomPeople;
		
		//오른쪽아래
		int rightBottomPeople = 0;
		for (int i = rr+1; i < N; i++) {
			for (int j = N-1; j >= bc; j--) {
				if(visited[i][j] == 1) break;
				rightBottomPeople += arr[i][j];
			}
		}
		rank[4] = rightBottomPeople;
		
		//가운데
		rank[0] = total - rank[1] - rank[2] - rank[3] - rank[4];
		
		Arrays.sort(rank);
		
		ans = Math.min(ans, rank[4] - rank[0]);
		
		//왼쪽부터 아래 채우기
		fill(lr, lc, br, bc, pos[1], 0);
		//오른쪽부터 아래 채우기
		fill(rr, rc, br, bc, pos[0], 0);
		
		backTrack(tr, tc, lCnt+1, rCnt);
		
		visited[lr][lc] = 0;
		visited[rr][rc] = 0;
		
		backTrack(tr, tc, lCnt, rCnt+1);
		
		visited[lr][lc] = 0;
		visited[rr][rc] = 0;
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				System.out.print(visited[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private static void fill(int startRow, int startCol, int endRow, int endCol, int[] direction, int val) {
		while (startRow != endRow && startCol != endCol) {
			startRow += direction[0];
			startCol += direction[1];
			
			visited[startRow][startCol] = val;
		}
	}

	private static boolean posCheck(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}
}
