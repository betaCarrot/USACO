import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class subrev {
	private static final int INF = 1 << 29;
	private static int N;
	private static int[] array;
	private static int[][][][] dp = new int[51][51][51][51];

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("subrev.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subrev.out")));
		N = Integer.parseInt(f.readLine());
		array = new int[N];
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(f.readLine());
		}
		for (int i = 0; i < 51; i++) {
			for (int j = 0; j < 51; j++) {
				for (int k = 0; k < 51; k++) {
					for (int m = 0; m < 51; m++) {
						dp[i][j][k][m] = -1;
					}
				}
			}
		}
		out.println(dfs(0, N - 1, 0, 50));
		out.close();
	}

	public static int dfs(int i, int j, int k, int m) {
		if (k > m) {
			return -INF;
		}
		if (i > j) {
			return 0;
		}
		if (i == j) {
			if (array[i] >= k && array[i] <= m) {
				return 1;
			}
			return 0;
		}
		if (dp[i][j][k][m] != -1) {
			return dp[i][j][k][m];
		}
		int result = dfs(i + 1, j - 1, k, m);
		if (array[j] >= k) {
			result = Math.max(result, dfs(i + 1, j - 1, array[j], m) + 1);
		}
		if (array[i] <= m) {
			result = Math.max(result, dfs(i + 1, j - 1, k, array[i]) + 1);
		}
		if (array[j] >= k && array[i] <= m) {
			result = Math.max(result, dfs(i + 1, j - 1, array[j], array[i]) + 2);
		}
		result = Math.max(result, dfs(i + 1, j, k, m));
		if (array[i] >= k) {
			result = Math.max(result, dfs(i + 1, j, array[i], m) + 1);
		}
		result = Math.max(result, dfs(i, j - 1, k, m));
		if (array[j] <= m) {
			result = Math.max(result, dfs(i, j - 1, k, array[j]) + 1);
		}
		dp[i][j][k][m] = result;
		return result;
	}
}