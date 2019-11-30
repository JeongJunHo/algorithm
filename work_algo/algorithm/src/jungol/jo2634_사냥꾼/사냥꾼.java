package jungol.jo2634_사냥꾼;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 사냥꾼 {
	static class Location implements Comparable<Location>{
		int x;
		int y;
		
		public Location(int col, int row) {
			x = col;
			y = row;
		}
		
		@Override
		public int compareTo(Location o) {
			return this.x - o.x;
		}
		
		@Override
		public String toString() {
			return x + " " + y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		
		int[] gun = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < gun.length; i++) {
			gun[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(gun);
//		System.out.println(Arrays.toString(gun));
		
		Location[] animal = new Location[n];
		for (int i = 0; i < animal.length; i++) {
			st = new StringTokenizer(br.readLine());
			animal[i] = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(animal);
		
//		System.out.println(Arrays.toString(animal));
		
		int currentGun = 0;
		int ans = 0;
		for (int i = 0; i < animal.length; i++) {
			while (currentGun < m-1 && gun[currentGun] < animal[i].x) {
				currentGun++;
			}
			boolean flag = false;
			
			if(currentGun > 0 && Math.abs(animal[i].x - gun[currentGun-1]) + animal[i].y <= l) {
				flag = true;
			}
			if(Math.abs(animal[i].x - gun[currentGun]) + animal[i].y <= l) {
				flag = true;
			}
			
			if(flag) {
				ans++;
			}
		}
		
		System.out.println(ans);
	}
}
