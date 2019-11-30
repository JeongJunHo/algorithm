package day15;

import java.util.Scanner;

public class 최장증가부분수열_Easy{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++){
            int N = sc.nextInt();
            int[] arr = new int[N];
            //자신이 위치할 수 있는 가장 긴 증가수열의 길이를 저장할 변수
            int[] lis = new int[N];
            int ans = 0;
            for(int i = 0; i < N; i++){
                arr[i]=sc.nextInt();
                int max = 0;
                //내 앞에놈들을 검사하면서
                for(int j = 0; j < i; j++){
                    //나보다 작은놈 중에 가장 긴 증가수열 길이를 가진놈을 찾아서
                    if(arr[j] < arr[i] && max < lis[j])
                        max = lis[j];
                }
                //걔 뒤에 붙으면 내가 가질 수 있는 가장 긴 증가수열의 길이
                lis[i] = max+ 1;
                //그 중에 가장 긴놈을 기억하면  
                ans = Math.max(ans, lis[i]);
//              lis[i] = 1;
//              for(int j = 0; j < i; j++) {
//                  if( arr[j] < arr[i] && lis[j]+1 > lis[i]) {
//                      lis[i] = 1+ lis[j];
//                  }
//              }
            }
            //그것이 답.
            System.out.println("#" + tc + " "  + ans);
        }
    }    
}