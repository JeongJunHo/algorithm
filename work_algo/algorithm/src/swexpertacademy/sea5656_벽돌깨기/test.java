package swexpertacademy.sea5656_벽돌깨기;

import java.util.Arrays;

public class test {
	public static void main(String[] args) {
		int[] arr = {1,2};
		
		test(arr);
		
		System.out.println(Arrays.toString(arr));
		
	}
	
	static void test(int[] tmp) {
		int[] temp = new int[2];
		
//		for (int i = 0; i < temp.length; i++) {
//			tmp[i] = temp[i].clone();
//		}
		tmp = temp;
	}
}


