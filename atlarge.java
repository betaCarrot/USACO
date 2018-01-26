import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class atlarge {
	private static int N, root;
	private static int[] distanceToRoot;
	private static int[] distanceToLeaf;
	private static boolean[] visited;
	private static ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("atlarge.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("atlarge.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		root = Integer.parseInt(st.nextToken()) - 1;
		for (int i = 0; i < N; i++) {
			edges.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			edges.get(a).add(b);
			edges.get(b).add(a);
		}
		distanceToRoot = new int[N];
		distanceToLeaf = new int[N];
		visited = new boolean[N];
		dfs(root, 0);
		visited = new boolean[N];
		bfs();
		int result = 0;
		for (int i = 0; i < N; i++) {
			if (i == root)
				continue;
			if (distanceToRoot[i] < distanceToLeaf[i])
				continue;
			if (distanceToRoot[findParent(i)] < distanceToLeaf[findParent(i)]) {
				result++;
			}
		}
		out.println(result);
		out.close();
	}

	public static int findParent(int index) {
		for (int i : edges.get(index)) {
			if (distanceToRoot[i] < distanceToRoot[index])
				return i;
		}
		return -1;
	}

	public static void dfs(int index, int length) {
		distanceToRoot[index] = length;
		visited[index] = true;
		for (int i : edges.get(index)) {
			if (!visited[i]) {
				dfs(i, length + 1);
			}
		}
	}

	public static void bfs() {
		ArrayDeque<node> queue = new ArrayDeque<node>();
		for (int i = 0; i < edges.size(); i++) {
			if (edges.get(i).size() == 1) {
				queue.offer(new node(i, 0));
			}
		}
		// System.out.println(queue.size());
		while (!queue.isEmpty()) {
			node curr = queue.poll();
			int index = curr.index;
			int length = curr.length;
			if (visited[index])
				continue;
			visited[index] = true;
			distanceToLeaf[index] = length;
			for (int i : edges.get(index)) {
				queue.offer(new node(i, length + 1));
			}
		}
	}

	public static void printArray(int[] array) {
		for (int i : array)
			System.out.print(i + " ");
		System.out.println();
	}
}

class node {
	public int index, length;

	public node(int a, int b) {
		index = a;
		length = b;
	}
}