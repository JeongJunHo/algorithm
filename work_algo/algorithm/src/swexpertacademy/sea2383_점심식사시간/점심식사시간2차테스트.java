package swexpertacademy.sea2383_점심식사시간;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 점심식사시간2차테스트 {
	static int N, ans;
	static List<Person> personList;
	static List<Stair> stairList;
	static class Stair{
		int row;
		int col;
		int level;
		public Stair(int row, int col, int level) {
			super();
			this.row = row;
			this.col = col;
			this.level = level;
		}
	}
	static class Person implements Comparable<Person>{
		int row;
		int col;
		int cost;
		int delay;
		
		public Person(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}

		@Override
		public int compareTo(Person o) {
			return this.cost - o.cost;
		}

		@Override
		public String toString() {
			return "Person [row=" + row + ", col=" + col + ", cost=" + cost + ", delay=" + delay + "]";
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			ans = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			personList = new ArrayList<Person>();
			stairList = new ArrayList<Stair>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if(num == 1) {
						personList.add(new Person(i, j));
					}else if (num > 1) {
						stairList.add(new Stair(i, j, num));
					}
				}
			}// i end
			
			perm_re(new int[personList.size()], 0);
			
			System.out.println("#" + tc + " " + ans);
		}// tc end
	}
	private static void perm_re(int[] sel, int idx) {
		if(sel.length == idx) {
			PriorityQueue<Person>[] qArr = new PriorityQueue[stairList.size()];
			Queue<Person>[] stairQueue = new LinkedList[stairList.size()];
			for (int i = 0; i < qArr.length; i++) {
				qArr[i] = new PriorityQueue<Person>();
				stairQueue[i] = new LinkedList<Person>();
			}
			
			for (int i = 0; i < sel.length; i++) {
				Person p = personList.get(i);
				Stair s = stairList.get(sel[i]);
				p.cost = Math.abs(p.row - s.row) + Math.abs(p.col - s.col);
				qArr[sel[i]].add(p);
			}
			
			int time = 0;
			while (true) {
				//시간이 간다.
				//계단 상황 진행 시킨다.
				//현재 시간과 같거나 적은사람이 있다면 계단 인원이 3명미만인지 보고 밀어넣는다.
				time++;
				
				for (int i = 0; i < stairQueue.length; i++) {
					int qSize = stairQueue[i].size();
					for (int j = 0; j < qSize; j++) {
						Person p = stairQueue[i].poll();
						p.delay++;
						if(p.delay < stairList.get(i).level) {
							stairQueue[i].add(p);
						}
					}
				}
				
				for (int i = 0; i < qArr.length; i++) {
					while (!qArr[i].isEmpty() && stairQueue[i].size() < 3 && qArr[i].peek().cost < time) {
						Person p = qArr[i].poll();
						p.delay = 0;
						stairQueue[i].add(p);
					}
				}
				
				//모든 큐가 다 비면 종료
				boolean check = true;
				for (int i = 0; i < stairQueue.length; i++) {
					if(!qArr[i].isEmpty() || !stairQueue[i].isEmpty()) {
						check = false;
						break;
					}
				}
				
				if(check) {
					break;
				}
			}
			
			ans = Math.min(ans, time);
			
			return;
		}
		
		for (int i = 0; i < stairList.size(); i++) {
			sel[idx] = i;
			perm_re(sel, idx+1);
		}
	}
}
