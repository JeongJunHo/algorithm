package baekjoon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


/*
����
�ڿ��� N�� M�� �־����� ��, �Ʒ� ������ �����ϴ� ���̰� M�� ������ ��� ���ϴ� ���α׷��� �ۼ��Ͻÿ�.

1���� N���� �ڿ��� �߿��� �ߺ� ���� M���� �� ����
�� ������ ���������̾�� �Ѵ�.
�Է�
ù° �ٿ� �ڿ��� N�� M�� �־�����. (1 �� M �� N �� 8)

���
�� �ٿ� �ϳ��� ������ ������ �����ϴ� ������ ����Ѵ�. �ߺ��Ǵ� ������ ���� �� ����ϸ� �ȵǸ�, �� ������ �������� �����ؼ� ����ؾ� �Ѵ�.

������ ���� ������ �����ϴ� ������ ����ؾ� �Ѵ�.

���� �Է� 1 
3 1
���� ��� 1 
1
2
3
���� �Է� 2 
4 2
���� ��� 2 
1 2
1 3
1 4
2 3
2 4
3 4
���� �Է� 3 
4 4
���� ��� 3 
1 2 3 4
 */
public class problem15650 {
	static boolean[] isUse;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		//n������ �迭
		int[] arr = new int[n];
		//�� �迭�� ��뿩�� �迭
		isUse = new boolean[n];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i+1;
		}
		
		//��¿� ����Ʈ
		ArrayList<Integer> list = new ArrayList<Integer>();
		//�޼ҵ� ����
		dfs(arr, n, m, list);
	} 
	
	static void dfs(int[] arr, int n, int m, ArrayList<Integer> list) {
		if(list.size() == m) {
			//����Ʈ ���� ������������ ����
			boolean check = true;
			for (int i = 0; i < list.size(); i++) {
				for (int j = i; j < list.size(); j++) {
					//���� �� �� ���� �� ���� ���� ���� ����ִ��� Ž��
					if(list.get(i) > list.get(j)) {
						check = false;
						break;
					}
				}
				//�̹� �ѹ��̶� �� ���� ���� �����ٸ� ����
				if(check == false) {
					break;
				}
			}
			
			//Ž�� ����� ���������� �´ٸ� ���
			if(check == true) {
				System.out.println(list.toString());
			}
			return;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if (isUse[i] == false) {
				isUse[i] = true;
				//��ͷ� �ѱ������ ���ο� ����Ʈ ���� (�ν��Ͻ� Ÿ���̱� ������ �ּҸ� �������� �ʱ� ����)
				ArrayList<Integer> tmpList = new ArrayList<Integer>();
				//������� �־�� ����Ʈ ���
				tmpList.addAll(list);
				//���� �ݺ����� �� �߰� �Է�
				tmpList.add(arr[i]);
				//��� ȣ��
				dfs(arr, n, m, tmpList);
				isUse[i] = false;
			}
		}
	}
}