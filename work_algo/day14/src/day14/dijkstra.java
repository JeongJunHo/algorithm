package day14;

import java.util.Arrays;
import java.util.Scanner;

public class dijkstra {
	public static void main(String[] args) {
		Scanner sc = new Scanner("6 11\r\n" + 
                "0 1 3\r\n" + 
                "0 2 5\r\n" + 
                "1 2 2\r\n" + 
                "1 3 6\r\n" + 
                "2 1 1\r\n" + 
                "2 4 6\r\n" + 
                "2 3 4\r\n" + 
                "3 4 2\r\n" + 
                "3 5 3\r\n" + 
                "4 0 3\r\n" + 
                "4 5 6");
		
		int V = sc.nextInt();
		int E = sc.nextInt();
		int[][] adj = new int[V][V];
		//입력을 인접행렬에 저장하시오 (유향그래프)
		for (int i = 0; i < E; i++) {
			adj[sc.nextInt()][sc.nextInt()] = sc.nextInt();
		}
		int[] dist = new int[V];
		boolean[] visited = new boolean[V];
		//0번을 시작점으로
		//0번 방문배열에 체크를 하고
		//dist에는 0번에 도달할 수 없는 정점은 큰 정수값을, 있는 곳은 0번부터의 거리를 적어주자.
		visited[0] = true;
		for (int i = 1; i < V; i++) {
			if(adj[0][i] == 0) {
				dist[i] = Integer.MAX_VALUE;
			}else {
				dist[i] = adj[0][i];
			}
		}
		System.out.println(Arrays.toString(dist));
		//남은 V-1개의 정점에 대해서 반복하며
		//가장 dist가 작은 녀석을 골라서(아직안고른거중에)
		//고른 놈으로부터 갈 수 있는 새로운 거리들 중 더 작은값을 갱신
		for (int i = 0; i < V-1; i++) {
			int minDist = Integer.MAX_VALUE;
			int minIdx = 0;
			//방문하지 않은 녀석 중 가장 dist가 짧은놈을 고르자.
			for (int j = 0; j < V; j++) {
				if (!visited[j] && dist[j] < minDist) {
					minDist = dist[j];
					minIdx = j;
				}
			}
			//모든 노드를 검사하면서, minIdx번째 노드로부터 갈 수 있는 곳이라면,
			//그 노드로 가는 현재까지 알던 dist와 minIx까지 가는 dist + minIdx로부터 그노드로 가는 거리 중 작은 값을 dist에 갱신
			for (int j = 0; j < V; j++) {
				//길이가 있으면서 adj[minIdx][j] != 0
				//내가 알고있던 j까지의 거리보다, minIdx를 거쳐 j로 가는 거리가 짧다면 -> dist[j] > dist[minIdx] + adj[minIdx][j]
				if(adj[minIdx][j] != 0 && dist[j] > dist[minIdx] + adj[minIdx][j]) {
					dist[j] = dist[minIdx] + adj[minIdx][j];
				}
			}
			visited[minIdx] = true;
			System.out.println(Arrays.toString(dist));
		}
	}
}
