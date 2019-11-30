package swexpertacademy.sea1247_최적_경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Location{
	int row;
	int col;
	
	Location(int r, int c){
		row = r;
		col = c;
	}
}

public class 최적_경로 {
	static int ans;
	static Location company, house;
	static Location[] customer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int t = Integer.parseInt(st.nextToken());
		
		for (int tc = 1; tc <= t; tc++) {
			ans = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			
			customer = new Location[n];
			
			//좌표입력
			st = new StringTokenizer(br.readLine());
			company = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			house = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for (int i = 0; i < n; i++) {
				customer[i] = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			perm(0);
			
			System.out.println("#" + tc + " " + ans);
		}// tc for end
	}// main end
	
	static void perm(int idx) {
		if(idx == customer.length) {
			int sum = calcDistance(company, customer[0]);
			for (int i = 0; i < customer.length-1; i++) {
				sum += calcDistance(customer[i], customer[i+1]);
			}
			sum += calcDistance(customer[customer.length-1], house);
			
			ans = Math.min(ans, sum);
			return;
		}
		
		for (int i = idx; i < customer.length; i++) {
			swap(idx, i);
			perm(idx + 1);
			swap(idx, i);
		}
		
	}
	
	static void swap(int a, int b) {
		Location tmp = customer[a];
		customer[a] = customer[b];
		customer[b] = tmp;
	}
	
	static int calcDistance(Location a, Location b) {
		return Math.abs(a.row - b.row) + Math.abs(a.col - b.col);
	}
}
