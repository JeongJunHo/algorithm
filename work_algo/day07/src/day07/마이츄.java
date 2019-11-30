package day07;

import java.util.LinkedList;
import java.util.Queue;

public class 마이츄 {
	static class Person{
		int num;
		int cnt;
		int acc;
		
		Person(int n, int c, int a){
			num = n;
			cnt = c;
			acc = a;
		}
	}
	
	public static void main(String[] args) {
		Queue<Person> queue = new LinkedList<>();
		int pNum = 1; //입장하는 사람의 번호
		int mychuCnt = 20;
		queue.add(new Person(pNum, 1, 0));
		Person p = null;
		
		while (mychuCnt > 0) {
			p = queue.poll();
			if(mychuCnt - p.cnt < 0) {
				p.acc += mychuCnt;
				mychuCnt = 0;
				p.cnt++;
			}else {
				mychuCnt -= p.cnt;
				p.acc += p.cnt;
				p.cnt++;
			}
			queue.add(p);
			queue.add(new Person(++pNum,1, 0));
			for (Person person : queue) {
				System.out.println(person.num + " " + person.cnt + " " +person.acc);
			}
			System.out.println();
		}
		
		for (Person person : queue) {
			System.out.println(person.num + " " +person.acc);
		}
	}
}
