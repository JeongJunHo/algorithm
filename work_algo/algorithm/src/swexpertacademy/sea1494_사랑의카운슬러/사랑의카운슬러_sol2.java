package swexpertacademy.sea1494_사랑의카운슬러;

import java.util.Scanner;

public class 사랑의카운슬러_sol2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            long[][] arr = new long[N][2];
            min = Long.MAX_VALUE;
            sumX =0;
            sumY =0;
            for (int i = 0; i < arr.length; i++) {
                arr[i][0] = sc.nextLong();
                arr[i][1] = sc.nextLong();
                sumX+=arr[i][0];
                sumY+=arr[i][1];
            }
            comb(arr,new int[arr.length/2], 0,0 );
            System.out.println("#"+tc+" "+min);
 
        }
    }
    static long min = Long.MAX_VALUE;
    static long sumX ;
    static long sumY ;
    static void comb(long [][] arr, int []sel, int aIdx, int sIdx) {
        if(sIdx==sel.length) {
            long x=0;
            long y =0;
            for (int i = 0; i < sel.length; i++) {
                x+=arr[sel[i]][0];
                y+=arr[sel[i]][1];
            }
            long xx = sumX-2*x;
            long yy = sumY-2*y;
            long res = xx*xx+yy*yy;
            if(res<min)min = res;
            return;
        }
        if(aIdx ==arr.length) {
            return;
        }
        sel[sIdx]=aIdx;
        comb(arr, sel, aIdx+1, sIdx+1);
        comb(arr, sel, aIdx+1, sIdx);
    }
}
