package baekjoon.bj17281_야구공;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 야구공 {
	static final int MAN = 9;
	//이닝수
	static int N;
	//도루중인 선수를 담을 큐
	static Queue<Integer> q = new LinkedList<Integer>();
	//답
	static int ans = Integer.MIN_VALUE;
	//이닝별 선수의 성적을 담을 배열
	static int[][] score;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		score = new int[N][MAN];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < MAN; j++) {
				score[i][j] = sc.nextInt();
			}
		}
		
		perm(new int[MAN], new boolean[MAN], 0);
		
		System.out.println(ans);
	}
	//order는 선수 번호
	private static void perm(int[] order, boolean[] visited, int idx) {
		if(idx == MAN) {
			if(order[3] == 0) {
				//현재이닝
				int current = 0;
				//현재 점수
				int tmpAns = 0;
				//아웃 카운트
				int out = 0;
				//타자를 호출할 인덱스
				int hitterIdx = 0;
				while (current < N) {
					//현재 타자가 현재 이닝에 낸 점수
					int hitterScore = score[current][order[hitterIdx]];
					
					//아웃
					if(hitterScore == 0) {
						out++;
					}else {
						//큐 사이즈만큼 돌면서 도루진행. 홈에 도착한 인원은 점수로 변환하고 아닌 선수는 다시 큐에 넣는다.
						int qSize = q.size();
						for (int i = 0; i < qSize; i++) {
							Integer tmp = q.poll();
							tmp += hitterScore;
							if(tmp >= 4) {
								tmpAns++;
							}else {
								q.add(tmp);
							}
						}
						if(hitterScore == 4) tmpAns++;
						else q.add(hitterScore);
					}

					//타자 변경 (끝까지 가면 1번타자로 변경)
					hitterIdx++;
					hitterIdx %= MAN;
					hitterIdx = hitterIdx % MAN;
					
					//쓰리아웃 체인지 도루중인 선수를 다 뺀다.
					if(out == 3) {
						out = 0;
						current++;
						q.clear();
					}
				}//while end
				
				ans = Math.max(ans, tmpAns);
			}//order3 if end
			
			return;
		}
		
		for (int i = 0; i < MAN; i++) {
			if(!visited[i]) {
				visited[i] = true;
				order[idx] = i;
				perm(order, visited, idx+1);
				visited[i] = false;
			}
		}
	}
}
