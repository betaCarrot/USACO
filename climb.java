import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class climb {
	private static int N;
	private static cow[] cows;
	private static int[] ups, downs;

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("climb.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("climb.out")));
		N = Integer.parseInt(f.readLine());
		cows = new cow[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(f.readLine());
			int up = Integer.parseInt(st.nextToken());
			int down = Integer.parseInt(st.nextToken());
			cows[i] = new cow(up, down);
		}
		Arrays.sort(cows);
		ups = new int[N + 1];
		downs = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			ups[i] = ups[i - 1] + cows[i - 1].up;
			downs[i] = Math.max(downs[i - 1], ups[i]) + cows[i - 1].down;
		}
		out.print(downs[N]);
		out.close();
		System.err.println(System.currentTimeMillis() - startTime);
	}
}

class cow implements Comparable<cow> {
	public int up, down;

	public cow(int a, int b) {
		up = a;
		down = b;
	}

	public int compareTo(cow next) {
		if (up <= down) {
			if (next.up > next.down) {
				return -1;
			}
			return up - next.up;
		} else {
			if (next.up <= next.down) {
				return 1;
			}
			return next.down - down;
		}
	}
}
