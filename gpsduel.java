import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class gpsduel {
	private static int N, M;
	private static final int INF = 100000000;
	private static ArrayList<ArrayList<edge>> forwardEdges = new ArrayList<ArrayList<edge>>();
	private static ArrayList<ArrayList<edge>> backwardEdges = new ArrayList<ArrayList<edge>>();
	private static int[] parentA, parentB;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("gpsduel.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gpsduel.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			forwardEdges.add(new ArrayList<edge>());
		}
		for (int i = 0; i < N; i++) {
			backwardEdges.add(new ArrayList<edge>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(f.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int distanceA = Integer.parseInt(st.nextToken());
			int distanceB = Integer.parseInt(st.nextToken());
			backwardEdges.get(to).add(new edge(from, distanceA, distanceB));
			forwardEdges.get(from).add(new edge(to, distanceA, distanceB));
		}
		parentA = new int[N];
		parentB = new int[N];
		for (int i = 0; i < N; i++) {
			parentA[i] = -1;
			parentB[i] = -1;
		}
		initA();
		initB();
		out.println(solve());
		out.close();
	}

	public static int solve() {
		int[] distances = new int[N];
		for (int i = 1; i < N; i++) {
			distances[i] = INF;
		}
		PriorityQueue<node> pq = new PriorityQueue<node>();
		pq.offer(new node(0, 0));
		while (!pq.isEmpty()) {
			node curr = pq.poll();
			int i = curr.vertex;
			int distance = curr.distance;
			for (edge e : forwardEdges.get(i)) {
				int j = e.to;
				int weight = 0;
				if (parentA[i] != j)
					weight++;
				if (parentB[i] != j)
					weight++;
				if (distance + weight < distances[j]) {
					distances[j] = distance + weight;
					pq.offer(new node(j, distances[j]));
				}
			}
		}
		return distances[N - 1];
	}

	public static void initA() {
		int[] distances = new int[N];
		for (int i = 0; i < N - 1; i++) {
			distances[i] = INF;
		}
		PriorityQueue<node> pq = new PriorityQueue<node>();
		pq.offer(new node(N - 1, 0));
		while (!pq.isEmpty()) {
			node curr = pq.poll();
			int i = curr.vertex;
			int distance = curr.distance;
			for (edge e : backwardEdges.get(i)) {
				int j = e.to;
				int weight = e.distanceA;
				if (distance + weight < distances[j]) {
					distances[j] = distance + weight;
					pq.offer(new node(j, distances[j]));
					parentA[j] = i;
				}
			}
		}
	}

	public static void initB() {
		int[] distances = new int[N];
		for (int i = 0; i < N - 1; i++) {
			distances[i] = INF;
		}
		PriorityQueue<node> pq = new PriorityQueue<node>();
		pq.offer(new node(N - 1, 0));
		while (!pq.isEmpty()) {
			node curr = pq.poll();
			int i = curr.vertex;
			int distance = curr.distance;
			for (edge e : backwardEdges.get(i)) {
				int j = e.to;
				int weight = e.distanceB;
				if (distance + weight < distances[j]) {
					distances[j] = distance + weight;
					pq.offer(new node(j, distances[j]));
					parentB[j] = i;
				}
			}
		}
	}
}

class edge {
	public int to, distanceA, distanceB;

	public edge(int a, int b, int c) {
		to = a;
		distanceA = b;
		distanceB = c;
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
