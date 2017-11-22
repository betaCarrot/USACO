import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class diamond {
	private static int[] dp = new int[10001];
	private static int[] counts = new int[10001];
	private static int N, K;
	private static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("diamond.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			counts[Integer.parseInt(f.readLine())]++;
		}
		for (int i = 1; i <= 10000; i++) {
			dp[i] = dp[i - 1] + counts[i];
		}
		for (int i = 1; i <= 10000; i++) {
			if (i + K > 10000)
				break;
			int amount = dp[i + K] - dp[i - 1];
			result = Math.max(result, amount);
		}
		out.println(result);
		out.close();
	}
}