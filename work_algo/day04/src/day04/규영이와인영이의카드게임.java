package day04;

import java.util.Arrays;
import java.util.Scanner;

public class 규영이와인영이의카드게임 {
	static int win;
	static int lose;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//테스트케이스
		int t = sc.nextInt();
		for (int tc = 1; tc <= t; tc++) {
			win = 0;
			lose = 0;
			//규영이 카드 목록
			int[] card = new int[9];
			for (int i = 0; i < card.length; i++) {
				card[i] = sc.nextInt();
			}
			
			//미영카드뭉치 새성
			int[] enemyCard = new int[9];
			int cnt = 0;
			for (int i = 1; i <= 18; i++) {
				//규영카드목록에 없는지 확인 후 삽입
				boolean isNew = true;
				for (int j = 0; j < card.length; j++) {
					if(i == card[j]) {
						isNew = false;
						break;
					}
				}
				if(isNew) {
					enemyCard[cnt++] = i;
				}
			}
			
			perm(card, enemyCard, 0);
			
			System.out.println("#" + tc + " " + win + " " + lose);
		}
	}
	
	static void perm(int[] card, int[] enemyCard, int idx) {
		//기저 모든 경우의 수가 끝났을 경우
		if(card.length == idx) {
			int myTotal = 0;
			int enemyTotal = 0;
			//경기 후 점수 누적
			for (int i = 0; i < enemyCard.length; i++) {
				int sum = card[i] + enemyCard[i];
				//내가 더 높다면
				if(card[i] > enemyCard[i]) {
					myTotal += sum;
				//적이 더 높다면
				}else {
					enemyTotal += sum;
				}
			}
			
			//승패 결정
			if(myTotal > enemyTotal) {
				win++;
			}else if(myTotal < enemyTotal) {
				lose++;
			}
			
			return;
		}
		
		//재귀
		for (int i = idx; i < card.length; i++) {
			swap(enemyCard, idx, i);
			perm(card, enemyCard, idx+1);
			swap(enemyCard, idx, i);
		}
	}
	
	static void swap(int[] card, int a, int b) {
		int tmp = card[a];
		card[a] = card[b];
		card[b] = tmp;
	}
}
