package day10;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 입력예제 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("src/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()) {
			System.out.println(sc.nextLine() + "");
		}
	}
}
