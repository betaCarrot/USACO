import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class nocross {
	private static int N;
	private static int[] left, right;
	private static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("nocross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocross.out")));
		N = Integer.parseInt(f.readLine());
		left = new int[N];
		right = new int[N];
		dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dp[i][j] = -1;
			}
		}
		for (int i = 0; i < N; i++) {
			left[i] = Integer.parseInt(f.readLine());
		}
		for (int i = 0; i < N; i++) {
			right[i] = Integer.parseInt(f.readLine());
		}
		out.println(dfs(0, 0));
		out.close();
	}

	public static int dfs(int i, int j) {
		if (i == N - 1 && j == N - 1)
			return 0;
		if (dp[i][j] != -1) {
			return dp[i][j];
		}
		int result = 0;
		if (i == N - 1) {
			if (Math.abs(left[i] - right[j]) <= 4) {
				result = 1;
			} else {
				result = dfs(i, j + 1);
			}
		} else if (j == N - 1) {
			if (Math.abs(left[i] - right[j]) <= 4) {
				result = 1;
			} else {
				result = dfs(i + 1, j);
			}
		} else {
			result = Math.max(dfs(i, j + 1), dfs(i + 1, j));
			if (Math.abs(left[i] - right[j]) <= 4) {
				result = Math.max(result, dfs(i + 1, j + 1) + 1);
			}
		}
		dp[i][j] = result;
		return result;
	}
}
