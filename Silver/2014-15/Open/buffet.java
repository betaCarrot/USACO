import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class buffet {
	private static int N;
	private static long E;
	private static int[] grasses;
	private static int[][] distances;
	private static ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
	private static long[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("buffet.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("buffet.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Long.parseLong(st.nextToken());
		grasses = new int[N];
		distances = new int[N][N];
		dp = new long[N];
		for (int i = 0; i < N; i++) {
			edges.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			int grass = Integer.parseInt(st.nextToken());
			grasses[i] = grass;
			int K = Integer.parseInt(st.nextToken());
			for (int k = 0; k < K; k++) {
				edges.get(i).add(Integer.parseInt(st.nextToken()) - 1);
			}
		}
		for (int i = 0; i < N; i++) {
			dp[i] = -1;
		}
		for (int i = 0; i < N; i++) {
			distances[i] = bfs(i);
		}
		long result = 0;
		for (int i = 0; i < N; i++) {
			result = Math.max(result, dfs(i));
		}
		out.println(result);
		out.close();
	}

	public static long dfs(int i) {
		if (dp[i] != -1) {
			return dp[i];
		}
		long ret = 0;
		for (int j = 0; j < N; j++) {
			if (distances[i][j] == -1) {
				continue;
			}
			if (grasses[j] < grasses[i]) {
				ret = Math.max(ret, dfs(j) - E * distances[i][j]);
			}
		}
		dp[i] = grasses[i] + ret;
		return grasses[i] + ret;
	}

	public static void printMatrix(int[][] matrix) {
		for (int[] is : matrix) {
			printArray(is);
		}
	}

	public static void printArray(int[] array) {
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public static int[] bfs(int index) {
		int[] distance = new int[N];
		for (int i = 0; i < N; i++) {
			distance[i] = -1;
		}
		boolean[] visited = new boolean[N];
		ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(index);
		int count = 0;
		while (!queue.isEmpty()) {
			ArrayDeque<Integer> temp = new ArrayDeque<Integer>();
			while (!queue.isEmpty()) {
				int i = queue.poll();
				if (visited[i])
					continue;
				visited[i] = true;
				distance[i] = count;
				for (int j : edges.get(i)) {
					if (visited[j])
						continue;
					temp.add(j);
				}
			}
			queue.addAll(temp);
			count++;
		}
		return distance;
	}
}
