import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class visitfj {
	private static int N, T;
	private static int[][] map;
	private static int[] dr = new int[] { -1, 1, 0, 0 };
	private static int[] dc = new int[] { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("visitfj.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("visitfj.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			for (int k = 0; k < N; k++) {
				map[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		out.println(shortestPath());
		out.close();
	}

	public static int shortestPath() {
		int[][][] distances = new int[N][N][4];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < 4; k++) {
					distances[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}
		distances[0][0][0] = 0;
		PriorityQueue<node> pq = new PriorityQueue<node>();
		pq.offer(new node(0, 0, 0, 0));
		while (!pq.isEmpty()) {
			node curr = pq.poll();
			int row = curr.row;
			int col = curr.col;
			int distance = curr.distance;
			int count = curr.count;
			if (count == 3) {
				if (distance + map[row][col] < distances[row][col][0]) {
					distances[row][col][0] = distance + map[row][col];
					pq.offer(new node(row, col, distance + map[row][col], 0));
				}
				continue;
			}
			for (int d = 0; d < 4; d++) {
				int nextR = row + dr[d];
				int nextC = col + dc[d];
				if (!valid(nextR, nextC))
					continue;
				if (distance + T < distances[nextR][nextC][count + 1]) {
					distances[nextR][nextC][count + 1] = distance + T;
					pq.offer(new node(nextR, nextC, distance + T, count + 1));
				}
			}
		}
		return Math.min(distances[N - 1][N - 1][0], Math.min(distances[N - 1][N - 1][1], distances[N - 1][N - 1][2]));
	}

	public static boolean valid(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}

class node implements Comparable<node> {
	public int row, col, distance, count;

	public node(int a, int b, int c, int d) {
		row = a;
		col = b;
		distance = c;
		count = d;
	}

	public int compareTo(node next) {
		return distance - next.distance;
	}
}