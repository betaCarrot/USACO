
/*
ID: majesti2
LANG: JAVA
TASK: fence6
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class fence6 {
	private static int N;
	private static int[] lengths;
	private static HashSet<node> set = new HashSet<node>();
	private static HashMap<Integer, node> map = new HashMap<Integer, node>();
	private static ArrayList<ArrayList<edge>> edges = new ArrayList<ArrayList<edge>>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("fence6.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence6.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		lengths = new int[N];
		int curr = 0;
		for (int iter = 0; iter < N; iter++) {
			st = new StringTokenizer(f.readLine());
			int index = Integer.parseInt(st.nextToken()) - 1;
			lengths[index] = Integer.parseInt(st.nextToken());
			int N1 = Integer.parseInt(st.nextToken());
			int N2 = Integer.parseInt(st.nextToken());
			int[] array1 = new int[N1 + 1];
			int[] array2 = new int[N2 + 1];
			st = new StringTokenizer(f.readLine());
			for (int i = 0; i < N1; i++) {
				array1[i] = Integer.parseInt(st.nextToken()) - 1;
			}
			array1[N1] = index;
			Arrays.sort(array1);
			node n1 = new node(array1);
			if (!set.contains(n1)) {
				set.add(n1);
				map.put(curr, n1);
				curr++;
			}
			st = new StringTokenizer(f.readLine());
			for (int i = 0; i < N2; i++) {
				array2[i] = Integer.parseInt(st.nextToken()) - 1;
			}
			array2[N2] = index;
			Arrays.sort(array2);
			node n2 = new node(array2);
			if (!set.contains(n2)) {
				set.add(n2);
				map.put(curr, n2);
				curr++;
			}
		}
		for (int i = 0; i < curr; i++) {
			edges.add(new ArrayList<edge>());
		}
		for (int i = 0; i < curr; i++) {
			for (int j = 0; j < curr; j++) {
				int check = check(i, j);
				if (check == -1)
					continue;
				edges.get(i).add(new edge(j, lengths[check]));
			}
		}
		int res = 1 << 29;
		for (int i = 0; i < curr; i++) {
			for (int j = i + 1; j < curr; j++) {
				int check = check(i, j);
				if (check == -1)
					continue;
				res = Math.min(res, dijkstra(i, j) + lengths[check]);
			}
		}
		out.println(res);
		out.close();
	}

	public static int dijkstra(int start, int end) {
		PriorityQueue<edge> pq = new PriorityQueue<edge>();
		int[] distances = new int[N];
		for (int i = 0; i < N; i++) {
			distances[i] = 1 << 29;
		}
		distances[start] = 0;
		pq.offer(new edge(start, 0));
		while (!pq.isEmpty()) {
			edge curr = pq.poll();
			int i = curr.to;
			int distance = curr.weight;
			for (edge e : edges.get(i)) {
				int j = e.to;
				if (i == start && j == end)
					continue;
				if (i == end && j == start)
					continue;
				int weight = e.weight;
				if (distance + weight < distances[j]) {
					distances[j] = distance + weight;
					pq.offer(new edge(j, distances[j]));
				}
			}
		}
		return distances[end];
	}

	public static int check(int i, int j) {
		for (int m : map.get(i).array) {
			for (int k : map.get(j).array) {
				if (m == k)
					return m;
			}
		}
		return -1;
	}
}

class node {
	public int[] array;

	public node(int[] array) {
		this.array = array;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(array);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		node other = (node) obj;
		if (!Arrays.equals(array, other.array))
			return false;
		return true;
	}
}

class edge implements Comparable<edge> {
	public int to, weight;

	public edge(int a, int b) {
		to = a;
		weight = b;
	}

	public int compareTo(edge next) {
		return weight - next.weight;
	}
}
