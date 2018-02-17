import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class umbrella {
	private static int N, M;
	private static int INF = 1000000000;
	private static int[] cows, prices;
	private static int[][] dp;

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("umbrella.in"));
		PrintWriter out = new PrintWriter(
				new BufferedWriter(new FileWriter("C:\\Users\\majesticdennis\\Desktop\\judge\\umbrella.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cows = new int[N];
		prices = new int[M + 1];
		dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < N; k++) {
				dp[i][k] = -1;
			}
		}
		for (int i = 0; i < N; i++) {
			cows[i] = Integer.parseInt(f.readLine());
		}
		Arrays.sort(cows);
		for (int i = 1; i <= M; i++) {
			prices[i] = Integer.parseInt(f.readLine());
		}
		int min = prices[M];
		for (int i = M - 1; i >= 1; i--) {
			prices[i] = Math.min(prices[i], min);
			min = Math.min(prices[i], min);
		}
		out.println(dfs(0, 0));
		out.close();
		System.err.println(System.currentTimeMillis() - startTime);
	}

	public static int dfs(int index, int last) {
		if (index == N) {
			if (last == N)
				return 0;
			return INF;
		}
		if (dp[index][last] != -1) {
			return dp[index][last];
		}
		int result = Math.min(dfs(index + 1, index + 1) + prices[cows[index] - cows[last] + 1], dfs(index + 1, last));
		dp[index][last] = result;
		return result;
	}
}
