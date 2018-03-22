
/*
 ID: majesti2
 LANG: JAVA
 TASK: rockers
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class rockers {
	private static int N, T, M;
	private static int[] array;
	private static int[][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("rockers.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rockers.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		array = new int[N + 1];
		st = new StringTokenizer(f.readLine());
		for (int i = 1; i <= N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		dp = new int[21][21][21];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				for (int k = 1; k <= T; k++) {
					if (array[i] > k) {
						dp[i][j][k] = dp[i - 1][j][k];
					} else {
						dp[i][j][k] = Math.max(dp[i - 1][j][k - array[i]], dp[i - 1][j - 1][T]) + 1;
					}
				}
			}
		}
		System.out.println(dp[N][M][T]);
		out.close();
	}
}
