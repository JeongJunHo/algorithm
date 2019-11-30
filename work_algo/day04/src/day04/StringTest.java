package day04;

public class StringTest {
	public static void main(String[] args) {
		String str = "010-4947-0826";
		//문자열의 길이는 length메소드
		for (int i = 0; i < args.length; i++) {
			//특정 위치의 글자를 얻어오고 싶으면 charAt
			char c = str.charAt(i);
			
			//특정 글자가 숫자인지 검사
			if (Character.isDigit(c)) {
				System.out.println("숫자");
			}
			
			//특정 글자가 알파벳인지 검사
			if (Character.isAlphabetic(c)) {
				System.out.println("알파벳");
			}
		}
		
		String myStr = "Jeong" + " " + "Jun" + " " + "Ho" + 3;
		StringBuilder sb = new StringBuilder();
		sb.append("Jeong").append(" ").append("Jun").append(" ").append("Ho").append(3);
		
		
		System.out.println(sb.toString());
		
		System.out.println(myStr);
	}
}
