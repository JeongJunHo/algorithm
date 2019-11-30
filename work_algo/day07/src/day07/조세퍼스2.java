package day07;

public class 조세퍼스2 {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7};
		
		int pos = 0;
		int cnt = 0;
		int N = 7;
		int K = 3;
		while (true) {
			if (arr[pos % N] != -1) {
				cnt++;
			}
			if(cnt == K) {
				System.out.println(arr[pos % N]);
				cnt = 0;
				arr[pos % N] = -1;
			}
			pos++;
		}
	}
}
