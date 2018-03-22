import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class distant {
	private static int N, A, B;
	private static int[][] map;
	private static boolean[][] visited;
	private static int[] dr = new int[] { 1, -1, 0, 0 };
	private static int[] dc = new int[] { 0, 0, 1, -1 };
	private static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("distant.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("distant.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String line = f.readLine();
			for (int k = 0; k < N; k++) {
				if (line.substring(k, k + 1).equals("("))
					map[i][k] = 1;
				else
					map[i][k] = 2;
			}
		}
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < N; k++) {
				visited = new boolean[N][N];
				int distance = shortestPath(i, k);
				result = Math.max(result, distance);
			}
		}
		out.println(result);
		out.close();
	}

	public static int shortestPath(int startRow, int startCol) {
		PriorityQueue<node> pq = new PriorityQueue<node>();
		pq.offer(new node(startRow, startCol, 0));
		int max = 0;
		while (!pq.isEmpty()) {
			node next = pq.poll();
			int minRow = next.getRow();
			int minCol = next.getCol();
			int distance = next.getDistance();
			if (visited[minRow][minCol])
				continue;
			visited[minRow][minCol] = true;
			for (int d = 0; d < 4; d++) {
				int row = minRow + dr[d];
				int col = minCol + dc[d];
				if (valid(row, col)) {
					int dist = distance;
					if (map[row][col] == map[minRow][minCol])
						dist += A;
					else
						dist += B;
					pq.offer(new node(row, col, dist));
				}
			}
			max = Math.max(max, distance);
		}
		return max;
	}

	public static boolean valid(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}
}

class node implements Comparable<node> {
	private int row;
	private int col;
	private int distance;

	public node(int r, int c, int d) {
		row = r;
		col = c;
		distance = d;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public int getDistance() {
		return distance;
	}

	public int compareTo(node next) {
		return Integer.compare(distance, next.distance);
	}
}
