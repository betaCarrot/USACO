import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ccski {
	private static int M, N, count, result;
	private static int[] parent, size;
	private static int[][] map;
	private static int[][] wayPoints;
	private static PriorityQueue<edge> pq = new PriorityQueue<edge>();
	private static int[] dr = new int[] { 1, 0 };
	private static int[] dc = new int[] { 0, 1 };
	private static int maxSize = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("ccski.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ccski.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		parent = new int[M * N];
		size = new int[M * N];
		map = new int[M][N];
		wayPoints = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(f.readLine());
			for (int k = 0; k < N; k++) {
				map[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		for (int row = 0; row < M; row++) {
			for (int col = 0; col < N; col++) {
				int index = row * N + col;
				for (int d = 0; d < 2; d++) {
					int nextR = row + dr[d];
					int nextC = col + dc[d];
					if (!valid(nextR, nextC)) {
						continue;
					}
					int nextIndex = nextR * N + nextC;
					int distance = Math.abs(map[row][col] - map[nextR][nextC]);
					pq.offer(new edge(index, nextIndex, distance));
				}
			}
		}
		for (int i = 0; i < M * N; i++) {
			parent[i] = i;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(f.readLine());
			for (int k = 0; k < N; k++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 1) {
					count++;
				}
				wayPoints[i][k] = temp;
			}
		}
		for (int row = 0; row < M; row++) {
			for (int col = 0; col < N; col++) {
				int index = row * N + col;
				size[index] = wayPoints[row][col];
			}
		}
		while (!pq.isEmpty()) {
			edge curr = pq.poll();
			int distance = curr.distance;
			int from = curr.from;
			int to = curr.to;
			join(from, to);
			if (maxSize >= count) {
				result = distance;
				break;
			}
		}
		out.println(result);
		out.close();
	}

	private static boolean valid(int r, int c) {
		return r >= 0 && r < M && c >= 0 && c < N;
	}

	public static int find(int target) {
		int parentIndex = target;
		while (parent[parentIndex] != parentIndex) {
			parentIndex = parent[parentIndex];
		}
		int i = target;
		while (i != parentIndex) {
			i = parent[i];
			parent[i] = parentIndex;
		}
		return parentIndex;
	}

	public static void join(int target1, int target2) {
		int root1 = find(target1);
		int root2 = find(target2);
		if (root1 != root2) {
			parent[root1] = root2;
			size[root2] += size[root1];
			maxSize = Math.max(maxSize, size[root2]);
		}
	}
}

class edge implements Comparable<edge> {
	int from, to, distance;

	public edge(int a, int b, int c) {
		from = a;
		to = b;
		distance = c;
	}

	public String toString() {
		return from + " " + to + " " + distance;
	}

	public int compareTo(edge next) {
		return distance - next.distance;
	}
}
