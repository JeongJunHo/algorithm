package baekjoon.problem1991;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node<T>{
	T data;
	Node<T> left;
	Node<T> right;
	
	Node(T data){
		this.data = data;
	}
}

class BinaryTree<T>{
	//최상위 노드
	Node<T> root;
	
	public BinaryTree() {
	}
	
	BinaryTree(Node<T> root){
		this.root = root;
	}
	
	//노드 추가
	void addNode(T data, T left, T right) {
		//최상위가 비었다면 최상위에 등록
		if(root == null) {
			root = new Node<T>(data);
			//.이 아니라면 왼쪽에 등록
			setLeft(root, left);
			//.이 아니라면 오른쪽에 등록
			setRight(root, right);
		//최상위가 이미 있다면
		}else {
			Queue<Node<T>> queue = new LinkedList<Node<T>>();
			//큐에 최상위 노드 삽입
			queue.add(root);
			
			//삽입이 완료되었는지 판단
			boolean in = false;
			//큐가 빌때까지
			while (!queue.isEmpty()) {
				//큐에서 노드를 빼낸다.
				Node<T> node = queue.poll();
				//노드의 왼쪽 자식이 있고 아직 삽입이 완료되지 않았다면
				if(node.left != null && !in) {
					//왼쪽 자식노드가 현재 삽입하려는 값이라면
					if(node.left.data.equals(data)) {
						//해당 노드의 왼편 오른편에 값을 지정 .이라면 넣지않는다.
						setLeft(node.left, left);
						setRight(node.left, right);
						//삽입완료처리
						in = true;
					}else {
						//해당값을 찾지 못했다면 더 깊이 이동하기위해 현재 위치를 큐에 넣는다.
						queue.add(node.left);
					}
				}
				//위의 내용 오른쪽으로 동일하게 수행
				if(node.right != null && !in) {
					if(node.right.data.equals(data)) {
						
						setLeft(node.right, left);
						setRight(node.right, right);
						in = true;
					}else {
						queue.add(node.right);
					}
				}
				
				if(in) {
					break;
				}
			}
		}
	}
	
	//왼쪽에 타겟값으로 노드를 생성 .일경우에 생성하지않는다.
	void setLeft(Node<T> node, T target){
		if(!target.equals(".")) {
			node.left = new Node<T>(target);
		}
	}
	
	//오른쪽에 타겟값으로 노드를 생성 .일경우에 생성하지않는다.
	void setRight(Node<T> node, T target) {
		if(!target.equals(".")) {
			node.right = new Node<T>(target);
		}
	}
	
	//전위순회
	void preOrder(Node<T> node){
		if(node == null) {
			return;
		}
		
		System.out.print(node.data);
		preOrder(node.left);
		preOrder(node.right);
	}
	
	//중위순회
	void inOrder(Node<T> node) {
		if(node == null) {
			return;
		}
		
		inOrder(node.left);
		System.out.print(node.data);
		inOrder(node.right);
	}
	
	//후위순회
	void postOrder(Node<T> node) {
		if(node == null) {
			return;
		}
		
		postOrder(node.left);
		postOrder(node.right);
		System.out.print(node.data);
	}
}

public class 트리_순회 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		BinaryTree<String> binaryTree =  new BinaryTree<String>();
		for (int i = 0; i < n; i++) {
			binaryTree.addNode(sc.next(), sc.next(), sc.next());
		}
		
		binaryTree.preOrder(binaryTree.root);
		System.out.println();
		binaryTree.inOrder(binaryTree.root);
		System.out.println();
		binaryTree.postOrder(binaryTree.root);
	}
}
