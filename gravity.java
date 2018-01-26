import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class gravity {
	private static int N, M;
	private static boolean[][] walls;
	private static boolean[][][] visited;
	private static final int UP = 1;
	private static final int DOWN = 0;
	private static final int INF = 100000000;
	private static int endR, endC, startR, startC;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("gravity.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gravity.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		walls = new boolean[N][M];
		visited = new boolean[N][M][2];
		for (int i = 0; i < N; i++) {
			String line = f.readLine();
			for (int k = 0; k < M; k++) {
				String s = line.substring(k, k + 1);
				if (s.equals("#")) {
					walls[i][k] = true;
				} else if (s.equals("C")) {
					startR = i;
					startC = k;
				} else if (s.equals("D")) {
					endR = i;
					endC = k;
				}
			}
		}
		out.println(bfs());
		out.close();
	}

	public static int bfs() {
		ArrayDeque<node> queue = new ArrayDeque<node>();
		queue.offer(new node(startR, startC, DOWN, 0));
		while (!queue.isEmpty()) {
			node curr = queue.poll();
			// System.out.println(curr);
			int row = curr.row;
			int col = curr.col;
			int gravity = curr.gravity;
			int length = curr.length;
			if (row == endR && col == endC) {
				return length;
			}
			if (visited[row][col][gravity])
				continue;
			visited[row][col][gravity] = true;
			if (gravity == DOWN) {
				if (!valid(row + 1, col))
					continue;
				if (!walls[row + 1][col]) {
					queue.addFirst(new node(row + 1, col, DOWN, length));
					continue;
				}
				if (valid(row, col - 1) && !walls[row][col - 1]) {
					queue.addFirst(new node(row, col - 1, DOWN, length));
				}
				if (valid(row, col + 1) && !walls[row][col + 1]) {
					queue.addFirst(new node(row, col + 1, DOWN, length));
				}
				queue.offer(new node(row, col, UP, length + 1));
			} else {
				if (!valid(row - 1, col))
					continue;
				if (!walls[row - 1][col]) {
					queue.addFirst(new node(row - 1, col, UP, length));
					continue;
				}
				if (valid(row, col - 1) && !walls[row][col - 1]) {
					queue.addFirst(new node(row, col - 1, UP, length));
				}
				if (valid(row, col + 1) && !walls[row][col + 1]) {
					queue.addFirst(new node(row, col + 1, UP, length));
				}
				queue.offer(new node(row, col, DOWN, length + 1));
			}
		}
		return -1;
	}

	public static boolean valid(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}
}

class node {
	public int row, col, gravity, length;

	public node(int a, int b, int c, int d) {
		row = a;
		col = b;
		gravity = c;
		length = d;
	}

	public String toString() {
		return row + " " + col + " " + gravity + " " + length;
	}
}
