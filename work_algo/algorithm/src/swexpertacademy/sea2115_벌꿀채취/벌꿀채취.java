package swexpertacademy.sea2115_벌꿀채취;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 벌꿀채취 {
	static int N, M, C, ans;
	static int[][] arr;
	static ArrayList<Worker> wList;
	static class Worker{
		int row;
		int col;
		int sum;
		int price;
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		public Worker(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		@Override
		public String toString() {
			return row + " " + col + " " + sum + " " + price;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			ans = Integer.MIN_VALUE;
			wList = new ArrayList<Worker>();
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= N-M; j++) {
					Worker w = new Worker(i, j);
					int sum = 0;
					for (int k = 0; k < M; k++) {
						w.list.add(arr[i][j+k]);
						sum += arr[i][j+k];
					}
					w.sum = sum;
					wList.add(w);
				}
			}
			
			for (Worker worker : wList) {
				if(worker.sum > C) {
					worker.price = powerSet(worker.list, new boolean[worker.list.size()], 0);
				}else {
					int price = 0;
					for (Integer n : worker.list) {
						price += n * n;
					}
					worker.price = price;
				}
			}
			
			comb(new Worker[2], 0, 0);
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static int powerSet(ArrayList<Integer> list, boolean[] sel, int idx) {
		int result = 0;
		if(list.size() == idx) {
			int sum = 0;
			for (int i = 0; i < list.size(); i++) {
				if(sel[i]) {
					sum += list.get(i);
				}
			}
			
			if(sum > C) {
				return 0; 
			}
			
			int price = 0;
			
			for (int i = 0; i < list.size(); i++) {
				if(sel[i]) {
					price += list.get(i) * list.get(i);
				}
			}
			
			return price;
		}
		
		sel[idx] = true;
		result = Math.max(result, powerSet(list, sel, idx+1));
		sel[idx] = false;
		result = Math.max(result, powerSet(list, sel, idx+1));
		
		return result;
	}

	static void comb(Worker[] sel, int idx, int s_idx) {
		if(sel.length == s_idx) {
			if(sel[0].row == sel[1].row && sel[0].col + M - 1 >= sel[1].col) {
				return;
			}
			int totalPrice = 0;
			for (Worker worker : sel) {
				totalPrice += worker.price;
			}
			ans = Math.max(ans, totalPrice);
			
			return;
		}
		
		if(idx == wList.size()) {
			return;
		}
		
		sel[s_idx] = wList.get(idx);
		comb(sel, idx + 1, s_idx + 1);
		comb(sel, idx + 1, s_idx);
		
	}
}
