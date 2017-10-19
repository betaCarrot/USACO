import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ballet {
	private static int[][] map = new int[4][2];
	private static int minX, minY, maxX, maxY, direction;
	private static final int NORTH = 1;
	private static final int EAST = 2;
	private static final int SOUTH = 3;
	private static final int WEST = 4;
	private static final int RL = 0;
	private static final int RR = 1;
	private static final int FL = 2;
	private static final int FR = 3;
	private static final int X = 0;
	private static final int Y = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ballet.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ballet.out")));
		int N = Integer.parseInt(f.readLine());
		direction = 1;
		map[RL][X] = 0;
		map[RL][Y] = 0;
		map[RR][X] = 1;
		map[RR][Y] = 0;
		map[FL][X] = 0;
		map[FL][Y] = 1;
		map[FR][X] = 1;
		map[FR][Y] = 1;
		boolean tripped = false;
		for (int i = 0; i < N; i++) {
			String s = f.readLine();
			String foot = s.substring(0, 2);
			String process = s.substring(2);
			int index = -1;
			if (foot.equals("RL"))
				index = RL;
			else if (foot.equals("RR"))
				index = RR;
			else if (foot.equals("FL"))
				index = FL;
			else
				index = FR;
			if (process.equals("F"))
				forward(index);
			else if (process.equals("B"))
				backward(index);
			else if (process.equals("L"))
				left(index);
			else if (process.equals("R"))
				right(index);
			else
				pivot(index);
			if (tripped()) {
				tripped = true;
				break;
			}
		}
		if (tripped)
			out.println(-1);
		else
			out.println(area());
		out.close();
	}

	public static void displayMap() {
		for (int i = 0; i <= 3; i++) {
			if (i == 0)
				System.out.print("RL: ");
			if (i == 1)
				System.out.print("RR: ");
			if (i == 2)
				System.out.print("FL: ");
			if (i == 3)
				System.out.print("FR: ");
			System.out.print(map[i][X] + " " + map[i][Y] + " ");
		}
		System.out.println();
	}

	public static int area() {
		return (maxX - minX + 1) * (maxY - minY + 1);
	}

	public static boolean tripped() {
		if (map[0][X] == map[1][X] && map[0][Y] == map[1][Y])
			return true;
		if (map[0][X] == map[2][X] && map[0][Y] == map[2][Y])
			return true;
		if (map[0][X] == map[3][X] && map[0][Y] == map[3][Y])
			return true;
		if (map[1][X] == map[2][X] && map[1][Y] == map[2][Y])
			return true;
		if (map[1][X] == map[3][X] && map[1][Y] == map[3][Y])
			return true;
		if (map[2][X] == map[3][X] && map[2][Y] == map[3][Y])
			return true;
		return false;
	}

	public static void forward(int index) {
		if (direction == NORTH)
			map[index][Y]++;
		if (direction == EAST)
			map[index][X]++;
		if (direction == SOUTH)
			map[index][Y]--;
		if (direction == WEST)
			map[index][X]--;
		update();
	}

	public static void backward(int index) {
		if (direction == NORTH)
			map[index][Y]--;
		if (direction == EAST)
			map[index][X]--;
		if (direction == SOUTH)
			map[index][Y]++;
		if (direction == WEST)
			map[index][X]++;
		update();
	}

	public static void left(int index) {
		if (direction == NORTH)
			map[index][X]--;
		if (direction == EAST)
			map[index][Y]++;
		if (direction == SOUTH)
			map[index][X]++;
		if (direction == WEST)
			map[index][Y]--;
		update();
	}

	public static void right(int index) {
		if (direction == NORTH)
			map[index][X]++;
		if (direction == EAST)
			map[index][Y]--;
		if (direction == SOUTH)
			map[index][X]--;
		if (direction == WEST)
			map[index][Y]++;
		update();
	}

	public static void pivot(int index) {
		int a = map[index][X];
		int b = map[index][Y];
		for (int i = 0; i <= 3; i++) {
			int x = map[i][X];
			int y = map[i][Y];
			map[i][X] = a + y - b;
			map[i][Y] = b - x + a;
		}
		update();
		updateDirection();
	}

	public static void update() {
		maxX = Math.max(maxX, Math.max(map[FR][X], Math.max(map[FL][X], Math.max(map[RL][X], map[RR][X]))));
		minX = Math.min(minX, Math.min(map[FR][X], Math.min(map[FL][X], Math.min(map[RL][X], map[RR][X]))));
		maxY = Math.max(maxY, Math.max(map[FR][Y], Math.max(map[FL][Y], Math.max(map[RL][Y], map[RR][Y]))));
		minY = Math.min(minY, Math.min(map[FR][Y], Math.min(map[FL][Y], Math.min(map[RL][Y], map[RR][Y]))));
	}

	public static void updateDirection() {
		if (direction == 4)
			direction = 1;
		else
			direction++;
	}
}
