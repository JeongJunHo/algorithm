package day13;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Prim_solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner("7 11\n"
				+ "0 1 31\r\n"
				+ "0 2 31\r\n"
				+ "0 6 31\r\n"
				+ "0 5 50\r\n"
				+ "1 2 21\r\n"
				+ "2 4 46\r\n"
				+ "2 6 25\r\n"
				+ "3 4 34\r\n"
				+ "4 6 51\r\n"
				+ "5 3 18\r\n"
				+ "5 4 40\r\n");

		int V = sc.nextInt();
		int E = sc.nextInt();
		//간선의 정보를 저장할 인접행렬
		int[][] adj = new int[V][V];
		
		//확보된 노드가 누구누구인지 확인할 수 있는 확보된 노드의 리스트.
		List<Integer> nodeList = new ArrayList<Integer>();
		//확보된 리스트를 순회하지 않고도 확보된놈인지 아닌지 구분하기 위한 방문배열
		boolean[] visited = new boolean[V];
		
		for (int i = 0; i < E; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			adj[a][b] = c;
			adj[b][a] = c;
		}
		
		//임의의 노드를 첫번째 노드로 지정.
		nodeList.add(0);
		//확보된 노드는 방문체크
		visited[0] = true;
		
		//최소값, 최소값의 위치, 간선비용의합을 저장할 변수
		int min, index, result = 0;
		
		//시작정점을 뺀 나머지 정점 수 만큼 반복
		for (int i = 0; i < V-1; i++) {
			min = 987654321;
			index = -1;
			//확보된 노드들을 하나씩 꺼내서, 기준노드(node)로 삼고 반복
			for (int node : nodeList) {
				//인접행렬을 이용하여 기준노드 기준으로 갈 수 있는 방문하지 않은 노드들과의 거리 중 최소값을 찾음.
				for (int j = 0; j < V; j++) {
					//방문하지 않았던 노드 -> !visited[j]
					//갈수있는 곳 -> adj[node][j] != 0
					//알고있던 최소값보다 작음 -> adj[node][j] < min
					if(!visited[j] && adj[node][j] != 0 && adj[node][j] < min) {
						min = adj[node][j];
						index = j;
					}
				}
			}
			
			//해당 비용을 누적함
			result += min;
			//최소값 정점을 확보된 노드리스트에 추가
			nodeList.add(index);
			//확보된 노드는 방문체크
			visited[index] = true;
		}
		System.out.println(result);
	}
}
