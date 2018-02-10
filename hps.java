import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class hps {
	private static int N, K;
	private static int[] moves;
	private static final int H = 0;
	private static final int P = 1;
	private static final int S = 2;
	private static int[][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("hps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		moves = new int[N];
		for (int i = 0; i < N; i++) {
			moves[i] = tohps(f.readLine());
		}
		dp = new int[N][K + 1][3];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < K + 1; j++) {
				for (int k = 0; k < 3; k++) {
					dp[i][j][k] = -1;
				}
			}
		}
		out.println(Math.max(dfs(0, 0, P), Math.max(dfs(0, 0, S), dfs(0, 0, H))));
		out.close();
	}

	public static int dfs(int index, int count, int curr) {
		if (index == N)
			return 0;
		if (dp[index][count][curr] != -1) {
			return dp[index][count][curr];
		}
		int point = 0;
		if (curr == H && moves[index] == S || curr == S && moves[index] == P || curr == P && moves[index] == H)
			point = 1;
		int result = 0;
		if (count == K)
			result = dfs(index + 1, count, curr);
		else {
			result = Math.max(dfs(index + 1, count, curr),
					Math.max(dfs(index + 1, count + 1, (curr + 1) % 3), dfs(index + 1, count + 1, (curr + 2) % 3)));
		}
		dp[index][count][curr] = point + result;
		return point + result;
	}

	public static int tohps(String s) {
		if (s.charAt(0) == 'H')
			return H;
		if (s.charAt(0) == 'P')
			return P;
		return S;
	}
}
