import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class cowids {
	private static int N, K;
	private static int[][] dp = new int[5001][11];
	private static final int MAX = 10000001;
	private static int[] result = new int[5001];

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("cowids.in"));
		PrintWriter out = new PrintWriter(
				new BufferedWriter(new FileWriter("C:\\Users\\majesticdennis\\Desktop\\judge\\cowids.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		if (K == 1) {
			StringBuilder sb = new StringBuilder();
			sb.append(1);
			for (int i = 0; i < N - 1; i++) {
				sb.append(0);
			}
			out.println(sb.toString());
		} else {
			for (int i = 0; i < 5001; i++) {
				for (int j = 0; j < 11; j++) {
					dp[i][j] = -1;
				}
			}
			solve(5000, N, K);
			out.print(output());
		}
		out.close();
		System.err.println(System.currentTimeMillis() - startTime);
	}

	public static String output() {
		int index = 5000;
		while (result[index] == 0) {
			index--;
		}
		StringBuilder sb = new StringBuilder();
		while (index > 0) {
			sb.append(result[index]);
			index--;
		}
		return sb.toString();
	}

	public static void solve(int length, int number, int ones) {
		if (length == 0)
			return;
		if (dfs(length - 1, ones) >= number) {
			result[length] = 0;
			solve(length - 1, number, ones);
		} else {
			result[length] = 1;
			solve(length - 1, number - dfs(length - 1, ones), ones - 1);
		}
	}

	public static int dfs(int length, int ones) {
		if (length == 0 && ones == 0) {
			return 1;
		}
		if (length == 0) {
			return 0;
		}
		if (ones == 0) {
			return 1;
		}
		if (dp[length][ones] != -1) {
			return dp[length][ones];
		}
		int result = Math.min(MAX, dfs(length - 1, ones) + dfs(length - 1, ones - 1));
		dp[length][ones] = result;
		return result;
	}
}
