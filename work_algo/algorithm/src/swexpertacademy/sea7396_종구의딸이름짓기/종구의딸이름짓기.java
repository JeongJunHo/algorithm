package swexpertacademy.sea7396_종구의딸이름짓기;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 종구의딸이름짓기 {
	static class Node{
		int r, c;
		char ch;
		Node(int r, int c, char ch){
			this.r = r;
			this.c = c;
			this.ch = ch;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			char[][] map = new char[N][M];
			for(int i = 0; i < N; i++)
				map[i] = sc.next().toCharArray();
			
			StringBuilder sb = new StringBuilder();
			//BFS시작.
			boolean[][] visited = new boolean[N][M];
			Queue<Node> queue = new LinkedList<>();
			queue.add(new Node(0,0,map[0][0]));
			visited[0][0] = true;
			char ch = map[0][0]; //현재 뎁쓰에서 가장 작은 문자
			while( !queue.isEmpty()) {
				char next = 'z';//일단 큰 값
				sb.append(ch);
				//현재 큐에 있는 모든 노드들을 꺼내서. 기억한 가장 빠른 문자이면서 다음 이동할 위치가 있으면 큐에 삽입하되. 
				//가장 작은 문자가 무엇이었는지 기억.
				int size = queue.size();
				//한 뎁쓰의 상태노드들을 검사
				for(int i = 0; i < size; i++) {
					Node current = queue.poll();
					//이번에 꺼낸 노드가 젤 작은 문자를 갖는다면
					if( ch == current.ch ) {
						for(int d = 0; d < 2; d++) {
							int nr = current.r + dr[d];
							int nc = current.c + dc[d];
							if( nr >= N || nc >= M )
								continue;
							if( visited[nr][nc] )
								continue;
							
							visited[nr][nc] = true;
							queue.add(new Node(nr,nc,map[nr][nc]));
							if( next > map[nr][nc] )
								next = map[nr][nc];
						}
					}
				}
				ch = next;
			}
			System.out.println("#" +tc + " " + sb.toString());
		}
	}
	//방향은 오른쪽과 아래
	static int[] dr = {0,1};
	static int[] dc = {1,0};
}