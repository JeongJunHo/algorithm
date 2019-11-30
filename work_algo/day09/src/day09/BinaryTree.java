package day09;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<Key> {
	Node<Key> root;
	
	public BinaryTree(Key data) {
		root = new Node<>();
		root.item = data;
	}
	
	public Node getRoot() {
		return root;
	}
	
	public void setLeft(Node node, Node left) {
		node.left = left;
	}
	public void setRight(Node node, Node right) {
		node.right = right;
	}
	public Node getLeft(Node node) {
		return node.left;
	}
	public Node getRight(Node node) {
		return node.right;
	}
	public void preOrder(Node node) {
		if(node == null)
			return;
		System.out.println(node.item);
		
		preOrder(node.left);
		preOrder(node.right);
	}
	public void levelOrder() {
		Queue<Node> queue = new LinkedList<Node>();
		
		queue.add(root);
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			System.out.print(node.item);
			
			if(node.left != null) {
				queue.add(node.left);
			}
			if(node.right != null) {
				queue.add(node.right);
			}
		}
	}
}
