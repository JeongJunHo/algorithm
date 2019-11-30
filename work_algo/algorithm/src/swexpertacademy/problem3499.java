package swexpertacademy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class problem3499 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			int n = sc.nextInt();
			
			String[] arr = new String[n];
			
			for (int i = 0; i < arr.length; i++) {
				arr[i] = sc.next();
			}
			
			int middle = n / 2;

			 List<String> list = new ArrayList<String>();
			
			if(n > 1 && n % 2 == 1) {
				middle++;
				for (int i = 0; i < middle-1; i++) {
					list.add(arr[i]);
					list.add(arr[i+middle]);
				}
				list.add(arr[middle-1]);
			}else {
				for (int i = 0; i < middle; i++) {
					list.add(arr[i]);
					list.add(arr[i+middle]);
				}
			}
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("#").append(tc).append(" ");
			
			for (String str : list) {
				sb.append(str).append(" ");
			}
			
			System.out.println(sb);
		}
	}
}
