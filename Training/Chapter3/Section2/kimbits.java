
/*
 ID: majesti2
 LANG: JAVA
 TASK: kimbits
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class kimbits {
	private static int N, L;
	private static long I;
	private static long[][] dp;
	private static String result;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("kimbits.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		I = Long.parseLong(st.nextToken());
		dp = new long[32][32];
		init();
		solve();
		out.println(result);
		out.close();
	}

	public static void solve() {
		StringBuilder sb = new StringBuilder();
		while (true) {
			long next = dp[N - 1][L];
			if (next < I) {
				sb.append(1);
				I -= dp[N - 1][L];
				N--;
				L--;
			} else {
				sb.append(0);
				N--;
			}
			if (N == 0) {
				result = sb.toString();
				return;
			}
		}
	}

	public static void init() {
		for (int i = 0; i < 32; i++) {
			dp[i][0] = 1L;
		}
		for (int k = 0; k < 32; k++) {
			dp[0][k] = 1L;
		}
		for (int i = 1; i < 32; i++) {
			for (int k = 1; k < 32; k++) {
				dp[i][k] = dp[i - 1][k - 1] + dp[i - 1][k];
			}
		}
	}
}
