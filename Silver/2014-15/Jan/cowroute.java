import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class cowroute {
	private static int A, B, N;
	private static PrintWriter out;
	private static final int MAX = 1000;
	private static ArrayList<ArrayList<edge>> edges = new ArrayList<ArrayList<edge>>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cowroute.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("cowroute.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		A = Integer.parseInt(st.nextToken()) - 1;
		B = Integer.parseInt(st.nextToken()) - 1;
		N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < MAX; i++) {
			edges.add(new ArrayList<edge>());
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(f.readLine());
			int[] array = new int[M];
			for (int j = 0; j < M; j++) {
				array[j] = Integer.parseInt(st.nextToken()) - 1;
			}
			for (int j = 0; j < M; j++) {
				for (int k = j + 1; k < M; k++) {
					int flights = k - j;
					edges.get(array[j]).add(new edge(array[k], cost, flights));
				}
			}
		}
		solve();
		out.close();
	}

	public static void solve() {
		long[] costs = new long[MAX];
		int[] flights = new int[MAX];
		boolean[] visited = new boolean[MAX];
		for (int i = 0; i < MAX; i++) {
			costs[i] = Long.MAX_VALUE;
			flights[i] = Integer.MAX_VALUE;
		}
		costs[A] = 0;
		flights[A] = 0;
		while (true) {
			int minIndex = -1;
			for (int i = 0; i < MAX; i++) {
				if (visited[i] || costs[i] == Long.MAX_VALUE) {
					continue;
				}
				if (minIndex == -1 || costs[i] < costs[minIndex]) {
					minIndex = i;
				}
			}
			if (minIndex == -1) {
				break;
			}
			int i = minIndex;
			long cost = costs[i];
			int flight = flights[i];
			visited[i] = true;
			for (edge e : edges.get(i)) {
				int j = e.to;
				int w = e.cost;
				int f = e.flights;
				long newCost = cost + w;
				int newFlights = flight + f;
				if (newCost < costs[j] || (newCost == costs[j] && newFlights < flights[j])) {
					costs[j] = newCost;
					flights[j] = newFlights;
				}
			}
		}
		if (costs[B] != Long.MAX_VALUE)
			out.println(costs[B] + " " + flights[B]);
		else
			out.println("-1 -1");
	}
}

class edge {
	public int to, cost, flights;

	public edge(int a, int b, int c) {
		to = a;
		cost = b;
		flights = c;
	}
}

class node implements Comparable<node> {
	public int vertex, flights;
	public long cost;

	public node(int a, long b, int c) {
		vertex = a;
		cost = b;
		flights = c;
	}

	public int compareTo(node next) {
		if (cost == next.cost) {
			return flights - next.flights;
		}
		return Long.compare(cost, next.cost);
	}
}
