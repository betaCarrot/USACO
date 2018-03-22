import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class mirror {
	private static final int EAST = 0;
	private static final int SOUTH = 1;
	private static final int WEST = 2;
	private static final int NORTH = 3;
	private static final int forward = 1;
	private static final int backward = 2;
	private static int direction, result;
	private static boolean finished;
	private static int[][] map;
	private static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("mirror.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mirror.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String line = f.readLine();
			for (int k = 0; k < M; k++) {
				if (line.substring(k, k + 1).equals("/"))
					map[i][k] = forward;
				else
					map[i][k] = backward;
			}
		}
		for (int i = 0; i < N; i++) {
			if (finished)
				break;
			direction = EAST;
			solve(i, 0, 0);
		}
		for (int i = 0; i < N; i++) {
			if (finished)
				break;
			direction = WEST;
			solve(i, M - 1, 0);
		}
		for (int i = 0; i < M; i++) {
			if (finished)
				break;
			direction = SOUTH;
			solve(0, i, 0);
		}
		for (int i = 0; i < M; i++) {
			if (finished)
				break;
			direction = NORTH;
			solve(N - 1, i, 0);
		}
		out.println(result);
		out.close();
	}

	public static void solve(int y, int x, int count) {
		changeDirection(map[y][x]);
		count++;
		if (count > N * M) {
			result = -1;
			finished = true;
		}
		if (direction == EAST) {
			if (!valid(y, x + 1)) {
				result = Math.max(result, count);
				return;
			} else
				solve(y, x + 1, count);
		} else if (direction == SOUTH) {
			if (!valid(y + 1, x)) {
				result = Math.max(result, count);
				return;
			} else
				solve(y + 1, x, count);
		} else if (direction == WEST) {
			if (!valid(y, x - 1)) {
				result = Math.max(result, count);
				return;
			} else
				solve(y, x - 1, count);
		} else {
			if (!valid(y - 1, x)) {
				result = Math.max(result, count);
				return;
			} else
				solve(y - 1, x, count);
		}
	}

	public static boolean valid(int y, int x) {
		return x >= 0 && x < M && y >= 0 && y < N;
	}

	public static void changeDirection(int mirror) {
		if (direction == EAST) {
			if (mirror == forward)
				direction = NORTH;
			else
				direction = SOUTH;
		} else if (direction == SOUTH) {
			if (mirror == forward)
				direction = WEST;
			else
				direction = EAST;
		} else if (direction == WEST) {
			if (mirror == forward)
				direction = SOUTH;
			else
				direction = NORTH;
		} else {
			if (mirror == forward)
				direction = EAST;
			else
				direction = WEST;
		}
	}
}
