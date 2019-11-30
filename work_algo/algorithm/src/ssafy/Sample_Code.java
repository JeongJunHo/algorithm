package ssafy;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
public class Sample_Code {

	// User and Game Server Information
	static final String NICKNAME = "Java Player";
	static final String HOST = "127.0.0.1";
	static final int PORT = 1447; // Do not modify
	
	// predefined variables(Do not modify these values)
	static final int TABLE_WIDTH = 254;
	static final int TABLE_HEIGHT = 124;
	static final int NUMBER_OF_BALLS = 5;
	static final int[][] HOLES = {{0, 0}, {130, 0}, {260, 0}, {0, 130}, {130, 130}, {260, 130}};
	
	public static void main(String[] args) {
		
		Socket socket = null;
		String recv_data = null;
		byte[] bytes = new byte[1024];
		int[][] balls = new int[NUMBER_OF_BALLS][2];

		try {
			socket = new Socket();
			System.out.println("Trying Connect: " + HOST + ":" + PORT);
			socket.connect(new InetSocketAddress(HOST, PORT));
			System.out.println("Connected: " + HOST + ":" + PORT);
			
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			String send_data = "9901/" + NICKNAME;
			bytes = send_data.getBytes("UTF-8");
			os.write(bytes);
			os.flush();
			System.out.println("Ready to play.");
			
			while (socket != null) {
				
				bytes = new byte[1024];
				
				int readByteCount = is.read(bytes);
				recv_data = new String(bytes, 0, readByteCount, "UTF-8");
				System.out.println("Data Received: " + recv_data);
				
				String[] split_data = recv_data.split("/");
				if (split_data[0].equals("9909")) break;
				
				int idx = 0;
				try {
					for (int i = 0; i < NUMBER_OF_BALLS; i++) {
						for (int j = 0; j < 2; j++) {
							balls[i][j] = Integer.parseInt(split_data[idx++]);
						}
					}
				}
				catch (Exception e) {
					bytes = new byte[1024];
					balls = new int[NUMBER_OF_BALLS][2];
					bytes = "9902/9902".getBytes("UTF-8");
					os.write(bytes);
					os.flush();
					System.out.println("Received Data has been currupted, Resend Requested.");
					continue;
				}
				Scanner sc = new Scanner(System.in);
				
				String[] split = recv_data.split("/");
				double[] myball = new double[2];
				myball[0] = Double.parseDouble(split[0]);
				myball[1] = Double.parseDouble(split[1]);
				
				double[][] targetArr = new double[4][2];
				int targetIdx = 2;
				for (int i = 0; i < 4; i++) {
					targetArr[i][0] = Double.parseDouble(split[targetIdx++]);
					targetArr[i][1] = Double.parseDouble(split[targetIdx++]);
				}
				
				double[] target = new double[2];
				for (int i = 0; i < targetArr.length; i++) {
					if(targetArr[i][0] != 0 && targetArr[i][1] != 0) {
						target[0] = targetArr[i][0];
						target[1] = targetArr[i][1];
						break;
					}
				}
				
				double x = target[0] - myball[0];
				double y = target[1] - myball[1];
				
				double myAngle = Math.atan2(x, y) * (180.0 / Math.PI); 
				
				//목적구가 향하는 가장 근접한 각도의 홀 탐색
				int[] targetHole = new int[2];
				int bestIdx = -1;
				double best = Double.MAX_VALUE;
				for (int i = 0; i < HOLES.length; i++) {
					double targetX = HOLES[i][0] - target[0];
					double targetY = HOLES[i][1] - target[1];
					
					double targetAngle = Math.atan2(targetX, targetY) * (180.0 / Math.PI);
					double calc = Math.abs(myAngle - targetAngle);
					if(best > calc) {
						best = calc;
						bestIdx = i;
					}
				}
				
				System.out.println("HOLE ::" + Arrays.toString(HOLES[bestIdx]));
				double targetX = HOLES[bestIdx][0] - target[0];
				double targetY = HOLES[bestIdx][1] - target[1];
				
				double targetAngle = Math.atan2(targetX, targetY) * (180.0 / Math.PI);
				
				System.out.println("targetAngle::" + targetAngle);
				
				System.out.println(Arrays.toString(target));
				solved(HOLES[bestIdx], myball, target, targetAngle);
				System.out.println(Arrays.toString(target));
				
//				x = target[0] - myball[0];
//				y = target[1] - myball[1];
//				
//				myAngle = Math.atan2(x, y) * (180.0 / Math.PI);
				
				double angle = myAngle;
				int power = 100;
				
//				double angle = sc.nextDouble();
//				int power = sc.nextInt();
				
				////////////////////////////////////////////////////////////////////
				// �־��� ����(balls)�� �������� �� ����(angle)�� ����(power)�� �����ϴ� �ڵ� �ۼ� //
				//////////////////////////////////////////////////////////////////
				
				String merged_data = angle + "/" + power;
				bytes = merged_data.getBytes("UTF-8");
				os.write(bytes);
				os.flush();
				System.out.println("Data Sent: " + merged_data);
				System.out.println();
				System.out.println();
			}
			os.close();
			is.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void solved(int[] hole, double[] myball, double[] target, double targetAngle) {
		double currentX = -1;
		double currentY = -1;
		int distance = Integer.MAX_VALUE;
		for (int i = -15; i <= 15; i++) {
			for (int j = -15; j <= 15; j++) {
				if(Math.abs(i) == 15 || Math.abs(j) == 15) {
					double nx = target[0] + i;
					double ny = target[1] + j;
					
					double tmpX = hole[0] - nx;
					double tmpY = hole[1] - ny;
					
					double nAngle = Math.atan2(tmpX, tmpY) * (180.0 / Math.PI);
					if((int)targetAngle == (int)nAngle) {
						System.out.println(nAngle);
						System.out.println("변경된 좌표::" + nx + " " + ny);
						target[0] = nx;
						target[1] = ny;
						System.out.println("복사완료");
						if(currentX == -1 && currentY == -1) {
							currentX = nx;
							currentY = ny;
						}else {
							int d = (int) (Math.abs(myball[0] - nx) + Math.abs(myball[1] - ny));
							if(distance > d) {
								currentX = nx;
								currentY = ny;
								distance = d;
							}
						}
					}
				}
			}
		}
	}
}
