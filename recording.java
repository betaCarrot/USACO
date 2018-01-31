import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class recording {
	private static int N;
	private static program[] programs;
	private static int[][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("recording.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("recording.out")));
		N = Integer.parseInt(f.readLine());
		programs = new program[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			programs[i] = new program(start, end);
		}
		Arrays.sort(programs);
		dp = new int[N + 1][N + 1][N + 1];
		for (int i = N - 1; i >= -1; i--) {
			for (int j = N - 1; j >= -1; j--) {
				for (int k = N - 1; k >= 0; k--) {
					int start = programs[k].start;
					int result = 0;
					if (i == -1 || start >= programs[i].end) {
						result = Math.max(result, 1 + dp[k + 1][j + 1][k + 1]);
					}
					if (j == -1 || start >= programs[j].end) {
						result = Math.max(result, 1 + dp[i + 1][k + 1][k + 1]);
					}
					result = Math.max(result, dp[i + 1][j + 1][k + 1]);
					dp[i + 1][j + 1][k] = result;
				}
			}
		}
		out.println(dp[0][0][0]);
		out.close();
	}
}

class program implements Comparable<program> {
	public int start, end;

	public program(int a, int b) {
		start = a;
		end = b;
	}

	public int compareTo(program next) {
		return start - next.start;
	}
}
