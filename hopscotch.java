import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class hopscotch {
	private static final int RED = 1;
	private static final int BLUE = -1;
	private static int R, C;
	private static int[][] map;
	private static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("hopscotch.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hopscotch.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			String line = f.readLine();
			for (int k = 0; k < C; k++) {
				if (line.substring(k, k + 1).equals("R"))
					map[i][k] = RED;
				else
					map[i][k] = BLUE;
			}
		}
		dfs(0, 0);
		out.println(result);
		out.close();
	}

	public static void dfs(int row, int col) {
		if (row == R - 1 && col == C - 1) {
			result++;
			return;
		}
		int color = map[row][col];
		for (int i = row + 1; i < R; i++) {
			for (int k = col + 1; k < C; k++) {
				if (map[i][k] == -color) {
					dfs(i, k);
				}
			}
		}
	}
}
