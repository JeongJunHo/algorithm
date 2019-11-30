package swexpertacademy;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class problem4008 {
	//연산을 진행할 숫자를 저장할 리스트
	static int[] number;
	//최소값을 저장할 변수
	static int min;
	//최대값을 저장할 변수
	static int max;
	//한번 수행된 연산자순서를 기억할 Collection타입 Set (중복을 허용하지 않는다. 중복값 입력시 덮어쓰기 수행)
	static Set<String> dupCheck;

	public static void main(String[] args) {
		//사용자 입력을 받을 객체 생성
		Scanner sc = new Scanner(System.in);
		//사용할 연산자문자열을 만들 때 참고할 연산자 템플릿 리스트 (사용자의 입력이 + - * / 순서로 항상 고정으로 들어오기 때문에 사용)
		final char[] OPERATORS = { '+', '-', '*', '/' };
		//테스트 케이스 수
		int t = sc.nextInt();

		//테스트 케이스 수만큼 반복
		for (int tc = 1; tc <= t; tc++) {
			dupCheck = new HashSet<String>();
			//최소값을 저장하기 위해 가장 큰 수를 기본값으로 세팅
			min = Integer.MAX_VALUE;
			//최대값을 저장하기 위해 가장 작은 수를 기본값으로 세팅
			max = Integer.MIN_VALUE;
			//연산에 사용할 숫자의 갯수
			int n = sc.nextInt();
			//숫자의 갯수만큼 배열 생성
			number = new int[n];
			
			//사용자가 입력하는 연산자의 숫자대로 문자열을 붙이기 위한 StringBuilder
			StringBuilder sb = new StringBuilder();
			
			//연산자 템플릿 리스트만큼 반복(사용자는 템플릿 순서대로 각 연산자의 숫자를 입력해준다.)
			for (int i = 0; i < OPERATORS.length; i++) {
				//현재 연산자의 갯수를 입력 받아 갯수만큼 StringBuilder에 저장한다.
				int tmp = sc.nextInt();
				for (int j = 0; j < tmp; j++) {
					sb.append(OPERATORS[i]);
				}
			}

			//StringBuilder에서 연산자 문자열 추출
			String operStr = sb.toString();
			//문자 리스트로 변환
			char[] operArr = operStr.toCharArray();

			//숫자를 입력받는다 
			for (int i = 0; i < n; i++) {
				number[i] = sc.nextInt();
			}

			//순열 재귀 호출
			perm(operArr, 0);
			
			//만들어진 최대값과 최소값의 차이를 출력한다.
			System.out.println("#" + tc + " " + (max - min));
		}//tc for end
	}//main end

	public static void perm(char[] arr, int idx){
		//이미 수행한 연산자조합이라면 종료
		if(dupCheck.contains(String.valueOf(arr))) {
			return;
		}
		//기저에 도달했다면 종료
		if(idx == arr.length) {
			//해당 조합을 다시 하지 않도록 Set에 기억
			dupCheck.add(String.valueOf(arr));
			//첫번째 숫자를 먼저 뽑는다.
			int result = number[0];
			//2번째 숫자부터 돌면서 연산자에 맞게 연산을 한다.
			for (int i = 1; i < number.length; i++) {
				switch (arr[i-1]) {
					case '+':
						result += number[i];
						break;
					case '-':
						result -= number[i];
						break;
					case '*':
						result *= number[i];
						break;
					case '/':
						result /= number[i];
						break;
				}
			}
			//최대값과 비교하여 더 크다면 치환
			if(max < result) {
				max = result;
			}
			//최소값과 비교하여 더 작다면 치환
			if(min > result) {
				min = result;
			}
			
			return;
		}
		
	   for (int i = idx; i < arr.length; i++) {
		   swap(arr, idx, i);
		   perm(arr, idx + 1);
		   swap(arr, idx, i);
	   }
	   
   }

	public static void swap(char[] arr, int a, int b) {
		char tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}