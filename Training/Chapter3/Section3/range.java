
/*
 ID: majesti2
 LANG: JAVA
 TASK: range
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class range {
	private static int N;
	private static int[][] dp;
	private static int[] results;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("range.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("range.out")));
		N = Integer.parseInt(f.readLine());
		dp = new int[N][N];
		results = new int[N + 1];
		for (int i = 0; i < N; i++) {
			String line = f.readLine();
			for (int k = 0; k < N; k++) {
				dp[i][k] = Integer.parseInt(line.substring(k, k + 1));
			}
		}
		for (int i = N - 2; i >= 0; i--) {
			for (int k = N - 2; k >= 0; k--) {
				if (dp[i][k] == 0)
					continue;
				dp[i][k] = Math.min(dp[i + 1][k + 1], Math.min(dp[i][k + 1], dp[i + 1][k])) + 1;
				for (int m = dp[i][k]; m >= 2; m--) {
					results[m]++;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i <= N; i++) {
			if (results[i] != 0) {
				sb.append(i + " " + results[i]).append("\n");
			}
		}
		out.print(sb.toString());
		out.close();
	}

	public static void printMatrix(int[][] matrix) {
		for (int[] is : matrix) {
			for (int i : is)
				System.out.print(i + " ");
			System.out.println();
		}
	}

	public static void printArray(int[] array) {
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
