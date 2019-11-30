package day08;

import day08.MySingleLinkedList.Node;

class MySingleLinkedList{
	Node head;
	int size;
	
	void add(int data, int idx) {
		if( size <= idx || idx < 0) {
			return;
		}
		
		Node tmp = new Node();
		tmp.data = data;
		if(idx == 0) {
			addToFirst(data);
		}else if(idx == size) {
			addToLast(data);
		}else {
			Node prev = get(idx-1);
			Node next = prev.link;
			prev.link = tmp;
			tmp.link = next;
		}
		size++;
	}
	
	void delete(int idx) {
		if( size < 1) {
			return;
		}
		if(idx == 0) {
			head = head.link;
		}else {
			Node prev = get(idx-1);
			Node target = prev.link;
			prev.link = target.link;
		}
		size--;
	}
	
	Node get(int idx) {
		if(size >= idx || idx < 0) {
			Node tmp = head;
			for (int i = 0; i < idx; i++) {
				tmp = tmp.link;
			}

			return tmp;
		}
		
		return null;
	}
	
	void addToFirst(int data) {
		Node n = new Node();
		n.data = data;
		
		if(head == null) {
			head = n;
		}else {
			n.link = head;
			head = n;
		}
		size++;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node tmp = head;
		while (tmp != null) {
			sb.append(tmp.data).append(" ");
			tmp = tmp.link;
		}
		return sb.toString();
	}
	
	void addToLast(int data) {
		Node n = new Node();
		n.data = data;
		
		if(head == null) {
			head = n;
		}else {
			Node tmp = head;
			while (tmp.link != null) {
				tmp = tmp.link;
			}
			tmp.link = n;
		}
		size++;
	}
	
	static class Node{
		int data;
		Node link;
	}
}

public class SinglyLinedListTest {
	public static void main(String[] args) {
		MySingleLinkedList.Node n1 = new MySingleLinkedList.Node();
		
		n1.data = 10;
		
		MySingleLinkedList.Node n2 = new MySingleLinkedList.Node();
		n2.data = 20;
		n1.link = n2;
		
		MySingleLinkedList.Node n3 = new MySingleLinkedList.Node();
		n3.data = 30;
		n2.link = n3;
		
		MySingleLinkedList.Node n4 = new MySingleLinkedList.Node();
		n4.data = 40;
		n4.link = n3;
		
		n2 = n3 = n4 = null;
		
		Node tmp = n1;
		while (tmp.link != null) {
			tmp = tmp.link;
		}
		
		MySingleLinkedList l = new MySingleLinkedList();
		
		l.addToFirst(1);
		l.addToFirst(2);
		l.addToFirst(3);
		l.addToLast(4);
		System.out.println(l.toString());
		System.out.println(l.get(1).data);
		l.add(10, 2);
		System.out.println(l.toString());
		l.delete(2);
		System.out.println(l.toString());
	}
}
