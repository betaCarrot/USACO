
/*
 ID: majesti2
 LANG: JAVA
 TASK: nocows
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class nocows {
	private static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("nocows.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		long[][] dp = new long[101][202];
		long[][] smalltrees = new long[101][202];
		dp[1][1] = 1;
		for (int i = 2; i <= K; i++) {
			for (int j = 1; j <= N; j += 2) {
				for (int k = 1; k <= j - 1 - k; k += 2) {
					int c = 1;
					if (k != j - k - 1)
						c = 2;
					dp[i][j] += (dp[i - 1][k] * smalltrees[i - 2][j - k - 1]
							+ smalltrees[i - 2][k] * dp[i - 1][j - k - 1] + dp[i - 1][k] * dp[i - 1][j - k - 1]) * c;
					dp[i][j] %= 9901;
				}
			}
			for (int k = 0; k <= N; k++) {
				smalltrees[i - 1][k] = dp[i - 1][k] + smalltrees[i - 2][k];
				smalltrees[i - 1][k] %= 9901;
			}
		}
		System.err.println(dp[K][N]);
		out.println(dp[K][N] % 9901);
		out.close();
	}
}
