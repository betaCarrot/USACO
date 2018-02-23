import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class tractor {
	private static int N;
	private static int startR, startC;
	private static boolean[][] bales = new boolean[1002][1002];
	private static int[] dr = new int[] { -1, 1, 0, 0 };
	private static int[] dc = new int[] { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("tractor.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("tractor.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		startR = Integer.parseInt(st.nextToken());
		startC = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			bales[r][c] = true;
		}
		out.println(bfs());
		out.close();
	}

	public static int bfs() {
		ArrayDeque<node> queue = new ArrayDeque<node>();
		queue.offer(new node(startR, startC, 0));
		boolean[][] visited = new boolean[1002][1002];
		while (!queue.isEmpty()) {
			node curr = queue.poll();
			int row = curr.row;
			int col = curr.col;
			int count = curr.count;
			if (row == 0 && col == 0) {
				return count;
			}
			if (visited[row][col])
				continue;
			visited[row][col] = true;
			for (int d = 0; d < 4; d++) {
				int nextR = row + dr[d];
				int nextC = col + dc[d];
				if (!valid(nextR, nextC))
					continue;
				if (bales[row][col]) {
					queue.offer(new node(nextR, nextC, count + 1));
				} else {
					queue.addFirst(new node(nextR, nextC, count));
				}
			}
		}
		return -1;
	}

	public static boolean valid(int r, int c) {
		return r >= 0 && r <= 1001 && c >= 0 && c <= 1001;
	}
}

class node {
	public int row, col, count;

	public node(int a, int b, int c) {
		row = a;
		col = b;
		count = c;
	}
}
