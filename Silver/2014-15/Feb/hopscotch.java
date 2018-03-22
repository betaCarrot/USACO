import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class hopscotch {
	private static int R, C, K;
	private static int[][] map;
	private static long[][] values;
	private static long MOD = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("hopscotch.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hopscotch.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		values = new long[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(f.readLine());
			for (int k = 0; k < C; k++) {
				map[i][k] = Integer.parseInt(st.nextToken());
				values[i][k] = -1;
			}
		}
		long result = dfs(0, 0);
		out.println(result);
		out.close();
	}

	public static long dfs(int row, int col) {
		if (row == R - 1 && col == C - 1) {
			return 1;
		}
		if (values[row][col] != -1) {
			return values[row][col];
		}
		long result = 0;
		for (int nextR = row + 1; nextR < R; nextR++) {
			for (int nextC = col + 1; nextC < C; nextC++) {
				if (map[row][col] != map[nextR][nextC]) {
					result += dfs(nextR, nextC);
					result %= MOD;
				}
			}
		}
		values[row][col] = result;
		return result;
	}

	public static boolean valid(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
}
