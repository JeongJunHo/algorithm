package swexpertacademy.sea5644_무선충전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 무선충전 {
	static class Charger{
		int row;
		int col;
		int cover;
		int perform;
		public Charger(int row, int col, int cover, int perform) {
			super();
			this.row = row;
			this.col = col;
			this.cover = cover;
			this.perform = perform;
		}
	}
	
	static class person{
		int row;
		int col;
		public person(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	static int M, A, ans;
	static int[] aDirect;
	static int[] bDirect;
	static person a;
	static person b;
	static int[][] pos = {{0,0},{-1,0},{0,1},{1,0},{0,-1}};
	static Charger[] chargerArr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			ans = 0;
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			chargerArr = new Charger[A];
			aDirect = new int[M];
			bDirect = new int[M];
			a = new person(1, 1);
			b = new person(10, 10);
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				aDirect[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				bDirect[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int col = Integer.parseInt(st.nextToken());
				int row = Integer.parseInt(st.nextToken());
				int cover = Integer.parseInt(st.nextToken());
				int perform = Integer.parseInt(st.nextToken());
				chargerArr[i] = new Charger(row, col, cover, perform);
			}

			//최초 충전
			int aMax = 0;
			int bMax = 0;
			for (int i = 0; i < chargerArr.length; i++) {
				Charger charger = chargerArr[i];
				if(chargeCheck(charger.cover, a.row, a.col, charger.row, charger.col)) aMax = Math.max(aMax, charger.perform);
				if(chargeCheck(charger.cover, b.row, b.col, charger.row, charger.col)) bMax = Math.max(bMax, charger.perform);
			}
			//초기 값 더하기
			ans += (aMax + bMax);
			
			for (int i = 0; i < M; i++) {
				//이동
				a.row += pos[aDirect[i]][0];
				a.col += pos[aDirect[i]][1];
				b.row += pos[bDirect[i]][0];
				b.col += pos[bDirect[i]][1];
				//충전
				chargeAction();
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	private static void chargeAction() {
		boolean[][] nearby = new boolean[2][A];
		
		for (int i = 0; i < chargerArr.length; i++) {
			Charger charger = chargerArr[i];
			if(chargeCheck(charger.cover, a.row, a.col, charger.row, charger.col)) nearby[0][i] = true;
			if(chargeCheck(charger.cover, b.row, b.col, charger.row, charger.col)) nearby[1][i] = true;
		}
		
		int aMax = 0;
		int aMaxIdx = -1;
		int bMax = 0;
		
		//A의 제일 높은 걸 찾는다.
		//B와 겹치는지 검사 후
		//겹친다면 A,B를 모두 포함한 것 중 해당값을 제외한 나머지 중 제일 높은 것 선택(해당값은 이미 선택한걸로 침)
		//안겹친다면 두번째도 제일 높은걸 선택 후 합산
		
		for (int i = 0; i < A; i++) {
			if(nearby[0][i]) {
				if(aMax < chargerArr[i].perform) {
					aMaxIdx = i;
					aMax = chargerArr[i].perform;
				}
			}
		}
		
		//A가 선택되었다면
		if(aMaxIdx != -1) {
			//B와 겹치는지 검사
			//겹친경우
			if(nearby[1][aMaxIdx]) {
				for (int i = 0; i < A; i++) {
					//현재 중복되는 최고값을 제외한 나머지 들 중 최고값 선택
					if(aMaxIdx != i && (nearby[0][i] || nearby[1][i])) {
						bMax = Math.max(bMax, chargerArr[i].perform);
					}
				}
				ans += (aMax + bMax);
			//안겹칠경우
			}else {
				for (int i = 0; i < A; i++) {
					if(nearby[1][i]) {
						bMax = Math.max(bMax, chargerArr[i].perform);
					}
				}
				ans += (aMax + bMax);
			}
			
			
		//A가 선택이 안되었다면 B만 진행
		}else {
			for (int i = 0; i < A; i++) {
				if(nearby[1][i]) {
					bMax = Math.max(bMax, chargerArr[i].perform);
				}
			}
			ans += bMax;
		}
	}

	static boolean chargeCheck(int cover, int pr, int pc, int cr, int cc) {
		if(cover >= (Math.abs(pr-cr) + Math.abs(pc-cc))) return true;
		return false;
	}
}
