package day15;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

//4 3 2 1
//1100  0011
//1010  0101  →  1111
//
//1번째아이템을 요놈이 갖고 있는지 아닌지 검사는?   x & (1<<0)
//x & (1<<1)
//x | (1<<2)  :  x | 0100

public class 양팔저울_지환 {
    static void bitmaskPowetset() {
        //아아템이 3개면.. 비트가 3칸 필요 아이템이 4개면.. 비트가 4칸 필요
        int[] arr = {1,2,3};
        int n = arr.length;
        int N = 1 << n;  // 부분집합의 경우의 수는 아이템의 수가 n일때 2의 n제곱
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < n; j++) {
                if( (i & (1<<j) ) != 0 ){
                    //j번째 아이템을 포함
                    System.out.print(arr[j]);
                }
            }
            System.out.println();
        }
    }
	
    public static void main(String[] args) {
    	bitmaskPowetset();
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            int[] arr = new int[N];
            boolean[] visited = new boolean[N];
 
            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }
            memo=new HashMap<>();
            int cnt = dfs(0, 0, 0, arr, visited, N);
            System.out.println("#" + tc + " " + cnt);
        } // end test cases
    }
    static HashMap<String, Integer> memo;
    static int dfs(int r, int l, int k, int[] arr, boolean[] visited, int N) {
        // 현재 상태 ( 고른패턴, 왼쪽무게합, 몇번째꺼까지 골랐는지 )
        String status  = Arrays.toString(visited) + l + "/" + k;
        if(memo.containsKey(status))
            return memo.get(status);
        if (k == N) {
            return 1;
        }
        int cnt = 0;
        // 재귀 팡팡
        for (int i = 0; i < N; i++) {
            // 쓴게 아니라면
            if (!visited[i]) {
                visited[i] = true;
 
                // 왼쪽 vs 오른쪽+새로운거
                // 왼쪽이 더 크거나 같으니까 조건만족.(진행가능)
                if (r + arr[i] <= l) {
                    cnt += dfs(r + arr[i], l, k + 1, arr, visited, N);
                }
                // 바꿔서도 해봐야지
                cnt += dfs(r, l + arr[i], k + 1, arr, visited, N);
                visited[i] = false;
            }
        }
        memo.put(status, cnt);
        return cnt;
    }
}
