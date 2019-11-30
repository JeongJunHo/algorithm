package baekjoon.bj1260_DFS와_BFS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class DFS와_BFS {
	//인접리스트 구현
	static Graph graph;
	//방문 체크
	static boolean[] check;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//정점의 갯수
		int n = sc.nextInt();
		//간선의 갯수
		int m = sc.nextInt();
		//탐색 시작 번호
		int v = sc.nextInt();
		
		graph = new Graph(n);
		
		//간선 갯수 만큼 반복
		for (int i = 1; i <= m; i++) {
			graph.put(sc.nextInt(), sc.nextInt());
		}
		
		//같은 깊이의 정점일 경우 낮은 값부터 접근하기 위해 정렬해준다.
		graph.sort();
		
		//탐색이력을 남기기위해 초기화(0번은 쓰지않음)
		check = new boolean[n+1];
		//깊이우선탐색
		dfs(v);
		
		//탐색이력을 남기기위해 초기화(0번은 쓰지않음)
		check = new boolean[n+1];
		bfs(v);
	}
	
	//Stack를 이용한 깊이우선탐색(Depth First Search)  (탐색을 시작할 정점 번호를 받는다.)
	public static void dfs(int v) {
		StringBuilder sb = new StringBuilder();
		//스택 생성
		Stack<Integer> stack = new Stack<Integer>();
		
		stack.push(v);
		//방문정점으로 변경
		check[v] = true;
		sb.append(v).append(" ");
		
		while (!stack.isEmpty()) {
			//간선방문여부
			boolean visit = false;
			//스택의 최상위 정점
			int top = stack.peek();
			//정점과 연결된 간선리스트를 불러온다.
			for (Integer vertex : graph.get(top)) {
				//아직 방문하지 않은 정점인지 체크
				if(!check[vertex]) {
					//방문정점으로 변경
					check[vertex] = true;
					//방문할 정점을 찾음
					visit = true;
					//출력버퍼에 입력
					sb.append(vertex).append(" ");
					//스택에 추가
					stack.push(vertex);
					//다음 정점으로 진행하지않고 끝낸다. 
					break;
				}
			}
			
			//더이상 방문할 정점이 없을 경우 해당 
			if(!visit) {
				stack.pop();
			}
		}
		
		System.out.println(sb);
	}
	
	//너비 우선 탐색(Breadth First Search)
	public static void bfs(int v) {
		StringBuilder sb = new StringBuilder();
		//깊이 우선 탐색을 위해 Queue 생성
		Queue<Integer> queue = new LinkedList<Integer>();
		
		//큐에 시작 정점을 삽입
		queue.add(v);
		//방문로그를 남긴다.
		check[v] = true;
		//버퍼에 해당 정점 출력 저장
		sb.append(v).append(" ");
		
		//큐가 완전히 빌때까지 반복
		while (!queue.isEmpty()) {
			//큐의 첫번째 정점의 간선을 순회
			for (Integer vertex : graph.get(queue.peek())) {
				//방문하지않은 정점이라면
				if(!check[vertex]) {
					queue.add(vertex);
					check[vertex] = true;
					sb.append(vertex).append(" ");
				}
			}
			
			queue.poll();
		}
		
		System.out.println(sb);
	}
}

class Graph{
	List<List<Integer>> vertexList;
	
	//기본 생성자는 쓰지않는다.
	private Graph() {
		
	}
	
	//정점의 갯수 + 1만큼 간선리스트를 만들어놓는다. (0번은 쓰지않는다.)
	public Graph(int n) {
		vertexList = new ArrayList<List<Integer>>();
		
		for (int i = 0; i < n+1; i++) {
			vertexList.add(new ArrayList<Integer>());
		}
	}
	
	//입력받은 정점과 정점을 잇는 간선을 기억한다. 문제는 양방향 간선이기때문에 동시에 기억한다.
	public void put(int vertex, int target) {
		vertexList.get(vertex).add(target);
		vertexList.get(target).add(vertex);
	}
	
	//정점의 간선리스트를 반환한다.
	public List<Integer> get(int vertex) {
		return vertexList.get(vertex);
	}
	
	//같은 레벨의 간선일 경우 낮은 정점부터 방문할 수 있도록 정점별 간선 정렬 실행
	public void sort() {
		for (List<Integer> list : vertexList) {
			Collections.sort(list);
		}
	}
}