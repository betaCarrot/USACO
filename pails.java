import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class pails {
	private static boolean[][][] map;
	private static int X, Y, K, M;
	private static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("pails.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[X + 1][Y + 1][K + 1];
		dfs(0, 0, 0);
		out.println(result);
		out.close();
	}

	public static void dfs(int x, int y, int count) {
		result = Math.min(result, Math.abs(M - (x + y)));
		if (count == K)
			return;
		if (map[x][y][count])
			return;
		map[x][y][count] = true;
		dfs(0, y, count + 1);
		dfs(x, 0, count + 1);
		dfs(X, y, count + 1);
		dfs(x, Y, count + 1);
		if (y < X - x)
			dfs(x + y, 0, count + 1);
		else
			dfs(X, y + x - X, count + 1);
		if (x < Y - y)
			dfs(0, x + y, count + 1);
		else
			dfs(x + y - Y, Y, count + 1);
	}
}
