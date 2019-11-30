package baekjoon.bj1931_회의실배정;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Schedule implements Comparable<Schedule>{
	int start;
	int end;
	
	Schedule(int start, int end){
		this.start = start;
		this.end = end;
	}

	@Override
	public int compareTo(Schedule o) {
		// TODO Auto-generated method stub
		if(end - o.end > 0) {
			return 1;
		}else if(end - o.end < 0) {
			return -1;
		}else {
			if(start - o.start > 0) {
				return 1;
			}else {
				return -1;
			}
		}
	}
}

public class 회의실배정 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		Schedule[] arr = new Schedule[n];
		
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = new Schedule(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(arr);
		
		int ans = 0;
		int end = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if(end <= arr[i].start) {
				ans++;
				end = arr[i].end;
			}
		}
		
		System.out.println(ans);
	}
}
