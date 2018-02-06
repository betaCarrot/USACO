import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class spainting {
	private static int N, M, K;
	private static long[] dp;
	private static final long MOD = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("spainting.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spainting.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dp = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			if (i < K) {
				dp[i] = (dp[i - 1] * M + M) % MOD;
			} else {
				dp[i] = (M * dp[i - 1] + MOD - ((M - 1) * (dp[i - K])) % MOD) % MOD;
			}
		}
		long total = 1;
		for (int i = 0; i < N; i++) {
			total *= M;
			total %= MOD;
		}
		out.println((MOD + total - (dp[N] - dp[N - 1])) % MOD);
		out.close();
	}
}
