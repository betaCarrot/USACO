
/*
ID: majesti2
LANG: JAVA
TASK: buylow
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class buylow {
	private static int N, res1;
	private static int[] array, dp;
	private static BigInteger[] counts;
	private static BigInteger res2;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("buylow.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("buylow.out")));
		N = Integer.parseInt(f.readLine());
		array = new int[N + 1];
		dp = new int[N + 1];
		counts = new BigInteger[N + 1];
		for (int i = 0; i <= N; i++) {
			counts[i] = BigInteger.ZERO;
		}
		String line;
		int curr = 0;
		while ((line = f.readLine()) != null) {
			StringTokenizer st = new StringTokenizer(line);
			while (st.hasMoreTokens()) {
				curr++;
				array[curr] = Integer.parseInt(st.nextToken());
			}
		}
		array[0] = 1 << 29;
		solve();
		out.println(res1 + " " + res2);
		out.close();
	}

	public static void solve() {
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < i; j++) {
				if (array[i] >= array[j])
					continue;
				dp[i] = Math.max(dp[i], dp[j] + 1);
			}
		}
		for (int i = 1; i <= N; i++) {
			int length = dp[i] - 1;
			if (length == 0) {
				counts[i] = BigInteger.ONE;
				continue;
			}
			int last = -1;
			for (int j = i - 1; j >= 1; j--) {
				if (array[i] < array[j] && dp[j] == length && array[j] != last) {
					counts[i] = counts[i].add(counts[j]);
					last = array[j];
				}
			}
		}
		int max = 0;
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, dp[i]);
		}
		BigInteger res = BigInteger.ZERO;
		int last = -1;
		for (int i = N; i >= 1; i--) {
			if (dp[i] == max && array[i] != last) {
				res = res.add(counts[i]);
				last = array[i];
			}
		}
		res1 = max;
		res2 = res;
	}
}