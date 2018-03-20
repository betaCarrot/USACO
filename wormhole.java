
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class wormhole {
	private static int N, result;
	private static ArrayList<int[]> list = new ArrayList<int[]>();
	private static int[] next, pair;
	private static hole[] holes;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("wormhole.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
		N = Integer.parseInt(f.readLine());
		holes = new hole[N];
		next = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			holes[i] = new hole(x, y);
		}
		init();
		solve();
		out.println(result);
		out.close();
	}

	public static void solve() {
		for (int[] pairing : list) {
			pair = new int[N];
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					if (pairing[i] == pairing[j]) {
						pair[i] = j;
						pair[j] = i;
					}
				}
			}
			for (int i = 0; i < N; i++) {
				if (dfs(i, 0)) {
					result++;
					break;
				}
			}
		}
	}

	public static boolean dfs(int index, int depth) {
		if (depth > N) {
			return true;
		}
		index = next[pair[index]];
		if (index == -1)
			return false;
		return dfs(index, depth + 1);
	}

	public static void init() {
		makePair(new int[N], 0, 1);
		Arrays.fill(next, -1);
		for (int i = 0; i < N; i++) {
			hole curr = holes[i];
			int minIndex = -1;
			int minX = Integer.MAX_VALUE;
			for (int j = 0; j < N; j++) {
				if (holes[j].y != curr.y)
					continue;
				if (holes[j].x < minX && holes[j].x > curr.x) {
					minX = holes[j].x;
					minIndex = j;
				}
			}
			next[i] = minIndex;
		}
	}

	public static void makePair(int[] curr, int index, int count) {
		if (count == N / 2 + 1) {
			int[] copy = new int[N];
			for (int j = 0; j < N; j++) {
				copy[j] = curr[j];
			}
			list.add(copy);
			return;
		}
		while (curr[index] != 0) {
			index++;
		}
		curr[index] = count;
		for (int i = index + 1; i < N; i++) {
			if (curr[i] == 0) {
				int[] copy = new int[N];
				for (int j = 0; j < N; j++) {
					copy[j] = curr[j];
				}
				copy[i] = count;
				makePair(copy, index + 1, count + 1);
			}
		}
	}
}

class hole {
	public int x, y;

	public hole(int a, int b) {
		x = a;
		y = b;
	}
}