
/*
 ID: majesti2
 LANG: JAVA
 TASK: subset
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class subset {
	private static int N;
	private static final int MAX = 781;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("subset.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
		N = Integer.parseInt(f.readLine());
		long[][] dp = new long[N + 1][MAX];
		dp[1][0] = dp[1][1] = 1L;
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < MAX; j++) {
				dp[i][j] = dp[i - 1][j];
				if (j >= i) {
					dp[i][j] += dp[i - 1][j - i];
				}
			}
		}
		if (((N + 1) * N / 2) % 2 == 1)
			out.println(0);
		else
			out.println(dp[N][(N + 1) * N / 4] / 2);
		System.err.println(dp[N][(N + 1) * N / 4] / 2);
		out.close();
	}
}
