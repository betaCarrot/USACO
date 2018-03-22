import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class rblock {
	private static int N, M;
	private static int[] parent;
	private static ArrayList<ArrayList<edge>> edges = new ArrayList<ArrayList<edge>>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("rblock.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rblock.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N];
		for (int i = 0; i < N; i++) {
			edges.add(new ArrayList<edge>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(f.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			edges.get(from).add(new edge(to, weight));
			edges.get(to).add(new edge(from, weight));
		}
		int initDistance = init();
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (edge e : edges.get(i)) {
				if (parent[e.to] != i)
					continue;
				e.weight *= 2;
				result = Math.max(result, solve() - initDistance);
				e.weight /= 2;
			}
		}
		out.println(result);
		out.close();
	}

	public static int solve() {
		PriorityQueue<node> pq = new PriorityQueue<node>();
		int[] distances = new int[N];
		for (int i = 0; i < N; i++) {
			distances[i] = Integer.MAX_VALUE;
		}
		distances[0] = 0;
		pq.offer(new node(0, 0));
		while (!pq.isEmpty()) {
			node curr = pq.poll();
			int i = curr.vertex;
			int distance = curr.distance;
			for (edge e : edges.get(i)) {
				int j = e.to;
				int weight = e.weight;
				if (distance + weight < distances[j]) {
					distances[j] = distance + weight;
					pq.offer(new node(j, distances[j]));
				}
			}
		}
		return distances[N - 1];
	}

	public static int init() {
		PriorityQueue<node> pq = new PriorityQueue<node>();
		int[] distances = new int[N];
		for (int i = 0; i < N; i++) {
			distances[i] = Integer.MAX_VALUE;
		}
		distances[0] = 0;
		pq.offer(new node(0, 0));
		while (!pq.isEmpty()) {
			node curr = pq.poll();
			int i = curr.vertex;
			int distance = curr.distance;
			for (edge e : edges.get(i)) {
				int j = e.to;
				int weight = e.weight;
				if (distance + weight < distances[j]) {
					distances[j] = distance + weight;
					pq.offer(new node(j, distances[j]));
					parent[j] = i;
				}
			}
		}
		return distances[N - 1];
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

class edge {
	public int to, weight;

	public edge(int a, int b) {
		to = a;
		weight = b;
	}
}
