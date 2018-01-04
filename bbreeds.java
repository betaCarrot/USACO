import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class bbreeds {
	private static int N;
	private static String str;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("bbreeds.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bbreeds.out")));
		str = f.readLine();
		N = str.length();
		int[][] dp = new int[N + 1][N + 1];
		dp[0][0] = 1;
		int numOpen = 0;
		for (int i = 1; i <= N; i++) {
			if (str.substring(i - 1, i).equals("(")) {
				numOpen++;
			} else {
				numOpen--;
			}
			for (int j = 0; j <= numOpen; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i - 1][numOpen - j];
				dp[i][j] %= 2012;
			}
		}
		out.println(dp[N][0]);
		out.close();
	}
}
