import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class a248 {
	private static int N;
	private static int[] array;
	private static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("248.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("248.out")));
		N = Integer.parseInt(f.readLine());
		array = new int[N];
		dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(f.readLine());
		}
		int result = 0;
		for (int i = 0; i < N; i++) {
			dp[i][i] = array[i];
			result = Math.max(result, array[i]);
		}
		for (int c = 1; c < N; c++) {
			for (int i = 0; i < N; i++) {
				int j = i + c;
				if (j >= N)
					break;
				dp[i][j] = -1;
				for (int k = i; k < j; k++) {
					if (dp[i][k] == dp[k + 1][j] && dp[i][k] > 0) {
						dp[i][j] = Math.max(dp[i][j], dp[i][k] + 1);
					}
				}
				result = Math.max(result, dp[i][j]);
			}
		}
		System.err.println(result);
		out.println(result);
		out.close();
	}

	public static void printMatrix(int[][] matrix) {
		for (int[] is : matrix) {
			for (int i : is)
				System.out.print(i + " ");
			System.out.println();
		}
	}
}
