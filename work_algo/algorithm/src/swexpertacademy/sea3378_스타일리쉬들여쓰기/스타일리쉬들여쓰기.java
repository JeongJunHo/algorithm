package swexpertacademy.sea3378_스타일리쉬들여쓰기;

import java.util.Arrays;
import java.util.Scanner;

public class 스타일리쉬들여쓰기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            int p = sc.nextInt(); // master가 입력하는 문자열의 라인 수
            int q = sc.nextInt(); // 당신이 입력한 문자열의 라인 수
            String[] master = new String[p];
            String[] me = new String[q];
            for(int i = 0; i < p; i++)
                master[i] = sc.next();
            for(int i = 0; i < q; i++)
                me[i] = sc.next();
             
            //result에 들어가야 하는 값은. 적합한 들여쓰기 수를 구하면 그 값을 저장. 이미 한번 저장했는데 또 적합한 들여쓰기 수가 발견되면 -1
            //result에 디폴트값을 -2로 두고 -2는 아직 계산하지 않은걸로.
            //result에 값을 계산했는데 이미 -2가 아닌 값이 들어있으면 두번째 계산결과가 나온걸로 간주. -1로 처리
            int[] result = new int[q];//나의 문장 q줄에 대해서 각각 몇칸을 들여쓰기 해야 하는지.
            Arrays.fill(result, -2);
            for(int r = 1; r <= 20; r++) {
                for(int c = 1; c <= 20; c++) {
                    for(int s = 1; s <= 20; s++) {
                        if( isOk(master , r , c ,s ) ) {
                            calIndent(me, r, c, s, result);
                        }
                    }
                }
            }
            System.out.print("#" + tc + " ");
            for(int indent : result) {
                System.out.print(indent + " ");
            }
            System.out.println();
        }
    }
    static void calIndent(String[] me, int r, int c, int s, int[] result) {
        int rCnt = 0, cCnt = 0, sCnt = 0;
        for(int i = 0; i < me.length; i++) {
            //해당 라인에 대해 인덴트 계산이 처음이라면
            if(result[i] == -2)
                result[i] = r * rCnt + c * cCnt + s * sCnt;
            else {
                //처음 계산이 아닌데 현재 r,c,s로도 계산한 인덴트가 아까 계산한것과 다르다면 결정할 수 없음
                if( result[i] != r*rCnt + c * cCnt + s * sCnt){
                    result[i] = -1;
                }
            }
            for(char ch : me[i].toCharArray() ) {
                switch(ch) {
                case '(': rCnt++; break;
                case ')': rCnt--; break;
                case '{': cCnt++; break;
                case '}': cCnt--; break;
                case '[': sCnt++; break;
                case ']': sCnt--; break;
                }
            }
        }
    }
     
    static boolean isOk(String[] master, int r, int c, int s) {
        //소,중,대괄호에 대해 열림을 만나면 각각 카운트를 세자. 닫힘을 만나면 카운트를 하나 줄여주자.
        //소 중 대괄호의 카운트를 위해서 세개의 변수를 준비해두고. 열리면 늘리고 닫히면 줄여준다면 이 세개의 변수는 a-b, c-d, e-f를 항상 표현
        int rCnt = 0, cCnt = 0, sCnt = 0;
        for(int i = 0; i < master.length; i++) {
            //이 반복은 각 문장의 한줄한줄에 대해 검사중..
            //해당 문장이 몇개의 들여쓰기로 시작하는지 세어 놓고.
            //그 들여쓰기의 수가 현재 rCnt, cCnt, sCnt의 값과 매개로 들어온 r,c,s에 의해서
            // r * rCnt + c * cCnt + s * sCnt 와 일치하는지 검사!!!
            int cnt = 0;//해당 라인의 들여쓰기의 수를 셀 변수
            for(char ch : master[i].toCharArray()) {
                if( ch == '.' )
                    cnt++;
                else
                    break;
            }
            //들여쓰기의 수가 계산과 맞지 않으면 잘못된 것.
            if( cnt != r * rCnt + c * cCnt + s * sCnt )
                return false;
            //해당 라인에서 열리고 닫힌 괄호를 검사해서 rCnt cCnt sCnt를 갱신.
            for(char ch : master[i].toCharArray() ) {
                switch(ch) {
                case '(': rCnt++; break;
                case ')': rCnt--; break;
                case '{': cCnt++; break;
                case '}': cCnt--; break;
                case '[': sCnt++; break;
                case ']': sCnt--; break;
                }
            }
        }
        //위에서 한번도 return false를 만나지 않고 마지막 문장이 넘어갔다면 다 옳은 것
        return true;
    }
}
