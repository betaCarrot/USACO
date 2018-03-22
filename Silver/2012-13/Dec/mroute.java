import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class mroute {
	private static int N, M, X;
	private static final double INF = 1000000000;
	private static ArrayList<ArrayList<edge>> edges = new ArrayList<ArrayList<edge>>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("mroute.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mroute.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			edges.add(new ArrayList<edge>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(f.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int latency = Integer.parseInt(st.nextToken());
			int capacity = Integer.parseInt(st.nextToken());
			edges.get(from).add(new edge(to, latency, capacity));
			edges.get(to).add(new edge(from, latency, capacity));
		}
		double result = solve();
		out.println((int) (result));
		out.close();
	}

	public static double solve() {
		double[] distances = new double[N];
		for (int i = 1; i < N; i++) {
			distances[i] = INF;
		}
		PriorityQueue<node> pq = new PriorityQueue<node>();
		pq.offer(new node(0, 0, INF, X));
		while (!pq.isEmpty()) {
			node curr = pq.poll();
			int vertex = curr.vertex;
			int i = vertex;
			double totalLatency = curr.totalLatency;
			double minCapacity = curr.minCapacity;
			for (edge e : edges.get(i)) {
				int j = e.to;
				double newLatency = totalLatency + e.latency;
				double newCapacity = Math.min(minCapacity, e.capacity);
				double newDistance = newLatency + X / newCapacity;
				if (newDistance < distances[j]) {
					distances[j] = newDistance;
					pq.offer(new node(j, newLatency, newCapacity, X));
				}
			}
		}
		return distances[N - 1];
	}
}

class edge {
	public int to, latency, capacity;

	public edge(int a, int b, int c) {
		to = a;
		latency = b;
		capacity = c;
	}
}

class node implements Comparable<node> {
	public int vertex;
	public double totalLatency, minCapacity, totalDistance;

	public node(int a, double b, double c, int x) {
		vertex = a;
		totalLatency = b;
		minCapacity = c;
		totalDistance = totalLatency + x / minCapacity;
	}

	public int compareTo(node next) {
		return Double.compare(totalDistance, next.totalDistance);
	}
}
