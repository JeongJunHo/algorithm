package day09;

public class BinaryTreeTest {
	public static void main(String[] args) {
		BinaryTree<String> tree = new BinaryTree<String>("A");
		Node root = tree.getRoot();
		
		Node n1 = new Node("B");
		Node n2 = new Node("C");
		Node n3 = new Node("D");
		Node n4 = new Node("E");
		Node n5 = new Node("F");
		Node n6 = new Node("G");
		Node n7 = new Node("H");
		Node n8 = new Node("I");
		
		tree.setLeft(root, n1);
		tree.setRight(root, n2);
		
		tree.setLeft(n1, n3);
		tree.setRight(n1, n4);
		
		tree.setLeft(n2, n5);
		tree.setRight(n2, n6);
		
		tree.setLeft(n3, n7);
		tree.setRight(n3, n8);
		
//		tree.preOrder(tree.getRoot());
		tree.levelOrder();
	}
}
