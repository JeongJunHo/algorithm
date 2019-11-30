package day02;

public class 정준호 {
	public static void main(String[] args) {
		//박스들의 위치 및 쌓인 갯수
		final int[] DATA = {7,4,2,0,0,6,0,7,0};
		//2차원 배열 생성
		char[][] area = new char[10][10];
		//상자배치
		for (int i = 0; i < DATA.length; i++) {
			//그려야할 상자 갯수
			int box_cnt = DATA[i];

			//할당된 숫자만큼 상자 그리기
			for (int j = 0; j < box_cnt; j++) {
				area[i][j] = 'B';
			}
		}
		
		for (int i = 0; i < area.length; i++) {
			for (int j = 0; j < area[i].length; j++) {
				System.out.print(area[i][j]);
			}
			System.out.println();
		}

		//최대 낙차
		int max = 0;

		//바뀐 상자가 담길 곳
		char[][] area2 = new char[10][10];
		
		//맨 뒤쪽부터 상자가 쌓인 그룹 검색
		for (int i = area.length-1; i >= 0; i--) {
			char[] boxGroup = area[i];
			int boxcnt = 0;
			for (int j = 0; j < boxGroup.length; j++) {
				if(boxGroup[j] == 'B') {
					boxcnt++;
				}
			}
			//상자그룹이 있다면
			if(boxcnt > 0) {
				System.out.println(i);
				System.out.println();
				for (int j = 0; j < boxGroup.length; j++) {
					char box = boxGroup[j];
					//상자가 없다면 다음칸으로
					if(box != 'B') {
						continue;
					}else {
						//상자가 있다면 다른 상자를 만나거나 마지막까지 반복하면서 낙하
						int cnt = 0;
						for (int k = i+1; k < area2.length; k++) {
							//낙하지점에 상자가 없다면
							if(area2[k][j] != 'B') {
								if(k != area2.length-1) {
									cnt++;
								}else {
									area2[k][j] = 'B';
								}
							}else {
								area2[k-1][j] = 'B';
							}
						}
						
						//낙차가 현재 최대값보다 크다면 치환
						if(max < cnt) {
							max = cnt;
						}
					}
				}
			}
		}
		
		for (int i = 0; i < area2.length; i++) {
			for (int j = 0; j < area2[i].length; j++) {
				System.out.print(area2[i][j]);
			}
			System.out.println();
		}
		
		System.out.println(max);
	}
}
