import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class relocate {
	private static int N, K, M;
	private static ArrayList<int[]> permutations = new ArrayList<int[]>();
	private static int[] markets;
	private static HashSet<Integer> set = new HashSet<Integer>();
	private static ArrayList<ArrayList<edge>> edges = new ArrayList<ArrayList<edge>>();
	private static int[][] distances;

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("relocate.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("relocate.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		markets = new int[K];
		for (int i = 0; i < K; i++) {
			int temp = Integer.parseInt(f.readLine()) - 1;
			markets[i] = temp;
			set.add(temp);
		}
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
		distances = new int[K][N];
		init1(new int[K], 0);
		init2();
		out.println(solve());
		out.close();
		System.err.println(System.currentTimeMillis() - startTime);
	}

	public static int solve() {
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			if (set.contains(i))
				continue;
			for (int[] ps : permutations) {
				int length = distances[ps[0]][i];
				for (int k = 0; k < ps.length - 1; k++) {
					length += distances[ps[k]][markets[ps[k + 1]]];
				}
				length += distances[ps[ps.length - 1]][i];
				result = Math.min(result, length);
			}
		}
		return result;
	}

	public static void dijkstra(int marketIndex) {
		int index = markets[marketIndex];
		int[] distance = new int[N];
		for (int i = 0; i < N; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		distance[index] = 0;
		PriorityQueue<node> pq = new PriorityQueue<node>();
		pq.offer(new node(index, 0));
		while (!pq.isEmpty()) {
			node curr = pq.poll();
			int i = curr.vertex;
			int dis = curr.distance;
			for (edge e : edges.get(i)) {
				int j = e.to;
				int weight = e.weight;
				if (dis + weight < distance[j]) {
					distance[j] = dis + weight;
					pq.offer(new node(j, distance[j]));
				}
			}
		}
		distances[marketIndex] = distance;
	}

	public static void init2() {
		for (int i = 0; i < K; i++) {
			dijkstra(i);
		}
	}

	public static void init1(int[] array, int index) {
		if (index == K) {
			if (valid(array)) {
				int[] copy = new int[K];
				for (int i = 0; i < K; i++) {
					copy[i] = array[i];
				}
				permutations.add(copy);
			}
			return;
		}
		for (int i = 0; i < K; i++) {
			array[index] = i;
			init1(array, index + 1);
		}
	}

	public static boolean valid(int[] array) {
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i : array) {
			if (set.contains(i))
				return false;
			set.add(i);
		}
		return true;
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
