package jungol.jo1828_냉장고;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 냉장고 {
	static class Freeze{
		int min;
		int max;
		
		public Freeze(int min, int max) {
			this.min = min;
			this.max = max;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		List<Freeze> list = new ArrayList<Freeze>();
		
		for (int i = 0; i < n; i++) {
			int minTemp = sc.nextInt();
			int maxTemp = sc.nextInt();
			
			if(list.size() == 0) {
				list.add(new Freeze(minTemp, maxTemp));
			}else {
				boolean add = false;
				for (Freeze freeze : list) {
					//현재 화학물질의 최저 온도가 냉장고 최저보다는 높고 최고보다는 낮다면
					if(freeze.min <= minTemp && freeze.max >= minTemp) {
						freeze.min = minTemp;
						add = true;
						break;
					}
					//현재 화학물질의 최고 온도가 냉장고 최고보다는 낮고 최저보다는 높다면 
					if(freeze.max >= maxTemp && freeze.max <= maxTemp) {
						freeze.max = maxTemp;
						add = true;
						break;
					}
				}
				
				if(!add) {
					list.add(new Freeze(minTemp, maxTemp));
				}
			}
		}
		
		System.out.println(list.size());
	}
}
