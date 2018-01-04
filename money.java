
/*
 ID: majesti2
 LANG: JAVA
 TASK: money
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class money {
	private static int V, N;
	private static int[] coins;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("money.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		V = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		coins = new int[V];
		String line;
		int index = 0;
		while ((line = f.readLine()) != null) {
			st = new StringTokenizer(line);
			while (st.hasMoreTokens()) {
				coins[index] = Integer.parseInt(st.nextToken());
				index++;
			}
		}
		Arrays.sort(coins);
		long[][] dp = new long[N + 1][V];
		for (int j = 0; j < V; j++) {
			dp[0][j] = 1L;
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < V; j++) {
				if (j == 0) {
					if (i >= coins[j] && i % coins[j] == 0) {
						dp[i][j] = 1L;
					}
					continue;
				}
				dp[i][j] = dp[i][j - 1];
				if (coins[j] > i)
					continue;
				dp[i][j] += dp[i - coins[j]][j];
			}
		}
		System.err.println(dp[N][V - 1]);
		out.println(dp[N][V - 1]);
		out.close();
	}

	public static void printArray(int[] array) {
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

}
