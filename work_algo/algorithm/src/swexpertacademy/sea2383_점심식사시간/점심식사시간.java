package swexpertacademy.sea2383_점심식사시간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 점심식사시간 {
	static class Location{
		int row;
		int col;
		
		public Location(int r, int c) {
			row = r;
			col = c;
		}
	}
	
	//맵크기, 답
	static int n, ans;
	//맵
	static int[][] map;
	//계단위치
	static List<Location> stairsList = new ArrayList<Location>();
	//사람위치
	static List<Location> peopleList = new ArrayList<Location>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t; tc++) {
			//초기화
			stairsList.clear();
			peopleList.clear();
			ans = Integer.MAX_VALUE;
			n = Integer.parseInt(br.readLine());
			
			map = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					if(tmp == 1) {
						peopleList.add(new Location(i, j));
					}else if(tmp > 1) {
						stairsList.add(new Location(i, j));
					}
					map[i][j] = tmp;
				}
			}
			
			//중복순열 (사람의 숫자만큼의 길이로 생성. 계단이 2개이기 때문에 0,1로 중복순열 생성)
			perm_re(new int[peopleList.size()], 0);
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
	//계단을 내려가는 상황을 재현하기위한 클래스
	static class StepCounter implements Comparable<StepCounter> {
		int step;
		int stairsCnt;
		
		public StepCounter(int step, int stairsCnt) {
			this.step = step;
			this.stairsCnt = stairsCnt;
		}
		
		@Override
		public int compareTo(StepCounter o) {
			return this.step - o.step;
		}
	}
	
	//중복순열
	private static void perm_re(int[] select, int idx) {
		//기저
		if(select.length == idx) {
			//각각의 계단에 사람별 도착한 스탭을 저장한 리스트 저장용
			List<StepCounter>[] step = new ArrayList[2];
			//비어있는 리스트 생성
			for (int i = 0; i < step.length; i++) {
				step[i] = new ArrayList<StepCounter>();
			}
			
			//중복순열결과를 순회
			for (int i = 0; i < select.length; i++) {
				//만들어진 순열로 계단 검색 0 또는 1
				Location stairs = stairsList.get(select[i]);
				//해당위치의 사람
				Location people = peopleList.get(i);
				
				//해당 사람이 계단까지 가는데 드는 비용
				int result = Math.abs(stairs.row - people.row) + Math.abs(stairs.col - people.col);
				
				//해당 계단을 사용하는 사람리스트에 비용을 기준으로 등록
				step[select[i]].add(new StepCounter(result, 0));
			}
			
			//비용(초) 기준으로 오름차순 정렬
			for (int i = 0; i < step.length; i++) {
				Collections.sort(step[i]);
			}
			
			//양쪽 계단 중 가장 늦게 끝나는 값이 결국 해당 테스트케이스의 걸리는 시간이므로 최소값을 담고 양쪽 계단 중 가장 큰값으로 치환한다.
			int result = Integer.MIN_VALUE;
			
			//양쪽 계단 같은 작업을 실행
			for (int i = 0; i < step.length; i++) {
				//계단의 높이를 가져온다.
				int stairsCnt = map[stairsList.get(i).row][stairsList.get(i).col];
				
				//현재계단을 3명이상 이용한다면
				if(step[i].size() > 3) {
					//계단을 내려가는 시뮬레이션을 위해 큐 생성
					Queue<StepCounter> q = new LinkedList<StepCounter>();
					//사람 인덱스
					int stepIndex = 0;
					//시간
					int time = 0;
					//사람이 남아있거나 큐가 빌때까지 반복
					while (stepIndex < step[i].size() || !q.isEmpty()) {
						//시간증가
						time++;
						
						//현재 큐 사이즈(계단 이용자)
						int qSize = q.size();
						for (int j = 0; j < qSize; j++) {
							StepCounter sc = q.poll();
							//계단한칸이동
							sc.stairsCnt++;
							//계단을 내려가는 중이라면
							if(sc.stairsCnt <= stairsCnt) {
								//다시 큐로 삽입
								q.add(sc);
							}
						}

						//계단에 진입한 사람이 3명 밑이고 사람이 남아있으며 대기하는 사람의 비용이 현재 초보다 적을 경우(대기하고있을경우)
						while (q.size() < 3 && stepIndex < step[i].size() && step[i].get(stepIndex).step < time) {
							//바로 계단을 한칸 내려간다.
							step[i].get(stepIndex).stairsCnt++;
							//계단 큐에 넣어준 후 사람 포인터를 한칸 뒤로 이동
							q.add(step[i].get(stepIndex++));
						}
					}// outer while end
					
					//모든 수행 후 걸린 시간초와 현재 result를 비교하여 큰 값을 치환
					result = Math.max(result, time);
				//현재계단을 3명이하로 이용한다면
				}else if(step[i].size() > 0) {
					//마지막 사람의 비용 + 계단의 높이 + 1만큼의 시간을 현재 구해진 result와 비교하여 더 큰 값을 치환
					result = Math.max(result, step[i].get(step[i].size()-1).step + stairsCnt + 1);
				}
			}
			
			//현재까지 나온 답과 방금 구한 result 중 작은 값을 ans에 치환
			ans = Math.min(result, ans);
			
			return;
		}
		
		for (int i = 0; i <= 1; i++) {
			select[idx] = i;
			perm_re(select, idx + 1);
		}
	}
}
