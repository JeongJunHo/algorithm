package jungol.jo1828_냉장고;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class 냉장고3 {
	static class Material{
		int min;
		int max;
		
		public Material(int min, int max) {
			// TODO Auto-generated constructor stub
			this.min = min;
			this.max = max;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		Material[] arr = new Material[n];
		
		for (int i = 0; i < n; i++) {
			arr[i] = new Material(sc.nextInt(), sc.nextInt());
		}
		
		Arrays.sort(arr, new Comparator<Material>() {

			@Override
			public int compare(Material o1, Material o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o2.max, o1.max);
			}
		});
		
		int minTemp = arr[0].min;
		int cnt = 1;
		for (int i = 0; i < arr.length; i++) {
			if(minTemp > arr[i].max) {
				minTemp = arr[i].min;
				cnt++;
			}
			
			minTemp = Math.max(minTemp, arr[i].min);
		}
		System.out.println(cnt);
	}
}
