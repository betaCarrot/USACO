import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class bookshelf {
	private static int N, L;
	private static book[] books;
	private static long[] prefixes;
	private static long[][] maxs;
	private static long[][] dp = new long[2001][2001];
	private static final int INF = 1000000000;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("bookshelf.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bookshelf.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		books = new book[N];
		prefixes = new long[N + 1];
		maxs = new long[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			long H = Long.parseLong(st.nextToken());
			long W = Long.parseLong(st.nextToken());
			books[i] = new book(H, W);
		}
		for (int i = 1; i <= N; i++) {
			prefixes[i] = prefixes[i - 1] + books[i - 1].width;
		}
		for (int i = 0; i < N; i++) {
			long max = books[i].height;
			for (int j = i; j < N; j++) {
				max = Math.max(max, books[j].height);
				maxs[i][j] = max;
			}
		}
		for (int i = 0; i <= 2000; i++) {
			for (int k = 0; k <= 2000; k++) {
				dp[i][k] = -1;
			}
		}
		out.println(dfs(0, 0));
		out.close();
	}

	public static long dfs(int index, int last) {
		if (index == N) {
			if (last == index) {
				return 0;
			}
			return INF;
		}
		if (dp[index][last] != -1) {
			return dp[index][last];
		}
		long width = prefixes[index + 1] - prefixes[last];
		if (width > L) {
			return INF;
		}
		long result = Math.min(dfs(index + 1, index + 1) + maxs[last][index], dfs(index + 1, last));
		dp[index][last] = result;
		return result;
	}
}

class book {
	public long height, width;

	public book(long a, long b) {
		height = a;
		width = b;
	}
}