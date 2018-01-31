import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class scode {
	private static int N;
	private static String s;
	private static final int MOD = 2014;
	private static int[][] values;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("scode.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("scode.out")));
		s = f.readLine();
		N = s.length();
		values = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < N; k++) {
				values[i][k] = -1;
			}
		}
		out.println(dfs(0, N - 1));
		out.close();
	}

	public static int dfs(int li, int ri) {
		if ((ri - li) <= 1) {
			return 0;
		}
		if (values[li][ri] != -1) {
			return values[li][ri];
		}
		int result = 0;
		for (int i = 0; i < (ri - li) / 2; i++) {
			String first = s.substring(li, li + i + 1);
			String second = s.substring(li + i + 1, ri + 1);
			String third = s.substring(ri - i, ri + 1);
			String fourth = s.substring(li, ri - i);
			if (second.startsWith(first)) {
				result += 1 + dfs(li + i + 1, ri);
			}
			if (second.endsWith(first)) {
				result += 1 + dfs(li + i + 1, ri);
			}
			if (fourth.startsWith(third)) {
				result += 1 + dfs(li, ri - i - 1);
			}
			if (fourth.endsWith(third)) {
				result += 1 + dfs(li, ri - i - 1);
			}
		}
		result %= MOD;
		values[li][ri] = result;
		return result;
	}
}
