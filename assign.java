import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class assign {
	private static int N, K, result;
	private static boolean[] status;
	private static int[] as, bs;

	public static void main(String[] args) throws IOException {
		BufferedReader f = new BufferedReader(new FileReader("assign.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("assign.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		status = new boolean[K];
		as = new int[K];
		bs = new int[K];
		for (int i = 0; i < K; i++) {
			StringTokenizer st1 = new StringTokenizer(f.readLine());
			String s = st1.nextToken();
			if (s.equals("S"))
				status[i] = true;
			else
				status[i] = false;
			as[i] = Integer.parseInt(st1.nextToken()) - 1;
			bs[i] = Integer.parseInt(st1.nextToken()) - 1;
		}
		fillArray(0, new int[N]);
		out.println(result);
		out.close();
	}

	public static boolean check(int[] is, int higher) {
		for (int i = 0; i < K; i++) {
			if (as[i] > higher || bs[i] > higher)
				continue;
			if (!check(is, status[i], as[i], bs[i])) {
				return false;
			}
		}
		return true;
	}

	public static boolean check(int[] is, boolean same, int a, int b) {
		return (same && is[a] == is[b]) || (!same && is[a] != is[b]);
	}

	public static void fillArray(int count, int[] temp) {
		if (count == N) {
			result++;
			return;
		}
		for (int i = 0; i <= 2; i++) {
			temp[count] = i;
			if (check(temp, count))
				fillArray(count + 1, temp);
		}
	}
}
