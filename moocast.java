import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class moocast {
	private static int[] powers;
	private static int[][] map;
	private static boolean[] visited;
	private static int N;
	private static int count = 0;
	private static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("moocast.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
		N = Integer.parseInt(f.readLine());
		powers = new int[N];
		map = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			map[i][0] = x;
			map[i][1] = y;
			powers[i] = p;
		}
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			dfs(i);
			result = Math.max(result, count);
			count = 0;
			visited = new boolean[N];
		}
		out.println(result);
		out.close();
	}

	public static void dfs(int index) {
		count++;
		visited[index] = true;
		int x1 = map[index][0];
		int y1 = map[index][1];
		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			int x2 = map[i][0];
			int y2 = map[i][1];
			if (distance(y1, x1, y2, x2) < powers[index]) {
				dfs(i);
			}
		}
	}

	public static double distance(int y1, int x1, int y2, int x2) {
		return Math.sqrt((y1 - y2) * (y1 - y2) + (x1 - x2) * (x1 - x2));
	}
}
