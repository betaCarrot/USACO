import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class tractor {
	private static int[] dr = new int[] { -1, 1, 0, 0 };
	private static int[] dc = new int[] { 0, 0, -1, 1 };
	private static int N, target, result;
	private static int[][] matrix;
	private static int[] parent, size;
	private static PriorityQueue<edge> edges = new PriorityQueue<edge>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("tractor.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("tractor.out")));
		N = Integer.parseInt(f.readLine());
		target = (N * N + 1) / 2;
		matrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			for (int k = 0; k < N; k++) {
				matrix[i][k] = Integer.parseInt(st.nextToken());
			}
		}
		parent = new int[N * N];
		size = new int[N * N];
		for (int i = 0; i < N * N; i++) {
			parent[i] = i;
			size[i] = 1;
		}
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				for (int d = 0; d < 4; d++) {
					int nextR = row + dr[d];
					int nextC = col + dc[d];
					if (!valid(nextR, nextC))
						continue;
					edges.offer(new edge(row * N + col, nextR * N + nextC,
							Math.abs(matrix[row][col] - matrix[nextR][nextC])));
				}
			}
		}
		while (!edges.isEmpty()) {
			edge curr = edges.poll();
			result = curr.getWeight();
			join(curr.getA(), curr.getB());
			if (size[find(curr.getB())] >= target)
				break;
		}
		out.println(result);
		out.close();
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
		}
	}

	public static boolean valid(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}
}

class edge implements Comparable<edge> {
	private int a;
	private int b;
	private int weight;

	public edge(int a, int b, int weight) {
		this.a = a;
		this.b = b;
		this.weight = weight;
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	public int getWeight() {
		return weight;
	}

	public String toString() {
		return a + " " + b + " " + weight;
	}

	public int compareTo(edge next) {
		return Integer.compare(weight, next.weight);
	}
}
