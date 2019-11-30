package day02;

import java.util.ArrayList;
import java.util.Arrays;

public class BabyGin {
	public static void main(String[] args) {
		
		int[] arr = {1,2,3,4,5,6};
		ArrayList<int[]> list = new ArrayList<int[]>();
		perm(arr, 0, list);
		
		boolean isBabyGin = false;
		
		for (int i = 0; i < list.size(); i++) {
			if(isBabyGin(list.get(i))) {
				isBabyGin = true;
				break;
			}
		}

		if (isBabyGin) {
			System.out.println("BabyGin입니다.");			
		}else {
			System.out.println("아닙니다.");			
		}
	}
	
	static boolean isBabyGin(int[] arr) {
		//순서 섞지말고, 현재 상태 그대로 앞 세장 뒤 세장을 검사
/*
		//앞부분 여부
		boolean front = false;

		//앞부분 런 트리플 검사
		if(arr[0] == arr[1] && arr[1] == arr[2]) {
			front = true;
		}else {
			if(arr[0] + 1 == arr[1] && arr[1] + 1 == arr[2]) {
				front = true;
			}
		}
		
		//뒷부분 여부
		boolean back = false;
				
		//뒷부분 런 트리플 검사
		if(arr[3] == arr[4] && arr[4] == arr[5]) {
			back = true;
		}else {
			if(arr[3] + 1 == arr[4] && arr[4] + 1 == arr[5]) {
				back = true;
			}
		}
*/
		int[] front = Arrays.copyOfRange(arr, 0, 3);
		int[] back = Arrays.copyOfRange(arr, 3, 6);
		
		
		
		return (isTriplet(front) || isRun(arr)) && (isTriplet(back) || isRun(back));
	}
	
	static boolean isTriplet(int[] arr) {
		//세칸짜리 배열을 받아서 해당 배열이 트리플릿인지 검사
		if(arr[0] == arr[1] && arr[1] == arr[2]) {
			return true;
		}
		return false;
	}
	
	static boolean isRun(int[] arr) {
		//숫자가 이어지는지
		if(arr[0] + 1 == arr[1] && arr[1] + 1 == arr[2]) {
			return true;
		}
		return false;
	}
	
	static void perm(int[] arr, int idx, ArrayList<int[]> list) {
		if (idx == arr.length) {
			list.add(arr);
//			System.out.print(Arrays.toString(arr));
			return;
		}
		for (int i = idx; i < arr.length; i++) {
			//idx(현재위치)랑 i랑 바꾸기
			swap(arr, i, idx);
			perm(arr, idx+1, list);
			swap(arr, i, idx);
		}
	}
	static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}
