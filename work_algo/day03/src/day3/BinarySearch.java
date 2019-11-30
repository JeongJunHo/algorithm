package day3;

public class BinarySearch {
	public static void main(String[] args) {
		//정렬된 리스트
		int[] arr = {2,4,7,9,11,19,23};
		//찾고자 하는 값
		int search = 7;
		//검색
		
		int start = 0;
		int end = arr.length-1;
		
		//시작값이 끝값과 같을 때까지 반복
		while (start <= end) {
			//중간값을 찾는다. 홀수의 경우 소수점 버림
			int middle = (start + end) / 2;
			//중간값과 찾으려는 숫자를 비교
			if (arr[middle] == search) {
				System.out.println((middle + 1) + "번째 원소입니다.");
				break;
			}else if(arr[middle] > search) {
				end = middle - 1;
			}else {
				start = middle + 1;
			}
		}
	}
}
