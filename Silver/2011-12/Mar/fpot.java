import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class fpot {
	private static int N, D;
	private static drop[] drops;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("fpot.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fpot.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		drops = new drop[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(f.readLine());
			int pos = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			drops[i] = new drop(pos, time);
		}
		Arrays.sort(drops);
		int result = solve();
		if (result == Integer.MAX_VALUE) {
			out.println(-1);
		} else {
			out.println(result);
		}
		out.close();
	}

	public static int solve() {
		TreeSet<drop> set = new TreeSet<drop>(new Comparator<drop>() {
			public int compare(drop a, drop b) {
				if (a.time == b.time) {
					return a.pos - b.pos;
				}
				return a.time - b.time;
			}
		});
		int left = 0;
		set.add(drops[0]);
		int right = 1;
		int result = Integer.MAX_VALUE;
		while (right <= N) {
			while (right <= N) {
				int check = set.last().time - set.first().time;
				if (check >= D) {
					break;
				}
				if (right == N) {
					return result;
				}
				set.add(drops[right]);
				right++;
			}
			result = Math.min(result, drops[right - 1].pos - drops[left].pos);
			set.remove(drops[left]);
			left++;
		}
		return result;
	}
}

class drop implements Comparable<drop> {
	public int pos, time;

	public drop(int a, int b) {
		pos = a;
		time = b;
	}

	public int compareTo(drop next) {
		return pos - next.pos;
	}
}
