package day09;

public class Node<Key> {
	Key item;
	public Node() {
		
	}
	public Node(Key item) {
		this.item = item;
	}
	
	Node<Key> left;
	Node<Key> right;
}
