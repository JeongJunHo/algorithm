package mobis;

public class problem1 {
	public static void main(String[] args) {
		int n = 10;
		int[] p = {5,4,3,2,1,6,7,8,9,10};
//		int[] p = {5,7,8,2,4,6,1,8,9,10};
		
		int answer = 0;
		
		int price = Integer.MAX_VALUE;
		
		for (int i = 0; i < p.length; i++) {
			if(p[i] < price) {
				price = p[i];
			}
			answer += price;
		}
		
		System.out.println(answer);
	}
}
