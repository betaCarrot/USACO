import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class dream {
	private static final int RED = 0;
	private static final int PINK = 1;
	private static final int ORANGE = 2;
	private static final int BLUE = 3;
	private static final int PURPLE = 4;
	private static final int EAST = 0;
	private static final int NORTH = 1;
	private static final int WEST = 2;
	private static final int SOUTH = 3;
	private static int[][] map;
	private static boolean[][][] visited;
	private static int[] dr = new int[] { -1, 1, 0, 0 };
	private static int[] dc = new int[] { 0, 0, -1, 1 };
	private static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("dream.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dream.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int result = bfs();
		out.println(result);
		out.close();
	}

	public static boolean valid(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}

	public static int bfs() {
		PriorityQueue<node> queue = new PriorityQueue<node>();
		queue.offer(new node(0, 0, 0, 0, 0));
		while (!queue.isEmpty()) {
			node curr = queue.poll();
			int row = curr.getRow();
			int col = curr.getCol();
			int smell = curr.getSmell();
			int count = curr.getCount();
			int direction = curr.getDirection();
			if (visited[row][col][smell])
				continue;
			boolean purplePassed = false;
			if (map[row][col] == PURPLE) {
				if (direction == EAST) {
					if (!valid(row, col + 1) || map[row][col + 1] == RED || map[row][col + 1] == BLUE)
						purplePassed = true;
					else
						queue.offer(new node(row, col + 1, 0, count + 1, direction));
				}
				if (direction == NORTH) {
					if (!valid(row - 1, col) || map[row - 1][col] == RED || map[row - 1][col] == BLUE)
						purplePassed = true;
					else
						queue.offer(new node(row - 1, col, 0, count + 1, direction));
				}
				if (direction == WEST) {
					if (!valid(row, col - 1) || map[row][col - 1] == RED || map[row][col - 1] == BLUE)
						purplePassed = true;
					else
						queue.offer(new node(row, col - 1, 0, count + 1, direction));
				}
				if (direction == SOUTH) {
					if (!valid(row + 1, col) || map[row + 1][col] == RED || map[row + 1][col] == BLUE)
						purplePassed = true;
					else
						queue.offer(new node(row + 1, col, 0, count + 1, direction));
				}
				if (!purplePassed)
					continue;
				else {
					smell = 0;
				}
			}
			visited[row][col][smell] = true;
			if (map[row][col] == RED)
				continue;
			if (map[row][col] == ORANGE)
				smell = 1;
			if (map[row][col] == BLUE && smell == 0)
				continue;
			if (row == N - 1 && col == M - 1) {
				return count;
			}
			for (int k = 0; k < 4; k++) {
				int nextRow = row + dr[k];
				int nextCol = col + dc[k];
				if (!valid(nextRow, nextCol))
					continue;
				if (k == 0) {
					queue.offer(new node(nextRow, nextCol, smell, count + 1, NORTH));
				}
				if (k == 1) {
					queue.offer(new node(nextRow, nextCol, smell, count + 1, SOUTH));
				}
				if (k == 2) {
					queue.offer(new node(nextRow, nextCol, smell, count + 1, WEST));
				}
				if (k == 3) {
					queue.offer(new node(nextRow, nextCol, smell, count + 1, EAST));
				}
			}
		}
		return -1;
	}
}

class node implements Comparable<node> {
	private int row, col, count, hasSmell, direction;

	public node(int r, int c, int b, int count, int dir) {
		row = r;
		col = c;
		hasSmell = b;
		this.count = count;
		direction = dir;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public int getSmell() {
		return hasSmell;
	}

	public int getCount() {
		return count;
	}

	public int getDirection() {
		return direction;
	}

	public String toString() {
		return row + " " + col + " " + hasSmell + " " + count + " " + direction;
	}

	public int compareTo(node next) {
		return Integer.compare(count, next.count);
	}
}
