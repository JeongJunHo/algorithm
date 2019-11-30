package swexpertacademy.sea4408_자기방으로돌아가기;

import java.util.Arrays;
import java.util.Scanner;

class Scope{
	int start;
	int end;
	
	Scope(int s, int e){
		start = s;
		end = e;
	}
}

public class 자기방으로돌아가기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			int n = sc.nextInt();
			
			Scope[] arr = new Scope[n];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = new Scope(sc.nextInt(), sc.nextInt());
			}
			
			int[] visited = new int[401];
			for (int i = 0; i < arr.length; i++) {
				fill(visited, arr[i]);
			}
			
			int cnt = 0;
			
			for (int i = 2; i < visited.length; i+=2) {
				cnt = Math.max(visited[i], cnt);
			}
			
			
			System.out.println("#" + tc + " " + cnt);
		}
	}
	
	static void fill(int[] visited, Scope scope) {
		int start = Math.min(scope.start, scope.end);
		int end = Math.max(scope.start, scope.end);
		
		if(start % 2 != 0) {
			start += 1;
		}
		
		for (int i = start; i <= end; i+=2) {
			visited[i]= visited[i]+1;
		}
	}
}
