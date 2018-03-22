import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class marathon {
	private static int N, K;
	private static int[][] points;
	private static int[][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("marathon.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("marathon.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		points = new int[N][2];
		dp = new int[N][K + 1][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			points[i][0] = x;
			points[i][1] = y;
		}
		for (int i = N - 1; i >= 1; i--) {
			for (int j = K; j >= 0; j--) {
				for (int k = N - 1; k >= 0; k--) {
					if (i == N - 1) {
						dp[i][j][k] = distance(i, k);
					} else {
						if (j == K) {
							dp[i][j][k] = distance(i, k) + dp[i + 1][j][i];
						} else {
							dp[i][j][k] = Math.min(distance(i, k) + dp[i + 1][j][i], dp[i + 1][j + 1][k]);
						}
					}
				}
			}
		}
		out.println(dp[1][0][0]);
		out.close();
	}

	public static int distance(int index1, int index2) {
		return Math.abs(points[index1][0] - points[index2][0]) + Math.abs(points[index1][1] - points[index2][1]);
	}
}
