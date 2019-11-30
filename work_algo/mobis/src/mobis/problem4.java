package mobis;

import java.util.LinkedList;
import java.util.Queue;

public class problem4 {
	public static void main(String[] args) {
		System.out.println(solution(30));
	}
	
	
    public static int solution(int T) {
        int answer = 0;
        if(T % 2 == 0) {
        	int pos[][] = {{-1,0},{1,0},{0,-1},{0,1}};
            char[][] word = {{'Q','W','E','R','T','Y','U','I','O','P'},{'A','S','D','F','G','H','J','K','L','.'},{'Z','X','C','V','B','N','M','.','.','.'}};
            Queue<Location> q = new LinkedList<Location>();
            q.add(new Location(1, 6, 'J', 0));
            
            while (!q.isEmpty()) {
    			Location loc = q.poll();
    			
    			if(loc.cnt == T) {
    				if(loc.word == 'J') {
    					answer++;
    				}
    			}else {
    				for (int i = 0; i < pos.length; i++) {
    					int nr = loc.row + pos[i][0];
    					int nc = loc.col + pos[i][1];
    					
    					if(posCheck(nr, nc) && word[nr][nc] != '.') {
    						q.add(new Location(nr, nc, word[nr][nc], loc.cnt+1));
    					}
    				}
    			}
    		}
    	}
        
        return answer;
    }
    
    public static boolean posCheck(int row, int col) {
    	return row >= 0 && row < 3 && col >= 0 && col < 10;
    }
    
    static class Location{
    	int row;
    	int col;
    	int word;
    	int cnt;
    	
		public Location(int row, int col, int word, int cnt) {
			super();
			this.row = row;
			this.col = col;
			this.word = word;
			this.cnt = cnt;
		}
    }
}
