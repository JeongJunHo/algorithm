package baekjoon.bj16235_나무재테크;

import java.util.ArrayList;
import java.util.Scanner;

public class 나무재테크_이경석 {
	
	
	static int[] dx = {-1,-1,0,1,1,1,0,-1};
	static int[] dy = {0,-1,-1,-1,0,1,1,1};
	static class info{
		int rem_food;		
		int start; //죽은 나무를 체크 하기 위한 지점
		int before_dead; // 죽은나무를 중복해서 양분으로 쓰지 않기 위한 체크지점
		ArrayList<Integer> alive;
		info()
		{			
			rem_food = 5;
			alive = new ArrayList<>();
			start = 0;
			before_dead = 0;			
		}
		
		void push(int age)
		{
			alive.add(age);
		}
		
		void breeding(int start_x,int start_y)
		{				
			//뒤에서 부터 제일 나이가 어린놈을 뽑는다.
			//start는 현재까지 살아있는 놈
			for(int i = alive.size()-1 ; i >= start; i--)
			{
				int age = alive.get(i);				
				if(age %5 ==0)
				{
					for(int k = 0 ; k < 8; k++)
					{
						int nx = start_x + dx[k];
						int ny = start_y + dy[k];
						if(nx >=0 && nx < N && ny >=0 && ny <N)
						{
							tree[ny][nx].push(1);
						}
					}
				}
			}
		}
		
		void feed()
		{	
			//뒤에서 부터 제일 나이가 어린놈을 뽑는다.
			//start는 현재까지 살아있는 놈
			for(int i = alive.size()-1; i >= start; i--)
			{
				int age = alive.get(i);
				if(rem_food-age >=0)
				{
					rem_food -= age;
					alive.set(i, age+1);
				}
				else {
					start = i+1;
					break;
				}
			}			
		}
		
		void dead()
		{
			for(int i = before_dead; i < start; i++)
			{
				rem_food += alive.get(i)/2;				
			}
			before_dead = start;
		}				
	}
	
	
	static int N,M,K;
	
	static int[][] food;
	static info[][] tree;
	
//	static void print(int N)
//	{
//		System.out.println("======food========");
//		for(int i = 0 ; i < N; i++)
//		{
//			for(int j = 0 ; j < N; j++)
//			{
//				System.out.print(tree[i][j].rem_food+" ");
//			}
//			System.out.println();
//		}
//		System.out.println("=======tree=======");
//		for(int i = 0 ; i < N; i++)
//		{
//			for(int j = 0 ; j < N; j++)
//			{
//				System.out.print(tree[i][j].pq.size()+" ");
//			}
//			System.out.println();
//		}
//		
//	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();		
		food = new int[N][N];
		tree = new info[N][N];
	
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
				tree[i][j] = new info();
		}
		for(int i = 0 ; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				food[i][j] = sc.nextInt();
			}
		}
		for(int i = 0 ; i < M; i++)
		{
			int x = sc.nextInt(),y = sc.nextInt(),age = sc.nextInt();
			tree[x-1][y-1].push(age);
		}
		
		int year = 0;
		while(year !=K)
		{
			//봄
						
			for(int i = 0 ; i < N; i++)
			{
				for(int j = 0 ; j < N; j++)
				{
					tree[i][j].feed();
				}
			}			
			
			//여름
			for(int i = 0 ; i < N; i++)
			{
				for(int j = 0 ; j < N; j++)
				{
					tree[i][j].dead();
				}
			}			
			//가을
			for(int i = 0 ; i < N; i++)
			{
				for(int j = 0 ; j < N; j++)
				{
					tree[i][j].breeding(j, i);
				}
			}		
			
			//겨울
			
			for(int i = 0 ; i < N; i++)
			{
				for(int j = 0; j <N; j++)
				{
					tree[i][j].rem_food += food[i][j];
				}
			}			
			year++;
		}
		//살아남은거 카운팅
		int answer = 0;
		for(int i = 0 ; i < N; i++)
		{
			for(int j = 0 ; j <N; j++)
			{
				answer += tree[i][j].alive.size()-tree[i][j].start;
			}
		}
		System.out.println(answer);
		
	}

}
