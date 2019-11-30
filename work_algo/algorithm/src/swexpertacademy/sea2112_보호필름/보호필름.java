package swexpertacademy.sea2112_보호필름;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 보호필름 {
	static int d, w, k, ans;
	static int[][] arr, copyArr;
	static boolean pass;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			ans = 0;
			pass = false;
			
			arr = new int[d][w];
			copyArr = new int[d][w];
			
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//안바꾸고 실행
			run(arr);
			
			while (!pass) {
				ans++;
				if(ans < k) {
					comb(new int[ans], 0, 0);
				}else {
					pass = true;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void comb(int[] select, int si, int idx) {
		if(pass) {
			return;
		}
		
		if(select.length == si) {
			perm(select, new int[select.length], 0);
			
			return;
		}
		
		if(idx == d) {
			return;
		}
		
		select[si] = idx;
		comb(select, si + 1, idx + 1);
		comb(select, si, idx + 1);
	}

	private static void perm(int[] select, int[] medicineSelect, int idx) {
		if(pass) {
			return;
		}
		
		if(medicineSelect.length == idx) {
			deepCopy();
			
			//약뿌리기
			for (int i = 0; i < select.length; i++) {
				Arrays.fill(copyArr[select[i]], medicineSelect[i]);
			}
			
			run(copyArr);
			
			return;
		}
		
		for (int i = 0; !pass && i <= 1; i++) {
			medicineSelect[idx] = i;
			perm(select, medicineSelect, idx + 1);
		}
	}

	static void run(int[][] arr) {
		int passCnt = 0;
		for (int i = 0; !pass && i < w; i++) {
			int cnt = 0;
			int type = -1;
			for (int j = 0; !pass && j < d; j++) {
				if(arr[j][i] != type) {
					type = arr[j][i];
					cnt = 1;
				}else {
					cnt++;
					if(cnt == k) {
						break;
					}
				}
			}
			
			//k개 이상 이어지지 않는다면
			if(cnt < k) {
				pass = false;
				break;
			}else {
				passCnt++;
			}
		}// i for end
		
		if(passCnt == w) {
			pass = true;
		}
	}
	
	static void deepCopy(){
		for (int i = 0; i < copyArr.length; i++) {
			copyArr[i] = arr[i].clone();
		}
	}
}
