package day09;

class Arr_BinaryTree{
	char[] tree;
	public Arr_BinaryTree(int max_level) {
		//max level에 맞게 배열 할당.
		tree = new char[1 << (max_level+1)];
	}
	public void setRoot(char data) {
		//루트 노드의 값을 data로 지정
		tree[1] = data;
	}
	public void setLeft(char data, int parent) {
		//parent인덱스에 위치한 노드의 왼쪽 자식 노드가 위치해야 되는 인덱스에 data입력.
		tree[parent*2] = data;
	}
	public void setRight(char data, int parent) {
		//parent인덱스에 위치한 노드의 오른쪽 자식 노드가 위치해야 되는 인덱스에 data입력.
		tree[parent*2+1] = data;
	} 
	public char getLeft(int node) {
		//node인덱스에 위치한 노드의 왼쪽 노드의 값을 리턴
		return tree[node*2];
	}
	public char getRight(int node) {
		//node인덱스에 위치한 노드의 오른쪽 노드의 값을 리턴
		return tree[node*2+1];
	}
	public String toString() {
		//toString
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < tree.length; i++) {
			sb.append(tree[i]).append(" ");
		}
		
		return sb.toString();
	}
	
	public void preOrder(int node) {
		if(node >= tree.length) {
			return;
		}
		if(tree[node] == '\0') {
			return;
		}
		//전위순회
		
		//현재위치 출력 후
		System.out.print(tree[node]);
		//왼쪽방문
		preOrder(node * 2);
		//오른쪽방문
		preOrder(node * 2 + 1);
	}
	
	public void printParent(int node) {
		//나를 방문
		System.out.println(tree[node]);
		//루트에 도달하면 종료
		if(node == 1) {
			return;
		}
		//내 부모를 재귀호출
		printParent(node / 2);
	}
}

public class 배열이진트리 {
	public static void main(String[] args) {
		Arr_BinaryTree tree = new Arr_BinaryTree(3);
		tree.setRoot('+');
		tree.setLeft('*', 1);
		tree.setRight('/', 1);
		tree.setLeft('1', 2);
		tree.setRight('2', 2);
		tree.setLeft('3', 3);
		tree.setRight('4', 3);
		
//		System.out.println(tree);
		tree.preOrder(1);
//		tree.printParent(7);
	}
}
