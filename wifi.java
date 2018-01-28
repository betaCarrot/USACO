import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class wifi {
	private static int N, A, B;
	private static double[] array;
	private static double[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("wifi.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wifi.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		array = new double[N];
		for (int i = 0; i < N; i++) {
			array[i] = Double.parseDouble(f.readLine());
		}
		Arrays.sort(array);
		dp = new double[N][N];
		for (int i = 0; i < N; i++) {
			dp[i][i] = A;
		}
		for (int c = 1; c <= N; c++) {
			for (int i = 0; i + c < N; i++) {
				int j = i + c;
				double min = A + (array[i] + array[j]) * 0.5 * B;
				for (int k = i; k < j; k++) {
					min = Math.min(min, dp[i][k] + dp[k + 1][j]);
				}
				dp[i][j] = min;
			}
		}
		if (Double.toString(dp[0][N - 1]).indexOf(".0") >= 0) {
			out.println((int) (dp[0][N - 1]));
		} else
			out.println(dp[0][N - 1]);
		out.close();
	}

	public static void printMatrix(double[][] matrix) {
		for (double[] is : matrix) {
			for (double i : is)
				System.out.print(i + " ");
			System.out.println();
		}
	}

}
