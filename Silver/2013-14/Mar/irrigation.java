import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class irrigation {
	private static int N, C;
	private static final int INF = 1000000000;
	private static int[][] fields;
	private static int[][] matrix;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("irrigation.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("irrigation.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		fields = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			fields[i][0] = x;
			fields[i][1] = y;
		}
		matrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;
				int distance = distance(i, j);
				if (distance < C)
					distance = INF;
				matrix[i][j] = distance;
			}
		}
		int result = MST();
		if (result == INF) {
			result = -1;
		}
		out.print(result);
		out.close();
	}

	public static int MST() {
		int[] distances = new int[N];
		boolean[] inTree = new boolean[N];
		for (int i = 0; i < N; i++) {
			distances[i] = INF;
		}
		distances[0] = 0;
		int treeCost = 0;
		int treeSize = 0;
		PriorityQueue<node> pq = new PriorityQueue<node>();
		pq.offer(new node(0, 0));
		while (!pq.isEmpty()) {
			node curr = pq.poll();
			int i = curr.vertex;
			int cost = curr.distance;
			if (inTree[i]) {
				continue;
			}
			treeSize++;
			treeCost += cost;
			inTree[i] = true;
			for (int j = 0; j < N; j++) {
				if (distances[j] > matrix[i][j]) {
					distances[j] = matrix[i][j];
					pq.offer(new node(j, distances[j]));
				}
			}
		}
		if (treeSize < N) {
			return INF;
		}
		return treeCost;
	}

	public static void printMatrix(int[][] matrix) {
		for (int[] is : matrix) {
			for (int i : is)
				System.out.print(i + " ");
			System.out.println();
		}
	}

	public static int distance(int i, int j) {
		return (fields[i][0] - fields[j][0]) * (fields[i][0] - fields[j][0])
				+ (fields[i][1] - fields[j][1]) * (fields[i][1] - fields[j][1]);
	}
}

class node implements Comparable<node> {
	public int vertex, distance;

	public node(int a, int b) {
		vertex = a;
		distance = b;
	}

	public int compareTo(node next) {
		return distance - next.distance;
	}
}
