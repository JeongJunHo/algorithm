package baekjoon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


/*
문제
자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.

1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
고른 수열은 오름차순이어야 한다.
입력
첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)

출력
한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.

수열은 사전 순으로 증가하는 순서로 출력해야 한다.

예제 입력 1 
3 1
예제 출력 1 
1
2
3
예제 입력 2 
4 2
예제 출력 2 
1 2
1 3
1 4
2 3
2 4
3 4
예제 입력 3 
4 4
예제 출력 3 
1 2 3 4
 */
public class problem15650 {
	static boolean[] isUse;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		//n까지의 배열
		int[] arr = new int[n];
		//각 배열의 사용여부 배열
		isUse = new boolean[n];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i+1;
		}
		
		//출력용 리스트
		ArrayList<Integer> list = new ArrayList<Integer>();
		//메소드 시작
		dfs(arr, n, m, list);
	} 
	
	static void dfs(int[] arr, int n, int m, ArrayList<Integer> list) {
		if(list.size() == m) {
			//리스트 값이 오름차순인지 검증
			boolean check = true;
			for (int i = 0; i < list.size(); i++) {
				for (int j = i; j < list.size(); j++) {
					//다음 값 중 현재 값 보다 적은 값이 들어있는지 탐색
					if(list.get(i) > list.get(j)) {
						check = false;
						break;
					}
				}
				//이미 한번이라도 더 작은 수를 만났다면 중지
				if(check == false) {
					break;
				}
			}
			
			//탐색 결과가 오름차순이 맞다면 출력
			if(check == true) {
				System.out.println(list.toString());
			}
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if (isUse[i] == false) {
				isUse[i] = true;
				//재귀로 넘기기위한 새로운 리스트 생성 (인스턴스 타입이기 때문에 주소를 공유하지 않기 위함)
				ArrayList<Integer> tmpList = new ArrayList<Integer>();
				//현재까지 넣어둔 리스트 담기
				tmpList.addAll(list);
				//현재 반복중인 값 추가 입력
				tmpList.add(arr[i]);
				//재귀 호출
				dfs(arr, n, m, tmpList);
				isUse[i] = false;
			}
		}
	}
}