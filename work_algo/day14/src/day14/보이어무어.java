package day14;

public class 보이어무어 {
    static int skipTable[];
    public static void main(String[] args) {
        skipTable = new int[256];
        String str = "abcdloee yoe ve move. Plovse, love me. lovelovelove";
        String pattern = "love";
        genSkipTable(pattern);
//      int found = search(str, 0, pattern);
        int found = search2(str, 0, pattern);
        while( found != -1 && found < str.length()) {
            System.out.println(found + "에서 패턴이 시작됩니다.");
            System.out.println(str.substring(found, found + pattern.length()));
            found = search2(str, found+pattern.length(), pattern);
        }
    }
    static void genSkipTable(String pattern) {
        //작성
        for (int i = 0; i < 256; i++)
            skipTable[i] = pattern.length();
        for (int i = 0; i < pattern.length(); i++)
            skipTable[pattern.charAt(i)] = pattern.length() - 1 - i;
         
    }
    static int search2(String str,int start_idx, String pattern) {
        int s_idx = start_idx + pattern.length()-1;
        int p_idx = pattern.length() - 1;
        while( p_idx >= 0 && s_idx < str.length() ) {
            while( str.charAt(s_idx) != pattern.charAt(p_idx) ) {
                int k  = skipTable[str.charAt(s_idx)];
                if( pattern.length() - p_idx > k ) {
                    s_idx = s_idx + pattern.length() - p_idx;
                }
                else {
                    s_idx += k;
                }
                if( s_idx >= str.length() )
                    return -1;
                p_idx = pattern.length() -1;
            }
            p_idx--;
            s_idx--;
        }
        return s_idx+1;
    }
     
    static int search(String str, int start_idx, String pattern) {
        //검사할 위치를 찾아서 ( 현재 검사 시작 위치 로부터 패턴 길이  -1 만큼 뒤.. )
        int index = start_idx + pattern.length() - 1;
         
        //검사할 위치가 문자열 끝을 넘어가지 않는다면 반복
        while( index < str.length() ) {
            //현재 위치부터 검사를 시작해서 패턴이 모두 일치하는지 or 몇번째 문자에서 틀리는지
            int s_idx = index; // 지금 검사하는 평문에서의 위치.
            int p_idx = pattern.length()-1; // 지금 검사하는 패턴에서의 위치.
            while( p_idx >=0 && str.charAt(s_idx) == pattern.charAt(p_idx) ) {
                s_idx--;
                p_idx--;
            }
            if( p_idx == -1 )
                return s_idx+1;
            //틀리다면 틀린 위치로 이동
            index = index - pattern.length() + 1 + p_idx;
            //틀린 위치의 문자에 해당하는 거리만큼 이동
            index = index + skipTable[str.charAt(index)];
        }
        return -1;
    }
}
