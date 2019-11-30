package swexpertacademy.sea1873_상호의배틀필드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 상호의배틀필드 {
	static int H;
	static int W;

	static class Location {
		int row;
		int col;

		Location(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		// 테스트케이스 수
		final int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			// 높이
			H = Integer.parseInt(st.nextToken());
			// 너비
			W = Integer.parseInt(st.nextToken());
			// 맵
			char[][] arr = new char[H][W];
			// 맵입력
			for (int i = 0; i < H; i++) {
				arr[i] = br.readLine().toCharArray();
			}
			// 사용자 입력 수
			final int N = Integer.parseInt(br.readLine());
			// 사용자입력
			final String command = br.readLine();

			// 실행
			play(arr, H, W, N, command);
			
			sb.append("#" + tc + " ");
			for (int i = 0; i < arr.length; i++) {
				sb.append(String.valueOf(arr[i])).append("\n");
			}
		}
		
		System.out.println(sb);
	}

	private static void play(char[][] arr, int h, int w, int n, String command) {
		Location loc = findTank(arr);

		for (int i = 0; i < command.length(); i++) {
			char action = command.charAt(i);
			int row = 0;
			int col = 0;
			switch (action) {
			case 'U':
				row = loc.row - 1;
				col = loc.col;
				if (posCheck(row, col) && arr[row][col] == '.') {
					arr[loc.row][loc.col] = '.';
					arr[row][col] = '^';
					loc.row = row;
					loc.col = col;
				} else {
					arr[loc.row][loc.col] = '^';
				}
				break;
			case 'D':
				row = loc.row + 1;
				col = loc.col;
				if (posCheck(row, col) && arr[row][col] == '.') {
					arr[loc.row][loc.col] = '.';
					arr[row][col] = 'v';
					loc.row = row;
					loc.col = col;
				} else {
					arr[loc.row][loc.col] = 'v';
				}
				break;
			case 'L':
				row = loc.row;
				col = loc.col - 1;
				if (posCheck(row, col) && arr[row][col] == '.') {
					arr[loc.row][loc.col] = '.';
					arr[row][col] = '<';
					loc.row = row;
					loc.col = col;
				} else {
					arr[loc.row][loc.col] = '<';
				}
				break;
			case 'R':
				row = loc.row;
				col = loc.col + 1;
				if (posCheck(row, col) && arr[row][col] == '.') {
					arr[loc.row][loc.col] = '.';
					arr[row][col] = '>';
					loc.row = row;
					loc.col = col;
				} else {
					arr[loc.row][loc.col] = '>';
				}
				break;
			case 'S':
				shoot(arr, loc);
				break;
			}
		}
	}

	private static void shoot(char[][] arr, Location loc) {
		int[] pos = null;
		switch (arr[loc.row][loc.col]) {
		case '^':
			pos = new int[]{-1,0};
			break;
		case 'v':
			pos = new int[]{1,0};
			break;
		case '<':
			pos = new int[]{0,-1};
			break;
		case '>':
			pos = new int[]{0,1};
			break;
		}
		
		int row = loc.row;
		int col = loc.col;
		while (posCheck(row, col)) {
			row += pos[0];
			col += pos[1];
			
			if(posCheck(row, col)) {
				if(arr[row][col] == '*') {
					arr[row][col] = '.';
					break;
				}else if(arr[row][col] == '#') {
					break;
				}
			}
		}
	}

	private static boolean posCheck(int row, int col) {
		return row >= 0 && row < H && col >= 0 && col < W;
	}

	private static Location findTank(char[][] arr) {
		String tank = "<>^v";
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (tank.contains(String.valueOf(arr[i][j]))) {
					return new Location(i, j);
				}
			}
		}

		return null;
	}
}
