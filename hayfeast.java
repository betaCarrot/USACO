import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class hayfeast {
	private static int N;
	private static long M;
	private static bale[] bales;
	private static long[] prefixes;
	private static Comparator<bale> comp = new Comparator<bale>() {
		public int compare(bale b1, bale b2) {
			if (b1.S == b2.S) {
				return Long.compare(b1.F, b2.F);
			}
			return b1.S - b2.S;
		}
	};

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("hayfeast.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hayfeast.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Long.parseLong(st.nextToken());
		bales = new bale[N];
		prefixes = new long[N + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			long F = Long.parseLong(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			bales[i] = new bale(F, S);
			prefixes[i + 1] = prefixes[i] + F;
		}
		out.println(solve());
		out.close();
	}

	public static int solve() {
		int result = Integer.MAX_VALUE;
		TreeSet<bale> set = new TreeSet<bale>(comp);
		int left = 0;
		int right = 0;
		while (right <= N) {
			while (right <= N) {
				long flavor = prefixes[right] - prefixes[left];
				if (flavor >= M) {
					break;
				}
				if (right == N && flavor < M) {
					return result;
				}
				set.add(bales[right]);
				right++;
			}
			result = Math.min(result, set.last().S);
			set.remove(bales[left]);
			left++;
		}
		return result;
	}
}

class bale {
	public long F;
	public int S;

	public bale(long a, int b) {
		F = a;
		S = b;
	}
}