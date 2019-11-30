package swexpertacademy;

import java.util.Scanner;

public class problem1284 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int tc = 1; tc <= t; tc++) {
			//A사 요금
			int p = sc.nextInt();
			//B사 기본요금
			int q = sc.nextInt();
			//r리터까지는 기본요금
			int r = sc.nextInt();
			//B사 초과요금
			int s = sc.nextInt();
			//수도 사용량
			int w = sc.nextInt();
			
			//A사 요금
			int aPrice = p * w;
			int bPrice = 0;
			//B사 요금계산 기본사용량을 초과한 경우
			if(w > r) {
				//초과 사용량
				int exceed = w - r;
				//초과 사용량 요금
				int exceedRate = exceed * s;
				//B사 최종요금
				bPrice = q + exceedRate;
			//B사 요금계산 기본사용량 이내로 썼을 경우
			}else {
				bPrice = q;
			}
			
			System.out.println("#" + tc + " " + (aPrice > bPrice ? bPrice : aPrice));
		}// for end
	}// main end
}// class end
