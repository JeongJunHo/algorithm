package swexpertacademy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class problem5658 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			//숫자의 갯수(4의 배수이며, 8 <= N <= 28)
			int n = sc.nextInt();
			//비밀번호가 되는 크기 순서
			int k = sc.nextInt();
			//문자로 이루어진 n개의 16진수 숫자
			String num = sc.next();
			//나온 숫자를 담을 set
			Set<Integer> set = new HashSet<Integer>();
			//한 변의 길이
			int side = n / 4;
			StringBuilder sb = new StringBuilder();
			//변의 길이 만큼 반복하며 숫자 저장
			for (int i = 0; i < side; i++) {
				//숫자 순회
				for (int j = 0; j < n; j++) {
					//숫자에 접근시 변을 몇번 움직였는지를 인덱스에 더하고 길이로 나머지 연산
					sb.append(String.valueOf(num.charAt((j + i) % n)));
					//변의 길이만큼 빌더가 채워졌을 경우 set에 추가
					if(sb.length() == side) {
						set.add(Integer.parseInt(sb.toString(), 16));
						sb.setLength(0);
					}
				}
			}
			//정렬을 위해 리스트로 변환
			List<Integer> list = new ArrayList<Integer>(set);
			//오름차순 정렬 후 내림차순으로 변환
			Collections.sort(list);
			Collections.reverse(list);
			
			System.out.println("#" + tc + " " + list.get(k-1));
		}
	}
}
