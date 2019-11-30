package baekjoon.bj5373_큐빙;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 큐빙 {
	static char[][] U, D, F, B, L, R;
	static final int CUBESIZE = 3;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			U = new char[CUBESIZE][CUBESIZE];
			D = new char[CUBESIZE][CUBESIZE];
			F = new char[CUBESIZE][CUBESIZE];
			B = new char[CUBESIZE][CUBESIZE];
			L = new char[CUBESIZE][CUBESIZE];
			R = new char[CUBESIZE][CUBESIZE];
			for (int i = 0; i < CUBESIZE; i++) {
				Arrays.fill(U[i], 'w');
				Arrays.fill(D[i], 'y');
				Arrays.fill(F[i], 'r');
				Arrays.fill(B[i], 'o');
				Arrays.fill(L[i], 'g');
				Arrays.fill(R[i], 'b');
			}

			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				String action = st.nextToken();

				if (action.equals("U+")) {
					cw(U);
					u_cw();
				} else if (action.equals("U-")) {
					ccw(U);
					u_ccw();
				} else if (action.equals("D+")) {
					ccw(D);
					d_cw();
				} else if (action.equals("D-")) {
					cw(D);
					d_ccw();
				} else if (action.equals("F+")) {
					cw(F);
					f_cw();
				} else if (action.equals("F-")) {
					ccw(F);
					f_ccw();
				} else if (action.equals("B+")) {
					ccw(B);
					b_cw();
				} else if (action.equals("B-")) {
					cw(B);
					b_ccw();
				} else if (action.equals("L+")) {
					ccw(L);
					l_cw();
				} else if (action.equals("L-")) {
					cw(L);
					l_ccw();
				} else if (action.equals("R+")) {
					cw(R);
					r_cw();
				} else if (action.equals("R-")) {
					ccw(R);
					r_ccw();
				}
				
			}

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					sb.append(U[i][j]);
				}
				sb.append("\n");
			}
		} // tc for end
		System.out.println(sb);
	}

	private static void l_ccw() {
		// U B D F
		char[][] tmpArr = new char[CUBESIZE][CUBESIZE];
		char[][] tmpArr2 = new char[CUBESIZE][CUBESIZE];
		// U B
		deepCopy(B, tmpArr);
		for (int i = 0; i < CUBESIZE; i++) {
			B[CUBESIZE - 1 - i][0] = U[i][0];
		}
		// B D
		deepCopy(D, tmpArr2);
		for (int i = 0; i < CUBESIZE; i++) {
			D[i][0] = tmpArr[i][0];
		}
		// D F
		deepCopy(F, tmpArr);
		for (int i = 0; i < CUBESIZE; i++) {
			F[CUBESIZE - 1 - i][0] = tmpArr2[i][0];
		}

		// F U
		for (int i = 0; i < CUBESIZE; i++) {
			U[i][0] = tmpArr[i][0];
		}
	}

	private static void l_cw() {
		// U F D B
		char[][] tmpArr = new char[CUBESIZE][CUBESIZE];
		char[][] tmpArr2 = new char[CUBESIZE][CUBESIZE];
		// U F
		deepCopy(F, tmpArr);
		for (int i = 0; i < CUBESIZE; i++) {
			F[i][0] = U[i][0];
		}
		// F D
		deepCopy(D, tmpArr2);
		for (int i = 0; i < CUBESIZE; i++) {
			D[CUBESIZE - 1 - i][0] = tmpArr[i][0];
		}
		// D B
		deepCopy(B, tmpArr);
		for (int i = 0; i < CUBESIZE; i++) {
			B[i][0] = tmpArr2[i][0];
		}

		// B U
		for (int i = 0; i < CUBESIZE; i++) {
			U[CUBESIZE - 1 - i][0] = tmpArr[i][0];
		}
	}

	private static void r_ccw() {
		// U F D B
		char[][] tmpArr = new char[CUBESIZE][CUBESIZE];
		char[][] tmpArr2 = new char[CUBESIZE][CUBESIZE];
		// U F
		deepCopy(F, tmpArr);
		for (int i = 0; i < CUBESIZE; i++) {
			F[i][CUBESIZE - 1] = U[i][CUBESIZE - 1];
		}
		// F D
		deepCopy(D, tmpArr2);
		for (int i = 0; i < CUBESIZE; i++) {
			D[CUBESIZE - 1 - i][CUBESIZE - 1] = tmpArr[i][CUBESIZE - 1];
		}
		// D B
		deepCopy(B, tmpArr);
		for (int i = 0; i < CUBESIZE; i++) {
			B[i][CUBESIZE - 1] = tmpArr2[i][CUBESIZE - 1];
		}

		// B U
		for (int i = 0; i < CUBESIZE; i++) {
			U[CUBESIZE - 1 - i][CUBESIZE - 1] = tmpArr[i][CUBESIZE - 1];
		}
	}

	private static void r_cw() {
		// U B D F
		char[][] tmpArr = new char[CUBESIZE][CUBESIZE];
		char[][] tmpArr2 = new char[CUBESIZE][CUBESIZE];
		// U B
		deepCopy(B, tmpArr);
		for (int i = 0; i < CUBESIZE; i++) {
			B[CUBESIZE - 1 - i][CUBESIZE - 1] = U[i][CUBESIZE - 1];
		}
		// B D
		deepCopy(D, tmpArr2);
		for (int i = 0; i < CUBESIZE; i++) {
			D[i][CUBESIZE - 1] = tmpArr[i][CUBESIZE - 1];
		}
		// D F
		deepCopy(F, tmpArr);
		for (int i = 0; i < CUBESIZE; i++) {
			F[CUBESIZE - 1 - i][CUBESIZE - 1] = tmpArr2[i][CUBESIZE - 1];
		}

		// F U
		for (int i = 0; i < CUBESIZE; i++) {
			U[i][CUBESIZE - 1] = tmpArr[i][CUBESIZE - 1];
		}
	}

	private static void f_cw() {
		// U R D L
		char[][] tmpArr = new char[CUBESIZE][CUBESIZE];
		char[][] tmpArr2 = new char[CUBESIZE][CUBESIZE];
		// U R
		deepCopy(R, tmpArr);
		for (int i = 0; i < CUBESIZE; i++) {
			R[i][0] = U[CUBESIZE - 1][i];
		}
		// R D
		deepCopy(D, tmpArr2);
		for (int i = 0; i < CUBESIZE; i++) {
			D[CUBESIZE - 1][CUBESIZE-1-i] = tmpArr[i][0];
		}
		// D L
		deepCopy(L, tmpArr);
		for (int i = 0; i < CUBESIZE; i++) {
			L[i][0] = tmpArr2[CUBESIZE - 1][i];
		}
		// L U
		for (int i = 0; i < CUBESIZE; i++) {
			U[CUBESIZE - 1][CUBESIZE-1-i] = tmpArr[i][0];
		}
	}

	private static void f_ccw() {
		// U L D R
		char[][] tmpArr = new char[CUBESIZE][CUBESIZE];
		char[][] tmpArr2 = new char[CUBESIZE][CUBESIZE];
		// U L
		deepCopy(L, tmpArr);
		for (int i = 0; i < CUBESIZE; i++) {
			L[CUBESIZE - 1 - i][0] = U[CUBESIZE - 1][i];
		}
		// L D
		deepCopy(D, tmpArr2);
		for (int i = 0; i < CUBESIZE; i++) {
			D[CUBESIZE - 1][i] = tmpArr[i][0];
		}
		// D R
		deepCopy(R, tmpArr);
		for (int i = 0; i < CUBESIZE; i++) {
			R[CUBESIZE - 1 - i][0] = tmpArr2[CUBESIZE - 1][i];
		}
		// R U
		for (int i = 0; i < CUBESIZE; i++) {
			U[CUBESIZE - 1][i] = tmpArr[i][0];
		}
	}

	private static void b_cw() {
		// U L D R
		char[][] tmpArr = new char[CUBESIZE][CUBESIZE];
		char[][] tmpArr2 = new char[CUBESIZE][CUBESIZE];
		// U L
		deepCopy(L, tmpArr);
		for (int i = 0; i < CUBESIZE; i++) {
			L[CUBESIZE - 1 - i][CUBESIZE - 1] = U[0][i];
		}
		// L D
		deepCopy(D, tmpArr2);
		for (int i = 0; i < CUBESIZE; i++) {
			D[0][i] = tmpArr[i][CUBESIZE - 1];
		}
		// D R
		deepCopy(R, tmpArr);
		for (int i = 0; i < CUBESIZE; i++) {
			R[CUBESIZE - 1 - i][CUBESIZE - 1] = tmpArr2[0][i];
		}
		// R U
		for (int i = 0; i < CUBESIZE; i++) {
			U[0][i] = tmpArr[i][CUBESIZE - 1];
		}
	}

	private static void b_ccw() {
		// U R D L
		char[][] tmpArr = new char[CUBESIZE][CUBESIZE];
		char[][] tmpArr2 = new char[CUBESIZE][CUBESIZE];
		// U R
		deepCopy(R, tmpArr);
		for (int i = 0; i < CUBESIZE; i++) {
			R[i][CUBESIZE - 1] = U[0][i];
		}
		// R D
		deepCopy(D, tmpArr2);
		for (int i = 0; i < CUBESIZE; i++) {
			D[0][CUBESIZE - 1 - i] = tmpArr[i][CUBESIZE - 1];
		}
		// D L
		deepCopy(L, tmpArr);
		for (int i = 0; i < CUBESIZE; i++) {
			L[i][CUBESIZE - 1] = tmpArr2[0][i];
		}
		// L U
		for (int i = 0; i < CUBESIZE; i++) {
			U[0][CUBESIZE-1-i] = tmpArr[i][CUBESIZE - 1];
		}
	}

	private static void u_ccw() {
		// F R B L
		char[][] tmpArr = new char[CUBESIZE][CUBESIZE];
		char[][] tmpArr2 = new char[CUBESIZE][CUBESIZE];
		// F R
		deepCopy(R, tmpArr);
		for (int i = 0; i < CUBESIZE; i++) {
			R[0][i] = F[0][i];
		}
		// R B
		deepCopy(B, tmpArr2);
		for (int i = 0; i < CUBESIZE; i++) {
			B[0][CUBESIZE - 1 - i] = tmpArr[0][i];
		}
		// B L
		deepCopy(L, tmpArr);
		for (int i = 0; i < CUBESIZE; i++) {
			L[0][i] = tmpArr2[0][i];
		}

		// L F
		for (int i = 0; i < CUBESIZE; i++) {
			F[0][CUBESIZE - 1 - i] = tmpArr[0][i];
		}
	}

	private static void u_cw() {
		// F L B R
		char[][] tmpArr = new char[CUBESIZE][CUBESIZE];
		char[][] tmpArr2 = new char[CUBESIZE][CUBESIZE];
		// F L
		deepCopy(L, tmpArr);
		for (int i = 0; i < CUBESIZE; i++) {
			L[0][CUBESIZE - 1 - i] = F[0][i];
		}
		// L B
		deepCopy(B, tmpArr2);
		for (int i = 0; i < CUBESIZE; i++) {
			B[0][i] = tmpArr[0][i];
		}
		// B R
		deepCopy(R, tmpArr);
		for (int i = 0; i < CUBESIZE; i++) {
			R[0][CUBESIZE - 1 - i] = tmpArr2[0][i];
		}

		// R F
		for (int i = 0; i < CUBESIZE; i++) {
			F[0][i] = tmpArr[0][i];
		}
	}

	private static void d_ccw() {
		// F L B R
		char[][] tmpArr = new char[CUBESIZE][CUBESIZE];
		char[][] tmpArr2 = new char[CUBESIZE][CUBESIZE];
		// F L
		deepCopy(L, tmpArr);
		for (int i = 0; i < CUBESIZE; i++) {
			L[CUBESIZE - 1][CUBESIZE - 1 - i] = F[CUBESIZE - 1][i];
		}
		// L B
		deepCopy(B, tmpArr2);
		for (int i = 0; i < CUBESIZE; i++) {
			B[CUBESIZE - 1][i] = tmpArr[CUBESIZE - 1][i];
		}
		// B R
		deepCopy(R, tmpArr);
		for (int i = 0; i < CUBESIZE; i++) {
			R[CUBESIZE - 1][CUBESIZE - 1 - i] = tmpArr2[CUBESIZE - 1][i];
		}

		// R F
		for (int i = 0; i < CUBESIZE; i++) {
			F[CUBESIZE - 1][i] = tmpArr[CUBESIZE - 1][i];
		}
	}

	private static void d_cw() {
		// F R B L
		char[][] tmpArr = new char[CUBESIZE][CUBESIZE];
		char[][] tmpArr2 = new char[CUBESIZE][CUBESIZE];
		// F R
		deepCopy(R, tmpArr);
		for (int i = 0; i < CUBESIZE; i++) {
			R[CUBESIZE - 1][i] = F[CUBESIZE - 1][i];
		}
		// R B
		deepCopy(B, tmpArr2);
		for (int i = 0; i < CUBESIZE; i++) {
			B[CUBESIZE - 1][CUBESIZE - 1 - i] = tmpArr[CUBESIZE - 1][i];
		}
		// B L
		deepCopy(L, tmpArr);
		for (int i = 0; i < CUBESIZE; i++) {
			L[CUBESIZE - 1][i] = tmpArr2[CUBESIZE - 1][i];
		}

		// L F
		for (int i = 0; i < CUBESIZE; i++) {
			F[CUBESIZE - 1][CUBESIZE - 1 - i] = tmpArr[CUBESIZE - 1][i];
		}
	}

	private static void ccw(char[][] arr) {
		for (int k = 0; k < 2; k++) {
			char[][] tmpArr = new char[CUBESIZE][CUBESIZE];
			tmpArr[1][1] = arr[1][1];

			// 상
			for (int i = CUBESIZE - 1; i > 0; i--) {
				tmpArr[0][i - 1] = arr[0][i];
			}
			// 좌
			for (int i = 0; i < CUBESIZE - 1; i++) {
				tmpArr[i + 1][0] = arr[i][0];
			}
			// 하
			for (int i = 0; i < CUBESIZE - 1; i++) {
				tmpArr[CUBESIZE - 1][i + 1] = arr[CUBESIZE - 1][i];
			}
			// 우
			for (int i = CUBESIZE - 1; i > 0; i--) {
				tmpArr[i - 1][CUBESIZE - 1] = arr[i][CUBESIZE - 1];
			}

			for (int i = 0; i < tmpArr.length; i++) {
				arr[i] = tmpArr[i];
			}
		}
	}

	private static void cw(char[][] arr) {
		for (int k = 0; k < 2; k++) {
			char[][] tmpArr = new char[CUBESIZE][CUBESIZE];
			tmpArr[1][1] = arr[1][1];

			// 상
			for (int i = 0; i < CUBESIZE - 1; i++) {
				tmpArr[0][i + 1] = arr[0][i];
			}
			// 좌
			for (int i = CUBESIZE - 1; i > 0; i--) {
				tmpArr[i - 1][0] = arr[i][0];
			}
			// 하
			for (int i = CUBESIZE - 1; i > 0; i--) {
				tmpArr[CUBESIZE - 1][i - 1] = arr[CUBESIZE - 1][i];
			}
			// 우
			for (int i = 0; i < CUBESIZE - 1; i++) {
				tmpArr[i + 1][CUBESIZE - 1] = arr[i][CUBESIZE - 1];
			}

			for (int i = 0; i < tmpArr.length; i++) {
				arr[i] = tmpArr[i];
			}
		}
	}

	static void deepCopy(char[][] origin, char[][] target) {
		for (int i = 0; i < origin.length; i++) {
			target[i] = origin[i].clone();
		}
	}
}