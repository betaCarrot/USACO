
/*
 ID: majesti2
 LANG: JAVA
 TASK: stamps
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class stamps {
	private static int K, N;
	private static int[] stamps;
	private static int[] dp;
	private static final int MAX = 200 * 10000;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("stamps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		stamps = new int[N];
		dp = new int[MAX + 1];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = -1;
		}
		String line;
		int index = 0;
		while ((line = f.readLine()) != null) {
			st = new StringTokenizer(line);
			while (st.hasMoreElements()) {
				stamps[index] = Integer.parseInt(st.nextToken());
				index++;
			}
		}
		Arrays.sort(stamps);
		int curr = 0;
		int result = -1;
		dp[0] = 0;
		while (true) {
			if (dp[curr] == -1) {
				result = curr;
				break;
			}
			if (dp[curr] == K) {
				curr++;
				continue;
			}
			for (int i = 0; i < N; i++) {
				int value = stamps[i];
				if (curr + value > MAX)
					continue;
				if (dp[curr + value] == -1)
					dp[curr + value] = dp[curr] + 1;
				else
					dp[curr + value] = Math.min(dp[curr + value], dp[curr] + 1);
			}
			curr++;
		}
		out.println(result - 1);
		out.close();
	}
}
