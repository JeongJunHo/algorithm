package baekjoon.bj5577_RBY팡;

import java.util.Scanner;

public class RBY팡 {
	static int n, ans = Integer.MAX_VALUE, remain;
	static int[] arr;
	static boolean[] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		arr = new int[n];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}
		
		for (int i = 0; i < n; i++) {
			//원본을 꺼내놓는다.
			int tmp = arr[i];
			//1~3번 반복
			for (int j = 1; j <= 3; j++) {
				//원본값과 같은 경우는 실행하지않는다.
				if(tmp != j) {
					//변경할 값으로 치환
					arr[i] = j;
					visited = new boolean[n];
					//잔여공을 담을 변수
					remain = n;
					recur();
					ans = Math.min(ans, remain);
				}
			}// j for end
			//다시 복구시킨다.
			arr[i] = tmp;
		}// i for end
		
		System.out.println(ans);
	}
	
	static void recur() {
		int current = 0;
		int cnt = 0;
		for (int i = 0; i < arr.length; i++) {
			//폭발하지않은 곳만 방문
			if(!visited[i]) {
				//현재값이랑 동일한 색이라면 카운트
				if(current == arr[i]) {
					cnt++;
				//다른색이 나왔다면
				}else {
					//4개 이상이 붙어있다면
					if(cnt >= 4) {
						remain -= cnt;
						int idx = i-1;
						while (cnt > 0) {
							if(!visited[idx]) {
								visited[idx] = true;
								cnt--;
							}
							idx--;
						}
						recur();
						return;
					}else {
						cnt = 1;
						current = arr[i];
					}
				}
			}
		}
		
		if(cnt >= 4) {
			remain -= cnt;
		}
	}
}
