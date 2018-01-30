import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class vacationgold {
	private static int N, M, K, Q;
	private static final int INF = 1000000000;
	private static ArrayList<ArrayList<edge>> forwardEdges = new ArrayList<ArrayList<edge>>();
	private static ArrayList<ArrayList<edge>> backwardEdges = new ArrayList<ArrayList<edge>>();
	private static int[][] forwardDistances;
	private static int[][] backwardDistances;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("vacationgold.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("vacationgold.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		forwardDistances = new int[K][N];
		backwardDistances = new int[K][N];
		for (int i = 0; i < N; i++) {
			forwardEdges.add(new ArrayList<edge>());
			backwardEdges.add(new ArrayList<edge>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(f.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			forwardEdges.get(from).add(new edge(to, cost));
			backwardEdges.get(to).add(new edge(from, cost));
		}
		for (int i = 0; i < K; i++) {
			int hub = Integer.parseInt(f.readLine()) - 1;
			forwardDistances[i] = shortestPathForward(hub);
			backwardDistances[i] = shortestPathBackward(hub);
		}
		long result = 0L;
		int count = 0;
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(f.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int min = INF;
			for (int j = 0; j < K; j++) {
				min = Math.min(min, backwardDistances[j][from] + forwardDistances[j][to]);
			}
			if (min != INF) {
				count++;
				result += min;
			}
		}
		out.println(count);
		out.println(result);
		out.close();
	}

	public static int[] shortestPathForward(int start) {
		int[] distances = new int[N];
		for (int i = 0; i < N; i++) {
			distances[i] = INF;
		}
		distances[start] = 0;
		PriorityQueue<node> pq = new PriorityQueue<node>();
		pq.offer(new node(start, 0));
		while (!pq.isEmpty()) {
			node curr = pq.poll();
			int i = curr.vertex;
			int distance = curr.distance;
			for (edge e : forwardEdges.get(i)) {
				int j = e.to;
				int cost = e.cost;
				if (distance + cost < distances[j]) {
					distances[j] = distance + cost;
					pq.offer(new node(j, distances[j]));
				}
			}
		}
		return distances;
	}

	public static int[] shortestPathBackward(int start) {
		int[] distances = new int[N];
		for (int i = 0; i < N; i++) {
			distances[i] = INF;
		}
		distances[start] = 0;
		PriorityQueue<node> pq = new PriorityQueue<node>();
		pq.offer(new node(start, 0));
		while (!pq.isEmpty()) {
			node curr = pq.poll();
			int i = curr.vertex;
			int distance = curr.distance;
			for (edge e : backwardEdges.get(i)) {
				int j = e.to;
				int cost = e.cost;
				if (distance + cost < distances[j]) {
					distances[j] = distance + cost;
					pq.offer(new node(j, distances[j]));
				}
			}
		}
		return distances;
	}
}

class edge {
	public int to, cost;

	public edge(int a, int b) {
		to = a;
		cost = b;
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