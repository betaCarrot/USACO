import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class tilechng {
	private static int N, M;
	private static int INF = 100000000;
	private static int[][] dp;
	private static int[] tiles;

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("tilechng.in"));
		PrintWriter out = new PrintWriter(
				new BufferedWriter(new FileWriter("C:\\Users\\majesticdennis\\Desktop\\judge\\tilechng.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		tiles = new int[N];
		dp = new int[N][M + 1];
		for (int i = 0; i < N; i++) {
			for (int k = 0; k <= M; k++) {
				dp[i][k] = -1;
			}
		}
		for (int i = 0; i < N; i++) {
			tiles[i] = Integer.parseInt(f.readLine());
		}
		int result = dfs(0, M);
		if (result == INF) {
			out.println(-1);
		} else {
			out.println(result);
		}
		out.close();
		System.err.println(System.currentTimeMillis() - startTime);
	}

	public static int dfs(int index, int area) {
		if (index == N) {
			if (area == 0)
				return 0;
			return INF;
		}
		if (dp[index][area] != -1) {
			return dp[index][area];
		}
		int min = INF;
		for (int side = 1; side * side <= area; side++) {
			min = Math.min(min, cost(tiles[index], side) + dfs(index + 1, area - side * side));
		}
		dp[index][area] = min;
		return min;
	}

	public static int cost(int A, int B) {
		return (A - B) * (A - B);
	}
}
