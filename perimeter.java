import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class perimeter {
	private static boolean[][] bales = new boolean[100 + 2][100 + 2];
	private static boolean[][] visited = new boolean[100 + 2][100 + 2];
	private static int[][] number = new int[102][102];
	private static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("perimeter.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));
		int n = Integer.parseInt(f.readLine());
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			bales[y][x] = true;
		}
		dfs(0, 0);
		out.println(result);
		out.close();
	}

	public static void bfs(int a, int b) {
		number[a][b] = -2;
		while (true) {
			int numVisited = 0;
			for (int y = 0; y <= 101; y++) {
				for (int x = 0; x <= 101; x++) {
					if (bales[y][x])
						continue;
					if (number[y][x] == -2) {
						numVisited++;
						number[y][x] = 1;
						if (valid(y - 1, x)) {
							if (bales[y - 1][x])
								result++;
							else if (number[y - 1][x] == 0)
								number[y - 1][x] = -2;
						}
						if (valid(y + 1, x)) {
							if (bales[y + 1][x])
								result++;
							else if (number[y + 1][x] == 0)
								number[y + 1][x] = -2;
						}
						if (valid(y, x - 1)) {
							if (bales[y][x - 1])
								result++;
							else if (number[y][x - 1] == 0)
								number[y][x - 1] = -2;
						}
						if (valid(y, x + 1)) {
							if (bales[y][x + 1])
								result++;
							else if (number[y][x + 1] == 0)
								number[y][x + 1] = -2;
						}
					}
				}
			}
			if (numVisited == 0)
				break;
		}
	}

	public static void dfs(int y, int x) {
		if (bales[y][x]) {
			result++;
			return;
		}
		if (visited[y][x])
			return;
		visited[y][x] = true;
		if (valid(y - 1, x))
			dfs(y - 1, x);
		if (valid(y + 1, x))
			dfs(y + 1, x);
		if (valid(y, x - 1))
			dfs(y, x - 1);
		if (valid(y, x + 1))
			dfs(y, x + 1);
	}

	public static boolean valid(int y, int x) {
		return y >= 0 && y <= 101 && x >= 0 && x <= 101;
	}
}
