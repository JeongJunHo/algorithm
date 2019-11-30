package baekjoon.bj14501_퇴사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Counsel {
	int day;
	int pay;
	
	Counsel(int d, int p){
		day = d;
		pay = p;
	}
}

public class 퇴사 {
	//일할 수 있는 일수, 최대로 얻을 수 있는 이익
	static int n, ans = Integer.MIN_VALUE;
	static Counsel[] schedule;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		//상담 스케줄 배열
		schedule = new Counsel[n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			//상담별 시간과 비용을 저장한다.
			schedule[i] = new Counsel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		//조합 실행
		comb(0, 0);
		
		System.out.println(ans);
	}
	
	static void comb(int idx, int wage) {
		//퇴사일에 맞게 일이 끝났을 경우
		if(idx == n) {
			//현재 급여보다 많이 벌었다면 치환
			ans = Math.max(ans, wage);
			
			return;
		}
		//퇴사일에 맞추지 못했다면 종료
		if(idx > n) {
			return;
		}
		
		//상담을 수락하고 급여를 누적한 후 해당 상담이 끝나는 날로 이동
		comb(idx + schedule[idx].day, wage + schedule[idx].pay);
		//상담을 안하고 다음날로 이동
		comb(idx + 1, wage);
	}
}
