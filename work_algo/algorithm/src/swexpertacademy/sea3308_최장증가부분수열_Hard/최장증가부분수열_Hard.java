package swexpertacademy.sea3308_최장증가부분수열_Hard;

import java.util.ArrayList;
import java.util.Scanner;

public class 최장증가부분수열_Hard {
    public static void main(String[] args) {
         
//      int[] arr = {1,3,4,6,7,9};
//      int left = 0;
//      int right = arr.length - 1;
//      int target = 8;
//      while( left <= right ) {
//          int mid = (left + right) / 2;
//          if( arr[mid] > target )
//              right = mid - 1;
//          else
//              left = mid + 1;
//      }
//      System.out.println(right);
         
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            int[] arr = new int[N];
            // 자신이 속할 수 있는 가장 긴 증가수열의 길이를 저장할 배열
            int[] lis = new int[N];
            // 증가수열의 각 자리에 들어갈 수 있는 제일 작은 수를 기억할 배열.
            int[] tmp = new int[N];
            int tmp_idx = 0; //tmp배열에 현재 크기.
            for(int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
                //tmp배열에서 내위치를 찾자.
                int left = 0;
                int right = tmp_idx;
                int target = arr[i];
                while( left <= right ) {
                    int mid = (left + right) / 2;
                    if( tmp[mid] > target )
                        right = mid - 1;
                    else
                        left = mid + 1;
                }
                 
                //-1이 나왔다? tmp[0]이 나, lis[i] = 1
                if(right == -1) {
                    tmp[0] = arr[i];
                    lis[i] = 1;
                }
                //나온 위치가 tmp_idx 다? tmp의 마지막자리에 나, lis[i] = tmp_idx+1 이고 tmp_idx는 하나 증가
                else if( tmp_idx == right) {
                    tmp[tmp_idx++] = arr[i];
                    lis[i] = tmp_idx;
                }
                //둘다 아니라면 ? lis[i] = lis[찾아진위치] + 1 
                else {
                    lis[i] = lis[right+1] + 1;
                    tmp[right+1] = arr[i];
                }
            }
             
            System.out.println("#" + tc + " " + (tmp_idx));
        }
    }
     
}