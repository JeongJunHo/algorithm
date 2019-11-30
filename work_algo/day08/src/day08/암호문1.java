package day08;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 암호문1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for (int tc = 1; tc <= 10; tc++) {
			int n = sc.nextInt();
			
			List<Integer> pwd = new ArrayList<Integer>();
			
			for (int i = 0; i < n; i++) {
				pwd.add(sc.nextInt());
			}
			
			//명령어 수
			int command = sc.nextInt();
			
			for (int i = 0; i < command; i++) {
				//문자버림
				sc.next();
				int pos = sc.nextInt();
				int length = sc.nextInt();
				List<Integer> addList = new ArrayList<Integer>();
				
				for (int j = 0; j < length; j++) {
					addList.add(sc.nextInt());
				}
				
				pwd.addAll(pos, addList);
			}
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("#").append(tc).append(" ");
			
			for (int j = 0; j < 10; j++) {
				sb.append(pwd.get(j)).append(" ");
			}
			
			System.out.println(sb);
		}
	}
}
