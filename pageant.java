import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class pageant {
	private static int N, M;
	private static int result = Integer.MAX_VALUE;
	private static boolean[][] map;
	private static int[][] components;
	private static int[] dr = new int[] { -1, 1, 0, 0 };
	private static int[] dc = new int[] { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("pageant.in"));
		PrintWriter out = new PrintWriter(
				new BufferedWriter(new FileWriter("C:\\Users\\majesticdennis\\Desktop\\judge\\pageant.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		components = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < M; k++) {
				components[i][k] = -1;
			}
		}
		for (int i = 0; i < N; i++) {
			String str = f.readLine();
			for (int k = 0; k < M; k++) {
				if (str.substring(k, k + 1).equals("X")) {
					map[i][k] = true;
				}
			}
		}
		findComponents();
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < M; k++) {
				result = Math.min(result, solve(i, k));
			}
		}
		out.println(result);
		System.err.println(System.currentTimeMillis() - startTime);
		out.close();
	}

	public static int solve(int startR, int startC) {
		ArrayDeque<node> queue = new ArrayDeque<node>();
		queue.offer(new node(startR, startC, 0));
		boolean[][] visited = new boolean[N][M];
		int[] array = new int[3];
		for (int i = 0; i < 3; i++) {
			array[i] = Integer.MAX_VALUE;
		}
		while (!queue.isEmpty()) {
			node curr = queue.poll();
			int row = curr.row;
			int col = curr.col;
			int distance = curr.distance;
			if (visited[row][col]) {
				continue;
			}
			if (distance > result) {
				break;
			}
			visited[row][col] = true;
			if (components[row][col] != -1) {
				array[components[row][col]] = Math.min(array[components[row][col]], distance);
			}
			if (array[0] != Integer.MAX_VALUE && array[1] != Integer.MAX_VALUE && array[2] != Integer.MAX_VALUE) {
				break;
			}
			for (int d = 0; d < 4; d++) {
				int nextR = row + dr[d];
				int nextC = col + dc[d];
				if (!valid(nextR, nextC))
					continue;
				if (map[nextR][nextC]) {
					queue.addFirst(new node(nextR, nextC, distance));
				} else {
					queue.offer(new node(nextR, nextC, distance + 1));
				}
			}
		}
		int result = 0;
		for (int i : array) {
			if (i == Integer.MAX_VALUE) {
				return i;
			}
			result += i;
		}
		if (!map[startR][startC]) {
			result++;
		}
		return result;
	}

	public static void floodFill(int numComponent) {
		while (true) {
			int numVisited = 0;
			for (int i = 0; i < N; i++) {
				for (int k = 0; k < M; k++) {
					if (components[i][k] == -2) {
						numVisited++;
						components[i][k] = numComponent;
						for (int d = 0; d < 4; d++) {
							int nextR = i + dr[d];
							int nextC = k + dc[d];
							if (!valid(nextR, nextC) || !map[nextR][nextC]) {
								continue;
							}
							if (components[nextR][nextC] == -1)
								components[nextR][nextC] = -2;
						}
					}
				}
			}
			if (numVisited == 0) {
				break;
			}
		}
	}

	public static void findComponents() {
		int numComponents = -1;
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < M; k++) {
				if (components[i][k] == -1 && map[i][k]) {
					numComponents++;
					components[i][k] = -2;
					floodFill(numComponents);
				}
			}
		}
	}

	public static boolean valid(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}

class node implements Comparable<node> {
	public int row, col, distance;

	public node(int a, int b, int c) {
		row = a;
		col = b;
		distance = c;
	}

	public int compareTo(node next) {
		return distance - next.distance;
	}
}
