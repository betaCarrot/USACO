import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class landscaping {
	private static int N, X, Y, Z;
	private static final int INF = 100000000;
	private static int[] array;
	private static int[][] dp = new int[101][2001];

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("landscaping.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("landscaping.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		Z = Integer.parseInt(st.nextToken());
		array = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			array[i] = b - a;
		}
		for (int i = 0; i < 101; i++) {
			for (int k = 0; k < 2001; k++) {
				dp[i][k] = -1;
			}
		}
		out.println(dfs(0, 0));
		out.close();
	}

	public static int dfs(int index, int amount) {
		if (index == N) {
			if (amount == 0) {
				return 0;
			}
			return INF;
		}
		if (dp[index][1000 + amount] != -1) {
			return dp[index][1000 + amount];
		}
		int result = INF;
		if (array[index] >= 0) {
			for (int i = 0; i <= array[index]; i++) {
				result = Math.min(result, X * i + dfs(index + 1, amount + array[index] - i));
			}
		} else {
			for (int i = 0; i >= array[index]; i--) {
				result = Math.min(result, Y * (-i) + dfs(index + 1, amount + array[index] - i));
			}
		}
		result += Z * Math.abs(amount);
		dp[index][1000 + amount] = result;
		return result;
	}
}