import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class maxcross {
	private static boolean[] brokens;
	private static int[] dp;
	private static int N, K, B;
	private static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("maxcross.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		brokens = new boolean[N + 1];
		dp = new int[N + 1];
		for (int i = 0; i < B; i++) {
			int index = Integer.parseInt(f.readLine());
			brokens[index] = true;
		}
		for (int i = 1; i <= N; i++) {
			dp[i] = dp[i - 1];
			if (brokens[i])
				dp[i]++;
		}
		for (int i = 1; i <= N; i++) {
			if (brokens[i])
				continue;
			int nextIndex = i + K - 1;
			if (nextIndex > N)
				break;
			int count = dp[nextIndex] - dp[i - 1];
			result = Math.min(result, count);
		}
		out.println(result);
		out.close();
	}
}
