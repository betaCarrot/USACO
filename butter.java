
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
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class butter {
	private static int N, P, C;
	private static int[] cows;
	private static int[] distances;
	private static boolean[] visited;
	private static ArrayList<ArrayList<edge>> edges = new ArrayList<ArrayList<edge>>();
	private static final int MAX = 10000000;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("butter.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		cows = new int[P];
		distances = new int[P];
		visited = new boolean[P];
		for (int i = 0; i < P; i++) {
			edges.add(new ArrayList<edge>());
		}
		for (int i = 0; i < N; i++) {
			cows[Integer.parseInt(f.readLine()) - 1]++;
		}
		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			edges.get(a).add(new edge(b, weight));
			edges.get(b).add(new edge(a, weight));
		}
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
			for (edge e : edges.get(i)) {
				int j = e.getNeighbor();
				int weight = e.getWeight();
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

class edge {
	private int neighbor;
	private int weight;

	public edge(int a, int b) {
		neighbor = a;
		weight = b;
	}

	public int getNeighbor() {
		return neighbor;
	}

	public int getWeight() {
		return weight;
	}
}
