import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class moocast {
	private static int N;
	private static int[][] cows;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("moocast.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
		N = Integer.parseInt(f.readLine());
		cows = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			cows[i][0] = x;
			cows[i][1] = y;
		}
		int result = binarySearch();
		out.println(result);
		out.close();
	}

	public static int binarySearch() {
		int low = 0;
		int high = 625000001;
		while (low != high) {
			int mid = (low + high) / 2;
			if (valid(mid)) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return (high + low) / 2;
	}

	public static boolean valid(int K) {
		visited = new boolean[N];
		visit(0, K);
		for (boolean b : visited) {
			if (!b)
				return false;
		}
		return true;
	}

	public static void visit(int index, int K) {
		if (visited[index]) {
			return;
		}
		visited[index] = true;
		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			if (distance(i, index) <= K) {
				visit(i, K);
			}
		}
	}

	public static int distance(int i, int j) {
		return (cows[i][0] - cows[j][0]) * (cows[i][0] - cows[j][0])
				+ (cows[i][1] - cows[j][1]) * (cows[i][1] - cows[j][1]);
	}
}
