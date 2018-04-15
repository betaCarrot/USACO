
/*
ID: majesti2
LANG: JAVA
TASK: snail
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class snail {
	public static int N, B;
	public static boolean[][] barriers, visited;
	public static boolean[][][] seen;
	public static int[] dr = new int[] { 0, 1, 0, -1 };
	public static int[] dc = new int[] { 1, 0, -1, 0 };
	public static final int EAST = 0;
	public static final int SOUTH = 1;
	public static final int WEST = 2;
	public static final int NORTH = 3;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("snail.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snail.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		barriers = new boolean[N][N];
		visited = new boolean[N][N];
		seen = new boolean[N][N][4];
		for (int i = 0; i < B; i++) {
			String str = f.readLine();
			int col = str.charAt(0) - 'A';
			int row = Integer.parseInt(str.substring(1)) - 1;
			barriers[row][col] = true;
		}
		int res1 = dfs(0, 0, SOUTH);
		visited = new boolean[N][N];
		seen = new boolean[N][N][4];
		int res2 = dfs(0, 0, EAST);
		out.println(Math.max(res1, res2));
		out.close();
	}

	public static int dfs(int row, int col, int direction) {
		if (seen[row][col][direction]) {
			return 0;
		}
		if (visited[row][col])
			return 0;
		seen[row][col][direction] = true;
		int res = 0;
		visited[row][col] = true;
		int nextR = row + dr[direction];
		int nextC = col + dc[direction];
		if (!valid(nextR, nextC) || barriers[nextR][nextC]) {
			visited[row][col] = false;
			int[] temp = new int[2];
			if (direction == 0 || direction == 2) {
				temp[0] = 1;
				temp[1] = 3;
			} else {
				temp[0] = 0;
				temp[1] = 2;
			}
			for (int i = 0; i < 2; i++) {
				res = Math.max(res, dfs(row, col, temp[i]));
			}
		} else {
			res = 1 + dfs(nextR, nextC, direction);
		}
		visited[row][col] = false;
		seen[row][col][direction] = false;
		return res;
	}

	public static boolean valid(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}
}
