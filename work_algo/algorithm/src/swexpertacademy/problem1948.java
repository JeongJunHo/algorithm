package swexpertacademy;

import java.util.Calendar;
import java.util.Scanner;

public class problem1948 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		//해당 월의 일수를 출력 배열 0번은 쓰지않는다
		int[] monthDay = {0,31,28,31,30,31,30,31,31,30,31,30,31};
		
		for (int tc = 1; tc <= t; tc++) {
			//과거 월
			int beforeMonth = sc.nextInt();
			//과거 일
			int beforeDay = sc.nextInt();
			//미래 월
			int afterMonth = sc.nextInt();
			//미래 일
			int afterDay = sc.nextInt();
			
			//경과일
			int gap = 0;
			
			while(true) {
				//미래일과 보다 커졌다면
				if(afterMonth <= beforeMonth && afterDay < beforeDay) {
					break;
				}
				//해당월의 날짜보다 적을 경우 다음날로
				if(monthDay[beforeMonth] >= beforeDay) {
					beforeDay++;
					gap++;
				//해당월의 날짜를 초과했을 경우 다음달 1일로 초기화
				}else {
					beforeMonth++;
					beforeDay = 1;
				}
			}
			System.out.println("#" + tc + " " + gap);
		}
	}
}
