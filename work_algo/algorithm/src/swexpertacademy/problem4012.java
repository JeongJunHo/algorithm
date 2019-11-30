package swexpertacademy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Food{
	int score;
	String Ingredients;
	
	Food(int s, String str){
		score = s;
		Ingredients = str;
	}
}

public class problem4012 {
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			int n = sc.nextInt();
			
			int[][] arr = new int[n][n];
			
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			
			boolean[][] check = new boolean[n][n];
			List<Food> list = new ArrayList<Food>();
					
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr.length; j++) {
					if(j != i && check[i][j] == false) {
						list.add(new Food(arr[i][j] + arr[j][i], i + "," + j));
						check[i][j] = true;
						check[j][i] = true;
					}
				}
			}
			
			for (int i = 0; i < check.length; i++) {
				for (int j = 0; j < check.length; j++) {
					System.out.println(i + " " + j + " " + check[i][j]);
				}
			}

			for (Food food : list) {
				System.out.println(food.score + " " + food.Ingredients);
			}
			
			int gap = 99999;
			
			for (int i = 0; i < list.size(); i++) {
				Food first = list.get(i);
				for (int j = 0; j < list.size(); j++) {
					Food second = list.get(j);
					if(i != j) {
						boolean duplicate = false;
						String[] splitStr = second.Ingredients.split(",");
						for (int k = 0; k < splitStr.length ; k++) {
							if(first.Ingredients.contains(splitStr[k])) {
								duplicate = true;
							}
						}
						if(duplicate == false) {
							int tmp = Math.abs(list.get(i).score - list.get(j).score);
							if(tmp < gap) {
								gap = tmp;
							}
						}
					}
				}
			}
			
			System.out.println("#" + tc + " " + gap);
		}
	}
}
