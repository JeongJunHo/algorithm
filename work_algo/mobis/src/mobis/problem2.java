package mobis;

import java.util.PriorityQueue;

public class problem2 {
	public static void main(String[] args) {
		
		String ans = solution(6, new String[] {"AZ3618", "XP9657", "SP6823", "UH7515", "TV6621", "WZ8264"}, new int[] {20, 16, 18, 20, 24, 19}, 8, new int[]{3, 7, 5, 8, 6, 5, 10, 2});
		System.out.println(ans);
	}
	
	static class Car implements Comparable<Car>{
		String plate;
		int use;
		public Car(String plate, int use) {
			super();
			this.plate = plate;
			this.use = use;
		}
		
		@Override
		public int compareTo(Car o) {
			if(this.use == o.use) {
				return this.plate.compareTo(o.plate);
			}
			return this.use - o.use;
		}
	}
	
    public static String solution(int n, String[] plates, int[] odo, int k, int[] drives) {
        String answer = "";
        
        PriorityQueue<Car> q = new PriorityQueue<Car>();
        
        for (int i = 0; i < n; i++) {
			q.add(new Car(plates[i], odo[i]));
		}
        
        for (int i = 0; i < k; i++) {
			Car car = q.poll();
			
			car.use += drives[i];
			
			q.add(car);
		}
        
        answer = q.poll().plate;
        
        return answer;
    }
}
