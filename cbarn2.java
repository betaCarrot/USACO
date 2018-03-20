import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class cbarn2 {
	private static int N, K;
	private static int[] array;
	private static long[][] matrix;
	private static long[][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("cbarn2.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn2.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		array = new int[N];
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(f.readLine());
		}
		matrix = new long[N][N];
		dp = new long[N][N][K + 1];
		init();
		long result = Long.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			result = Math.min(result, dfs(1, 0, 1));
			rotate();
			init();
		}
		out.println(result);
		out.close();
	}

	public static long dfs(int index, int last, int count) {
		if (index == N) {
			return matrix[last][index - 1];
		}
		if (dp[index][last][count] != -1) {
			return dp[index][last][count];
		}
		long result = 0;
		if (count == K) {
			result = dfs(index + 1, last, count);
		} else {
			result = Math.min(dfs(index + 1, last, count), matrix[last][index - 1] + dfs(index + 1, index, count + 1));
		}
		dp[index][last][count] = result;
		return result;
	}

	public static void init() {
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				matrix[i][j] = matrix[i][j - 1] + (long) array[j] * (j - i);
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < K + 1; k++) {
					dp[i][j][k] = -1;
				}
			}
		}
	}

	public static void rotate() {
		int temp = array[0];
		for (int i = 0; i < N - 1; i++) {
			array[i] = array[i + 1];
		}
		array[N - 1] = temp;
	}
}
