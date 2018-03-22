import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class mootube {
	private static int[] parent, size, results;
	private static int N, Q;
	private static edge[] edges;
	private static query[] queries;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("mootube.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		parent = new int[N];
		size = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
		for (int i = 0; i < N; i++) {
			size[i] = 1;
		}
		edges = new edge[N - 1];
		queries = new query[Q];
		results = new int[Q];
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			edges[i] = new edge(a, b, w);
		}
		Arrays.sort(edges);
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()) - 1;
			queries[i] = new query(a, b, i);
		}
		Arrays.sort(queries);
		int index = 0;
		for (query q : queries) {
			int threshold = q.threshold;
			int start = q.start;
			while (index < edges.length && edges[index].weight >= threshold) {
				join(edges[index].start, edges[index].end);
				index++;
			}
			results[q.index] = sizeOf(start) - 1;
		}
		StringBuilder sb = new StringBuilder();
		for (int i : results) {
			sb.append(i).append("\n");
		}
		out.println(sb.toString());
		out.close();
	}

	public static void printArray(int[] array) {
		for (int i : array)
			System.out.print(i + " ");
		System.out.println();
	}

	private static int sizeOf(int target) {
		return size[find(target)];
	}

	public static int find(int target) {
		int parentIndex = target;
		while (parent[parentIndex] != parentIndex) {
			parentIndex = parent[parentIndex];
		}
		int i = target;
		while (i != parentIndex) {
			i = parent[i];
			parent[i] = parentIndex;
		}
		return parentIndex;
	}

	public static void join(int target1, int target2) {
		int root1 = find(target1);
		int root2 = find(target2);
		if (root1 != root2) {
			parent[root1] = root2;
			size[root2] += size[root1];
		}
	}
}

class edge implements Comparable<edge> {
	public int start, end, weight;

	public edge(int a, int b, int w) {
		start = a;
		end = b;
		weight = w;
	}
	
	public int compareTo(edge next) {
		return -Integer.compare(weight, next.weight);
	}
}

class query implements Comparable<query> {
	public int threshold, start, index;

	public query(int a, int b, int c) {
		threshold = a;
		start = b;
		index = c;
	}

	public int compareTo(query next) {
		return -Integer.compare(threshold, next.threshold);
	}
}
