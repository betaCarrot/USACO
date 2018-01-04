
/*
 ID: majesti2
 LANG: JAVA
 TASK: butter
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class butter {
	private static int N, P, C;
	private static int[] cows;
	private static int[] distances;
	private static boolean[] visited;
	private static pathInc[] pathIncs;
	private static pathDec[] pathDecs;
	private static final int MAX = 10000000;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("butter.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		pathIncs = new pathInc[C];
		pathDecs = new pathDec[C];
		cows = new int[P];
		distances = new int[P];
		visited = new boolean[P];
		for (int i = 0; i < N; i++) {
			cows[Integer.parseInt(f.readLine()) - 1]++;
		}
		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			pathIncs[i] = new pathInc(a, b, weight);
			pathDecs[i] = new pathDec(a, b, weight);
		}
		Arrays.sort(pathIncs);
		Arrays.sort(pathDecs);
		int result = MAX;
		for (int i = 0; i < P; i++) {
			result = Math.min(result, dijkstra(i));
		}
		out.println(result);
		out.close();
	}

	public static int dijkstra(int start) {
		for (int i = 0; i < P; i++) {
			distances[i] = MAX;
		}
		visited = new boolean[P];
		distances[start] = 0;
		PriorityQueue<node> pq = new PriorityQueue<node>();
		pq.offer(new node(start, 0));
		while (!pq.isEmpty()) {
			node curr = pq.poll();
			int minIndex = curr.getVertex();
			if (visited[minIndex])
				continue;
			visited[minIndex] = true;
			int i = minIndex;
			int incIndexLow = -Arrays.binarySearch(pathIncs, new pathInc(i, i, 0)) - 1;
			int incIndexHigh = -Arrays.binarySearch(pathIncs, new pathInc(i, Integer.MAX_VALUE, Integer.MAX_VALUE)) - 1;
			int decIndexLow = -Arrays.binarySearch(pathDecs, new pathDec(i, i, Integer.MAX_VALUE)) - 1;
			int decIndexHigh = -Arrays.binarySearch(pathDecs, new pathDec(i, Integer.MIN_VALUE, 0)) - 1;
			for (int index = incIndexLow; index < incIndexHigh; index++) {
				int j = pathIncs[index].getB();
				int weight = pathIncs[index].getWeight();
				if (!visited[j] && distances[i] + weight < distances[j]) {
					distances[j] = distances[i] + weight;
					pq.offer(new node(j, distances[j]));
				}
			}
			for (int index = decIndexLow; index < decIndexHigh; index++) {
				int j = pathDecs[index].getB();
				int weight = pathDecs[index].getWeight();
				if (!visited[j] && distances[i] + weight < distances[j]) {
					distances[j] = distances[i] + weight;
					pq.offer(new node(j, distances[j]));
				}
			}
		}
		int total = 0;
		for (int i = 0; i < P; i++) {
			total += distances[i] * cows[i];
		}
		return total;
	}

	public static void printMatrix(int[][] matrix) {
		for (int[] is : matrix) {
			for (int i : is)
				System.out.print(i + " ");
			System.out.println();
		}
	}
}

class node implements Comparable<node> {
	private int vertex;
	private int distance;

	public node(int v, int d) {
		vertex = v;
		distance = d;
	}

	public int getVertex() {
		return vertex;
	}

	public int getDistance() {
		return distance;
	}

	public int compareTo(node next) {
		return Integer.compare(distance, next.distance);
	}
}

class pathInc implements Comparable<pathInc> {
	private int a;
	private int b;
	private int weight;

	public pathInc(int a, int b, int w) {
		this.a = Math.min(a, b);
		this.b = Math.max(a, b);
		weight = w;
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
		return a + " " + b;
	}

	public int compareTo(pathInc next) {
		if (Integer.compare(a, next.a) != 0) {
			return Integer.compare(a, next.a);
		}
		if (Integer.compare(b, next.b) != 0) {
			return Integer.compare(b, next.b);
		}
		return Integer.compare(weight, next.weight);
	}
}

class pathDec implements Comparable<pathDec> {
	private int a;
	private int b;
	private int weight;

	public pathDec(int a, int b, int w) {
		this.a = Math.max(a, b);
		this.b = Math.min(a, b);
		weight = w;
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
		return a + " " + b;
	}

	public int compareTo(pathDec next) {
		if (Integer.compare(a, next.a) != 0) {
			return -Integer.compare(a, next.a);
		}
		if (Integer.compare(b, next.b) != 0) {
			return -Integer.compare(b, next.b);
		}
		return -Integer.compare(weight, next.weight);
	}
}
