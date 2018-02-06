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
	private static ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
	private static int[] deep, mins;
	private static boolean[] visited;
	private static boolean[] leaves;

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
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			edges.get(from).add(to);
			edges.get(to).add(from);
		}
		deep = new int[N];
		mins = new int[N];
		visited = new boolean[N];
		leaves = new boolean[N];
		for (int i = 0; i < N; i++) {
			if (edges.get(i).size() == 1) {
				leaves[i] = true;
			}
		}
		dfs(root, 0);
		visited = new boolean[N];
		bfs();
		visited = new boolean[N];
		out.println(solve(root));
		out.close();
	}

	public static int solve(int index) {
		if (mins[index] <= deep[index]) {
			return 1;
		}
		int result = 0;
		visited[index] = true;
		for (int j : edges.get(index)) {
			if (!visited[j])
				result += solve(j);
		}
		return result;
	}

	public static void dfs(int index, int distance) {
		if (visited[index])
			return;
		deep[index] = distance;
		visited[index] = true;
		for (int j : edges.get(index)) {
			dfs(j, distance + 1);
		}
	}

	public static void bfs() {
		int count = 0;
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		for (int i = 0; i < N; i++) {
			mins[i] = Integer.MAX_VALUE;
		}
		for (int i = 0; i < N; i++) {
			if (leaves[i]) {
				queue.offer(i);
			}
		}
		while (!queue.isEmpty()) {
			ArrayDeque<Integer> temp = new ArrayDeque<Integer>();
			while (!queue.isEmpty()) {
				int curr = queue.poll();
				if (visited[curr])
					continue;
				mins[curr] = count;
				visited[curr] = true;
				for (int j : edges.get(curr)) {
					temp.offer(j);
				}
			}
			queue.addAll(temp);
			count++;
		}
	}
}
