package day12;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class DFS {
	static int[][] arr;
	static boolean[] check;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int v = sc.nextInt();
		int r = sc.nextInt();
		
		check = new boolean[v+1];
		
		arr = new int[v+1][v+1];
		
		for (int i = 0; i < r; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			arr[a][b] = 1;
			arr[b][a] = 1;
		}
		
//		for (int i = 0; i < arr.length; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
		dfs(1);
		System.out.println();
		
		Arrays.fill(check, false);
		//1 3 7 6 5 2 4
		//정점의 갯수 만큼 방문 배열을 준비한다.
		//정수 스택을 준비한다.(각 정점의 자료형이 인트)
		Stack<Integer> stack = new Stack<Integer>();
		//첫번째 방문할 정점을 스택에 담는다.
		stack.push(1);
		//아래작업을 스택이 빌때까지 반복
		while (!stack.isEmpty()) {
			//스택에서 팝 해서 방문하지 않았다면
			int tmp = stack.pop();
			if(check[tmp]) {
				continue;
			}
			check[tmp] = true;
			System.out.print(tmp + " ");
			for (int i = 1; i <= v; i++) {
				if(!check[i] && arr[tmp][i] == 1) {
					//해당 정점과 연결되어있으면서, 방문하지 않은 정점이 있다면, 스택에 푸쉬
					stack.push(i);
				}
			}
		}
	}
	
	static void dfs(int vertex) {
		check[vertex] = true;
		System.out.print(vertex + " ");
		for (int i = 1; i < arr.length; i++) {
			if(!check[i] && arr[vertex][i] == 1) {
				check[i] = true;
				dfs(i);
			}
		}
	}
}
