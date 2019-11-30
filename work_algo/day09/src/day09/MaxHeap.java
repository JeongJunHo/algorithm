package day09;

public class MaxHeap {
	Integer[] arr = new Integer[2];
	Integer root;
	int idx = 1;
	int level = 0;
	
	/**
	 * 배열 확장 메소드
	 * @param level
	 */
	private void convertArr(int level) {
		//배열을 확장한다. (트리의 레벨만큼 쉬프트연산한 길이로 확장)
		Integer[] tmp = new Integer[(1 << level + 1)];
		System.arraycopy(arr, 0, tmp, 0, arr.length);
		//확장한 배열을 arr에 치환
		arr = tmp;
	}
	
	/**
	 * 삽입
	 * @param data
	 */
	public void insert(int data) {
		//루트가 없다면
		if(root == null) {
			//현재 idx에 데이터 삽입하고 idx를 다음값으로 설정
			arr[idx++] = data;
			//루트를 현재 값으로 설정
			root = data;
		//루트가 있다면
		}else {
			//현재 인덱스의 참조가 배열의 길이를 초과한다면
			if(idx >= arr.length) {
				//레벨을 증가시키고 배열 확장
				convertArr(++level);
			}
			//데이터를 삽입.
			arr[idx] = data;
			//정렬을 위한 재귀 (인덱스를 늘려준다.)
			upSwap(idx++);
		}
	}
	
	/**
	 * 삭제(루트만 삭제 가능)
	 */
	public void delete() {
		//루트만 있을 경우
		if(arr.length == 2) {
			//루트를 삭제
			arr[1] = root = null;
		}else {
			//루트에 마지막 값 치환
			arr[1] = root = arr[--idx];
			//마지막 값 삭제
			arr[idx] = null;
			if(idx == (1 << level)) {
				//배열축소
				convertArr(--level);
			}
			//재정렬
			downSwap(1, 0);
		}
	}
	
	/**
	 * 자리바꾸기 재귀 메소드
	 * @param idx
	 */
	public void upSwap(int idx) {
		//최상위에 다다르면 종료
		if(idx == 1) {
			//루트를 현재값으로 치환
			root = arr[idx];
			return;
		}
		
		//부모 인덱스
		int parent = idx / 2;
		//부모보다 내가 크다면 자리를 바꾼 후 재귀
		if(arr[parent] < arr[idx]) {
			swap(arr, parent, idx);
			upSwap(parent);
		}
	}
	
	public void downSwap(int idx, int l) {
		//마지막 레벨에 도달했다면 종료
		if(l == level) {
			return;
		}
		//왼쪽 인덱스
		int left = idx * 2;
		//오른쪽 인덱스
		int right = idx * 2 + 1;
		if(arr[left] != null && arr[idx] < arr[left]) {
			swap(arr, idx, left);
			downSwap(left, l+1);
		}
		if(arr[right] != null && arr[idx] < arr[right]) {
			swap(arr, idx, right);
			downSwap(right, l+1);
		}
	}
	
	public void swap(Integer[] tmpArr, int a, int b) {
		int tmp = tmpArr[a];
		tmpArr[a] = tmpArr[b];
		tmpArr[b] = tmp;
	}
	
	public void print() {
		int tmpLevel = 0;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] != null)
				System.out.print(arr[i] + " ");
			if(i == (1<<tmpLevel)-1) {
				if(arr[i] != null)
					System.out.println();
				tmpLevel++;
			}
		}
		System.out.println();
	}
}
