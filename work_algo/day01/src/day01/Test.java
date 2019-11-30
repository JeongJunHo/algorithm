package day01;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("나이 : ");
		int age = Integer.parseInt(scan.nextLine());
		System.out.print("이름 : ");
		String name = scan.nextLine();
		
		System.out.println("나이는 " + age + " 이름은 " + name);
	}
}
