import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class island {
	private static int R, C, N;
	private static int[][] map, matrix, components, dp;
	private static boolean[] found;
	private static final int LAND = 0;
	private static final int SAFE = 1;
	private static final int DEEP = 2;
	private static int[] dr = new int[] { -1, 1, 0, 0 };
	private static int[] dc = new int[] { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("island.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("island.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		components = new int[R][C];
		for (int i = 0; i < R; i++) {
			String str = f.readLine();
			for (int k = 0; k < C; k++) {
				String s = str.substring(k, k + 1);
				if (s.equals(".")) {
					map[i][k] = DEEP;
				}
				if (s.equals("X")) {
					map[i][k] = LAND;
				}
				if (s.equals("S")) {
					map[i][k] = SAFE;
				}
			}
		}
		findComponents();
		matrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(matrix[i], Integer.MAX_VALUE);
		}
		found = new boolean[N];
		findDistances();
		dp = new int[N][(int) (Math.pow(2, N))];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
		}
		int S = 0;
		for (int i = 0; i < N; i++) {
			S = S | (1 << i);
		}
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			result = Math.min(result, dfs(i, S));
		}
		out.println(result);
		out.close();
	}

	public static int dfs(int i, int S) {
		if (S == 0) {
			return 0;
		}
		if (dp[i][S] != -1)
			return dp[i][S];
		int result = Integer.MAX_VALUE;
		for (int j = 0; j < N; j++) {
			if (((S >> j) & 1) == 1) {
				result = Math.min(result, dfs(j, S & (~(1 << j))) + matrix[i][j]);
			}
		}
		dp[i][S] = result;
		return result;
	}

	public static void findComponents() {
		for (int i = 0; i < R; i++) {
			Arrays.fill(components[i], -1);
		}
		for (int i = 0; i < R; i++) {
			for (int k = 0; k < C; k++) {
				if (components[i][k] == -1 && map[i][k] == LAND) {
					floodFill(i, k);
					N++;
				}
			}
		}
	}

	public static void floodFill(int row, int col) {
		components[row][col] = N;
		for (int d = 0; d < 4; d++) {
			int nextR = row + dr[d];
			int nextC = col + dc[d];
			if (!valid(nextR, nextC))
				continue;
			if (components[nextR][nextC] == -1 && map[nextR][nextC] == LAND) {
				floodFill(nextR, nextC);
			}
		}
	}

	public static void findDistances() {
		for (int i = 0; i < R; i++) {
			for (int k = 0; k < C; k++) {
				if (map[i][k] != LAND || found[components[i][k]]) {
					continue;
				}
				find(i, k, components[i][k]);
				found[components[i][k]] = true;
			}
		}
	}

	public static void find(int startR, int startC, int component) {
		ArrayDeque<node> queue = new ArrayDeque<node>();
		queue.offer(new node(startR, startC, 0));
		boolean[][] visited = new boolean[R][C];
		while (!queue.isEmpty()) {
			node curr = queue.poll();
			int row = curr.row;
			int col = curr.col;
			int distance = curr.distance;
			if (visited[row][col])
				continue;
			visited[row][col] = true;
			if (components[row][col] != -1) {
				matrix[component][components[row][col]] = Math.min(matrix[component][components[row][col]], distance);
				matrix[components[row][col]][component] = Math.min(matrix[components[row][col]][component], distance);
			}
			for (int d = 0; d < 4; d++) {
				int nextR = row + dr[d];
				int nextC = col + dc[d];
				if (!valid(nextR, nextC))
					continue;
				if (map[nextR][nextC] == LAND) {
					queue.addFirst(new node(nextR, nextC, distance));
				}
				if (map[nextR][nextC] == SAFE) {
					queue.offer(new node(nextR, nextC, distance + 1));
				}
			}
		}
	}

	public static boolean valid(int row, int col) {
		return row >= 0 && row < R && col >= 0 && col < C;
	}
}

class node {
	public int row, col, distance;

	public node(int a, int b, int c) {
		row = a;
		col = b;
		distance = c;
	}
}