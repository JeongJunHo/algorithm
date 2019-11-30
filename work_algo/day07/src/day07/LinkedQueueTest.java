package day07;

class LinkedQueue{
	static class Node{
		int data;
		Node link;
	}
	//연결큐는 다음번에 꺼낼 자료의 위치인 front와 마지막에 넣은 자료의 위치인 rear가 있음
	//각각의 자료의 타입은 Node이니까
	Node front;
	Node rear;
	
	void enQueue(int data) {
		//data를 저장하는 Node객체 하나를 생성.
		Node newData = new Node();
		newData.data = data;
		
		//큐가 공백일때  다음번에 꺼내야 되는놈이 지금 넣는놈이고, 지금 넣는놈이 마지막에 넣은놈
		if(isEmpty()) {
			front = newData;
			rear = newData;
		}else {
			rear.link = newData;
			rear = newData;
		}
		//현재 rear가 d바라보고 있는 친구 의 다음위치에 새로만든걸 연결시켜주고 rear도 그놈을 바라봄.
	}
	
	int deQueue() {
		if(front == null) {
			rear = null;
			return -1;
		}
		Node tmp = front;
		
		front = front.link;
		
		return tmp.data;
	}
	
	boolean isEmpty() {
		if(front == null && rear == null) {
			return true;
		}
		
		return false;
	}
}

public class LinkedQueueTest {
	public static void main(String[] args) {
		LinkedQueue lq = new LinkedQueue();
		
		lq.enQueue(1);
		lq.enQueue(2);
		lq.enQueue(3);
		
		for (int i = 0; i < 4; i++) {
			int tmp = lq.deQueue();
			if(tmp != -1) {
				System.out.println(tmp);
			}else {
				System.out.println("비었습니다.");	
			}
		}
		
		
	}
}
