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
	private static int N, M, initDistance;
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
			parent[i] = -1;
		}
		for (int i = 0; i < N; i++) {
			edges.add(new ArrayList<edge>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(f.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int distance = Integer.parseInt(st.nextToken());
			edges.get(from).add(new edge(to, distance));
			edges.get(to).add(new edge(from, distance));
		}
		init();
		int index = N - 1;
		int result = 0;
		while (index != 0) {
			int firstIndex = -1;
			int secondIndex = -1;
			for (int i = 0; i < edges.get(index).size(); i++) {
				if (edges.get(index).get(i).to == parent[index]) {
					edges.get(index).get(i).distance *= 2;
					firstIndex = i;
					break;
				}
			}
			for (int i = 0; i < edges.get(parent[index]).size(); i++) {
				if (edges.get(parent[index]).get(i).to == index) {
					edges.get(parent[index]).get(i).distance *= 2;
					secondIndex = i;
					break;
				}
			}
			result = Math.max(result, solve() - initDistance);
			edges.get(index).get(firstIndex).distance /= 2;
			edges.get(parent[index]).get(secondIndex).distance /= 2;
			index = parent[index];
		}
		out.println(result);
		out.close();
	}

	public static int solve() {
		int[] distances = new int[N];
		for (int i = 1; i < N; i++) {
			distances[i] = Integer.MAX_VALUE;
		}
		PriorityQueue<node> pq = new PriorityQueue<node>();
		pq.offer(new node(0, 0));
		while (!pq.isEmpty()) {
			node curr = pq.poll();
			int i = curr.vertex;
			int distance = curr.distance;
			for (edge e : edges.get(i)) {
				int j = e.to;
				int weight = e.distance;
				if (distance + weight < distances[j]) {
					distances[j] = distance + weight;
					pq.offer(new node(j, distances[j]));
				}
			}
		}
		return distances[N - 1];
	}

	public static void init() {
		int[] distances = new int[N];
		for (int i = 1; i < N; i++) {
			distances[i] = Integer.MAX_VALUE;
		}
		PriorityQueue<node> pq = new PriorityQueue<node>();
		pq.offer(new node(0, 0));
		while (!pq.isEmpty()) {
			node curr = pq.poll();
			int i = curr.vertex;
			int distance = curr.distance;
			for (edge e : edges.get(i)) {
				int j = e.to;
				int weight = e.distance;
				if (distance + weight < distances[j]) {
					distances[j] = distance + weight;
					pq.offer(new node(j, distances[j]));
					parent[j] = i;
				}
			}
		}
		initDistance = distances[N - 1];
	}
}

class edge {
	public int to, distance;

	public edge(int b, int c) {
		to = b;
		distance = c;
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
