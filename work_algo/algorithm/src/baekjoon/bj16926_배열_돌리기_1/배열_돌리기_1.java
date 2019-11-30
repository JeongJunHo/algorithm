package baekjoon.bj16926_배열_돌리기_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//배열 돌리기1
public class 배열_돌리기_1 {
	static int[][] arr;
	static int n, m;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		arr = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		arr_spin(r);
	}
	
	static void arr_spin(int r) {
		int[][] copy = deepCopy(arr);
		
		//움직일 순서 (아래, 오른쪽, 위, 왼쪽)
		int[][] pos = {{1,0},{0,1},{-1,0},{0,-1}};

		//r번만큼 반복
		for (int i = 0; i < r; i++) {
			arr = deepCopy(copy);
			//움직이는 횟수
			int xMove = n - 1;
			int yMove = m - 1;
			int row = 0;
			int col = 0;
			
			//이동할 수 있을 때 까지
			while (xMove > 0 && yMove > 0) {
				//4방향으로 진행한다.
				for (int j = 0; j < pos.length; j++) {
					int move = pos[j][0] != 0 ? xMove : yMove;
					
					//이동할수 있는 횟수만큼 이동하며 좌표수정
					for (int k = 0; k < move; k++) {
						int nrow = row + pos[j][0];
						int ncol = col + pos[j][1];
						
						copy[nrow][ncol] = arr[row][col];
						
						row = nrow;
						col = ncol;
					}
				}
				
				//안으로 이동
				
				xMove-=2;
				yMove-=2;
				row++;
				col++;
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(copy[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static int[][] deepCopy(int[][] arr){
		int[][] copy = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			copy[i] = arr[i].clone();
		}
		
		return copy;
	}
}
