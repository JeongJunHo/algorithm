package swexpertacademy.sea2382_미생물격리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class 미생물격리 {
	static class Microbe{
		int row;
		int col;
		int cnt;
		int direct;
		
		public Microbe(int row, int col, int cnt, int direct) {
			this.row = row;
			this.col = col;
			this.cnt = cnt;
			this.direct = direct;
		}
	}
	
	static int ans, n, m, k;
	//위치방문체크, 사방보정값 상하좌우 순
	static int[][] visited, pos = {{-1,0},{1,0},{0,-1},{0,1}};
	static List<Microbe> list = new ArrayList<Microbe>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			list.clear();
			ans = 0;
			
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				list.add(new Microbe(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			//시간초만큼 반복
			for (int i = 0; i < m; i++) {
				visited = new int[n][n];
				for (int j = 0; j < list.size(); j++) {
					Microbe mic = list.get(j);
					mic.row += pos[mic.direct-1][0];
					mic.col += pos[mic.direct-1][1];
					
					posCheck(mic);
					
					if(mic.cnt == 0) {
						list.remove(j--);
					}else {
						visited[mic.row][mic.col]++;
					}
				}
				
				for (int j = 0; j < n; j++) {
					for (int k = 0; k < n; k++) {
						if(visited[j][k] > 1) {
							Microbe[] tmp = new Microbe[visited[j][k]];
							Stack<Integer> idxStack = new Stack<Integer>(); 
							int idx = 0;
							for (int listIdx = 0; listIdx < list.size(); listIdx++) {
								Microbe mic = list.get(listIdx);
								if(mic.row == j && mic.col == k) {
									tmp[idx++] = mic;
									idxStack.push(listIdx);
								}
							}
							
							Microbe max = tmp[0];
							int sum = tmp[0].cnt;
							for (int l = 1; l < tmp.length; l++) {
								if(max.cnt < tmp[l].cnt) {
									max = tmp[l];
								}
								sum += tmp[l].cnt;
							}
							
							max.cnt = sum;
							while (!idxStack.isEmpty()) {
								int tmpIdx = idxStack.pop();
								if(!list.get(tmpIdx).equals(max)) {
									list.remove(tmpIdx);
								}
							}
						}
					}// k for end
				}// j for end
			}
			
			for (Microbe microbe : list) {
				ans += microbe.cnt;
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void posCheck(Microbe mic) {
		if(mic.row == 0 || mic.row == n-1 || mic.col == 0 || mic.col == n-1) {
			mic.cnt = mic.cnt / 2;
			if(mic.direct % 2 == 0) {
				mic.direct -= 1;
			}else {
				mic.direct += 1;
			}
			
		}
	}
}
