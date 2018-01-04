import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class radio {
	private static int N, M;
	private static int fX, fY, bX, bY;
	private static int[][] matrix;
	private static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("radio.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("radio.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		fX = Integer.parseInt(st.nextToken());
		fY = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(f.readLine());
		bX = Integer.parseInt(st.nextToken());
		bY = Integer.parseInt(st.nextToken());
		int originalbX = bX;
		int originalbY = bY;
		String fPath = f.readLine();
		String bPath = f.readLine();
		matrix = new int[N + 1][M + 1];
		for (int i = 0; i <= N; i++) {
			matrix[i][0] = distance(fX, fY, bX, bY);
			for (int j = 1; j <= M; j++) {
				String s = bPath.substring(j - 1, j);
				if (s.equals("N"))
					bY++;
				if (s.equals("S"))
					bY--;
				if (s.equals("E"))
					bX++;
				if (s.equals("W"))
					bX--;
				matrix[i][j] = distance(fX, fY, bX, bY);
			}
			bX = originalbX;
			bY = originalbY;
			if (i == N)
				break;
			String s = fPath.substring(i, i + 1);
			if (s.equals("N"))
				fY++;
			if (s.equals("S"))
				fY--;
			if (s.equals("E"))
				fX++;
			if (s.equals("W"))
				fX--;
		}
		int[][] dp = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			dp[i][0] = dp[i - 1][0] + matrix[i][0];
		}
		for (int j = 1; j <= M; j++) {
			dp[0][j] = dp[0][j - 1] + matrix[0][j];
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				dp[i][j] = matrix[i][j] + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
			}
		}
		System.err.println(dp[N][M]);
		out.println(dp[N][M]);
		out.close();
	}

	public static int distance(int r1, int c1, int r2, int c2) {
		return (r1 - r2) * (r1 - r2) + (c1 - c2) * (c1 - c2);
	}
}
