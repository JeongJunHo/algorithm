package mobis;

public class problem3 {
	public static void main(String[] args) {
		System.out.println(solution(new String[] {"ABCBA","DABAG","EBABH","FAJAI","AKLMA"}));
		System.out.println(solution(new String[] {"ABCBA","DABAG","EBABH","FAJAI","AKLMO"}));
		System.out.println(solution(new String[] {"ABCDF","ABCDF","ABCDF","ABCDF","ABCDF"}));
		String[] str = new String[1000];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 1000; i++) {
			sb.append("A");
		}
		for (int i = 0; i < str.length; i++) {
			str[i] = sb.toString();
		}
		System.out.println(solution(str));
	}
	
	static class Location{
		int row;
		int col;
		char word;
		
		public Location(int row, int col, char word) {
			super();
			this.row = row;
			this.col = col;
			this.word = word;
		}
	}
	
    public static int solution(String[] board) {
    	long start = System.currentTimeMillis();
        int answer = 0;
        int max = board.length;
        
        char[][] map = new char[max][max];
        for (int i = 0; i < max; i++) {
			map[i] = board[i].toCharArray();
		}
        
        int[][] pos = {{-1,-1},{-1,1},{1,-1},{1,1}};
        
        for (int i = 0; i < max; i++) {
        	if(answer / 2 >= Math.min(max-1-i, i)) {
				continue;
			}
			for (int j = 0; j < max; j++) {
				if(answer / 2 >= Math.min(max-1-j, j)) {
					continue;
				}
				
				int lt = 0;
				int rt = 0;
				int lb = 0;
				int rb = 0;
				int maxSize = 0;
				
				//좌상
				int nr = i;
				int nc = j;
				while (true) {
					nr += pos[0][0];
					nc += pos[0][1];
					
					if(posCheck(nr, nc, max) && map[nr][nc] == map[i][j]) {
						lt++;
					}else {
						break;
					}
				}
				if(lt == 0 || lt <= answer/2) {
					continue;
				}else {
					maxSize = lt;
				}
				//우상
				nr = i;
				nc = j;
				while (true) {
					nr += pos[1][0];
					nc += pos[1][1];
					
					if(posCheck(nr, nc, max) && map[nr][nc] == map[i][j]) {
						rt++;
					}else {
						break;
					}
				}
				if(rt == 0 || rt <= answer/2) {
					continue;
				}else {
					maxSize = Math.min(maxSize, rt);
				}
				//좌하
				nr = i;
				nc = j;
				while (true) {
					nr += pos[2][0];
					nc += pos[2][1];
					
					if(posCheck(nr, nc, max) && map[nr][nc] == map[i][j]) {
						lb++;
					}else {
						break;
					}
				}
				if(lb == 0 || lb <= answer/2) {
					continue;
				}else {
					maxSize = Math.min(maxSize, lb);
				}
				//우하
				nr = i;
				nc = j;
				while (true) {
					nr += pos[3][0];
					nc += pos[3][1];
					
					if(posCheck(nr, nc, max) && map[nr][nc] == map[i][j]) {
						rb++;
					}else {
						break;
					}
				}
				if(rb != 0) {
					maxSize = Math.min(maxSize, rb);
					answer = Math.max(answer, maxSize*2+1);
				}
			}
		}
        
        long end = System.currentTimeMillis();
        System.out.println("시간 : " + (end - start));
        
        return answer;
    }
    
    public static boolean posCheck(int row, int col, int max) {
    	return row >= 0 && row < max && col >= 0 && col < max;
    }
}
