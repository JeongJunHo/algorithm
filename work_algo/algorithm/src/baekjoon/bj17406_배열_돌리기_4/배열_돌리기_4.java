package baekjoon.bj17406_배열_돌리기_4;

import java.util.ArrayList;
import java.util.Scanner;

class Spin{
	int r;
	int c;
	int s;
	public Spin(int r, int c, int s) {
		super();
		this.r = r;
		this.c = c;
		this.s = s;
	}
}

public class 배열_돌리기_4 {
	static int[][] arr;
	static int n, m, k;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		k = sc.nextInt();
		
		arr = new int[n][m];
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		ArrayList<Spin> spinList = new ArrayList<Spin>();
		for (int i = 0; i < k; i++) {
			spinList.add(new Spin(sc.nextInt(),sc.nextInt(),sc.nextInt()));
		}
		
		bf(spinList, 0);
		
		System.out.println(min);
	}
	
	public static void bf(ArrayList<Spin> spinList, int idx) {
		if(idx >= spinList.size()) {
			int[][] copyArr = deepCopy();
			for (Spin spin : spinList) {
				arrSpin(copyArr, spin);
			}
			
			for (int i = 0; i < copyArr.length; i++) {
				int sum = 0;
				for (int j = 0; j < copyArr[i].length; j++) {
					sum += copyArr[i][j];
				}
				min = Math.min(min, sum);
			}
			
			return;
		}
		
		for (int j = idx; j < spinList.size(); j++) {
			swap(spinList, idx, j);
			bf(spinList, idx+1);
			swap(spinList, idx, j);
		}
	}
	
	public static void swap(ArrayList<Spin> spinList, int a, int b) {
		Spin tmp = spinList.get(a);
		spinList.set(a, spinList.get(b));
		spinList.set(b, tmp);
	}
	
	public static int[][] deepCopy(){
		int[][] tmp = new int[n][m];
		
		for (int i = 0; i < arr.length; i++) {
			tmp[i] = arr[i].clone();
		}
		
		return tmp;
	}
	
	public static void arrSpin(int[][] arr, Spin spin) {
		int srow = spin.r - spin.s - 1;
		int scol = spin.c - spin.s - 1;
		int erow = spin.r + spin.s - 1;
		int ecol = spin.c + spin.s - 1;
		while(!((srow + scol) == (erow + ecol)) ) {
			int tmp = arr[srow][scol];
			//위
			for (int i = srow+1; i <= erow; i++) {
				arr[i-1][scol] = arr[i][scol];
			}
			//왼쪽
			for (int i = scol+1; i <= ecol; i++) {
				arr[erow][i-1] = arr[erow][i];
			}
			//아래
			for (int i = erow-1; i >= srow; i--) {
				arr[i+1][ecol] = arr[i][ecol];
			}
			//오른쪽
			for (int i = ecol-1; i > scol; i--) {
				arr[srow][i+1] = arr[srow][i];
			}
			arr[srow][scol+1] = tmp;
			srow++;
			scol++;
			erow--;
			ecol--;
		}
	}
}
