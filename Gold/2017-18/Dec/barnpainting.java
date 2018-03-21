import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class barnpainting {
	private static int N, K;
	private static int[] colors, distances;
	private static boolean[] visited;
	private static long[][] dp;
	private static long MOD = 1000000007;
	private static ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("barnpainting.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barnpainting.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		colors = new int[N];
		distances = new int[N];
		visited = new boolean[N];
		dp = new long[N][4];
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < 4; k++) {
				dp[i][k] = -1;
			}
		}
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
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(f.readLine());
			int index = Integer.parseInt(st.nextToken()) - 1;
			int color = Integer.parseInt(st.nextToken());
			colors[index] = color;
		}
		init(0, 0);
		out.println(dfs(0, 1));
		out.close();
	}

	public static void init(int index, int distance) {
		distances[index] = distance;
		visited[index] = true;
		for (int j : edges.get(index)) {
			if (visited[j])
				continue;
			init(j, distance + 1);
		}
	}

	public static long dfs(int index, int color) {
		if (colors[index] == color)
			return 0;
		if (dp[index][color] != -1) {
			return dp[index][color];
		}
		long result;
		if (colors[index] != 0) {
			int nextColor = colors[index];
			result = 1;
			for (int j : edges.get(index)) {
				if (distances[j] < distances[index])
					continue;
				result *= dfs(j, nextColor);
				result %= MOD;
			}
		} else {
			result = 0;
			for (int nextColor = 1; nextColor <= 3; nextColor++) {
				if (index != 0 && nextColor == color)
					continue;
				long temp = 1;
				for (int j : edges.get(index)) {
					if (distances[j] < distances[index])
						continue;
					temp *= dfs(j, nextColor);
					temp %= MOD;
				}
				result += temp;
				result %= MOD;
			}
		}
		dp[index][color] = result;
		return result;
	}
}
