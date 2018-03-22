import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class fencedin {
	private static int A, B, N, M;
	private static int[] vs, hs;
	private static int[] dr = new int[] { -1, 1, 0, 0 };
	private static int[] dc = new int[] { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("fencedin.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fencedin.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		vs = new int[M + 2];
		hs = new int[N + 2];
		for (int i = 1; i <= N; i++) {
			hs[i] = Integer.parseInt(f.readLine());
		}
		hs[N + 1] = A;
		for (int i = 1; i <= M; i++) {
			vs[i] = Integer.parseInt(f.readLine());
		}
		vs[M + 1] = B;
		Arrays.sort(hs);
		Arrays.sort(vs);
		System.out.println(MST());
		out.close();
		System.out.println(System.currentTimeMillis() - startTime);
	}

	public static long MST() {
		long[][] distances = new long[N + 1][M + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= M; j++) {
				distances[i][j] = Integer.MAX_VALUE;
			}
		}
		boolean[][] inTree = new boolean[N + 1][M + 1];
		long treeCost = 0;
		distances[0][0] = 0;
		PriorityQueue<node> pq = new PriorityQueue<node>();
		pq.offer(new node(0, 0, 0));
		while (!pq.isEmpty()) {
			node curr = pq.poll();
			int row = curr.row;
			int col = curr.col;
			long distance = curr.distance;
			if (inTree[row][col])
				continue;
			treeCost += distance;
			inTree[row][col] = true;
			for (int i = 0; i < 4; i++) {
				int nextR = row + dr[i];
				int nextC = col + dc[i];
				if (!valid(nextR, nextC))
					continue;
				if (weight(row, col, nextR, nextC) < distances[nextR][nextC]) {
					distances[nextR][nextC] = weight(row, col, nextR, nextC);
					pq.offer(new node(nextR, nextC, distances[nextR][nextC]));
				}
			}
		}
		return treeCost;
	}

	public static boolean valid(int row, int col) {
		return row >= 0 && row <= N && col >= 0 && col <= M;
	}

	public static int weight(int row1, int col1, int row2, int col2) {
		if (row1 == row2) {
			return hs[row1 + 1] - hs[row1];
		}
		return vs[col1 + 1] - vs[col1];
	}
}

class node implements Comparable<node> {
	public int row, col;
	public long distance;

	public node(int a, int b, long c) {
		row = a;
		col = b;
		distance = c;
	}

	public int compareTo(node next) {
		return Long.compare(distance, next.distance);
	}
}
