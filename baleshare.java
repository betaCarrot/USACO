import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class baleshare {
	private static int N;
	private static int[] bales, prefixes;
	private static int[][][] dp = new int[20][2001][2001];

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("baleshare.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("baleshare.out")));
		N = Integer.parseInt(f.readLine());
		bales = new int[N];
		prefixes = new int[N + 1];
		for (int i = 0; i < N; i++) {
			bales[i] = Integer.parseInt(f.readLine());
			prefixes[i + 1] = prefixes[i] + bales[i];
		}
		for (int i = 0; i < 20; i++) {
			for (int k = 0; k <= 2000; k++) {
				for (int m = 0; m <= 2000; m++)
					dp[i][k][m] = -1;
			}
		}
		out.println(dfs(0, 0, 0));
		out.close();
		System.err.println(System.currentTimeMillis() - startTime);
	}

	public static int dfs(int index, int a, int b) {
		int c = prefixes[index] - a - b;
		if (index == N) {
			return Math.max(a, Math.max(b, c));
		}
		if (dp[index][a][b] != -1) {
			return dp[index][a][b];
		}
		int result = Math.min(dfs(index + 1, a + bales[index], b),
				Math.min(dfs(index + 1, a, b + bales[index]), dfs(index + 1, a, b)));
		dp[index][a][b] = result;
		return result;
	}
}
