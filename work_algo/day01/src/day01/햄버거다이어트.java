package day01;

import java.util.ArrayList;
import java.util.Scanner;

public class 햄버거다이어트 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		//테스트 케이스 숫자 입력
		Integer t = sc.nextInt();
		
		//재료의 수
		Integer n = sc.nextInt();
		//칼로리제한
		Integer l = sc.nextInt();
		
		//재료 리스트
		ArrayList<Meterial> meterialList = new ArrayList<Meterial>();
		//재료의 수만큼 반복
		for (int i = 0; i < n; i++) {
			//재료의 점수
			Integer score = sc.nextInt();
			//재료의 칼로리
			Integer calorie = sc.nextInt();
			
			//재료 객체 생성
			Meterial meterial = new Meterial(score, calorie);
			meterialList.add(meterial);
		}
		
		//햄버거 점수 리스트
		ArrayList<Integer> burgerScoreList = new ArrayList<Integer>();
		
		for (int i = 0; i < meterialList.size(); i++) {
			for (int j = 0; j < args.length; j++) {
				for (int mete = 0; mete < args.length; mete++) {
					
				}
			}
		}
	}
}

class Meterial {
	private Integer score;
	private Integer calorie;
	
	public Meterial() {
		// TODO Auto-generated constructor stub
	}
	
	public Meterial(Integer score, Integer calorie) {
		this.score = score;
		this.calorie = calorie;
	}
	
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getCalorie() {
		return calorie;
	}
	public void setCalorie(Integer calorie) {
		this.calorie = calorie;
	}
	public Double realScore() {
		return (double)score / (double)calorie;
	}
}

