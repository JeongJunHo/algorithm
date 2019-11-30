package day09;

public class MaxHeapTest {
	public static void main(String[] args) {
		MaxHeap mh = new MaxHeap();
		
		mh.insert(4);
		mh.insert(13);
		mh.insert(11);
		mh.insert(15);
		mh.insert(19);
		mh.insert(20);
		
		mh.print();
		System.out.println(mh.level);
		System.out.println("=================");
		System.out.println("root : " + mh.root);
		System.out.println("idx : " + mh.idx);
		
		mh.delete();
		
		mh.print();
		System.out.println(mh.level);
		System.out.println("=================");
		System.out.println("root : " + mh.root);
		System.out.println("idx : " + mh.idx);
	}
}
